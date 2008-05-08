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

public class ProjectEditFrame extends JPanel {

    public ProjectEditFrame() {
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
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(ProjectEditFrame.class);
    query = entityManager.createQuery(resourceMap.getString("query.query")); // NOI18N
    list = org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
    masterScrollPane = new javax.swing.JScrollPane();
    masterTable = new javax.swing.JTable();
    nameLabel = new javax.swing.JLabel();
    descriptionLabel = new javax.swing.JLabel();
    documentationLabel = new javax.swing.JLabel();
    priorityLabel = new javax.swing.JLabel();
    completedLabel = new javax.swing.JLabel();
    recurringLabel = new javax.swing.JLabel();
    startLabel = new javax.swing.JLabel();
    finishLabel = new javax.swing.JLabel();
    deadlineLabel = new javax.swing.JLabel();
    estimationLabel = new javax.swing.JLabel();
    idParrentLabel = new javax.swing.JLabel();
    idCostLabel = new javax.swing.JLabel();
    nameField = new javax.swing.JTextField();
    descriptionField = new javax.swing.JTextField();
    documentationField = new javax.swing.JTextField();
    priorityField = new javax.swing.JTextField();
    completedField = new javax.swing.JTextField();
    recurringField = new javax.swing.JTextField();
    startField = new javax.swing.JTextField();
    finishField = new javax.swing.JTextField();
    deadlineField = new javax.swing.JTextField();
    estimationField = new javax.swing.JTextField();
    idParrentField = new javax.swing.JTextField();
    idCostField = new javax.swing.JTextField();
    saveButton = new javax.swing.JButton();
    refreshButton = new javax.swing.JButton();
    newButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();

    FormListener formListener = new FormListener();

    setName("Form"); // NOI18N

    masterScrollPane.setName("masterScrollPane"); // NOI18N

    masterTable.setName("masterTable"); // NOI18N

    org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
    org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
    columnBinding.setColumnName("Name");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${priority}"));
    columnBinding.setColumnName("Priority");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${completed}"));
    columnBinding.setColumnName("Completed");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${recurring}"));
    columnBinding.setColumnName("Recurring");
    columnBinding.setColumnClass(Short.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${start}"));
    columnBinding.setColumnName("Start");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${finish}"));
    columnBinding.setColumnName("Finish");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${deadline}"));
    columnBinding.setColumnName("Deadline");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${estimation}"));
    columnBinding.setColumnName("Estimation");
    columnBinding.setColumnClass(Integer.class);
    bindingGroup.addBinding(jTableBinding);
    jTableBinding.bind();
    masterScrollPane.setViewportView(masterTable);

    nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
    nameLabel.setName("nameLabel"); // NOI18N

    descriptionLabel.setText(resourceMap.getString("descriptionLabel.text")); // NOI18N
    descriptionLabel.setName("descriptionLabel"); // NOI18N

    documentationLabel.setText(resourceMap.getString("documentationLabel.text")); // NOI18N
    documentationLabel.setName("documentationLabel"); // NOI18N

    priorityLabel.setText(resourceMap.getString("priorityLabel.text")); // NOI18N
    priorityLabel.setName("priorityLabel"); // NOI18N

    completedLabel.setText(resourceMap.getString("completedLabel.text")); // NOI18N
    completedLabel.setName("completedLabel"); // NOI18N

    recurringLabel.setText(resourceMap.getString("recurringLabel.text")); // NOI18N
    recurringLabel.setName("recurringLabel"); // NOI18N

    startLabel.setText(resourceMap.getString("startLabel.text")); // NOI18N
    startLabel.setName("startLabel"); // NOI18N

    finishLabel.setText(resourceMap.getString("finishLabel.text")); // NOI18N
    finishLabel.setName("finishLabel"); // NOI18N

    deadlineLabel.setText(resourceMap.getString("deadlineLabel.text")); // NOI18N
    deadlineLabel.setName("deadlineLabel"); // NOI18N

    estimationLabel.setText(resourceMap.getString("estimationLabel.text")); // NOI18N
    estimationLabel.setName("estimationLabel"); // NOI18N

    idParrentLabel.setText(resourceMap.getString("idParrentLabel.text")); // NOI18N
    idParrentLabel.setName("idParrentLabel"); // NOI18N

    idCostLabel.setText(resourceMap.getString("idCostLabel.text")); // NOI18N
    idCostLabel.setName("idCostLabel"); // NOI18N

    nameField.setName("nameField"); // NOI18N

    org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), nameField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    descriptionField.setName("descriptionField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), descriptionField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    documentationField.setName("documentationField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.documentation}"), documentationField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), documentationField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    priorityField.setName("priorityField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.priority}"), priorityField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), priorityField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    completedField.setName("completedField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.completed}"), completedField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), completedField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    recurringField.setName("recurringField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.recurring}"), recurringField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), recurringField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    startField.setName("startField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.start}"), startField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), startField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    finishField.setName("finishField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.finish}"), finishField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), finishField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    deadlineField.setName("deadlineField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.deadline}"), deadlineField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deadlineField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    estimationField.setName("estimationField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.estimation}"), estimationField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), estimationField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    idParrentField.setName("idParrentField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.idParrent}"), idParrentField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), idParrentField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    idCostField.setName("idCostField"); // NOI18N

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.idCost}"), idCostField, org.jdesktop.beansbinding.BeanProperty.create("text"));
    binding.setSourceUnreadableValue(null);
    bindingGroup.addBinding(binding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), idCostField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
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
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(newButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(refreshButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(saveButton))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(nameLabel)
              .addComponent(descriptionLabel)
              .addComponent(documentationLabel)
              .addComponent(priorityLabel)
              .addComponent(completedLabel)
              .addComponent(recurringLabel)
              .addComponent(startLabel)
              .addComponent(finishLabel)
              .addComponent(deadlineLabel)
              .addComponent(estimationLabel)
              .addComponent(idParrentLabel)
              .addComponent(idCostLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(descriptionField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(documentationField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(priorityField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(completedField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(recurringField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(startField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(finishField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(deadlineField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(estimationField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(idParrentField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
              .addComponent(idCostField, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)))
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteButton, newButton, refreshButton, saveButton});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(nameLabel)
          .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(descriptionLabel)
          .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(documentationLabel)
          .addComponent(documentationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(priorityLabel)
          .addComponent(priorityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(completedLabel)
          .addComponent(completedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(recurringLabel)
          .addComponent(recurringField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(startLabel)
          .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(finishLabel)
          .addComponent(finishField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deadlineLabel)
          .addComponent(deadlineField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(estimationLabel)
          .addComponent(estimationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(idParrentLabel)
          .addComponent(idParrentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(idCostLabel)
          .addComponent(idCostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ProjectEditFrame.this.saveButtonActionPerformed(evt);
      }
      else if (evt.getSource() == refreshButton) {
        ProjectEditFrame.this.refreshButtonActionPerformed(evt);
      }
      else if (evt.getSource() == newButton) {
        ProjectEditFrame.this.newButtonActionPerformed(evt);
      }
      else if (evt.getSource() == deleteButton) {
        ProjectEditFrame.this.deleteButtonActionPerformed(evt);
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
            List<wdp.entities.Project> toRemove = new ArrayList<wdp.entities.Project>(selected.length);
            for (int idx = 0; idx < selected.length; idx++) {
                wdp.entities.Project p = list.get(masterTable.convertRowIndexToModel(selected[idx]));
                toRemove.add(p);
                entityManager.remove(p);
            }
            list.removeAll(toRemove);
	}//GEN-LAST:event_deleteButtonActionPerformed

	private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
            wdp.entities.Project p = new wdp.entities.Project();
            entityManager.persist(p);
            list.add(p);
            int row = list.size() - 1;
            masterTable.setRowSelectionInterval(row, row);
            masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
	}//GEN-LAST:event_newButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
	}//GEN-LAST:event_saveButtonActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField completedField;
  private javax.swing.JLabel completedLabel;
  private javax.swing.JTextField deadlineField;
  private javax.swing.JLabel deadlineLabel;
  private javax.swing.JButton deleteButton;
  private javax.swing.JTextField descriptionField;
  private javax.swing.JLabel descriptionLabel;
  private javax.swing.JTextField documentationField;
  private javax.swing.JLabel documentationLabel;
  private javax.persistence.EntityManager entityManager;
  private javax.swing.JTextField estimationField;
  private javax.swing.JLabel estimationLabel;
  private javax.swing.JTextField finishField;
  private javax.swing.JLabel finishLabel;
  private javax.swing.JTextField idCostField;
  private javax.swing.JLabel idCostLabel;
  private javax.swing.JTextField idParrentField;
  private javax.swing.JLabel idParrentLabel;
  private java.util.List<wdp.entities.Project> list;
  private javax.swing.JScrollPane masterScrollPane;
  private javax.swing.JTable masterTable;
  private javax.swing.JTextField nameField;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JButton newButton;
  private javax.swing.JTextField priorityField;
  private javax.swing.JLabel priorityLabel;
  private javax.persistence.Query query;
  private javax.swing.JTextField recurringField;
  private javax.swing.JLabel recurringLabel;
  private javax.swing.JButton refreshButton;
  private javax.swing.JButton saveButton;
  private javax.swing.JTextField startField;
  private javax.swing.JLabel startLabel;
  private org.jdesktop.beansbinding.BindingGroup bindingGroup;
  // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new ProjectEditFrame());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
