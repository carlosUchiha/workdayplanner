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
import wdp.entities.raw.Right;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author robson
 */
public class OperationJpaController {

    public OperationJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operation operation) throws PreexistingEntityException, Exception {
        if (operation.getRightCollection() == null) {
            operation.setRightCollection(new ArrayList<Right>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Right> attachedRightCollection = new ArrayList<Right>();
            for (Right rightCollectionRightToAttach : operation.getRightCollection()) {
                rightCollectionRightToAttach = em.getReference(rightCollectionRightToAttach.getClass(), rightCollectionRightToAttach.getId());
                attachedRightCollection.add(rightCollectionRightToAttach);
            }
            operation.setRightCollection(attachedRightCollection);
            em.persist(operation);
            for (Right rightCollectionRight : operation.getRightCollection()) {
                Operation oldIdOperationOfRightCollectionRight = rightCollectionRight.getIdOperation();
                rightCollectionRight.setIdOperation(operation);
                rightCollectionRight = em.merge(rightCollectionRight);
                if (oldIdOperationOfRightCollectionRight != null) {
                    oldIdOperationOfRightCollectionRight.getRightCollection().remove(rightCollectionRight);
                    oldIdOperationOfRightCollectionRight = em.merge(oldIdOperationOfRightCollectionRight);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOperation(operation.getId()) != null) {
                throw new PreexistingEntityException("Operation " + operation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operation operation) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operation persistentOperation = em.find(Operation.class, operation.getId());
            Collection<Right> rightCollectionOld = persistentOperation.getRightCollection();
            Collection<Right> rightCollectionNew = operation.getRightCollection();
            Collection<Right> attachedRightCollectionNew = new ArrayList<Right>();
            for (Right rightCollectionNewRightToAttach : rightCollectionNew) {
                rightCollectionNewRightToAttach = em.getReference(rightCollectionNewRightToAttach.getClass(), rightCollectionNewRightToAttach.getId());
                attachedRightCollectionNew.add(rightCollectionNewRightToAttach);
            }
            rightCollectionNew = attachedRightCollectionNew;
            operation.setRightCollection(rightCollectionNew);
            operation = em.merge(operation);
            for (Right rightCollectionOldRight : rightCollectionOld) {
                if (!rightCollectionNew.contains(rightCollectionOldRight)) {
                    rightCollectionOldRight.setIdOperation(null);
                    rightCollectionOldRight = em.merge(rightCollectionOldRight);
                }
            }
            for (Right rightCollectionNewRight : rightCollectionNew) {
                if (!rightCollectionOld.contains(rightCollectionNewRight)) {
                    Operation oldIdOperationOfRightCollectionNewRight = rightCollectionNewRight.getIdOperation();
                    rightCollectionNewRight.setIdOperation(operation);
                    rightCollectionNewRight = em.merge(rightCollectionNewRight);
                    if (oldIdOperationOfRightCollectionNewRight != null && !oldIdOperationOfRightCollectionNewRight.equals(operation)) {
                        oldIdOperationOfRightCollectionNewRight.getRightCollection().remove(rightCollectionNewRight);
                        oldIdOperationOfRightCollectionNewRight = em.merge(oldIdOperationOfRightCollectionNewRight);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operation.getId();
                if (findOperation(id) == null) {
                    throw new NonexistentEntityException("The operation with id " + id + " no longer exists.");
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
            Operation operation;
            try {
                operation = em.getReference(Operation.class, id);
                operation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operation with id " + id + " no longer exists.", enfe);
            }
            Collection<Right> rightCollection = operation.getRightCollection();
            for (Right rightCollectionRight : rightCollection) {
                rightCollectionRight.setIdOperation(null);
                rightCollectionRight = em.merge(rightCollectionRight);
            }
            em.remove(operation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operation> findOperationEntities() {
        return findOperationEntities(true, -1, -1);
    }

    public List<Operation> findOperationEntities(int maxResults, int firstResult) {
        return findOperationEntities(false, maxResults, firstResult);
    }

    private List<Operation> findOperationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Operation as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Operation findOperation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operation.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperationCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Operation as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
