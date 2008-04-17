/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wdp.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Robson
 */
@Entity
@Table(name = "COST")
@NamedQueries({@NamedQuery(name = "Cost.findById", query = "SELECT c FROM Cost c WHERE c.id = :id"), @NamedQuery(name = "Cost.findByCost", query = "SELECT c FROM Cost c WHERE c.cost = :cost"), @NamedQuery(name = "Cost.findByName", query = "SELECT c FROM Cost c WHERE c.name = :name"), @NamedQuery(name = "Cost.findByInclude", query = "SELECT c FROM Cost c WHERE c.include = :include")})
public class Cost implements Serializable {

  @Transient
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;
  @Column(name = "COST")
  private Double cost;
  @Column(name = "NAME")
  private String name;
  @Column(name = "INCLUDE")
  private Short include;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCost")
  private Collection<Project> projectCollection;

  public Cost() {
  }

  public Cost(Integer id) {
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

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    Double oldCost = this.cost;
    this.cost = cost;
    changeSupport.firePropertyChange("cost", oldCost, cost);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    String oldName = this.name;
    this.name = name;
    changeSupport.firePropertyChange("name", oldName, name);
  }

  public Short getInclude() {
    return include;
  }

  public void setInclude(Short include) {
    Short oldInclude = this.include;
    this.include = include;
    changeSupport.firePropertyChange("include", oldInclude, include);
  }

  public Collection<Project> getProjectCollection() {
    return projectCollection;
  }

  public void setProjectCollection(Collection<Project> projectCollection) {
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
    //return "wdp.entities.Cost[id=" + id + "]";
    return name+" ("+cost+")";
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }
}
