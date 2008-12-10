/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.reports;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import wdp.entities.ctrl.WorkerJpaController;

/**
 *
 * @author robson
 */
public class ReportLauncher {

    public void run() {
        String fileName = "c:/Projects/workdayplanner/src/wdp/reports/reporttest.jasper";
        Map parameters = new HashMap();
        WorkerJpaController ctrl = new WorkerJpaController();
        EntityManager em = ctrl.getEntityManager();
        parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
        try {
            JasperRunManager.runReportToPdfFile(fileName, parameters);
        } catch (JRException ex) {
            Logger.getLogger(ReportLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
