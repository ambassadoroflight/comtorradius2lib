package net.comtor.radius.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.CampaignXZone;
import web.connection.ApplicationDAO;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Apr 16, 2019
 */
public class CampaignXZoneDAOFacade extends ComtorDaoElementLogicFacade<CampaignXZone, Long> {

    public List<Long> findAllByCampaign(long campaign) throws ComtorDaoException {
        ApplicationDAO dao = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "\n"
                    + " SELECT \n"
                    + "     campaign_x_zone.zone \n"
                    + " FROM    \n"
                    + "     campaign_x_zone \n"
                    + " WHERE   \n"
                    + "     campaign_x_zone.campaign = ? \n";
            dao = new ApplicationDAO();
            conn = dao.getJdbcConnection();
            ps = conn.prepareStatement(query);

            int pos = 1;
            ps.setLong(pos++, campaign);

            rs = ps.executeQuery();

            List<Long> zones = new ArrayList<>();

            while (rs.next()) {
                zones.add(rs.getLong(1));
            }

            return zones;
        } catch (ComtorDaoException | SQLException ex) {
            throw new ComtorDaoException(ex);
        } finally {
            ComtorJDBCDao.safeClose(dao, conn, ps, rs);
        }
    }

    public void deleteAllByCampaign(long campaign) throws ComtorDaoException {
        ApplicationDAO dao = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "\n"
                    + " DELETE FROM \n"
                    + "     campaign_x_zone \n"
                    + " WHERE \n"
                    + "     campaign_x_zone.campaign = ? \n";
            dao = new ApplicationDAO();
            conn = dao.getJdbcConnection();
            ps = conn.prepareStatement(query);

            int pos = 1;
            ps.setLong(pos++, campaign);

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ComtorDaoException(ex);
        } finally {
            ComtorJDBCDao.safeClose(dao, conn, ps, null);
        }
    }
}
