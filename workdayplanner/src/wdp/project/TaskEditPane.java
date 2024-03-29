/*
 * $Id$
 * Created on 25 kwiecień 2008, 19:11
 */
package wdp.project;

import javax.swing.JDialog;
import org.jdesktop.application.Action;
import wdp.SelectWinExitState;
import wdp.WdpApp;
import wdp.entities.raw.Task;

/**
 *
 * @author  Robson
 */
public class TaskEditPane extends javax.swing.JPanel {

    /** Creates new form TaskEditPane */
    public TaskEditPane() {
        initComponents();
    }

    public TaskEditPane(Task aTask) {
        initComponents();
        setTask(aTask);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelProject = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooserStart = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldEstimation = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldMood = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneDescription = new javax.swing.JTextPane();
        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jProject = new wdp.project.JProject();
        jDateChooserFinish = new wdp.beans.JDateTimeChooser();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(TaskEditPane.class);
        jLabelProject.setText(resourceMap.getString("jLabelProject.text")); // NOI18N
        jLabelProject.setName("jLabelProject"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jDateChooserStart.setDateFormatString(resourceMap.getString("jDateChooserStart.dateFormatString")); // NOI18N
        jDateChooserStart.setName("jDateChooserStart"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextFieldEstimation.setText(resourceMap.getString("jTextFieldEstimation.text")); // NOI18N
        jTextFieldEstimation.setName("jTextFieldEstimation"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextFieldMood.setText(resourceMap.getString("jTextFieldMood.text")); // NOI18N
        jTextFieldMood.setName("jTextFieldMood"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextPaneDescription.setName("jTextPaneDescription"); // NOI18N
        jScrollPane1.setViewportView(jTextPaneDescription);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getActionMap(TaskEditPane.class, this);
        jButtonCancel.setAction(actionMap.get("cancel")); // NOI18N
        jButtonCancel.setText(resourceMap.getString("jButtonCancel.text")); // NOI18N
        jButtonCancel.setName("jButtonCancel"); // NOI18N

        jButtonOk.setAction(actionMap.get("acceptValue")); // NOI18N
        jButtonOk.setText(resourceMap.getString("jButtonOk.text")); // NOI18N
        jButtonOk.setName("jButtonOk"); // NOI18N

        jProject.setName("jProject"); // NOI18N

        jDateChooserFinish.setName("jDateChooserFinish"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabelProject))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserStart, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jTextFieldEstimation, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jTextFieldMood, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jProject, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                            .addComponent(jDateChooserFinish, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelProject)
                    .addComponent(jProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooserStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooserFinish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldEstimation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldMood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private wdp.beans.JDateTimeChooser jDateChooserFinish;
    private com.toedter.calendar.JDateChooser jDateChooserStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelProject;
    private wdp.project.JProject jProject;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldEstimation;
    private javax.swing.JTextField jTextFieldMood;
    private javax.swing.JTextPane jTextPaneDescription;
    // End of variables declaration//GEN-END:variables
    private Task value = null;
    private SelectWinExitState exitState = SelectWinExitState.CANCEL;

    private void setTask(Task aTask) {
        if (aTask != null) {
            value = aTask;
            //jTextFieldProject.setText(aTask.getIdProject().getName());
            jProject.setObject(aTask.getIdProject());
            jTextFieldEstimation.setText(aTask.getCurrentEstimation().toString());
            jTextFieldMood.setText(""+aTask.getMood());
            jDateChooserStart.setDate(aTask.getStarted());
            jDateChooserFinish.setDate(aTask.getFinish());
            jTextPaneDescription.setText(aTask.getDescription());
        } else {
            setTask(new Task());
        }

    }

    private Task getTask() {
        return value;
    }

    @Action
    public void acceptValue() {
        value.setCurrentEstimation(Integer.parseInt(jTextFieldEstimation.getText()));
        value.setMood(Integer.parseInt(jTextFieldMood.getText()));
        if (jTextPaneDescription.getText().length() > 0) {
            value.setDescription(jTextPaneDescription.getText());
        } else {
            if (value.getIdProject().getRecurring() == 1) {
                value.setDescription("Recurring");
            }
        }
        value.setStarted(jDateChooserStart.getDate());
        value.setFinish(jDateChooserFinish.getDate());
        exitState = SelectWinExitState.ACCEPT;
        this.setVisible(false);
        firePropertyChange("visible", true, false);
    }

    @Action
    public void cancel() {
        value = null;
        exitState = SelectWinExitState.CANCEL;
        this.setVisible(false);
        firePropertyChange("visible", true, false);
    }

    public static Task showTaskEditWindow(Task aTask) {
        TaskEditPane pane = new TaskEditPane(aTask);
        final JDialog frame = new JDialog();
        frame.setContentPane(pane);
        frame.pack();
        frame.setModal(true);
        frame.setLocationRelativeTo(WdpApp.getApplication().getMainFrame());
        pane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("visible")) {
                    System.out.println("panel niewidoczny: ");
                    frame.setVisible(false);
                }
            }
        });
        WdpApp.getApplication().show(frame);
        if(pane.exitState == SelectWinExitState.ACCEPT)
            return pane.getTask();
        else
            return null;
    }
    
}
