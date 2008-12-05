/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author robson
 */
@Entity
@Table(name = "OPERATION", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"OPERATION_NAME"})})
@NamedQueries({@NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o"), @NamedQuery(name = "Operation.findById", query = "SELECT o FROM Operation o WHERE o.id = :id"), @NamedQuery(name = "Operation.findByOperationName", query = "SELECT o FROM Operation o WHERE o.operationName = :operationName"), @NamedQuery(name = "Operation.findByLogging", query = "SELECT o FROM Operation o WHERE o.logging = :logging"), @NamedQuery(name = "Operation.findByIsRead", query = "SELECT o FROM Operation o WHERE o.isRead = :isRead"), @NamedQuery(name = "Operation.findByIsWrite", query = "SELECT o FROM Operation o WHERE o.isWrite = :isWrite")})
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "OPERATION_NAME", nullable = false, length = 255)
    private String operationName;
    @Basic(optional = false)
    @Column(name = "LOGGING", nullable = false)
    private short logging;
    @Basic(optional = false)
    @Column(name = "IS_READ", nullable = false)
    private short isRead;
    @Basic(optional = false)
    @Column(name = "IS_WRITE", nullable = false)
    private short isWrite;
    @OneToMany(mappedBy = "idOperation")
    private Collection<Right> rightCollection;

    public Operation() {
    }

    public Operation(Integer id) {
        this.id = id;
    }

    public Operation(Integer id, String operationName, short logging, short isRead, short isWrite) {
        this.id = id;
        this.operationName = operationName;
        this.logging = logging;
        this.isRead = isRead;
        this.isWrite = isWrite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public short getLogging() {
        return logging;
    }

    public void setLogging(short logging) {
        this.logging = logging;
    }

    public short getIsRead() {
        return isRead;
    }

    public void setIsRead(short isRead) {
        this.isRead = isRead;
    }

    public short getIsWrite() {
        return isWrite;
    }

    public void setIsWrite(short isWrite) {
        this.isWrite = isWrite;
    }

    public Collection<Right> getRightCollection() {
        return rightCollection;
    }

    public void setRightCollection(Collection<Right> rightCollection) {
        this.rightCollection = rightCollection;
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
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Operation[id=" + id + "]";
    }

}
