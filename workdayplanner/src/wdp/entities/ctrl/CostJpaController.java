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
import wdp.entities.raw.Cost;
import wdp.entities.raw.Project;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author robson
 */
public class CostJpaController {

    public CostJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cost cost) throws PreexistingEntityException, Exception {
        if (cost.getProjectCollection() == null) {
            cost.setProjectCollection(new ArrayList<Project>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Project> attachedProjectCollection = new ArrayList<Project>();
            for (Project projectCollectionProjectToAttach : cost.getProjectCollection()) {
                projectCollectionProjectToAttach = em.getReference(projectCollectionProjectToAttach.getClass(), projectCollectionProjectToAttach.getId());
                attachedProjectCollection.add(projectCollectionProjectToAttach);
            }
            cost.setProjectCollection(attachedProjectCollection);
            em.persist(cost);
            for (Project projectCollectionProject : cost.getProjectCollection()) {
                Cost oldIdCostOfProjectCollectionProject = projectCollectionProject.getIdCost();
                projectCollectionProject.setIdCost(cost);
                projectCollectionProject = em.merge(projectCollectionProject);
                if (oldIdCostOfProjectCollectionProject != null) {
                    oldIdCostOfProjectCollectionProject.getProjectCollection().remove(projectCollectionProject);
                    oldIdCostOfProjectCollectionProject = em.merge(oldIdCostOfProjectCollectionProject);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCost(cost.getId()) != null) {
                throw new PreexistingEntityException("Cost " + cost + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cost cost) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cost persistentCost = em.find(Cost.class, cost.getId());
            Collection<Project> projectCollectionOld = persistentCost.getProjectCollection();
            Collection<Project> projectCollectionNew = cost.getProjectCollection();
            List<String> illegalOrphanMessages = null;
            for (Project projectCollectionOldProject : projectCollectionOld) {
                if (!projectCollectionNew.contains(projectCollectionOldProject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Project " + projectCollectionOldProject + " since its idCost field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Project> attachedProjectCollectionNew = new ArrayList<Project>();
            for (Project projectCollectionNewProjectToAttach : projectCollectionNew) {
                projectCollectionNewProjectToAttach = em.getReference(projectCollectionNewProjectToAttach.getClass(), projectCollectionNewProjectToAttach.getId());
                attachedProjectCollectionNew.add(projectCollectionNewProjectToAttach);
            }
            projectCollectionNew = attachedProjectCollectionNew;
            cost.setProjectCollection(projectCollectionNew);
            cost = em.merge(cost);
            for (Project projectCollectionNewProject : projectCollectionNew) {
                if (!projectCollectionOld.contains(projectCollectionNewProject)) {
                    Cost oldIdCostOfProjectCollectionNewProject = projectCollectionNewProject.getIdCost();
                    projectCollectionNewProject.setIdCost(cost);
                    projectCollectionNewProject = em.merge(projectCollectionNewProject);
                    if (oldIdCostOfProjectCollectionNewProject != null && !oldIdCostOfProjectCollectionNewProject.equals(cost)) {
                        oldIdCostOfProjectCollectionNewProject.getProjectCollection().remove(projectCollectionNewProject);
                        oldIdCostOfProjectCollectionNewProject = em.merge(oldIdCostOfProjectCollectionNewProject);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cost.getId();
                if (findCost(id) == null) {
                    throw new NonexistentEntityException("The cost with id " + id + " no longer exists.");
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
            Cost cost;
            try {
                cost = em.getReference(Cost.class, id);
                cost.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cost with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Project> projectCollectionOrphanCheck = cost.getProjectCollection();
            for (Project projectCollectionOrphanCheckProject : projectCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cost (" + cost + ") cannot be destroyed since the Project " + projectCollectionOrphanCheckProject + " in its projectCollection field has a non-nullable idCost field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cost);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cost> findCostEntities() {
        return findCostEntities(true, -1, -1);
    }

    public List<Cost> findCostEntities(int maxResults, int firstResult) {
        return findCostEntities(false, maxResults, firstResult);
    }

    private List<Cost> findCostEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Cost as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cost findCost(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cost.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Cost as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
