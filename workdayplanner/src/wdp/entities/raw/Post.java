/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "POST", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@NamedQueries({@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"), @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"), @NamedQuery(name = "Post.findByName", query = "SELECT p FROM Post p WHERE p.name = :name")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPost")
    private List<Assignment> assignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPost")
    private List<Worker> workerCollection;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assignment> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(List<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }

    public List<Worker> getWorkerCollection() {
        return workerCollection;
    }

    public void setWorkerCollection(List<Worker> workerCollection) {
        this.workerCollection = workerCollection;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Post[id=" + id + "]";
    }

}
