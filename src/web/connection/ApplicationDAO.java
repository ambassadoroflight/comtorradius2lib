package web.connection;

import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.ComtorJDBCDataSourceDao;
import web.global.GlobalLib;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2019
 */
public class ApplicationDAO extends ComtorJDBCDataSourceDao {

    private static String DRIVER = GlobalLib.getInstance().getDatabaseDriver();
    private static String URL = GlobalLib.getInstance().getDatabaseURL();
    private static String USER = GlobalLib.getInstance().getDatabaseUser();
    private static String PASSWORD = GlobalLib.getInstance().getDatabasePassword();

    public ApplicationDAO() throws ComtorDaoException {
        super(DRIVER, URL, USER, PASSWORD);
    }
}
