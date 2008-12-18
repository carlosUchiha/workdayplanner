/*
 * $Id$
 * JTimePanel.java
 * Created on 13 lipiec 2008, 10:27
 */

package wdp.beans;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author  robson
 */
public class JTimeChooser extends javax.swing.JPanel {
    private static final long serialVersionUID = 8913369762644440134L;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;

    /** Creates new form JTimePanel */
    public JTimeChooser() {
        setName("JTimeChooser");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        jSpinFieldHour.setValue(calendar.get(Calendar.HOUR));
        jSpinFieldMinute.setValue(calendar.get(Calendar.MINUTE));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jSpinFieldHour = new com.toedter.components.JSpinField();
        jSpinFieldMinute = new com.toedter.components.JSpinField();

        setName("Form"); // NOI18N
        setLayout(new java.awt.GridBagLayout());

        jSpinFieldHour.setMaximum(24);
        jSpinFieldHour.setMinimum(0);
        jSpinFieldHour.setName("jSpinFieldHour"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSpinFieldHour, gridBagConstraints);

        jSpinFieldMinute.setMaximum(59);
        jSpinFieldMinute.setMinimum(0);
        jSpinFieldMinute.setName("jSpinFieldMinute"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(jSpinFieldMinute, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField jSpinFieldHour;
    private com.toedter.components.JSpinField jSpinFieldMinute;
    // End of variables declaration//GEN-END:variables

    public void setDate(Date aDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(aDate);
        setHour(calendar.get(Calendar.HOUR_OF_DAY));
        setMinute(calendar.get(Calendar.MINUTE));
        setSecond(calendar.get(Calendar.SECOND));
    }
    
    public void setTime(int hour, int minute) {
        setHour(hour);
        setMinute(minute);
    }
    
    public void setTime(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }
    
    private void setHour(int hour) {
        if(hour>=0 && hour <24) {
            int oldHour = this.hour;
            this.hour = hour;
            jSpinFieldHour.setValue(hour);
            firePropertyChange("hour", oldHour, hour);
        }
    }
    
    private void setMinute(int minute) {
        if(hour>=0 && hour <=59) {
            int oldMinute = this.minute;
            this.minute = minute;
            jSpinFieldMinute.setValue(minute);
            firePropertyChange("minute", oldMinute, minute);
        }
    }
    
    private void setSecond(int second) {
        if(hour>=0 && hour <=59) {
            int oldSecond = this.second;
            this.second = second;
            firePropertyChange("second", oldSecond, second);
        }
    }
    
    public int getMinute() {
        return jSpinFieldMinute.getValue();
    }
    
    public int getHour() {
        return jSpinFieldHour.getValue();
    }
    
    public int getSecond() {
        return second;
    }
    
    static public void main(String[] s) {
        JFrame frame = new JFrame("JTimeChooser");
        frame.getContentPane().add(new JTimeChooser());
        frame.pack();
        frame.setVisible(true);
    }    
}