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
@Table(name = "ASSIGNMENT")
@NamedQueries({
    @NamedQuery(name = "Assignment.findById", query = "SELECT a FROM Assignment a WHERE a.id = :id"),
    @NamedQuery(name = "Assignment.findBySkill", query = "SELECT a FROM Assignment a WHERE a.skill = :skill")
})
public class Assignment implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "SKILL")
    private Integer skill;
    @JoinColumn(name = "ID_POST", referencedColumnName = "ID")
    @ManyToOne
    private Post idPost;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID")
    @ManyToOne
    private Project idProject;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID")
    @ManyToOne
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
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        Integer oldSkill = this.skill;
        this.skill = skill;
        changeSupport.firePropertyChange("skill", oldSkill, skill);
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        Post oldIdPost = this.idPost;
        this.idPost = idPost;
        changeSupport.firePropertyChange("idPost", oldIdPost, idPost);
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
        return "wdp.entities.Assignment[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
