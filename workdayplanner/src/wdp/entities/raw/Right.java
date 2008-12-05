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
@Table(name = "RIGHT", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_WORKER", "ID_PROJECT", "ID_OPERATION"})})
@NamedQueries({@NamedQuery(name = "Right.findAll", query = "SELECT r FROM Right r"), @NamedQuery(name = "Right.findById", query = "SELECT r FROM Right r WHERE r.id = :id"), @NamedQuery(name = "Right.findByAllowed", query = "SELECT r FROM Right r WHERE r.allowed = :allowed")})
public class Right implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
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
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
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
        this.id = id;
    }

    public Short getAllowed() {
        return allowed;
    }

    public void setAllowed(Short allowed) {
        this.allowed = allowed;
    }

    public Operation getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Operation idOperation) {
        this.idOperation = idOperation;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
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
        return "wdp.entities.raw.Right[id=" + id + "]";
    }

}
