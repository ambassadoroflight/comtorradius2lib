package net.comtor.radius.facade;

import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.AdvertisingCampaign;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Apr 04, 2019
 */
public class AdvertisingCampaignDAOFacade
        extends ComtorDaoElementLogicFacade<AdvertisingCampaign, Long> {

    @Override
    public void create(AdvertisingCampaign ad) throws ComtorDaoException {
        if (ad.getStart_date() == null) {
            ad.setStart_date(new java.sql.Date(System.currentTimeMillis()));
        }

        super.create(ad);
    }

    @Override
    public void edit(AdvertisingCampaign ad) throws ComtorDaoException {
        if (ad.getStart_date() == null) {
            ad.setStart_date(new java.sql.Date(System.currentTimeMillis()));
        }

        super.edit(ad);
    }

}
