/*
 * $Id$
 * JDateTimeChooser.java
 * Created on 5 październik 2008, 08:44
 */

package wdp.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JPopupMenu;
import org.jdesktop.application.Action;

/**
 *
 * @author  robson
 */
public class JDateTimeChooser extends javax.swing.JPanel {

    /** Creates new form JDateTimeChooser */
    public JDateTimeChooser() {
        initComponents();
    }

    public Date getDate() {
        return selectedDate;
    }

    public void setDate(Date finish) {
        this.selectedDate = finish;
        jTextFieldDateEditor.setDate(finish);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSelect = new javax.swing.JButton();
        jTextFieldDateEditor = new com.toedter.calendar.JTextFieldDateEditor();

        setName("Form"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getActionMap(JDateTimeChooser.class, this);
        jButtonSelect.setAction(actionMap.get("selectDateTime")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getResourceMap(JDateTimeChooser.class);
        jButtonSelect.setText(resourceMap.getString("jButtonSelect.text")); // NOI18N
        jButtonSelect.setName("jButtonSelect"); // NOI18N
        jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectActionPerformed(evt);
            }
        });

        jTextFieldDateEditor.setDateFormatString(resourceMap.getString("jTextFieldDateEditor.dateFormatString")); // NOI18N
        jTextFieldDateEditor.setName("jTextFieldDateEditor"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTextFieldDateEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextFieldDateEditor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButtonSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectActionPerformed
  
}//GEN-LAST:event_jButtonSelectActionPerformed

    @Action
    public void selectDateTime() {
        popup = new JPopupMenu();
        popup.setLightWeightPopupEnabled(true);
        dateTimePanel = new JDateTimePanel(selectedDate);
        dateTimePanel.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                log.info("Data zmieniona na : "+evt.getNewValue());
                selectedDate = (Date) evt.getNewValue();
                jTextFieldDateEditor.setDate((Date) evt.getNewValue());
                log.info("A wpisala sie jako : "+jTextFieldDateEditor.getDate());
                jTextFieldDateEditor.repaint();
                popup.setVisible(false);
            }
        });
        popup.add(dateTimePanel);
        int x = jButtonSelect.getWidth() - (int) popup.getPreferredSize().getWidth();
        int y = jButtonSelect.getY() + jButtonSelect.getHeight();
        popup.show(jButtonSelect, x, y);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelect;
    private com.toedter.calendar.JTextFieldDateEditor jTextFieldDateEditor;
    // End of variables declaration//GEN-END:variables
    private JPopupMenu popup = null;
    private JDateTimePanel dateTimePanel = null;
    private Date selectedDate = new Date();
    private static Logger log = Logger.getLogger(JDateTimeChooser.class.getName());
    
}
