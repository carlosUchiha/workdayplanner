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
import wdp.entities.raw.Team;
import wdp.entities.raw.Worker;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author robson
 */
public class TeamJpaController {

    public TeamJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Team team) throws PreexistingEntityException, Exception {
        if (team.getWorkerCollection() == null) {
            team.setWorkerCollection(new ArrayList<Worker>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Worker> attachedWorkerCollection = new ArrayList<Worker>();
            for (Worker workerCollectionWorkerToAttach : team.getWorkerCollection()) {
                workerCollectionWorkerToAttach = em.getReference(workerCollectionWorkerToAttach.getClass(), workerCollectionWorkerToAttach.getId());
                attachedWorkerCollection.add(workerCollectionWorkerToAttach);
            }
            team.setWorkerCollection(attachedWorkerCollection);
            em.persist(team);
            for (Worker workerCollectionWorker : team.getWorkerCollection()) {
                Team oldIdTeamOfWorkerCollectionWorker = workerCollectionWorker.getIdTeam();
                workerCollectionWorker.setIdTeam(team);
                workerCollectionWorker = em.merge(workerCollectionWorker);
                if (oldIdTeamOfWorkerCollectionWorker != null) {
                    oldIdTeamOfWorkerCollectionWorker.getWorkerCollection().remove(workerCollectionWorker);
                    oldIdTeamOfWorkerCollectionWorker = em.merge(oldIdTeamOfWorkerCollectionWorker);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTeam(team.getId()) != null) {
                throw new PreexistingEntityException("Team " + team + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Team team) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Team persistentTeam = em.find(Team.class, team.getId());
            Collection<Worker> workerCollectionOld = persistentTeam.getWorkerCollection();
            Collection<Worker> workerCollectionNew = team.getWorkerCollection();
            List<String> illegalOrphanMessages = null;
            for (Worker workerCollectionOldWorker : workerCollectionOld) {
                if (!workerCollectionNew.contains(workerCollectionOldWorker)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Worker " + workerCollectionOldWorker + " since its idTeam field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Worker> attachedWorkerCollectionNew = new ArrayList<Worker>();
            for (Worker workerCollectionNewWorkerToAttach : workerCollectionNew) {
                workerCollectionNewWorkerToAttach = em.getReference(workerCollectionNewWorkerToAttach.getClass(), workerCollectionNewWorkerToAttach.getId());
                attachedWorkerCollectionNew.add(workerCollectionNewWorkerToAttach);
            }
            workerCollectionNew = attachedWorkerCollectionNew;
            team.setWorkerCollection(workerCollectionNew);
            team = em.merge(team);
            for (Worker workerCollectionNewWorker : workerCollectionNew) {
                if (!workerCollectionOld.contains(workerCollectionNewWorker)) {
                    Team oldIdTeamOfWorkerCollectionNewWorker = workerCollectionNewWorker.getIdTeam();
                    workerCollectionNewWorker.setIdTeam(team);
                    workerCollectionNewWorker = em.merge(workerCollectionNewWorker);
                    if (oldIdTeamOfWorkerCollectionNewWorker != null && !oldIdTeamOfWorkerCollectionNewWorker.equals(team)) {
                        oldIdTeamOfWorkerCollectionNewWorker.getWorkerCollection().remove(workerCollectionNewWorker);
                        oldIdTeamOfWorkerCollectionNewWorker = em.merge(oldIdTeamOfWorkerCollectionNewWorker);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = team.getId();
                if (findTeam(id) == null) {
                    throw new NonexistentEntityException("The team with id " + id + " no longer exists.");
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
            Team team;
            try {
                team = em.getReference(Team.class, id);
                team.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The team with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Worker> workerCollectionOrphanCheck = team.getWorkerCollection();
            for (Worker workerCollectionOrphanCheckWorker : workerCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Team (" + team + ") cannot be destroyed since the Worker " + workerCollectionOrphanCheckWorker + " in its workerCollection field has a non-nullable idTeam field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(team);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Team> findTeamEntities() {
        return findTeamEntities(true, -1, -1);
    }

    public List<Team> findTeamEntities(int maxResults, int firstResult) {
        return findTeamEntities(false, maxResults, firstResult);
    }

    private List<Team> findTeamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Team as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Team findTeam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Team.class, id);
        } finally {
            em.close();
        }
    }

    public int getTeamCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Team as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
