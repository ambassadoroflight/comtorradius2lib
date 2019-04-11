package net.comtor.radius.facade;

import java.util.LinkedList;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.PrepaidRate;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2018
 */
public class PrepaidRateDAOFacade
        extends ComtorDaoElementLogicFacade<PrepaidRate, Long> {

    public LinkedList<PrepaidRate> findAll() throws ComtorDaoException {
        return findAll(getFindQuery());
    }

}
