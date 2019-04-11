package net.comtor.radius.facade;

import java.util.LinkedList;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.PrepaidCustomer;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2018
 */
public class PrepaidCustmerDAOFacade
        extends ComtorDaoElementLogicFacade<PrepaidCustomer, Long> {

    @Override
    public void create(PrepaidCustomer prepaidCustomer) throws ComtorDaoException {
        long time = prepaidCustomer.getCreation_date().getTime();
        prepaidCustomer.setCreation_ddate(new java.sql.Date(time));
        
        super.create(prepaidCustomer);
    }

    public LinkedList<PrepaidCustomer> findLatestSold(String seller, int limit)
            throws ComtorDaoException {
        String query = getFindQuery()
                + " WHERE \n"
                + "     prepaid_customer.created_by = ? \n"
                + " ORDER BY \n"
                + "     prepaid_customer.id DESC \n"
                + " LIMIT ? \n";
        Object[] param = {seller, limit};

        return findAll(query, param);
    }

}
