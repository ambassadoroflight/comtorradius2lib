package net.comtor.radius.facade;

import java.util.LinkedList;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.Country;

/**
 *
 * @author juandiego@comtor.net
 * @since
 * @version May 03, 2019
 */
public class CountryDAOFacade extends ComtorDaoElementLogicFacade<Country, String> {

    public LinkedList<Country> findAll() throws ComtorDaoException {
        String query = getFindQuery() + " ORDER BY country.name ASC \n";

        return findAll(query);
    }

}
