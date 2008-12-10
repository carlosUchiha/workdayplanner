/*
 * $Id$
 */
package wdp;

import java.io.FileInputStream;
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
    private static Logger log = Logger.getLogger(WdpApp.class.getName());

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        loadLoggerConfig();
        wdpMainWindow = new WdpMainWindow(this);
        show(wdpMainWindow);
    }

    protected void loadLoggerConfig() {
        String loggerCfgFile = "src/META-INF/logger.properties";
        try {
            InputStream is = new FileInputStream(loggerCfgFile);
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException ex) {
            log.log( Level.SEVERE, "Can't open logger config file: "+loggerCfgFile, ex);
        } catch (SecurityException ex) {
            log.log( Level.SEVERE, "Error reading logger config file: "+loggerCfgFile, ex);
        } catch (Exception ex) {
            log.log( Level.SEVERE, "Some error reading logger config file: "+loggerCfgFile, ex);
        }
        log.log( Level.SEVERE, "Logger level test: SEVERE");
        log.log( Level.WARNING, "Logger level test: WARNING");
        log.log( Level.INFO, "Logger level test: INFO");
        log.log( Level.FINE, "Logger level test: FINE");
        log.log( Level.FINER, "Logger level test: FINER");
        log.log( Level.FINEST, "Logger level test: FINEST");
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
