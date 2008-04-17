/*
 * WdpApp.java
 */

package wdp;

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
      wdpMainWindow = new WdpMainWindow(this);
      show(wdpMainWindow);
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
      if(session == null) {
        session = new Session();
      }
      return session;
    }
}
