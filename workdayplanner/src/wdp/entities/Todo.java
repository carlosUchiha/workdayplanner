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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Robson
 */
@Entity
@Table(name = "TODO")
@NamedQueries({
    @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id"),
    @NamedQuery(name = "Todo.findByIdProject", query = "SELECT t FROM Todo t WHERE t.idProject = :idProject"),
    @NamedQuery(name = "Todo.findByIdWorker", query = "SELECT t FROM Todo t WHERE t.idWorker = :idWorker")
})
public class Todo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ID_PROJECT", nullable = false)
    private int idProject;
    @Column(name = "ID_WORKER", nullable = false)
    private int idWorker;

    public Todo() {
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Todo(Integer id, int idProject, int idWorker) {
        this.id = id;
        this.idProject = idProject;
        this.idWorker = idWorker;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        int oldIdProject = this.idProject;
        this.idProject = idProject;
        changeSupport.firePropertyChange("idProject", oldIdProject, idProject);
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        int oldIdWorker = this.idWorker;
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
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.Todo[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
