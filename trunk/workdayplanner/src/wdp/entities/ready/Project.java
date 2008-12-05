/*
 * $Id: Project.java 8 2008-05-08 15:59:57Z rdubisz $
 */
package wdp.entities.ready;

import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.event.SwingPropertyChangeSupport;
import wdp.entities.raw.Cost;

/**
 *
 * @author Robson
 */
public class Project extends wdp.entities.raw.Project {

    SwingPropertyChangeSupport changeSupport = new SwingPropertyChangeSupport(this);

    @Override
    public void setId(Integer id) {
        Integer oldId = getId();
        super.setId(id);
        changeSupport.firePropertyChange("id", oldId, id);
    }

    @Override
    public void setName(String name) {
        String oldName = getName();
        super.setName(name);
        changeSupport.firePropertyChange("name", oldName, name);
    }

    @Override
    public void setDescription(String description) {
        String oldDescription = getDescription();
        super.setDescription(description);
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    @Override
    public void setDocumentation(String documentation) {
        String oldDocumentation = getDocumentation();
        super.setDocumentation(documentation);
        changeSupport.firePropertyChange("documentation", oldDocumentation, documentation);
    }

    @Override
    public void setPriority(Integer priority) {
        Integer oldPriority = getPriority();
        super.setPriority(priority);
        changeSupport.firePropertyChange("priority", oldPriority, priority);
    }

    @Override
    public void setCompleted(int completed) {
        int oldCompleted = getCompleted();
        super.setCompleted(completed);
        changeSupport.firePropertyChange("completed", oldCompleted, completed);
    }

    @Override
    public void setRecurring(short recurring) {
        Short oldRecurring = getRecurring();
        super.setRecurring(recurring);
        changeSupport.firePropertyChange("recurring", (int)oldRecurring, (int)recurring);
    }

    @Override
    public void setStarted(Date start) {
        Date oldStart = getStarted();
        super.setStarted(start);
        changeSupport.firePropertyChange("started", oldStart, start);
    }

    @Override
    public void setFinish(Date finish) {
        Date oldFinish = getFinish();
        super.setFinish(finish);
        changeSupport.firePropertyChange("finish", oldFinish, finish);
    }

    @Override
    public void setDeadline(Date deadline) {
        Date oldDeadline = getDeadline();
        super.setDeadline(deadline);
        changeSupport.firePropertyChange("deadline", oldDeadline, deadline);
    }

    @Override
    public void setEstimation(Integer estimation) {
        Integer oldEstimation = getEstimation();
        super.setEstimation(estimation);
        changeSupport.firePropertyChange("estimation", oldEstimation, estimation);
    }

    /**
     * TODO nie wiem czy to nie powinno byc do Cost z ready?
     * @param idCost
     */
    @Override
    public void setIdCost(Cost idCost) {
        Cost oldIdCost = getIdCost();
        super.setIdCost(idCost);
        changeSupport.firePropertyChange("idCost", oldIdCost, idCost);
    }

    /**
     * TODO nie wiem czy to nie powinno byc do Cost z ready?
     * @param idCost
     */
    @Override
    public void setIdParrent(wdp.entities.raw.Project idParrent) {
        wdp.entities.raw.Project oldIdParrent = getIdParrent();
        super.setIdParrent(idParrent);
        changeSupport.firePropertyChange("idParrent", oldIdParrent, idParrent);
    }

    @Override
    public String toString() {
        return getName();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

}
