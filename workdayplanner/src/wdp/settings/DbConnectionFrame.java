/*
 * DbConnectionFrame.java
 * $Id$
 * Created on 2008-12-04, 15:06:09
 */

package wdp.settings;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;
import org.jdesktop.application.Action;
import org.xml.sax.SAXException;

/**
 *
 * @author robson
 */
public class DbConnectionFrame extends javax.swing.JPanel {

    /** Creates new form DbConnectionFrame */
    public DbConnectionFrame() {
        initComponents();
        readDbSettings();
        jTextFieldDbUrl.setText(document.getDbUrl());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldDbUrl = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();

        setName("Form"); // NOI18N

        jLabel1.setName("jLabel1"); // NOI18N

        jTextFieldDbUrl.setName("jTextFieldDbUrl"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(wdp.WdpApp.class).getContext().getActionMap(DbConnectionFrame.class, this);
        jButtonSave.setAction(actionMap.get("saveDbConnection")); // NOI18N
        jButtonSave.setName("jButtonSave"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDbUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                    .addComponent(jButtonSave, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void saveDbConnection() {
        document.setDbUrl(jTextFieldDbUrl.getText());
        document.save();
        this.setVisible(false);
    }

    /**
     * Reads persistence.xml
     */
    public void readDbSettings() {
        try {
            document = new PersistenceXmlDocument();
            document.read();
        } catch (ParserConfigurationException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            log.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new DbConnectionFrame());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldDbUrl;
    // End of variables declaration//GEN-END:variables
    private static Logger log = Logger.getLogger(DbConnectionFrame.class.getName());
    private PersistenceXmlDocument document = null;
    
}