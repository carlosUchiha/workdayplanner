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
@Table(name = "RIGHT")
@NamedQueries({
    @NamedQuery(name = "Right.findById", query = "SELECT r FROM Right r WHERE r.id = :id"),
    @NamedQuery(name = "Right.findByAllowed", query = "SELECT r FROM Right r WHERE r.allowed = :allowed")
})
public class Right implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ALLOWED")
    private Short allowed;
    @JoinColumn(name = "ID_OPERATION", referencedColumnName = "ID")
    @ManyToOne
    private Operation idOperation;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID")
    @ManyToOne
    private Project idProject;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID")
    @ManyToOne
    private Worker idWorker;

    public Right() {
    }

    public Right(Integer id) {
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

    public Short getAllowed() {
        return allowed;
    }

    public void setAllowed(Short allowed) {
        Short oldAllowed = this.allowed;
        this.allowed = allowed;
        changeSupport.firePropertyChange("allowed", oldAllowed, allowed);
    }

    public Operation getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Operation idOperation) {
        Operation oldIdOperation = this.idOperation;
        this.idOperation = idOperation;
        changeSupport.firePropertyChange("idOperation", oldIdOperation, idOperation);
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        Project oldIdProject = this.idProject;
        this.idProject = idProject;
        changeSupport.firePropertyChange("idProject", oldIdProject, idProject);
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
        if (!(object instanceof Right)) {
            return false;
        }
        Right other = (Right) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.Right[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
