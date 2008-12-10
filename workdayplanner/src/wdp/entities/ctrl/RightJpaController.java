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
import wdp.entities.raw.Operation;
import wdp.entities.raw.Project;
import wdp.entities.raw.Right;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class RightJpaController {

    public RightJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Right right) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operation idOperation = right.getIdOperation();
            if (idOperation != null) {
                idOperation = em.getReference(idOperation.getClass(), idOperation.getId());
                right.setIdOperation(idOperation);
            }
            Project idProject = right.getIdProject();
            if (idProject != null) {
                idProject = em.getReference(idProject.getClass(), idProject.getId());
                right.setIdProject(idProject);
            }
            Worker idWorker = right.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                right.setIdWorker(idWorker);
            }
            em.persist(right);
            if (idOperation != null) {
                idOperation.getRightCollection().add(right);
                idOperation = em.merge(idOperation);
            }
            if (idProject != null) {
                idProject.getRightCollection().add(right);
                idProject = em.merge(idProject);
            }
            if (idWorker != null) {
                idWorker.getRightCollection().add(right);
                idWorker = em.merge(idWorker);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRight(right.getId()) != null) {
                throw new PreexistingEntityException("Right " + right + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Right right) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Right persistentRight = em.find(Right.class, right.getId());
            Operation idOperationOld = persistentRight.getIdOperation();
            Operation idOperationNew = right.getIdOperation();
            Project idProjectOld = persistentRight.getIdProject();
            Project idProjectNew = right.getIdProject();
            Worker idWorkerOld = persistentRight.getIdWorker();
            Worker idWorkerNew = right.getIdWorker();
            if (idOperationNew != null) {
                idOperationNew = em.getReference(idOperationNew.getClass(), idOperationNew.getId());
                right.setIdOperation(idOperationNew);
            }
            if (idProjectNew != null) {
                idProjectNew = em.getReference(idProjectNew.getClass(), idProjectNew.getId());
                right.setIdProject(idProjectNew);
            }
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                right.setIdWorker(idWorkerNew);
            }
            right = em.merge(right);
            if (idOperationOld != null && !idOperationOld.equals(idOperationNew)) {
                idOperationOld.getRightCollection().remove(right);
                idOperationOld = em.merge(idOperationOld);
            }
            if (idOperationNew != null && !idOperationNew.equals(idOperationOld)) {
                idOperationNew.getRightCollection().add(right);
                idOperationNew = em.merge(idOperationNew);
            }
            if (idProjectOld != null && !idProjectOld.equals(idProjectNew)) {
                idProjectOld.getRightCollection().remove(right);
                idProjectOld = em.merge(idProjectOld);
            }
            if (idProjectNew != null && !idProjectNew.equals(idProjectOld)) {
                idProjectNew.getRightCollection().add(right);
                idProjectNew = em.merge(idProjectNew);
            }
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getRightCollection().remove(right);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getRightCollection().add(right);
                idWorkerNew = em.merge(idWorkerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = right.getId();
                if (findRight(id) == null) {
                    throw new NonexistentEntityException("The right with id " + id + " no longer exists.");
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
            Right right;
            try {
                right = em.getReference(Right.class, id);
                right.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The right with id " + id + " no longer exists.", enfe);
            }
            Operation idOperation = right.getIdOperation();
            if (idOperation != null) {
                idOperation.getRightCollection().remove(right);
                idOperation = em.merge(idOperation);
            }
            Project idProject = right.getIdProject();
            if (idProject != null) {
                idProject.getRightCollection().remove(right);
                idProject = em.merge(idProject);
            }
            Worker idWorker = right.getIdWorker();
            if (idWorker != null) {
                idWorker.getRightCollection().remove(right);
                idWorker = em.merge(idWorker);
            }
            em.remove(right);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Right> findRightEntities() {
        return findRightEntities(true, -1, -1);
    }

    public List<Right> findRightEntities(int maxResults, int firstResult) {
        return findRightEntities(false, maxResults, firstResult);
    }

    private List<Right> findRightEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Right as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Right findRight(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Right.class, id);
        } finally {
            em.close();
        }
    }

    public int getRightCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Right as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
