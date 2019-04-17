package net.comtor.radius.facade;

import java.util.LinkedList;
import net.comtor.dao.ComtorDaoException;
import net.comtor.dao.generics.ComtorDaoElementLogicFacade;
import net.comtor.radius.element.Campaign;
import net.comtor.radius.element.CampaignXZone;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Apr 16, 2019
 */
public class CampaignDAOFacade
        extends ComtorDaoElementLogicFacade<Campaign, Long> {

    @Override
    public void create(Campaign campaign) throws ComtorDaoException {
        if (campaign.getStart_date() == null) {
            campaign.setStart_date(new java.sql.Date(System.currentTimeMillis()));
        }

        super.create(campaign);

        assignZones(campaign);
    }

    @Override
    public void edit(Campaign campaign) throws ComtorDaoException {
        if (campaign.getStart_date() == null) {
            campaign.setStart_date(new java.sql.Date(System.currentTimeMillis()));
        }

        super.edit(campaign);
                
        updateZones(campaign);
    }

    @Override
    public void remove(Campaign campaign) throws ComtorDaoException {
        deleteZones(campaign);

        super.remove(campaign);
    }

    private void deleteZones(Campaign campaign) throws ComtorDaoException {
        new CampaignXZoneDAOFacade().deleteAllByCampaign(campaign.getId());
    }

    private void assignZones(Campaign campaign) throws ComtorDaoException {
        LinkedList<String> zones = campaign.getZones();

        if ((zones == null) || zones.isEmpty()) {
            return;
        }

        CampaignXZoneDAOFacade facade = new CampaignXZoneDAOFacade();

        for (String zone : zones) {
            facade.create(new CampaignXZone(campaign.getId(), Long.parseLong(zone)));
        }
    }
    
    private void updateZones(Campaign campaign) throws ComtorDaoException {
        deleteZones(campaign);
        assignZones(campaign);
    }

}
