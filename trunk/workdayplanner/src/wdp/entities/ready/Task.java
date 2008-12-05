/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.ready;

import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.event.SwingPropertyChangeSupport;
import wdp.entities.raw.Project;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class Task extends wdp.entities.raw.Task {

    SwingPropertyChangeSupport changeSupport = new SwingPropertyChangeSupport(this);

    public Task() {
        super();
    }

    public Task(wdp.entities.ready.Project selected) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void setCurrentEstimation(Integer currentEstimation) {
        Integer old = getCurrentEstimation();
        super.setCurrentEstimation(currentEstimation);
        changeSupport.firePropertyChange("currentEstimation", old, currentEstimation);
    }

    @Override
    public void setDescription(String description) {
        String old = getDescription();
        super.setDescription(description);
        changeSupport.firePropertyChange("description", old, description);
    }

    @Override
    public void setFinish(Date finish) {
        Date old = getFinish();
        super.setFinish(finish);
        changeSupport.firePropertyChange("finish", old, finish);
    }

    @Override
    public void setId(Integer id) {
        Integer old = getId();
        super.setId(id);
        changeSupport.firePropertyChange("id", old, id);
    }

    @Override
    public void setIdProject(Project idProject) {
        Project old = getIdProject();
        super.setIdProject(idProject);
        changeSupport.firePropertyChange("idProject", old, idProject);
    }

    @Override
    public void setIdWorker(Worker idWorker) {
        Worker old = getIdWorker();
        super.setIdWorker(idWorker);
        changeSupport.firePropertyChange("idWorker", old, idWorker);
    }

    @Override
    public void setMood(int mood) {
        int old = getMood();
        super.setMood(mood);
        changeSupport.firePropertyChange("mood", old, mood);
    }

    @Override
    public void setStarted(Date started) {
        Date old = getStarted();
        super.setStarted(started);
        changeSupport.firePropertyChange("started", old, started);
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
