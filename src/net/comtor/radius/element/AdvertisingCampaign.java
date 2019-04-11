package net.comtor.radius.element;

import java.io.Serializable;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.annotations.ComtorDaoFactory;
import net.comtor.dao.annotations.ComtorElement;
import net.comtor.dao.annotations.ComtorForeingField;
import net.comtor.dao.annotations.ComtorId;
import net.comtor.dao.annotations.ComtorSequence;
import web.connection.ApplicationDAO;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Apr 04, 2019
 */
@ComtorElement(tableName = "advertising_campaign")
@ComtorDaoFactory(factory = ApplicationDAO.class)
public class AdvertisingCampaign implements Serializable {

    private static final long serialVersionUID = 737240323779905295L;

    @ComtorId
    @ComtorSequence(name = ComtorJDBCDao.MYSQL_SEQUENCE, typeInsert = ComtorSequence.POST_INSERT)
    private long id;
    private long sponsor;
    private String description;
    private String banner_1;
    private String banner_2;
    private long survey;
    private java.sql.Date start_date;
    private java.sql.Date end_date;
    private boolean active;

    @ComtorForeingField(referencesClass = Sponsor.class, foreingColumn = "name", referencesColumn = "sponsor")
    private String sponsor_name;

    public AdvertisingCampaign() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSponsor() {
        return sponsor;
    }

    public void setSponsor(long sponsor) {
        this.sponsor = sponsor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner_1() {
        return banner_1;
    }

    public void setBanner_1(String banner_1) {
        this.banner_1 = banner_1;
    }

    public String getBanner_2() {
        return banner_2;
    }

    public void setBanner_2(String banner_2) {
        this.banner_2 = banner_2;
    }

    public long getSurvey() {
        return survey;
    }

    public void setSurvey(long survey) {
        this.survey = survey;
    }

    public java.sql.Date getStart_date() {
        return start_date;
    }

    public void setStart_date(java.sql.Date start_date) {
        this.start_date = start_date;
    }

    public java.sql.Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(java.sql.Date end_date) {
        this.end_date = end_date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSponsor_name() {
        return sponsor_name;
    }

    public void setSponsor_name(String sponsor_name) {
        this.sponsor_name = sponsor_name;
    }

    public String getStatus() {
        return (active ? "Activa" : "Inactiva");
    }

    @Override
    public String toString() {
        return "AdvertisingCampaign{"
                + "id=" + id
                + ", sponsor=" + sponsor
                + ", description=" + description
                + ", banner_1=" + banner_1
                + ", banner_2=" + banner_2
                + ", start_date=" + start_date
                + ", end_date=" + end_date
                + ", active=" + active
                + '}';
    }

}
