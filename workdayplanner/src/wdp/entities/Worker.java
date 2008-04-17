/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "WORKER")
@NamedQueries({
    @NamedQuery(name = "Worker.findById", query = "SELECT w FROM Worker w WHERE w.id = :id"), 
    @NamedQuery(name = "Worker.findByFirstname", query = "SELECT w FROM Worker w WHERE w.firstname = :firstname"), 
    @NamedQuery(name = "Worker.findBySurname", query = "SELECT w FROM Worker w WHERE w.surname = :surname"), 
    @NamedQuery(name = "Worker.findByBirthday", query = "SELECT w FROM Worker w WHERE w.birthday = :birthday"), 
    @NamedQuery(name = "Worker.findByEmail", query = "SELECT w FROM Worker w WHERE w.email = :email"), 
    @NamedQuery(name = "Worker.findByWww", query = "SELECT w FROM Worker w WHERE w.www = :www"), 
    @NamedQuery(name = "Worker.findByTelWork", query = "SELECT w FROM Worker w WHERE w.telWork = :telWork"), 
    @NamedQuery(name = "Worker.findByTelMobile", query = "SELECT w FROM Worker w WHERE w.telMobile = :telMobile"), 
    @NamedQuery(name = "Worker.findByTelHome", query = "SELECT w FROM Worker w WHERE w.telHome = :telHome"), 
    @NamedQuery(name = "Worker.findByIm1", query = "SELECT w FROM Worker w WHERE w.im1 = :im1"), 
    @NamedQuery(name = "Worker.findByIm2", query = "SELECT w FROM Worker w WHERE w.im2 = :im2"), 
    @NamedQuery(name = "Worker.findByIm3", query = "SELECT w FROM Worker w WHERE w.im3 = :im3"), 
    @NamedQuery(name = "Worker.findByOffice", query = "SELECT w FROM Worker w WHERE w.office = :office"), 
    @NamedQuery(name = "Worker.findByLoginName", query = "SELECT w FROM Worker w WHERE w.loginName = :loginName"), 
    @NamedQuery(name = "Worker.findByLoginPassword", query = "SELECT w FROM Worker w WHERE w.loginPassword = :loginPassword"), 
    @NamedQuery(name = "Worker.findByHired", query = "SELECT w FROM Worker w WHERE w.hired = :hired"), 
    @NamedQuery(name = "Worker.findByFired", query = "SELECT w FROM Worker w WHERE w.fired = :fired")})
public class Worker implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "WWW")
    private String www;
    @Column(name = "TEL_WORK")
    private String telWork;
    @Column(name = "TEL_MOBILE")
    private String telMobile;
    @Column(name = "TEL_HOME")
    private String telHome;
    @Column(name = "IM1")
    private String im1;
    @Column(name = "IM2")
    private String im2;
    @Column(name = "IM3")
    private String im3;
    @Column(name = "OFFICE")
    private String office;
    @Column(name = "LOGIN_NAME")
    private String loginName;
    @Column(name = "LOGIN_PASSWORD")
    private String loginPassword;
    @Column(name = "HIRED", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hired;
    @Column(name = "FIRED")
    @Temporal(TemporalType.DATE)
    private Date fired;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private Collection<Right> rightCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private Collection<Assignment> assignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWorker")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "idWorker")
    private Collection<Settings> settingsCollection;
    @OneToMany(mappedBy = "idAuthor")
    private Collection<Note> noteCollection;
    @JoinColumn(name = "ID_POST", referencedColumnName = "ID")
    @ManyToOne
    private Post idPost;
    @JoinColumn(name = "ID_TEAM", referencedColumnName = "ID")
    @ManyToOne
    private Team idTeam;
    @OneToMany(mappedBy = "idBoss")
    private Collection<Worker> workerCollection;
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
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        changeSupport.firePropertyChange("firstname", oldFirstname, firstname);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        String oldSurname = this.surname;
        this.surname = surname;
        changeSupport.firePropertyChange("surname", oldSurname, surname);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        Date oldBirthday = this.birthday;
        this.birthday = birthday;
        changeSupport.firePropertyChange("birthday", oldBirthday, birthday);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        String oldWww = this.www;
        this.www = www;
        changeSupport.firePropertyChange("www", oldWww, www);
    }

    public String getTelWork() {
        return telWork;
    }

    public void setTelWork(String telWork) {
        String oldTelWork = this.telWork;
        this.telWork = telWork;
        changeSupport.firePropertyChange("telWork", oldTelWork, telWork);
    }

    public String getTelMobile() {
        return telMobile;
    }

    public void setTelMobile(String telMobile) {
        String oldTelMobile = this.telMobile;
        this.telMobile = telMobile;
        changeSupport.firePropertyChange("telMobile", oldTelMobile, telMobile);
    }

    public String getTelHome() {
        return telHome;
    }

    public void setTelHome(String telHome) {
        String oldTelHome = this.telHome;
        this.telHome = telHome;
        changeSupport.firePropertyChange("telHome", oldTelHome, telHome);
    }

    public String getIm1() {
        return im1;
    }

    public void setIm1(String im1) {
        String oldIm1 = this.im1;
        this.im1 = im1;
        changeSupport.firePropertyChange("im1", oldIm1, im1);
    }

    public String getIm2() {
        return im2;
    }

    public void setIm2(String im2) {
        String oldIm2 = this.im2;
        this.im2 = im2;
        changeSupport.firePropertyChange("im2", oldIm2, im2);
    }

    public String getIm3() {
        return im3;
    }

    public void setIm3(String im3) {
        String oldIm3 = this.im3;
        this.im3 = im3;
        changeSupport.firePropertyChange("im3", oldIm3, im3);
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        String oldOffice = this.office;
        this.office = office;
        changeSupport.firePropertyChange("office", oldOffice, office);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        String oldLoginName = this.loginName;
        this.loginName = loginName;
        changeSupport.firePropertyChange("loginName", oldLoginName, loginName);
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        String oldLoginPassword = this.loginPassword;
        this.loginPassword = loginPassword;
        changeSupport.firePropertyChange("loginPassword", oldLoginPassword, loginPassword);
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        Date oldHired = this.hired;
        this.hired = hired;
        changeSupport.firePropertyChange("hired", oldHired, hired);
    }

    public Date getFired() {
        return fired;
    }

    public void setFired(Date fired) {
        Date oldFired = this.fired;
        this.fired = fired;
        changeSupport.firePropertyChange("fired", oldFired, fired);
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

    public Collection<Settings> getSettingsCollection() {
        return settingsCollection;
    }

    public void setSettingsCollection(Collection<Settings> settingsCollection) {
        this.settingsCollection = settingsCollection;
    }

    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    public Post getIdPost() {
        return idPost;
    }

    public void setIdPost(Post idPost) {
        Post oldIdPost = this.idPost;
        this.idPost = idPost;
        changeSupport.firePropertyChange("idPost", oldIdPost, idPost);
    }

    public Team getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Team idTeam) {
        Team oldIdTeam = this.idTeam;
        this.idTeam = idTeam;
        changeSupport.firePropertyChange("idTeam", oldIdTeam, idTeam);
    }

    public Collection<Worker> getWorkerCollection() {
        return workerCollection;
    }

    public void setWorkerCollection(Collection<Worker> workerCollection) {
        this.workerCollection = workerCollection;
    }

    public Worker getIdBoss() {
        return idBoss;
    }

    public void setIdBoss(Worker idBoss) {
        Worker oldIdBoss = this.idBoss;
        this.idBoss = idBoss;
        changeSupport.firePropertyChange("idBoss", oldIdBoss, idBoss);
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
        //return "wdp.entities.Worker[id=" + id + "]";
      return firstname+" "+surname;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

}
