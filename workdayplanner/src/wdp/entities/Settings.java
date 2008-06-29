/*
 * $Id$
 */
package wdp.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Robson
 */
@Entity
@Table(name = "SETTINGS")
@NamedQueries({
    @NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id"),
    @NamedQuery(name = "Settings.findByKeyword", query = "SELECT s FROM Settings s WHERE s.keyword = :keyword"),
    @NamedQuery(name = "Settings.findByValueString", query = "SELECT s FROM Settings s WHERE s.valueString = :valueString"),
    @NamedQuery(name = "Settings.findByDefaultValue", query = "SELECT s FROM Settings s WHERE s.defaultValue = :defaultValue"),
    @NamedQuery(name = "Settings.findByDescription", query = "SELECT s FROM Settings s WHERE s.description = :description")
})
public class Settings implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "KEYWORD")
    private String keyword;
    @Column(name = "VALUE_STRING")
    private String valueString;
    @Column(name = "DEFAULT_ VALUE")
    private String defaultValue;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID")
    @ManyToOne
    private Worker idWorker;

    public Settings() {
    }

    public Settings(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        String oldKeyword = this.keyword;
        this.keyword = keyword;
        changeSupport.firePropertyChange("keyword", oldKeyword, keyword);
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        String oldValueString = this.valueString;
        this.valueString = valueString;
        changeSupport.firePropertyChange("valueString", oldValueString, valueString);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        String oldDefaultValue = this.defaultValue;
        this.defaultValue = defaultValue;
        changeSupport.firePropertyChange("defaultValue", oldDefaultValue, defaultValue);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public Worker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Worker idWorker) {
        Worker oldIdWorker = this.idWorker;
        this.idWorker = idWorker;
        changeSupport.firePropertyChange("idWorker", oldIdWorker, idWorker);
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
        return "wdp.entities.Settings[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}