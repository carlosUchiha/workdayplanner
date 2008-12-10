/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author robson
 */
@Entity
@Table(name = "TASK", catalog = "", schema = "")
@NamedQueries({@NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"), @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"), @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"), @NamedQuery(name = "Task.findByStarted", query = "SELECT t FROM Task t WHERE t.started = :started"), @NamedQuery(name = "Task.findByFinish", query = "SELECT t FROM Task t WHERE t.finish = :finish"), @NamedQuery(name = "Task.findByMood", query = "SELECT t FROM Task t WHERE t.mood = :mood"), @NamedQuery(name = "Task.findByCurrentEstimation", query = "SELECT t FROM Task t WHERE t.currentEstimation = :currentEstimation")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    private String description;
    @Column(name = "STARTED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Column(name = "FINISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;
    @Basic(optional = false)
    @Column(name = "MOOD", nullable = false)
    private int mood;
    @Column(name = "CURRENT_ESTIMATION")
    private Integer currentEstimation;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Project idProject;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Worker idWorker;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Task(Project selected) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Task(Integer id, String description, int mood) {
        this.id = id;
        this.description = description;
        this.mood = mood;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Integer getCurrentEstimation() {
        return currentEstimation;
    }

    public void setCurrentEstimation(Integer currentEstimation) {
        this.currentEstimation = currentEstimation;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Task[id=" + id + "]";
    }

}
