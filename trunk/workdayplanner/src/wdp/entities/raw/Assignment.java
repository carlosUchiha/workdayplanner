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
@Table(name = "ASSIGNMENT", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_WORKER", "ID_PROJECT"})})
@NamedQueries({@NamedQuery(name = "Assignment.findAll", query = "SELECT a FROM Assignment a"), @NamedQuery(name = "Assignment.findById", query = "SELECT a FROM Assignment a WHERE a.id = :id"), @NamedQuery(name = "Assignment.findBySkill", query = "SELECT a FROM Assignment a WHERE a.skill = :skill")})
public class Assignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SKILL")
    private Integer skill;
    @JoinColumn(name = "ID_POST", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Post idPost;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Project idProject;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Worker idWorker;

    public Assignment() {
    }

    public Assignment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
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
        if (!(object instanceof Assignment)) {
            return false;
        }
        Assignment other = (Assignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Assignment[id=" + id + "]";
    }

}
