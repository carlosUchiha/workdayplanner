/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;

/**
 *
 * @author Robson
 */
@Entity
@Table(name = "TASK")
@NamedQueries({@NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"), @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"), @NamedQuery(name = "Task.findByStart", query = "SELECT t FROM Task t WHERE t.start = :start"), @NamedQuery(name = "Task.findByFinish", query = "SELECT t FROM Task t WHERE t.finish = :finish"), @NamedQuery(name = "Task.findByMood", query = "SELECT t FROM Task t WHERE t.mood = :mood"), @NamedQuery(name = "Task.findByCurrentEstimation", query = "SELECT t FROM Task t WHERE t.currentEstimation = :currentEstimation")})
public class Task implements Serializable {
	@Transient
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Column(name = "FINISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;
    @Column(name = "MOOD")
    private Integer mood;
    @Column(name = "CURRENT_ESTIMATION")
    private Integer currentEstimation;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID")
    @ManyToOne
    private Project idProject;
    @JoinColumn(name = "ID_WORKER", referencedColumnName = "ID")
    @ManyToOne
    private Worker idWorker;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }
    
    public Task(Project project) {
        this.idProject = project;
        this.start = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
		Integer oldId = this.id;
        this.id = id;
		changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
		String oldDescription = this.description;
        this.description = description;
		changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
		Date oldStart = this.start;
        this.start = start;
		changeSupport.firePropertyChange("start", oldStart, start);
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
		Date oldFinish = this.finish;
        this.finish = finish;
		changeSupport.firePropertyChange("finish", oldFinish, finish);
    }

    public Integer getMood() {
        return mood;
    }

    public void setMood(Integer mood) {
		Integer oldMood = this.mood;
        this.mood = mood;
		changeSupport.firePropertyChange("mood", oldMood, mood);
    }

    public Integer getCurrentEstimation() {
        return currentEstimation;
    }

    public void setCurrentEstimation(Integer currentEstimation) {
		Integer oldCurrentEstimation = this.currentEstimation;
        this.currentEstimation = currentEstimation;
		changeSupport.firePropertyChange("currentEstimation", oldCurrentEstimation, currentEstimation);
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
        return "wdp.entities.Task[id=" + id + "]";
    }

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

}
