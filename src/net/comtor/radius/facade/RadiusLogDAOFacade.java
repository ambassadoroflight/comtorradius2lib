package net.comtor.radius.facade;

import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.RadiusLog;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2018
 */
public class RadiusLogDAOFacade extends ComtorDaoElementLogicFacade<RadiusLog, Long> {

    @Override
    public void create(RadiusLog log) throws ComtorDaoException {
        long now = System.currentTimeMillis();
        log.setDate(new java.sql.Date(now));
        log.setHour(new java.sql.Time(now));
        log.setDatetime(new java.sql.Timestamp(now));

        super.create(log);
    }

}
