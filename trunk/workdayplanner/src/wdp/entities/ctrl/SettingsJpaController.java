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
import wdp.entities.raw.Settings;
import wdp.entities.raw.Worker;

/**
 *
 * @author robson
 */
public class SettingsJpaController {

    public SettingsJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Settings settings) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Worker idWorker = settings.getIdWorker();
            if (idWorker != null) {
                idWorker = em.getReference(idWorker.getClass(), idWorker.getId());
                settings.setIdWorker(idWorker);
            }
            em.persist(settings);
            if (idWorker != null) {
                idWorker.getSettingsCollection().add(settings);
                idWorker = em.merge(idWorker);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSettings(settings.getId()) != null) {
                throw new PreexistingEntityException("Settings " + settings + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Settings settings) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Settings persistentSettings = em.find(Settings.class, settings.getId());
            Worker idWorkerOld = persistentSettings.getIdWorker();
            Worker idWorkerNew = settings.getIdWorker();
            if (idWorkerNew != null) {
                idWorkerNew = em.getReference(idWorkerNew.getClass(), idWorkerNew.getId());
                settings.setIdWorker(idWorkerNew);
            }
            settings = em.merge(settings);
            if (idWorkerOld != null && !idWorkerOld.equals(idWorkerNew)) {
                idWorkerOld.getSettingsCollection().remove(settings);
                idWorkerOld = em.merge(idWorkerOld);
            }
            if (idWorkerNew != null && !idWorkerNew.equals(idWorkerOld)) {
                idWorkerNew.getSettingsCollection().add(settings);
                idWorkerNew = em.merge(idWorkerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = settings.getId();
                if (findSettings(id) == null) {
                    throw new NonexistentEntityException("The settings with id " + id + " no longer exists.");
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
            Settings settings;
            try {
                settings = em.getReference(Settings.class, id);
                settings.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The settings with id " + id + " no longer exists.", enfe);
            }
            Worker idWorker = settings.getIdWorker();
            if (idWorker != null) {
                idWorker.getSettingsCollection().remove(settings);
                idWorker = em.merge(idWorker);
            }
            em.remove(settings);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Settings> findSettingsEntities() {
        return findSettingsEntities(true, -1, -1);
    }

    public List<Settings> findSettingsEntities(int maxResults, int firstResult) {
        return findSettingsEntities(false, maxResults, firstResult);
    }

    private List<Settings> findSettingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Settings as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Settings findSettings(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Settings.class, id);
        } finally {
            em.close();
        }
    }

    public int getSettingsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Settings as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
