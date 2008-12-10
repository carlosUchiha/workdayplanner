/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdp.entities.ctrl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import wdp.entities.ctrl.exceptions.NonexistentEntityException;
import wdp.entities.ctrl.exceptions.PreexistingEntityException;
import wdp.entities.raw.Project;
import wdp.entities.raw.Task;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class TaskJpaController {

    public TaskJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Task task) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project idProject = task.getIdProject();
            if (idProject != null) {
                idProject = em.getReference(idProject.getClass(), idProject.getId());
                task.setIdProject(idProject);
            }
            Worker idWorker = task.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                task.setIdWorker(idWorker);
            }
            em.persist(task);
            if (idProject != null) {
                idProject.getTaskCollection().add(task);
                idProject = em.merge(idProject);
            }
            if (idWorker != null) {
                idWorker.getTaskCollection().add(task);
                idWorker = em.merge(idWorker);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTask(task.getId()) != null) {
                throw new PreexistingEntityException("Task " + task + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Task task) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task persistentTask = em.find(Task.class, task.getId());
            Project idProjectOld = persistentTask.getIdProject();
            Project idProjectNew = task.getIdProject();
            Worker idWorkerOld = persistentTask.getIdWorker();
            Worker idWorkerNew = task.getIdWorker();
            if (idProjectNew != null) {
                idProjectNew = em.getReference(idProjectNew.getClass(), idProjectNew.getId());
                task.setIdProject(idProjectNew);
            }
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                task.setIdWorker(idWorkerNew);
            }
            task = em.merge(task);
            if (idProjectOld != null && !idProjectOld.equals(idProjectNew)) {
                idProjectOld.getTaskCollection().remove(task);
                idProjectOld = em.merge(idProjectOld);
            }
            if (idProjectNew != null && !idProjectNew.equals(idProjectOld)) {
                idProjectNew.getTaskCollection().add(task);
                idProjectNew = em.merge(idProjectNew);
            }
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getTaskCollection().remove(task);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getTaskCollection().add(task);
                idWorkerNew = em.merge(idWorkerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = task.getId();
                if (findTask(id) == null) {
                    throw new NonexistentEntityException("The task with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Task task;
            try {
                task = em.getReference(Task.class, id);
                task.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The task with id " + id + " no longer exists.", enfe);
            }
            Project idProject = task.getIdProject();
            if (idProject != null) {
                idProject.getTaskCollection().remove(task);
                idProject = em.merge(idProject);
            }
            Worker idWorker = task.getIdWorker();
            if (idWorker != null) {
                idWorker.getTaskCollection().remove(task);
                idWorker = em.merge(idWorker);
            }
            em.remove(task);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Task> findTaskEntities() {
        return findTaskEntities(true, -1, -1);
    }

    public List<Task> findTaskEntities(int maxResults, int firstResult) {
        return findTaskEntities(false, maxResults, firstResult);
    }

    private List<Task> findTaskEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Task as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Task findTask(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Task.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaskCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Task as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
