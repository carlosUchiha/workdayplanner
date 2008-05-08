/*
 * SysTtrayMenu.java
 * Created: 2007-03-18 WorkDayPlanner
 * $Id$
 */
package wdp;

import java.awt.MenuItem;
import java.awt.PopupMenu;

/**
 * Menu for systray icon
 * @author Robson
 */
public class SysTrayMenu extends PopupMenu {

    private static final long serialVersionUID = 2263407029875065678L;
    private MenuItem jMenuItemRestore = null;  //  @jve:decl-index=0:

    private MenuItem jMenuItemExit = null;  //  @jve:decl-index=0:


    /**
     * 
     */
    public SysTrayMenu() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     */
    private void initialize() {
        this.add(getJMenuItemRestore());  // Generated

        this.add(getJMenuItemExit());  // Generated

        this.setLabel("WorkDayPlanner");
    }

    /**
     * This method initializes jMenuItemRestore
     * @return javax.swing.JMenuItem
     */
    private MenuItem getJMenuItemRestore() {
        if (jMenuItemRestore == null) {
            jMenuItemRestore = new MenuItem();
            jMenuItemRestore.setLabel("Restore");
            jMenuItemRestore.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //WdpApp.getApplication().
                }
            });
        }
        return jMenuItemRestore;
    }

    /**
     * This method initializes jMenuItemExit
     * @return javax.swing.JMenuItem
     */
    private MenuItem getJMenuItemExit() {
        if (jMenuItemExit == null) {
            jMenuItemExit = new MenuItem();
            //jMenuItemExit.setIcon(IconMgr.getExit());
            jMenuItemExit.setLabel("Exit");
            jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //WorkDayPlanner.getInstance().exitProgram();
                }
            });
        }
        return jMenuItemExit;
    }
}
