/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author robson
 */
@Entity
@Table(name = "SETTINGS", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"KEYWORD"})})
@NamedQueries({@NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s"), @NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id"), @NamedQuery(name = "Settings.findByKeyword", query = "SELECT s FROM Settings s WHERE s.keyword = :keyword"), @NamedQuery(name = "Settings.findByValueString", query = "SELECT s FROM Settings s WHERE s.valueString = :valueString"), @NamedQuery(name = "Settings.findByDefaultValue", query = "SELECT s FROM Settings s WHERE s.defaultValue = :defaultValue"), @NamedQuery(name = "Settings.findByDescription", query = "SELECT s FROM Settings s WHERE s.description = :description")})
public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "KEYWORD", nullable = false, length = 255)
    private String keyword;
    @Basic(optional = false)
    @Column(name = "VALUE_STRING", nullable = false, length = 255)
    private String valueString;
    @Basic(optional = false)
    @Column(name = "DEFAULT_ VALUE", nullable = false, length = 255)
    private String defaultValue;
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID")
    @ManyToOne
    private Worker idWorker;

    public Settings() {
    }

    public Settings(Integer id) {
        this.id = id;
    }

    public Settings(Integer id, String keyword, String valueString, String defaultValue) {
        this.id = id;
        this.keyword = keyword;
        this.valueString = valueString;
        this.defaultValue = defaultValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Worker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Worker idWorker) {
        this.idWorker = idWorker;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Settings[id=" + id + "]";
    }

}
