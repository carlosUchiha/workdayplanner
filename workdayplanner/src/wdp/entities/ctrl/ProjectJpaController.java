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
import wdp.entities.raw.Right;
import java.util.ArrayList;
import java.util.Collection;
import wdp.entities.raw.Assignment;
import wdp.entities.raw.Task;

/**
 *
 * @author robson
 */
public class ProjectJpaController {

    public ProjectJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Project project) throws PreexistingEntityException, Exception {
        if (project.getRightCollection() == null) {
            project.setRightCollection(new ArrayList<Right>());
        }
        if (project.getAssignmentCollection() == null) {
            project.setAssignmentCollection(new ArrayList<Assignment>());
        }
        if (project.getTaskCollection() == null) {
            project.setTaskCollection(new ArrayList<Task>());
        }
        if (project.getProjectCollection() == null) {
            project.setProjectCollection(new ArrayList<Project>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cost idCost = project.getIdCost();
            if (idCost != null) {
                idCost = em.getReference(idCost.getClass(), idCost.getId());
                project.setIdCost(idCost);
            }
            Project idParrent = project.getIdParrent();
            if (idParrent != null) {
                idParrent = em.getReference(idParrent.getClass(), idParrent.getId());
                project.setIdParrent(idParrent);
            }
            Collection<Right> attachedRightCollection = new ArrayList<Right>();
            for (Right rightCollectionRightToAttach : project.getRightCollection()) {
                rightCollectionRightToAttach = em.getReference(rightCollectionRightToAttach.getClass(), rightCollectionRightToAttach.getId());
                attachedRightCollection.add(rightCollectionRightToAttach);
            }
            project.setRightCollection(attachedRightCollection);
            Collection<Assignment> attachedAssignmentCollection = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionAssignmentToAttach : project.getAssignmentCollection()) {
                assignmentCollectionAssignmentToAttach = em.getReference(assignmentCollectionAssignmentToAttach.getClass(), assignmentCollectionAssignmentToAttach.getId());
                attachedAssignmentCollection.add(assignmentCollectionAssignmentToAttach);
            }
            project.setAssignmentCollection(attachedAssignmentCollection);
            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
            for (Task taskCollectionTaskToAttach : project.getTaskCollection()) {
                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getId());
                attachedTaskCollection.add(taskCollectionTaskToAttach);
            }
            project.setTaskCollection(attachedTaskCollection);
            Collection<Project> attachedProjectCollection = new ArrayList<Project>();
            for (Project projectCollectionProjectToAttach : project.getProjectCollection()) {
                projectCollectionProjectToAttach = em.getReference(projectCollectionProjectToAttach.getClass(), projectCollectionProjectToAttach.getId());
                attachedProjectCollection.add(projectCollectionProjectToAttach);
            }
            project.setProjectCollection(attachedProjectCollection);
            em.persist(project);
            if (idCost != null) {
                idCost.getProjectCollection().add(project);
                idCost = em.merge(idCost);
            }
            if (idParrent != null) {
                idParrent.getProjectCollection().add(project);
                idParrent = em.merge(idParrent);
            }
            for (Right rightCollectionRight : project.getRightCollection()) {
                Project oldIdProjectOfRightCollectionRight = rightCollectionRight.getIdProject();
                rightCollectionRight.setIdProject(project);
                rightCollectionRight = em.merge(rightCollectionRight);
                if (oldIdProjectOfRightCollectionRight != null) {
                    oldIdProjectOfRightCollectionRight.getRightCollection().remove(rightCollectionRight);
                    oldIdProjectOfRightCollectionRight = em.merge(oldIdProjectOfRightCollectionRight);
                }
            }
            for (Assignment assignmentCollectionAssignment : project.getAssignmentCollection()) {
                Project oldIdProjectOfAssignmentCollectionAssignment = assignmentCollectionAssignment.getIdProject();
                assignmentCollectionAssignment.setIdProject(project);
                assignmentCollectionAssignment = em.merge(assignmentCollectionAssignment);
                if (oldIdProjectOfAssignmentCollectionAssignment != null) {
                    oldIdProjectOfAssignmentCollectionAssignment.getAssignmentCollection().remove(assignmentCollectionAssignment);
                    oldIdProjectOfAssignmentCollectionAssignment = em.merge(oldIdProjectOfAssignmentCollectionAssignment);
                }
            }
            for (Task taskCollectionTask : project.getTaskCollection()) {
                Project oldIdProjectOfTaskCollectionTask = taskCollectionTask.getIdProject();
                taskCollectionTask.setIdProject(project);
                taskCollectionTask = em.merge(taskCollectionTask);
                if (oldIdProjectOfTaskCollectionTask != null) {
                    oldIdProjectOfTaskCollectionTask.getTaskCollection().remove(taskCollectionTask);
                    oldIdProjectOfTaskCollectionTask = em.merge(oldIdProjectOfTaskCollectionTask);
                }
            }
            for (Project projectCollectionProject : project.getProjectCollection()) {
                Project oldIdParrentOfProjectCollectionProject = projectCollectionProject.getIdParrent();
                projectCollectionProject.setIdParrent(project);
                projectCollectionProject = em.merge(projectCollectionProject);
                if (oldIdParrentOfProjectCollectionProject != null) {
                    oldIdParrentOfProjectCollectionProject.getProjectCollection().remove(projectCollectionProject);
                    oldIdParrentOfProjectCollectionProject = em.merge(oldIdParrentOfProjectCollectionProject);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProject(project.getId()) != null) {
                throw new PreexistingEntityException("Project " + project + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Project project) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Project persistentProject = em.find(Project.class, project.getId());
            Cost idCostOld = persistentProject.getIdCost();
            Cost idCostNew = project.getIdCost();
            Project idParrentOld = persistentProject.getIdParrent();
            Project idParrentNew = project.getIdParrent();
            Collection<Right> rightCollectionOld = persistentProject.getRightCollection();
            Collection<Right> rightCollectionNew = project.getRightCollection();
            Collection<Assignment> assignmentCollectionOld = persistentProject.getAssignmentCollection();
            Collection<Assignment> assignmentCollectionNew = project.getAssignmentCollection();
            Collection<Task> taskCollectionOld = persistentProject.getTaskCollection();
            Collection<Task> taskCollectionNew = project.getTaskCollection();
            Collection<Project> projectCollectionOld = persistentProject.getProjectCollection();
            Collection<Project> projectCollectionNew = project.getProjectCollection();
            List<String> illegalOrphanMessages = null;
            for (Assignment assignmentCollectionOldAssignment : assignmentCollectionOld) {
                if (!assignmentCollectionNew.contains(assignmentCollectionOldAssignment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assignment " + assignmentCollectionOldAssignment + " since its idProject field is not nullable.");
                }
            }
            for (Task taskCollectionOldTask : taskCollectionOld) {
                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Task " + taskCollectionOldTask + " since its idProject field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCostNew != null) {
                idCostNew = em.getReference(idCostNew.getClass(), idCostNew.getId());
                project.setIdCost(idCostNew);
            }
            if (idParrentNew != null) {
                idParrentNew = em.getReference(idParrentNew.getClass(), idParrentNew.getId());
                project.setIdParrent(idParrentNew);
            }
            Collection<Right> attachedRightCollectionNew = new ArrayList<Right>();
            for (Right rightCollectionNewRightToAttach : rightCollectionNew) {
                rightCollectionNewRightToAttach = em.getReference(rightCollectionNewRightToAttach.getClass(), rightCollectionNewRightToAttach.getId());
                attachedRightCollectionNew.add(rightCollectionNewRightToAttach);
            }
            rightCollectionNew = attachedRightCollectionNew;
            project.setRightCollection(rightCollectionNew);
            Collection<Assignment> attachedAssignmentCollectionNew = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionNewAssignmentToAttach : assignmentCollectionNew) {
                assignmentCollectionNewAssignmentToAttach = em.getReference(assignmentCollectionNewAssignmentToAttach.getClass(), assignmentCollectionNewAssignmentToAttach.getId());
                attachedAssignmentCollectionNew.add(assignmentCollectionNewAssignmentToAttach);
            }
            assignmentCollectionNew = attachedAssignmentCollectionNew;
            project.setAssignmentCollection(assignmentCollectionNew);
            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getId());
                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
            }
            taskCollectionNew = attachedTaskCollectionNew;
            project.setTaskCollection(taskCollectionNew);
            Collection<Project> attachedProjectCollectionNew = new ArrayList<Project>();
            for (Project projectCollectionNewProjectToAttach : projectCollectionNew) {
                projectCollectionNewProjectToAttach = em.getReference(projectCollectionNewProjectToAttach.getClass(), projectCollectionNewProjectToAttach.getId());
                attachedProjectCollectionNew.add(projectCollectionNewProjectToAttach);
            }
            projectCollectionNew = attachedProjectCollectionNew;
            project.setProjectCollection(projectCollectionNew);
            project = em.merge(project);
            if (idCostOld != null && !idCostOld.equals(idCostNew)) {
                idCostOld.getProjectCollection().remove(project);
                idCostOld = em.merge(idCostOld);
            }
            if (idCostNew != null && !idCostNew.equals(idCostOld)) {
                idCostNew.getProjectCollection().add(project);
                idCostNew = em.merge(idCostNew);
            }
            if (idParrentOld != null && !idParrentOld.equals(idParrentNew)) {
                idParrentOld.getProjectCollection().remove(project);
                idParrentOld = em.merge(idParrentOld);
            }
            if (idParrentNew != null && !idParrentNew.equals(idParrentOld)) {
                idParrentNew.getProjectCollection().add(project);
                idParrentNew = em.merge(idParrentNew);
            }
            for (Right rightCollectionOldRight : rightCollectionOld) {
                if (!rightCollectionNew.contains(rightCollectionOldRight)) {
                    rightCollectionOldRight.setIdProject(null);
                    rightCollectionOldRight = em.merge(rightCollectionOldRight);
                }
            }
            for (Right rightCollectionNewRight : rightCollectionNew) {
                if (!rightCollectionOld.contains(rightCollectionNewRight)) {
                    Project oldIdProjectOfRightCollectionNewRight = rightCollectionNewRight.getIdProject();
                    rightCollectionNewRight.setIdProject(project);
                    rightCollectionNewRight = em.merge(rightCollectionNewRight);
                    if (oldIdProjectOfRightCollectionNewRight != null && !oldIdProjectOfRightCollectionNewRight.equals(project)) {
                        oldIdProjectOfRightCollectionNewRight.getRightCollection().remove(rightCollectionNewRight);
                        oldIdProjectOfRightCollectionNewRight = em.merge(oldIdProjectOfRightCollectionNewRight);
                    }
                }
            }
            for (Assignment assignmentCollectionNewAssignment : assignmentCollectionNew) {
                if (!assignmentCollectionOld.contains(assignmentCollectionNewAssignment)) {
                    Project oldIdProjectOfAssignmentCollectionNewAssignment = assignmentCollectionNewAssignment.getIdProject();
                    assignmentCollectionNewAssignment.setIdProject(project);
                    assignmentCollectionNewAssignment = em.merge(assignmentCollectionNewAssignment);
                    if (oldIdProjectOfAssignmentCollectionNewAssignment != null && !oldIdProjectOfAssignmentCollectionNewAssignment.equals(project)) {
                        oldIdProjectOfAssignmentCollectionNewAssignment.getAssignmentCollection().remove(assignmentCollectionNewAssignment);
                        oldIdProjectOfAssignmentCollectionNewAssignment = em.merge(oldIdProjectOfAssignmentCollectionNewAssignment);
                    }
                }
            }
            for (Task taskCollectionNewTask : taskCollectionNew) {
                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
                    Project oldIdProjectOfTaskCollectionNewTask = taskCollectionNewTask.getIdProject();
                    taskCollectionNewTask.setIdProject(project);
                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
                    if (oldIdProjectOfTaskCollectionNewTask != null && !oldIdProjectOfTaskCollectionNewTask.equals(project)) {
                        oldIdProjectOfTaskCollectionNewTask.getTaskCollection().remove(taskCollectionNewTask);
                        oldIdProjectOfTaskCollectionNewTask = em.merge(oldIdProjectOfTaskCollectionNewTask);
                    }
                }
            }
            for (Project projectCollectionOldProject : projectCollectionOld) {
                if (!projectCollectionNew.contains(projectCollectionOldProject)) {
                    projectCollectionOldProject.setIdParrent(null);
                    projectCollectionOldProject = em.merge(projectCollectionOldProject);
                }
            }
            for (Project projectCollectionNewProject : projectCollectionNew) {
                if (!projectCollectionOld.contains(projectCollectionNewProject)) {
                    Project oldIdParrentOfProjectCollectionNewProject = projectCollectionNewProject.getIdParrent();
                    projectCollectionNewProject.setIdParrent(project);
                    projectCollectionNewProject = em.merge(projectCollectionNewProject);
                    if (oldIdParrentOfProjectCollectionNewProject != null && !oldIdParrentOfProjectCollectionNewProject.equals(project)) {
                        oldIdParrentOfProjectCollectionNewProject.getProjectCollection().remove(projectCollectionNewProject);
                        oldIdParrentOfProjectCollectionNewProject = em.merge(oldIdParrentOfProjectCollectionNewProject);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = project.getId();
                if (findProject(id) == null) {
                    throw new NonexistentEntityException("The project with id " + id + " no longer exists.");
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
            Project project;
            try {
                project = em.getReference(Project.class, id);
                project.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The project with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Assignment> assignmentCollectionOrphanCheck = project.getAssignmentCollection();
            for (Assignment assignmentCollectionOrphanCheckAssignment : assignmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Project (" + project + ") cannot be destroyed since the Assignment " + assignmentCollectionOrphanCheckAssignment + " in its assignmentCollection field has a non-nullable idProject field.");
            }
            Collection<Task> taskCollectionOrphanCheck = project.getTaskCollection();
            for (Task taskCollectionOrphanCheckTask : taskCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Project (" + project + ") cannot be destroyed since the Task " + taskCollectionOrphanCheckTask + " in its taskCollection field has a non-nullable idProject field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cost idCost = project.getIdCost();
            if (idCost != null) {
                idCost.getProjectCollection().remove(project);
                idCost = em.merge(idCost);
            }
            Project idParrent = project.getIdParrent();
            if (idParrent != null) {
                idParrent.getProjectCollection().remove(project);
                idParrent = em.merge(idParrent);
            }
            Collection<Right> rightCollection = project.getRightCollection();
            for (Right rightCollectionRight : rightCollection) {
                rightCollectionRight.setIdProject(null);
                rightCollectionRight = em.merge(rightCollectionRight);
            }
            Collection<Project> projectCollection = project.getProjectCollection();
            for (Project projectCollectionProject : projectCollection) {
                projectCollectionProject.setIdParrent(null);
                projectCollectionProject = em.merge(projectCollectionProject);
            }
            em.remove(project);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Project> findProjectEntities() {
        return findProjectEntities(true, -1, -1);
    }

    public List<Project> findProjectEntities(int maxResults, int firstResult) {
        return findProjectEntities(false, maxResults, firstResult);
    }

    private List<Project> findProjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Project as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Project findProject(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Project.class, id);
        } finally {
            em.close();
        }
    }

    public int getProjectCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Project as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
