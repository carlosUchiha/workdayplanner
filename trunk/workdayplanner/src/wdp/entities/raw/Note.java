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
@Table(name = "NOTE", catalog = "", schema = "")
@NamedQueries({@NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"), @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id"), @NamedQuery(name = "Note.findByTitle", query = "SELECT n FROM Note n WHERE n.title = :title"), @NamedQuery(name = "Note.findByDescription", query = "SELECT n FROM Note n WHERE n.description = :description"), @NamedQuery(name = "Note.findByCreated", query = "SELECT n FROM Note n WHERE n.created = :created"), @NamedQuery(name = "Note.findByTypename", query = "SELECT n FROM Note n WHERE n.typename = :typename"), @NamedQuery(name = "Note.findByIdPosition", query = "SELECT n FROM Note n WHERE n.idPosition = :idPosition"), @NamedQuery(name = "Note.findByPrivateOnly", query = "SELECT n FROM Note n WHERE n.privateOnly = :privateOnly")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "TITLE", length = 255)
    private String title;
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;
    @Basic(optional = false)
    @Column(name = "CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "TYPENAME", nullable = false, length = 255)
    private String typename;
    @Basic(optional = false)
    @Column(name = "ID_POSITION", nullable = false)
    private int idPosition;
    @Basic(optional = false)
    @Column(name = "PRIVATE_ONLY", nullable = false)
    private short privateOnly;
    @JoinColumn(name = "ID_AUTHOR", referencedColumnName = "ID")
    @ManyToOne
    private Worker idAuthor;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(Integer id, Date created, String typename, int idPosition, short privateOnly) {
        this.id = id;
        this.created = created;
        this.typename = typename;
        this.idPosition = idPosition;
        this.privateOnly = privateOnly;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(int idPosition) {
        this.idPosition = idPosition;
    }

    public short getPrivateOnly() {
        return privateOnly;
    }

    public void setPrivateOnly(short privateOnly) {
        this.privateOnly = privateOnly;
    }

    public Worker getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Worker idAuthor) {
        this.idAuthor = idAuthor;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Note[id=" + id + "]";
    }

}
