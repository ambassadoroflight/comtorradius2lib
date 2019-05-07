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
import net.comtor.util.StringUtil;
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

    public List<Hotspot> find(final Long sponsor, final long zone, final String country,
            final java.sql.Date start_date, final java.sql.Date end_date) throws ComtorDaoException {
        String query = "\n"
                + " SELECT \n"
                + "     h.* \n"
                + " FROM \n"
                + "     hotspot h \n";
 
        if ((sponsor > 0) || (start_date != null) || (end_date != null)) {
            query += ""
                    + " JOIN campaign_x_zone cxz    ON cxz.zone = h.zone \n"
                    + " JOIN campaign c             ON c.id = cxz.campaign \n";
        }

        if ((zone > 0) || StringUtil.isValid(country)) {
            query += " JOIN zone z ON z.id = h.zone \n";
        }
        
        query += ""
                + " WHERE \n"
                + "     1 = 1 \n";
        List<Object> params = new ArrayList<>();

        if (sponsor > 0) {
            query += "  AND c.sponsor = ? \n";
            params.add(sponsor);
        }

        if (zone > 0) {
            query += "  AND h.zone = ? \n";
            params.add(zone);
        }

        if (StringUtil.isValid(country)) {
            query += "  AND z.country = ? \n";
            params.add(country);
        }

        if (start_date != null) {
            query += "  AND c.start_date >= ? \n";
            params.add(start_date);
        }

        if (end_date != null) {
            query += "  AND c.end_date <= ? \n";
            params.add(end_date);
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
