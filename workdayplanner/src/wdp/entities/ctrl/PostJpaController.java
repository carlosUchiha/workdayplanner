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
import wdp.entities.ctrl.exceptions.IllegalOrphanException;
import wdp.entities.ctrl.exceptions.NonexistentEntityException;
import wdp.entities.ctrl.exceptions.PreexistingEntityException;
import wdp.entities.raw.Assignment;
import java.util.ArrayList;
import java.util.Collection;
import wdp.entities.raw.Post;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class PostJpaController {

    public PostJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Post post) throws PreexistingEntityException, Exception {
        if (post.getAssignmentCollection() == null) {
            post.setAssignmentCollection(new ArrayList<Assignment>());
        }
        if (post.getWorkerCollection() == null) {
            post.setWorkerCollection(new ArrayList<Worker>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Assignment> attachedAssignmentCollection = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionAssignmentToAttach : post.getAssignmentCollection()) {
                assignmentCollectionAssignmentToAttach = em.getReference(assignmentCollectionAssignmentToAttach.getClass(), assignmentCollectionAssignmentToAttach.getId());
                attachedAssignmentCollection.add(assignmentCollectionAssignmentToAttach);
            }
            post.setAssignmentCollection(attachedAssignmentCollection);
            Collection<Worker> attachedWorkerCollection = new ArrayList<Worker>();
            for (Worker workerCollectionWorkerToAttach : post.getWorkerCollection()) {
                workerCollectionWorkerToAttach = em.getReference(workerCollectionWorkerToAttach.getClass(), workerCollectionWorkerToAttach.getId());
                attachedWorkerCollection.add(workerCollectionWorkerToAttach);
            }
            post.setWorkerCollection(attachedWorkerCollection);
            em.persist(post);
            for (Assignment assignmentCollectionAssignment : post.getAssignmentCollection()) {
                Post oldIdPostOfAssignmentCollectionAssignment = assignmentCollectionAssignment.getIdPost();
                assignmentCollectionAssignment.setIdPost(post);
                assignmentCollectionAssignment = em.merge(assignmentCollectionAssignment);
                if (oldIdPostOfAssignmentCollectionAssignment != null) {
                    oldIdPostOfAssignmentCollectionAssignment.getAssignmentCollection().remove(assignmentCollectionAssignment);
                    oldIdPostOfAssignmentCollectionAssignment = em.merge(oldIdPostOfAssignmentCollectionAssignment);
                }
            }
            for (Worker workerCollectionWorker : post.getWorkerCollection()) {
                Post oldIdPostOfWorkerCollectionWorker = workerCollectionWorker.getIdPost();
                workerCollectionWorker.setIdPost(post);
                workerCollectionWorker = em.merge(workerCollectionWorker);
                if (oldIdPostOfWorkerCollectionWorker != null) {
                    oldIdPostOfWorkerCollectionWorker.getWorkerCollection().remove(workerCollectionWorker);
                    oldIdPostOfWorkerCollectionWorker = em.merge(oldIdPostOfWorkerCollectionWorker);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPost(post.getId()) != null) {
                throw new PreexistingEntityException("Post " + post + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Post post) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post persistentPost = em.find(Post.class, post.getId());
            Collection<Assignment> assignmentCollectionOld = persistentPost.getAssignmentCollection();
            Collection<Assignment> assignmentCollectionNew = post.getAssignmentCollection();
            Collection<Worker> workerCollectionOld = persistentPost.getWorkerCollection();
            Collection<Worker> workerCollectionNew = post.getWorkerCollection();
            List<String> illegalOrphanMessages = null;
            for (Assignment assignmentCollectionOldAssignment : assignmentCollectionOld) {
                if (!assignmentCollectionNew.contains(assignmentCollectionOldAssignment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assignment " + assignmentCollectionOldAssignment + " since its idPost field is not nullable.");
                }
            }
            for (Worker workerCollectionOldWorker : workerCollectionOld) {
                if (!workerCollectionNew.contains(workerCollectionOldWorker)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Worker " + workerCollectionOldWorker + " since its idPost field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Assignment> attachedAssignmentCollectionNew = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionNewAssignmentToAttach : assignmentCollectionNew) {
                assignmentCollectionNewAssignmentToAttach = em.getReference(assignmentCollectionNewAssignmentToAttach.getClass(), assignmentCollectionNewAssignmentToAttach.getId());
                attachedAssignmentCollectionNew.add(assignmentCollectionNewAssignmentToAttach);
            }
            assignmentCollectionNew = attachedAssignmentCollectionNew;
            post.setAssignmentCollection(assignmentCollectionNew);
            Collection<Worker> attachedWorkerCollectionNew = new ArrayList<Worker>();
            for (Worker workerCollectionNewWorkerToAttach : workerCollectionNew) {
                workerCollectionNewWorkerToAttach = em.getReference(workerCollectionNewWorkerToAttach.getClass(), workerCollectionNewWorkerToAttach.getId());
                attachedWorkerCollectionNew.add(workerCollectionNewWorkerToAttach);
            }
            workerCollectionNew = attachedWorkerCollectionNew;
            post.setWorkerCollection(workerCollectionNew);
            post = em.merge(post);
            for (Assignment assignmentCollectionNewAssignment : assignmentCollectionNew) {
                if (!assignmentCollectionOld.contains(assignmentCollectionNewAssignment)) {
                    Post oldIdPostOfAssignmentCollectionNewAssignment = assignmentCollectionNewAssignment.getIdPost();
                    assignmentCollectionNewAssignment.setIdPost(post);
                    assignmentCollectionNewAssignment = em.merge(assignmentCollectionNewAssignment);
                    if (oldIdPostOfAssignmentCollectionNewAssignment != null && !oldIdPostOfAssignmentCollectionNewAssignment.equals(post)) {
                        oldIdPostOfAssignmentCollectionNewAssignment.getAssignmentCollection().remove(assignmentCollectionNewAssignment);
                        oldIdPostOfAssignmentCollectionNewAssignment = em.merge(oldIdPostOfAssignmentCollectionNewAssignment);
                    }
                }
            }
            for (Worker workerCollectionNewWorker : workerCollectionNew) {
                if (!workerCollectionOld.contains(workerCollectionNewWorker)) {
                    Post oldIdPostOfWorkerCollectionNewWorker = workerCollectionNewWorker.getIdPost();
                    workerCollectionNewWorker.setIdPost(post);
                    workerCollectionNewWorker = em.merge(workerCollectionNewWorker);
                    if (oldIdPostOfWorkerCollectionNewWorker != null && !oldIdPostOfWorkerCollectionNewWorker.equals(post)) {
                        oldIdPostOfWorkerCollectionNewWorker.getWorkerCollection().remove(workerCollectionNewWorker);
                        oldIdPostOfWorkerCollectionNewWorker = em.merge(oldIdPostOfWorkerCollectionNewWorker);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = post.getId();
                if (findPost(id) == null) {
                    throw new NonexistentEntityException("The post with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post post;
            try {
                post = em.getReference(Post.class, id);
                post.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The post with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Assignment> assignmentCollectionOrphanCheck = post.getAssignmentCollection();
            for (Assignment assignmentCollectionOrphanCheckAssignment : assignmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Post (" + post + ") cannot be destroyed since the Assignment " + assignmentCollectionOrphanCheckAssignment + " in its assignmentCollection field has a non-nullable idPost field.");
            }
            Collection<Worker> workerCollectionOrphanCheck = post.getWorkerCollection();
            for (Worker workerCollectionOrphanCheckWorker : workerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Post (" + post + ") cannot be destroyed since the Worker " + workerCollectionOrphanCheckWorker + " in its workerCollection field has a non-nullable idPost field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(post);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Post> findPostEntities() {
        return findPostEntities(true, -1, -1);
    }

    public List<Post> findPostEntities(int maxResults, int firstResult) {
        return findPostEntities(false, maxResults, firstResult);
    }

    private List<Post> findPostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Post as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Post findPost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Post.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Post as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
