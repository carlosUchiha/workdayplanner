/*
 * $Id$
 * Created on 24 luty 2008, 16:30
 */
package wdp.project;

import java.awt.EventQueue;
import java.util.Date;
import javax.swing.JFrame;
import wdp.WdpApp;
import wdp.entities.ready.Project;
import wdp.entities.ready.Task;

/**
 *
 * @author  Robson
 */
public class ProjectAndTaskFrame extends javax.swing.JPanel {

    /** Creates new form ProjectAndTaskFrame */
    public ProjectAndTaskFrame() {
        initComponents();
        initProjectsPanel();
        initTasksPanel();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPaneVert = new javax.swing.JSplitPane();
    jSplitPaneHorizon = new javax.swing.JSplitPane();
    jPanel1 = new javax.swing.JPanel();
    jPanelButtons = new javax.swing.JPanel();
    jButtonAddTaskToList = new javax.swing.JButton();
    jPanelProjectFields = new javax.swing.JPanel();
    jLabelProjectDescription = new javax.swing.JLabel();
    jScrollPaneProjectDescription = new javax.swing.JScrollPane();
    jTextAreaProjectDescription = new javax.swing.JTextArea();
    jTextFieldProjectPriority = new javax.swing.JTextField();
    jLabelProjectRecurring = new javax.swing.JLabel();
    jCheckBoxProjectRecurring = new javax.swing.JCheckBox();
    jScrollPaneTaskList = new javax.swing.JScrollPane();
    jPanelProjectFake = new javax.swing.JPanel();

    setName("Form"); // NOI18N

    jSplitPaneVert.setName("jSplitPaneVert"); // NOI18N
    jSplitPaneVert.setOneTouchExpandable(true);

    jSplitPaneHorizon.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
    jSplitPaneHorizon.setName("jSplitPaneHorizon"); // NOI18N
    jSplitPaneHorizon.setOneTouchExpandable(true);

    jPanel1.setName("jPanel1"); // NOI18N
    jPanel1.setLayout(new java.awt.BorderLayout());

    jPanelButtons.setName("jPanelButtons"); // NOI18N

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(ProjectAndTaskFrame.class);
    jButtonAddTaskToList.setText(resourceMap.getString("jButtonAddTaskToList.text")); // NOI18N
    jButtonAddTaskToList.setName("jButtonAddTaskToList"); // NOI18N
    jButtonAddTaskToList.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonAddTaskToListActionPerformed(evt);
      }
    });
    jPanelButtons.add(jButtonAddTaskToList);

    jPanel1.add(jPanelButtons, java.awt.BorderLayout.CENTER);

    jPanelProjectFields.setName("jPanelProjectFields"); // NOI18N

    jLabelProjectDescription.setText(resourceMap.getString("jLabelProjectDescription.text")); // NOI18N
    jLabelProjectDescription.setName("jLabelProjectDescription"); // NOI18N

    jScrollPaneProjectDescription.setName("jScrollPaneProjectDescription"); // NOI18N

    jTextAreaProjectDescription.setColumns(20);
    jTextAreaProjectDescription.setRows(5);
    jTextAreaProjectDescription.setName("jTextAreaProjectDescription"); // NOI18N
    jScrollPaneProjectDescription.setViewportView(jTextAreaProjectDescription);

    jTextFieldProjectPriority.setText(resourceMap.getString("jTextFieldProjectPriority.text")); // NOI18N
    jTextFieldProjectPriority.setName("jTextFieldProjectPriority"); // NOI18N

    jLabelProjectRecurring.setText(resourceMap.getString("jLabelProjectRecurring.text")); // NOI18N
    jLabelProjectRecurring.setName("jLabelProjectRecurring"); // NOI18N

    jCheckBoxProjectRecurring.setText(resourceMap.getString("jCheckBoxProjectRecurring.text")); // NOI18N
    jCheckBoxProjectRecurring.setName("jCheckBoxProjectRecurring"); // NOI18N

    javax.swing.GroupLayout jPanelProjectFieldsLayout = new javax.swing.GroupLayout(jPanelProjectFields);
    jPanelProjectFields.setLayout(jPanelProjectFieldsLayout);
    jPanelProjectFieldsLayout.setHorizontalGroup(
      jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelProjectFieldsLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabelProjectDescription)
          .addComponent(jLabelProjectRecurring))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPaneProjectDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
          .addComponent(jTextFieldProjectPriority, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
          .addComponent(jCheckBoxProjectRecurring, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanelProjectFieldsLayout.setVerticalGroup(
      jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelProjectFieldsLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabelProjectDescription)
          .addComponent(jScrollPaneProjectDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanelProjectFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTextFieldProjectPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabelProjectRecurring))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jCheckBoxProjectRecurring)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel1.add(jPanelProjectFields, java.awt.BorderLayout.PAGE_START);

    jSplitPaneHorizon.setLeftComponent(jPanel1);

    jScrollPaneTaskList.setName("jScrollPaneTaskList"); // NOI18N
    jSplitPaneHorizon.setBottomComponent(jScrollPaneTaskList);

    jSplitPaneVert.setRightComponent(jSplitPaneHorizon);

    jPanelProjectFake.setName("jPanelProjectFake"); // NOI18N

    javax.swing.GroupLayout jPanelProjectFakeLayout = new javax.swing.GroupLayout(jPanelProjectFake);
    jPanelProjectFake.setLayout(jPanelProjectFakeLayout);
    jPanelProjectFakeLayout.setHorizontalGroup(
      jPanelProjectFakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanelProjectFakeLayout.setVerticalGroup(
      jPanelProjectFakeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 419, Short.MAX_VALUE)
    );

    jSplitPaneVert.setLeftComponent(jPanelProjectFake);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPaneVert, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jSplitPaneVert, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonAddTaskToListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTaskToListActionPerformed
      addTask();
  }//GEN-LAST:event_jButtonAddTaskToListActionPerformed

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new ProjectAndTaskFrame());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonAddTaskToList;
  private javax.swing.JCheckBox jCheckBoxProjectRecurring;
  private javax.swing.JLabel jLabelProjectDescription;
  private javax.swing.JLabel jLabelProjectRecurring;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanelButtons;
  private javax.swing.JPanel jPanelProjectFake;
  private javax.swing.JPanel jPanelProjectFields;
  private javax.swing.JScrollPane jScrollPaneProjectDescription;
  private javax.swing.JScrollPane jScrollPaneTaskList;
  private javax.swing.JSplitPane jSplitPaneHorizon;
  private javax.swing.JSplitPane jSplitPaneVert;
  private javax.swing.JTextArea jTextAreaProjectDescription;
  private javax.swing.JTextField jTextFieldProjectPriority;
  // End of variables declaration//GEN-END:variables
    private ProjectTreePane projectTreePane = null;
    private TaskListPane taskListPane = null;

    private void initProjectsPanel() {
        projectTreePane = new ProjectTreePane();
        jSplitPaneVert.setLeftComponent(projectTreePane);
        projectTreePane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("selected")) {
                    System.out.println("New value: " + ((Project) evt.getNewValue()).toString());
                    projectSelected((Project) evt.getNewValue());
                }
            }
        });
        projectTreePane.getJTreeProjects().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() >= 2) {
                    if(projectTreePane.getSelected() != null)
                        addTask();
                }
            }
        });
    }

    private void initTasksPanel() {
        taskListPane = new TaskListPane();
        jScrollPaneTaskList.setViewportView(taskListPane);
    }

    private void projectSelected(Project aProject) {
        jTextAreaProjectDescription.setText(aProject.getDescription());
        jTextFieldProjectPriority.setText(aProject.getPriority().toString());
    }

    private void addTask() {
        Task aTask = new Task(projectTreePane.getSelected());
        Task lastTask = taskListPane.getLastTask();
        Date startDate = null;
        if(lastTask!=null) {
            startDate = lastTask.getFinish();
        } else {
            startDate = new Date();
        }
        aTask.setStarted(startDate);
        aTask.setIdWorker(WdpApp.getApplication().getSession().getWorker());
        Task tmpTask = TaskEditPane.showTaskEditWindow(aTask);
        if(tmpTask != null)
            taskListPane.addTask(aTask);
    }
}
