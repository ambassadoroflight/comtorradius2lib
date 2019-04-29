package net.comtor.radius.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.Hotspot;
import web.connection.ApplicationDAO;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2018
 */
public class HotspotDAOFacade extends ComtorDaoElementLogicFacade<Hotspot, Long> {

    public LinkedList<Hotspot> findAll() throws ComtorDaoException {
        return super.findAll(getFindQuery());
    }

    public List<Hotspot> findBySponsor(final Long sponsor) throws ComtorDaoException {
        String query = "\n"
                + " SELECT \n"
                + "     h.* \n"
                + " FROM \n"
                + "     hotspot h \n";
        ArrayList<Object> params = new ArrayList<>();

        if (sponsor > 0) {
            query += " "
                    + " JOIN campaign_x_zone cxz    ON cxz.zone = h.zone \n"
                    + " JOIN campaign c             ON c.id = cxz.campaign \n"
                    + " WHERE \n"
                    + "     c.sponsor = ? \n";
            params.add(sponsor);
        }
        
        return findAll(query, params.toArray());
    }

    public Hotspot findByCalledStationIdAndIpAddress(final String called_station_id,
            final String ip_address) throws ComtorDaoException {
        String query = getFindQuery()
                + " WHERE \n"
                + "     hotspot.called_station_id = ? \n"
                + " AND hotspot.ip_address = ? \n";
        Object[] params = {called_station_id, ip_address};
        LinkedList<Hotspot> hotspots = findAll(query, params);

        if ((hotspots == null) || hotspots.isEmpty()) {
            return null;
        }

        return hotspots.getFirst();
    }

    public long getCountByZone(long zone) throws ComtorDaoException {
        String query = getFindQuery()
                + " WHERE \n"
                + "     hotspot.zone = ? \n";

        return super.getCountElements(query, zone);
    }

    public void deleteHotspotsFromZone(final long zone) throws ComtorDaoException {
        ApplicationDAO dao = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String update = "\n"
                    + " UPDATE \n"
                    + "     hotspot \n"
                    + " SET \n"
                    + "     zone = 0 \n"
                    + " WHERE \n"
                    + "     hotspot.zone = ? \n";
            dao = new ApplicationDAO();
            conn = dao.getJdbcConnection();
            ps = conn.prepareStatement(update);

            int pos = 1;
            ps.setLong(pos++, zone);

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ComtorDaoException(ex);
        } finally {
            ComtorJDBCDao.safeClose(dao, conn, ps, null);
        }
    }

}
