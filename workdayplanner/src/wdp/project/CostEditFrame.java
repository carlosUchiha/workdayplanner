/*
 * $Id$
 */
package wdp.project;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CostEditFrame extends JPanel {

    public CostEditFrame() {
        initComponents();
        TableSelectionListener listener = new TableSelectionListener();
        masterTable.getSelectionModel().addListSelectionListener(listener);
        entityManager.getTransaction().begin();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

    entityManager = javax.persistence.Persistence.createEntityManagerFactory("db.fdbPU").createEntityManager();
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(CostEditFrame.class);
    query = entityManager.createQuery(resourceMap.getString("query.query")); // NOI18N
    list = org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
    masterScrollPane = new javax.swing.JScrollPane();
    masterTable = new javax.swing.JTable();
    costLabel = new javax.swing.JLabel();
    nameLabel = new javax.swing.JLabel();
    includeLabel = new javax.swing.JLabel();
    costField = new javax.swing.JTextField();
    nameField = new javax.swing.JTextField();
    includeField = new javax.swing.JTextField();
    saveButton = new javax.swing.JButton();
    refreshButton = new javax.swing.JButton();
    newButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();

    FormListener formListener = new FormListener();

    setName("Form"); // NOI18N

    masterScrollPane.setName("masterScrollPane"); // NOI18N

    masterTable.setName("masterTable"); // NOI18N

    org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
    org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${cost}"));
    columnBinding.setColumnName("Cost");
    columnBinding.setColumnClass(Double.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
    columnBinding.setColumnName("Name");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${include}"));
    columnBinding.setColumnName("Include");
    columnBinding.setColumnClass(Short.class);
    bindingGroup.addBinding(jTableBinding);

    masterScrollPane.setViewportView(masterTable);

    costLabel.setText(resourceMap.getString("costLabel.text")); // NOI18N
    costLabel.setName("costLabel"); // NOI18N

    nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
    nameLabel.setName("nameLabel"); // NOI18N

    includeLabel.setText(resourceMap.getString("includeLabel.text")); // NOI18N
    includeLabel.setName("includeLabel"); // NOI18N

    costField.setName("costField"); // NOI18N

    org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.cost}"), costField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), costField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    nameField.setName("nameField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), nameField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    includeField.setName("includeField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.include}"), includeField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), includeField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
    saveButton.setName("saveButton"); // NOI18N
    saveButton.addActionListener(formListener);

    refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
    refreshButton.setName("refreshButton"); // NOI18N
    refreshButton.addActionListener(formListener);

    newButton.setText(resourceMap.getString("newButton.text")); // NOI18N
    newButton.setName("newButton"); // NOI18N
    newButton.addActionListener(formListener);

    deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
    deleteButton.setEnabled(false);
    deleteButton.setName("deleteButton"); // NOI18N
    deleteButton.addActionListener(formListener);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(newButton)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(deleteButton)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(refreshButton)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(saveButton)
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(costLabel)
          .addComponent(nameLabel)
          .addComponent(includeLabel))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(costField, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
          .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
          .addComponent(includeField, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteButton, newButton, refreshButton, saveButton});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(costLabel)
          .addComponent(costField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(nameLabel)
          .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(includeLabel)
          .addComponent(includeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(saveButton)
          .addComponent(refreshButton)
          .addComponent(deleteButton)
          .addComponent(newButton))
        .addContainerGap())
    );

    bindingGroup.bind();
  }

  // Code for dispatching events from components to event handlers.

  private class FormListener implements java.awt.event.ActionListener {
    FormListener() {}
    public void actionPerformed(java.awt.event.ActionEvent evt) {
      if (evt.getSource() == saveButton) {
        CostEditFrame.this.saveButtonActionPerformed(evt);
      }
      else if (evt.getSource() == refreshButton) {
        CostEditFrame.this.refreshButtonActionPerformed(evt);
      }
      else if (evt.getSource() == newButton) {
        CostEditFrame.this.newButtonActionPerformed(evt);
      }
      else if (evt.getSource() == deleteButton) {
        CostEditFrame.this.deleteButtonActionPerformed(evt);
      }
    }
  }// </editor-fold>//GEN-END:initComponents

    private class TableSelectionListener implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == masterTable.getSelectionModel()) {
                boolean enabled = (masterTable.getSelectedRow() != -1);
                deleteButton.setEnabled(enabled);
            }
        }
    }

	private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
            entityManager.getTransaction().rollback();
            entityManager.getTransaction().begin();
            list.clear();
            list.addAll(query.getResultList());
	}//GEN-LAST:event_refreshButtonActionPerformed

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
            int[] selected = masterTable.getSelectedRows();
            List<wdp.entities.ready.Cost> toRemove = new ArrayList<wdp.entities.ready.Cost>(selected.length);
            for (int idx = 0; idx < selected.length; idx++) {
                wdp.entities.ready.Cost c = list.get(masterTable.convertRowIndexToModel(selected[idx]));
                toRemove.add(c);
                entityManager.remove(c);
            }
            list.removeAll(toRemove);
	}//GEN-LAST:event_deleteButtonActionPerformed

	private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
            wdp.entities.ready.Cost c = new wdp.entities.ready.Cost();
            entityManager.persist(c);
            list.add(c);
            int row = list.size() - 1;
            masterTable.setRowSelectionInterval(row, row);
            masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
	}//GEN-LAST:event_newButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
	}//GEN-LAST:event_saveButtonActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField costField;
  private javax.swing.JLabel costLabel;
  private javax.swing.JButton deleteButton;
  private javax.persistence.EntityManager entityManager;
  private javax.swing.JTextField includeField;
  private javax.swing.JLabel includeLabel;
  private java.util.List<wdp.entities.ready.Cost> list;
  private javax.swing.JScrollPane masterScrollPane;
  private javax.swing.JTable masterTable;
  private javax.swing.JTextField nameField;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JButton newButton;
  private javax.persistence.Query query;
  private javax.swing.JButton refreshButton;
  private javax.swing.JButton saveButton;
  private org.jdesktop.beansbinding.BindingGroup bindingGroup;
  // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new CostEditFrame());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
