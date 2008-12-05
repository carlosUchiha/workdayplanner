/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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

/**
 *
 * @author robson
 */
@Entity
@Table(name = "PROJECT", catalog = "", schema = "")
@NamedQueries({@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"), @NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"), @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"), @NamedQuery(name = "Project.findByDescription", query = "SELECT p FROM Project p WHERE p.description = :description"), @NamedQuery(name = "Project.findByDocumentation", query = "SELECT p FROM Project p WHERE p.documentation = :documentation"), @NamedQuery(name = "Project.findByPriority", query = "SELECT p FROM Project p WHERE p.priority = :priority"), @NamedQuery(name = "Project.findByCompleted", query = "SELECT p FROM Project p WHERE p.completed = :completed"), @NamedQuery(name = "Project.findByRecurring", query = "SELECT p FROM Project p WHERE p.recurring = :recurring"), @NamedQuery(name = "Project.findByStarted", query = "SELECT p FROM Project p WHERE p.started = :started"), @NamedQuery(name = "Project.findByFinish", query = "SELECT p FROM Project p WHERE p.finish = :finish"), @NamedQuery(name = "Project.findByDeadline", query = "SELECT p FROM Project p WHERE p.deadline = :deadline"), @NamedQuery(name = "Project.findByEstimation", query = "SELECT p FROM Project p WHERE p.estimation = :estimation")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    @Column(name = "DOCUMENTATION", length = 255)
    private String documentation;
    @Column(name = "PRIORITY")
    private Integer priority;
    @Basic(optional = false)
    @Column(name = "COMPLETED", nullable = false)
    private int completed;
    @Basic(optional = false)
    @Column(name = "RECURRING", nullable = false)
    private short recurring;
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
    @JoinColumn(name = "ID_COST", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
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

    public Project(Integer id, String name, int completed, short recurring) {
        this.id = id;
        this.name = name;
        this.completed = completed;
        this.recurring = recurring;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public short getRecurring() {
        return recurring;
    }

    public void setRecurring(short recurring) {
        this.recurring = recurring;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
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
        this.idCost = idCost;
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
        this.idParrent = idParrent;
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
        return "wdp.entities.raw.Project[id=" + id + "]";
    }

}
