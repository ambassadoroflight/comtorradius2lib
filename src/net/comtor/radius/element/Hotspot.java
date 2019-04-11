package net.comtor.radius.element;

import java.io.Serializable;
import web.connection.ApplicationDAO;
import net.comtor.dao.ComtorJDBCDao;
import net.comtor.dao.annotations.ComtorDaoFactory;
import net.comtor.dao.annotations.ComtorElement;
import net.comtor.dao.annotations.ComtorForeingField;
import net.comtor.dao.annotations.ComtorId;
import net.comtor.dao.annotations.ComtorSequence;

/**
 *
 * @author juandiego@comtor.net
 * @since Jan 24, 2019
 */
@ComtorElement(tableName = "hotspot")
@ComtorDaoFactory(factory = ApplicationDAO.class)
public class Hotspot implements Serializable {

    private static final long serialVersionUID = 2481433213059013742L;

    @ComtorId
    @ComtorSequence(name = ComtorJDBCDao.MYSQL_SEQUENCE, typeInsert = ComtorSequence.POST_INSERT)
    private long id;
    private String called_station_id;
    private String ip_address;
    private String name;
    private long zone;
    private String username;
    private String password;
    private boolean configured;

    @ComtorForeingField(referencesClass = Zone.class, foreingColumn = "name", referencesColumn = "zone")
    private String zone_name;

    public Hotspot() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalled_station_id() {
        return called_station_id;
    }

    public void setCalled_station_id(String called_station_id) {
        this.called_station_id = called_station_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getZone() {
        return zone;
    }

    public void setZone(long zone) {
        this.zone = zone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfigured() {
        return configured;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    @Override
    public String toString() {
        return "Hotspot{"
                + "id=" + id
                + ", called_station_id=" + called_station_id
                + ", ip_address=" + ip_address
                + ", name=" + name
                + ", username=" + username
                + ", password=" + password
                + ", configured=" + configured
                + '}';
    }

}
