/*
 * JWorker.java
 *
 * Created on 18 grudzień 2007, 18:11
 */

package wdp.worker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.persistence.Transient;
import org.jdesktop.application.Action;
import wdp.entities.Worker;

/**
 *
 * @author  Robson
 */
public class JWorker extends javax.swing.JPanel {
	
	/** Creates new form JWorker */
	public JWorker() {
		initComponents();
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jTextFieldValue = new javax.swing.JTextField();
    jButtonSelect = new javax.swing.JButton();

    setName("Form"); // NOI18N
    setLayout(new java.awt.GridBagLayout());

    jTextFieldValue.setEditable(false);
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(JWorker.class);
    jTextFieldValue.setText(resourceMap.getString("jTextFieldValue.text")); // NOI18N
    jTextFieldValue.setName("jTextFieldValue"); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 1.0;
    add(jTextFieldValue, gridBagConstraints);

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getActionMap(JWorker.class, this);
    jButtonSelect.setAction(actionMap.get("showWorkersListWindow")); // NOI18N
    jButtonSelect.setName("jButtonSelect"); // NOI18N
    jButtonSelect.setPreferredSize(new java.awt.Dimension(21, 21));
    add(jButtonSelect, new java.awt.GridBagConstraints());
  }// </editor-fold>//GEN-END:initComponents
	
	public Worker getObject() {
		return object;
	}

	public void setObject(Worker object) {
    Worker oldValue = this.object;
		this.object = object;
		if(object!=null) {
			jTextFieldValue.setText(object.getFirstname()+" "+object.getSurname());
      jTextFieldValue.setToolTipText("Team: "+object.getIdTeam().getName());
		} else {
			jTextFieldValue.setText("");
      jTextFieldValue.setToolTipText("");
		}
    changeSupport.firePropertyChange("object", oldValue, object);
	}
	
	@Override
	public void setEnabled(boolean aEnabled) {
		//jTextFieldValue.setEnabled(aEnabled);
		jButtonSelect.setEnabled(aEnabled);
	}
  
  @Override
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  @Action
  public void showWorkersListWindow() {
    setObject(WorkerSelectFrame.selectEntity());
  }
	
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonSelect;
  private javax.swing.JTextField jTextFieldValue;
  // End of variables declaration//GEN-END:variables
	private Worker object = null;
	
  @Transient
  private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
}
