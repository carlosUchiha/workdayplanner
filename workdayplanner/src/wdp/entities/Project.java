/*
 * $Id$
 */
package wdp.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Robson
 */
@Entity
@Table(name = "PROJECT")
@NamedQueries({
    @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description"),
    @NamedQuery(name = "Project.findByDocumentation", query = "SELECT p FROM Project p WHERE p.documentation = :documentation"),
    @NamedQuery(name = "Project.findByPriority", query = "SELECT p FROM Project p WHERE p.priority = :priority"),
    @NamedQuery(name = "Project.findByCompleted", query = "SELECT p FROM Project p WHERE p.completed = :completed"),
    @NamedQuery(name = "Project.findByRecurring", query = "SELECT p FROM Project p WHERE p.recurring = :recurring"),
    @NamedQuery(name = "Project.findByStarted", query = "SELECT p FROM Project p WHERE p.started = :started"),
    @NamedQuery(name = "Project.findByFinish", query = "SELECT p FROM Project p WHERE p.finish = :finish"),
    @NamedQuery(name = "Project.findByDeadline", query = "SELECT p FROM Project p WHERE p.deadline = :deadline"),
    @NamedQuery(name = "Project.findByEstimation", query = "SELECT p FROM Project p WHERE p.estimation = :estimation")
})
public class Project implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DOCUMENTATION")
    private String documentation;
    @Column(name = "PRIORITY")
    private Integer priority;
    @Column(name = "COMPLETED", nullable = false)
    private int completed;
    @Column(name = "RECURRING")
    private Short recurring;
    @Column(name = "STARTED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Column(name = "FINISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Column(name = "ESTIMATION")
    private Integer estimation;
    @OneToMany(mappedBy = "idProject")
    private Collection<Right> rightCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<Assignment> assignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<Task> taskCollection;
    @JoinColumn(name = "ID_COST", referencedColumnName = "ID")
    @ManyToOne
    private Cost idCost;
    @OneToMany(mappedBy = "idParrent")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "ID_PARRENT", referencedColumnName = "ID")
    @ManyToOne
    private Project idParrent;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Project(Integer id, String name, int completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        String oldDocumentation = this.documentation;
        this.documentation = documentation;
        changeSupport.firePropertyChange("documentation", oldDocumentation, documentation);
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        Integer oldPriority = this.priority;
        this.priority = priority;
        changeSupport.firePropertyChange("priority", oldPriority, priority);
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        int oldCompleted = this.completed;
        this.completed = completed;
        changeSupport.firePropertyChange("completed", oldCompleted, completed);
    }

    public Short getRecurring() {
        return recurring;
    }

    public void setRecurring(Short recurring) {
        Short oldRecurring = this.recurring;
        this.recurring = recurring;
        changeSupport.firePropertyChange("recurring", oldRecurring, recurring);
    }

    public Date getStart() {
        return started;
    }

    public void setStart(Date start) {
        Date oldStart = this.started;
        this.started = start;
        changeSupport.firePropertyChange("started", oldStart, start);
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        Date oldFinish = this.finish;
        this.finish = finish;
        changeSupport.firePropertyChange("finish", oldFinish, finish);
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        Date oldDeadline = this.deadline;
        this.deadline = deadline;
        changeSupport.firePropertyChange("deadline", oldDeadline, deadline);
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        Integer oldEstimation = this.estimation;
        this.estimation = estimation;
        changeSupport.firePropertyChange("estimation", oldEstimation, estimation);
    }

    public Collection<Right> getRightCollection() {
        return rightCollection;
    }

    public void setRightCollection(Collection<Right> rightCollection) {
        this.rightCollection = rightCollection;
    }

    public Collection<Assignment> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(Collection<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }

    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public Cost getIdCost() {
        return idCost;
    }

    public void setIdCost(Cost idCost) {
        Cost oldIdCost = this.idCost;
        this.idCost = idCost;
        changeSupport.firePropertyChange("idCost", oldIdCost, idCost);
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Project getIdParrent() {
        return idParrent;
    }

    public void setIdParrent(Project idParrent) {
        Project oldIdParrent = this.idParrent;
        this.idParrent = idParrent;
        changeSupport.firePropertyChange("idParrent", oldIdParrent, idParrent);
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "wdp.entities.Project[id=" + id + "]";
        return name;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
