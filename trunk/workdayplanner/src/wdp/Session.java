/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp;

import java.util.Date;
import javax.persistence.EntityManager;
import wdp.entities.Worker;

/**
 * @author Robson
 */
public class Session {
  private Date start = new Date();
  private Date finish = null;
  private Worker worker = null;
  
  Session() { 
    EntityManager entityManager = javax.persistence.Persistence.createEntityManagerFactory("db.fdbPU").createEntityManager();
    javax.persistence.Query query = entityManager.createNamedQuery("Worker.findById"); // NOI18N
    query.setParameter("id", 1);
    worker = (Worker) query.getSingleResult();
  }

  public Date getStart() {
    return start;
  }

  public Date getFinish() {
    return finish;
  }

  public void setFinish(Date finish) {
    this.finish = finish;
  }

  public Worker getWorker() {
    return worker;
  }
}
