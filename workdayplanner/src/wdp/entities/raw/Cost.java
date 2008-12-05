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
@Table(name = "COST", catalog = "", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
@NamedQueries({@NamedQuery(name = "Cost.findAll", query = "SELECT c FROM Cost c"), @NamedQuery(name = "Cost.findById", query = "SELECT c FROM Cost c WHERE c.id = :id"), @NamedQuery(name = "Cost.findByCost", query = "SELECT c FROM Cost c WHERE c.cost = :cost"), @NamedQuery(name = "Cost.findByName", query = "SELECT c FROM Cost c WHERE c.name = :name"), @NamedQuery(name = "Cost.findByInclude", query = "SELECT c FROM Cost c WHERE c.include = :include")})
public class Cost implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "COST", precision = 15, scale = 15)
    private Double cost;
    @Column(name = "NAME", length = 255)
    private String name;
    @Column(name = "INCLUDE")
    private Short include;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCost")
    private List<Project> projectCollection;

    public Cost() {
    }

    public Cost(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getInclude() {
        return include;
    }

    public void setInclude(Short include) {
        this.include = include;
    }

    public List<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(List<Project> projectCollection) {
        this.projectCollection = projectCollection;
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
        if (!(object instanceof Cost)) {
            return false;
        }
        Cost other = (Cost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wdp.entities.raw.Cost[id=" + id + "]";
    }

}
