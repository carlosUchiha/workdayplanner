/*
 * $Id$
 */
package wdp;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class WdpApp extends SingleFrameApplication {

    private Session session = null;
    private WdpMainWindow wdpMainWindow = null;

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        try {
             InputStream is = getClass().getResourceAsStream("META-INF/logger.properties");
            //LogManager.getLogManager().readConfiguration(str);
            wdpMainWindow = new WdpMainWindow(this);
            show(wdpMainWindow);
        } catch (SecurityException ex) {
            Logger.getLogger(WdpApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of WdpApp
     */
    public static WdpApp getApplication() {
        return Application.getInstance(WdpApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(WdpApp.class, args);
    }

    public Session getSession() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }
}
