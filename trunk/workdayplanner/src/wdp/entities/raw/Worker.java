/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.raw;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author robson
 */
@Entity
@Table(name = "WORKER", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"LOGIN_NAME"})})
@NamedQueries({@NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"), @NamedQuery(name = "Worker.findById", query = "SELECT w FROM Worker w WHERE w.id = :id"), @NamedQuery(name = "Worker.findByFirstname", query = "SELECT w FROM Worker w WHERE w.firstname = :firstname"), @NamedQuery(name = "Worker.findBySurname", query = "SELECT w FROM Worker w WHERE w.surname = :surname"), @NamedQuery(name = "Worker.findByBirthday", query = "SELECT w FROM Worker w WHERE w.birthday = :birthday"), @NamedQuery(name = "Worker.findByEmail", query = "SELECT w FROM Worker w WHERE w.email = :email"), @NamedQuery(name = "Worker.findByWww", query = "SELECT w FROM Worker w WHERE w.www = :www"), @NamedQuery(name = "Worker.findByTelWork", query = "SELECT w FROM Worker w WHERE w.telWork = :telWork"), @NamedQuery(name = "Worker.findByTelMobile", query = "SELECT w FROM Worker w WHERE w.telMobile = :telMobile"), @NamedQuery(name = "Worker.findByTelHome", query = "SELECT w FROM Worker w WHERE w.telHome = :telHome"), @NamedQuery(name = "Worker.findByIm1", query = "SELECT w FROM Worker w WHERE w.im1 = :im1"), @NamedQuery(name = "Worker.findByIm2", query = "SELECT w FROM Worker w WHERE w.im2 = :im2"), @NamedQuery(name = "Worker.findByIm3", query = "SELECT w FROM Worker w WHERE w.im3 = :im3"), @NamedQuery(name = "Worker.findByOffice", query = "SELECT w FROM Worker w WHERE w.office = :office"), @NamedQuery(name = "Worker.findByLoginName", query = "SELECT w FROM Worker w WHERE w.loginName = :loginName"), @NamedQuery(name = "Worker.findByLoginPassword", query = "SELECT w FROM Worker w WHERE w.loginPassword = :loginPassword"), @NamedQuery(name = "Worker.findByHired", query = "SELECT w FROM Worker w WHERE w.hired = :hired"), @NamedQuery(name = "Worker.findByFired", query = "SELECT w FROM Worker w WHERE w.fired = :fired")})
public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "FIRSTNAME", nullable = false, length = 255)
    private String firstname;
    @Basic(optional = false)
    @Column(name = "SURNAME", nullable = false, length = 255)
    private String surname;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "EMAIL", length = 45)
    private String email;
    @Column(name = "WWW", length = 255)
    private String www;
    @Column(name = "TEL_WORK", length = 45)
    private String telWork;
    @Column(name = "TEL_MOBILE", length = 45)
    private String telMobile;
    @Column(name = "TEL_HOME", length = 45)
    private String telHome;
    @Column(name = "IM1", length = 45)
    private String im1;
    @Column(name = "IM2", length = 45)
    private String im2;
    @Column(name = "IM3", length = 45)
    private String im3;
    @Column(name = "OFFICE", length = 45)
    private String office;
    @Column(name = "LOGIN_NAME", length = 45)
    private String loginName;
    @Column(name = "LOGIN_PASSWORD", length = 45)
    private String loginPassword;
    @Basic(optional = false)
    @Column(name = "HIRED", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hired;
    @Column(name = "FIRED")
    @Temporal(TemporalType.DATE)
    private Date fired;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private List<Right> rightCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private List<Assignment> assignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private List<Task> taskCollection;
    @OneToMany(mappedBy = "idWorker")
    private List<Settings> settingsCollection;
    @OneToMany(mappedBy = "idAuthor")
    private List<Note> noteCollection;
    @JoinColumn(name = "ID_POST", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Post idPost;
    @JoinColumn(name = "ID_TEAM", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Team idTeam;
    @OneToMany(mappedBy = "idBoss")
    private List<Worker> workerCollection;
    @JoinColumn(name = "ID_BOSS", referencedColumnName = "ID")
    @ManyToOne
    private Worker idBoss;

    public Worker() {
    }

    public Worker(Integer id) {
        this.id = id;
    }

    public Worker(Integer id, String firstname, String surname, Date hired) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.hired = hired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getTelWork() {
        return telWork;
    }

    public void setTelWork(String telWork) {
        this.telWork = telWork;
    }

    public String getTelMobile() {
        return telMobile;
    }

    public void setTelMobile(String telMobile) {
        this.telMobile = telMobile;
    }

    public String getTelHome() {
        return telHome;
    }

    public void setTelHome(String telHome) {
        this.telHome = telHome;
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(String im1) {
        this.im1 = im1;
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(String im2) {
        this.im2 = im2;
    }

    public String getIm3() {
        return im3;
    }

    public void setIm3(String im3) {
        this.im3 = im3;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
    }

    public Date getFired() {
        return fired;
    }

    public void setFired(Date fired) {
        this.fired = fired;
    }

    public List<Right> getRightCollection() {
        return rightCollection;
    }

    public void setRightCollection(List<Right> rightCollection) {
        this.rightCollection = rightCollection;
    }

    public List<Assignment> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(List<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
    }

    public List<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(List<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    public List<Settings> getSettingsCollection() {
        return settingsCollection;
    }

    public void setSettingsCollection(List<Settings> settingsCollection) {
        this.settingsCollection = settingsCollection;
    }

    public List<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(List<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        this.idPost = idPost;
    }

    public Team getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Team idTeam) {
        this.idTeam = idTeam;
    }

    public List<Worker> getWorkerCollection() {
        return workerCollection;
    }

    public void setWorkerCollection(List<Worker> workerCollection) {
        this.workerCollection = workerCollection;
    }

    public Worker getIdBoss() {
        return idBoss;
    }

    public void setIdBoss(Worker idBoss) {
        this.idBoss = idBoss;
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
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Worker[id=" + id + "]";
    }

}
