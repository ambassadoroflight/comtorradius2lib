package net.comtor.radius.facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.SellerAuthToken;
import web.connection.ApplicationDAO;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 31, 2019
 */
public class SellerAuthTokenDAOFacade
        extends ComtorDaoElementLogicFacade<SellerAuthToken, String> {

    public void createOrEdit(SellerAuthToken token) throws ComtorDaoException {
        ApplicationDAO dao = null;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String query = "\n"
                    + " INSERT INTO \n"
                    + "     seller_auth_token (seller, token, expiration_dare) \n"
                    + " VALUES \n"
                    + "     (?, ?, ?) \n"
                    + " ON DUPLICATE KEY UPDATE \n"
                    + "     token = ?, \n"
                    + "     expiration_date = ? \n";
            dao = new ApplicationDAO();
            conn = dao.getJdbcConnection();
            ps = conn.prepareStatement(query);

            String seller = token.getSeller();
            String auth_token = token.getToken();
            Timestamp expiration_date = token.getExpiration_date();

            int pos = 1;
            ps.setString(pos++, seller);
            ps.setString(pos++, auth_token);
            ps.setTimestamp(pos++, expiration_date);
            ps.setString(pos++, auth_token);
            ps.setTimestamp(pos++, expiration_date);

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new ComtorDaoException(ex);
        } finally {
            ComtorJDBCDao.safeClose(dao, conn, ps, null);
        }
    }

}
