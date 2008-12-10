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
import wdp.entities.raw.Assignment;
import wdp.entities.raw.Post;
import wdp.entities.raw.Project;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class AssignmentJpaController {

    public AssignmentJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Assignment assignment) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post idPost = assignment.getIdPost();
            if (idPost != null) {
                idPost = em.getReference(idPost.getClass(), idPost.getId());
                assignment.setIdPost(idPost);
            }
            Project idProject = assignment.getIdProject();
            if (idProject != null) {
                idProject = em.getReference(idProject.getClass(), idProject.getId());
                assignment.setIdProject(idProject);
            }
            Worker idWorker = assignment.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                assignment.setIdWorker(idWorker);
            }
            em.persist(assignment);
            if (idPost != null) {
                idPost.getAssignmentCollection().add(assignment);
                idPost = em.merge(idPost);
            }
            if (idProject != null) {
                idProject.getAssignmentCollection().add(assignment);
                idProject = em.merge(idProject);
            }
            if (idWorker != null) {
                idWorker.getAssignmentCollection().add(assignment);
                idWorker = em.merge(idWorker);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAssignment(assignment.getId()) != null) {
                throw new PreexistingEntityException("Assignment " + assignment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Assignment assignment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Assignment persistentAssignment = em.find(Assignment.class, assignment.getId());
            Post idPostOld = persistentAssignment.getIdPost();
            Post idPostNew = assignment.getIdPost();
            Project idProjectOld = persistentAssignment.getIdProject();
            Project idProjectNew = assignment.getIdProject();
            Worker idWorkerOld = persistentAssignment.getIdWorker();
            Worker idWorkerNew = assignment.getIdWorker();
            if (idPostNew != null) {
                idPostNew = em.getReference(idPostNew.getClass(), idPostNew.getId());
                assignment.setIdPost(idPostNew);
            }
            if (idProjectNew != null) {
                idProjectNew = em.getReference(idProjectNew.getClass(), idProjectNew.getId());
                assignment.setIdProject(idProjectNew);
            }
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                assignment.setIdWorker(idWorkerNew);
            }
            assignment = em.merge(assignment);
            if (idPostOld != null && !idPostOld.equals(idPostNew)) {
                idPostOld.getAssignmentCollection().remove(assignment);
                idPostOld = em.merge(idPostOld);
            }
            if (idPostNew != null && !idPostNew.equals(idPostOld)) {
                idPostNew.getAssignmentCollection().add(assignment);
                idPostNew = em.merge(idPostNew);
            }
            if (idProjectOld != null && !idProjectOld.equals(idProjectNew)) {
                idProjectOld.getAssignmentCollection().remove(assignment);
                idProjectOld = em.merge(idProjectOld);
            }
            if (idProjectNew != null && !idProjectNew.equals(idProjectOld)) {
                idProjectNew.getAssignmentCollection().add(assignment);
                idProjectNew = em.merge(idProjectNew);
            }
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getAssignmentCollection().remove(assignment);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getAssignmentCollection().add(assignment);
                idWorkerNew = em.merge(idWorkerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = assignment.getId();
                if (findAssignment(id) == null) {
                    throw new NonexistentEntityException("The assignment with id " + id + " no longer exists.");
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
            Assignment assignment;
            try {
                assignment = em.getReference(Assignment.class, id);
                assignment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The assignment with id " + id + " no longer exists.", enfe);
            }
            Post idPost = assignment.getIdPost();
            if (idPost != null) {
                idPost.getAssignmentCollection().remove(assignment);
                idPost = em.merge(idPost);
            }
            Project idProject = assignment.getIdProject();
            if (idProject != null) {
                idProject.getAssignmentCollection().remove(assignment);
                idProject = em.merge(idProject);
            }
            Worker idWorker = assignment.getIdWorker();
            if (idWorker != null) {
                idWorker.getAssignmentCollection().remove(assignment);
                idWorker = em.merge(idWorker);
            }
            em.remove(assignment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Assignment> findAssignmentEntities() {
        return findAssignmentEntities(true, -1, -1);
    }

    public List<Assignment> findAssignmentEntities(int maxResults, int firstResult) {
        return findAssignmentEntities(false, maxResults, firstResult);
    }

    private List<Assignment> findAssignmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Assignment as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Assignment findAssignment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Assignment.class, id);
        } finally {
            em.close();
        }
    }

    public int getAssignmentCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Assignment as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
