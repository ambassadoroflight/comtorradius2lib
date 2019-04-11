package net.comtor.radius.element;

import java.io.Serializable;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.annotations.ComtorDaoFactory;
import net.comtor.dao.annotations.ComtorElement;
import net.comtor.dao.annotations.ComtorId;
import net.comtor.dao.annotations.ComtorSequence;
import web.connection.ApplicationDAO;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Apr 03, 2019
 */
@ComtorElement(tableName = "zone")
@ComtorDaoFactory(factory = ApplicationDAO.class)
public class Zone implements Serializable {

    private static final long serialVersionUID = -8208208000808458825L;

    @ComtorId
    @ComtorSequence(name = ComtorJDBCDao.MYSQL_SEQUENCE, typeInsert = ComtorSequence.POST_INSERT)
    private long id;
    private String name;
    private long advertising_campaign;

    public Zone() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdvertising_campaign() {
        return advertising_campaign;
    }

    public void setAdvertising_campaign(long advertising_campaign) {
        this.advertising_campaign = advertising_campaign;
    }

    @Override
    public String toString() {
        return "Zone{"
                + "id=" + id
                + ", name=" + name
                + ", advertising_campaign=" + advertising_campaign
                + '}';
    }

}
