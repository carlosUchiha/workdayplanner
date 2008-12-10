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
import wdp.entities.raw.Todo;

/**
 *
 * @author robson
 */
public class TodoJpaController {

    public TodoJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Todo todo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(todo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTodo(todo.getId()) != null) {
                throw new PreexistingEntityException("Todo " + todo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Todo todo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            todo = em.merge(todo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = todo.getId();
                if (findTodo(id) == null) {
                    throw new NonexistentEntityException("The todo with id " + id + " no longer exists.");
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
            Todo todo;
            try {
                todo = em.getReference(Todo.class, id);
                todo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The todo with id " + id + " no longer exists.", enfe);
            }
            em.remove(todo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Todo> findTodoEntities() {
        return findTodoEntities(true, -1, -1);
    }

    public List<Todo> findTodoEntities(int maxResults, int firstResult) {
        return findTodoEntities(false, maxResults, firstResult);
    }

    private List<Todo> findTodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Todo as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Todo findTodo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Todo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTodoCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Todo as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
