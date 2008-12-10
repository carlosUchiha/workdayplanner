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
import wdp.entities.raw.Post;
import wdp.entities.raw.Team;
import wdp.entities.raw.Worker;
import wdp.entities.raw.Right;
import java.util.ArrayList;
import java.util.Collection;
import wdp.entities.raw.Assignment;
import wdp.entities.raw.Task;
import wdp.entities.raw.Settings;
import wdp.entities.raw.Note;

/**
 *
 * @author robson
 */
public class WorkerJpaController {

    public WorkerJpaController() {
        emf = Persistence.createEntityManagerFactory("db.fdbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Worker worker) throws PreexistingEntityException, Exception {
        if (worker.getRightCollection() == null) {
            worker.setRightCollection(new ArrayList<Right>());
        }
        if (worker.getAssignmentCollection() == null) {
            worker.setAssignmentCollection(new ArrayList<Assignment>());
        }
        if (worker.getTaskCollection() == null) {
            worker.setTaskCollection(new ArrayList<Task>());
        }
        if (worker.getSettingsCollection() == null) {
            worker.setSettingsCollection(new ArrayList<Settings>());
        }
        if (worker.getNoteCollection() == null) {
            worker.setNoteCollection(new ArrayList<Note>());
        }
        if (worker.getWorkerCollection() == null) {
            worker.setWorkerCollection(new ArrayList<Worker>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Post idPost = worker.getIdPost();
            if (idPost != null) {
                idPost = em.getReference(idPost.getClass(), idPost.getId());
                worker.setIdPost(idPost);
            }
            Team idTeam = worker.getIdTeam();
            if (idTeam != null) {
                idTeam = em.getReference(idTeam.getClass(), idTeam.getId());
                worker.setIdTeam(idTeam);
            }
            Worker idBoss = worker.getIdBoss();
            if (idBoss != null) {
                idBoss = em.getReference(idBoss.getClass(), idBoss.getId());
                worker.setIdBoss(idBoss);
            }
            Collection<Right> attachedRightCollection = new ArrayList<Right>();
            for (Right rightCollectionRightToAttach : worker.getRightCollection()) {
                rightCollectionRightToAttach = em.getReference(rightCollectionRightToAttach.getClass(), rightCollectionRightToAttach.getId());
                attachedRightCollection.add(rightCollectionRightToAttach);
            }
            worker.setRightCollection(attachedRightCollection);
            Collection<Assignment> attachedAssignmentCollection = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionAssignmentToAttach : worker.getAssignmentCollection()) {
                assignmentCollectionAssignmentToAttach = em.getReference(assignmentCollectionAssignmentToAttach.getClass(), assignmentCollectionAssignmentToAttach.getId());
                attachedAssignmentCollection.add(assignmentCollectionAssignmentToAttach);
            }
            worker.setAssignmentCollection(attachedAssignmentCollection);
            Collection<Task> attachedTaskCollection = new ArrayList<Task>();
            for (Task taskCollectionTaskToAttach : worker.getTaskCollection()) {
                taskCollectionTaskToAttach = em.getReference(taskCollectionTaskToAttach.getClass(), taskCollectionTaskToAttach.getId());
                attachedTaskCollection.add(taskCollectionTaskToAttach);
            }
            worker.setTaskCollection(attachedTaskCollection);
            Collection<Settings> attachedSettingsCollection = new ArrayList<Settings>();
            for (Settings settingsCollectionSettingsToAttach : worker.getSettingsCollection()) {
                settingsCollectionSettingsToAttach = em.getReference(settingsCollectionSettingsToAttach.getClass(), settingsCollectionSettingsToAttach.getId());
                attachedSettingsCollection.add(settingsCollectionSettingsToAttach);
            }
            worker.setSettingsCollection(attachedSettingsCollection);
            Collection<Note> attachedNoteCollection = new ArrayList<Note>();
            for (Note noteCollectionNoteToAttach : worker.getNoteCollection()) {
                noteCollectionNoteToAttach = em.getReference(noteCollectionNoteToAttach.getClass(), noteCollectionNoteToAttach.getId());
                attachedNoteCollection.add(noteCollectionNoteToAttach);
            }
            worker.setNoteCollection(attachedNoteCollection);
            Collection<Worker> attachedWorkerCollection = new ArrayList<Worker>();
            for (Worker workerCollectionWorkerToAttach : worker.getWorkerCollection()) {
                workerCollectionWorkerToAttach = em.getReference(workerCollectionWorkerToAttach.getClass(), workerCollectionWorkerToAttach.getId());
                attachedWorkerCollection.add(workerCollectionWorkerToAttach);
            }
            worker.setWorkerCollection(attachedWorkerCollection);
            em.persist(worker);
            if (idPost != null) {
                idPost.getWorkerCollection().add(worker);
                idPost = em.merge(idPost);
            }
            if (idTeam != null) {
                idTeam.getWorkerCollection().add(worker);
                idTeam = em.merge(idTeam);
            }
            if (idBoss != null) {
                idBoss.getWorkerCollection().add(worker);
                idBoss = em.merge(idBoss);
            }
            for (Right rightCollectionRight : worker.getRightCollection()) {
                Worker oldIdWorkerOfRightCollectionRight = rightCollectionRight.getIdWorker();
                rightCollectionRight.setIdWorker(worker);
                rightCollectionRight = em.merge(rightCollectionRight);
                if (oldIdWorkerOfRightCollectionRight != null) {
                    oldIdWorkerOfRightCollectionRight.getRightCollection().remove(rightCollectionRight);
                    oldIdWorkerOfRightCollectionRight = em.merge(oldIdWorkerOfRightCollectionRight);
                }
            }
            for (Assignment assignmentCollectionAssignment : worker.getAssignmentCollection()) {
                Worker oldIdWorkerOfAssignmentCollectionAssignment = assignmentCollectionAssignment.getIdWorker();
                assignmentCollectionAssignment.setIdWorker(worker);
                assignmentCollectionAssignment = em.merge(assignmentCollectionAssignment);
                if (oldIdWorkerOfAssignmentCollectionAssignment != null) {
                    oldIdWorkerOfAssignmentCollectionAssignment.getAssignmentCollection().remove(assignmentCollectionAssignment);
                    oldIdWorkerOfAssignmentCollectionAssignment = em.merge(oldIdWorkerOfAssignmentCollectionAssignment);
                }
            }
            for (Task taskCollectionTask : worker.getTaskCollection()) {
                Worker oldIdWorkerOfTaskCollectionTask = taskCollectionTask.getIdWorker();
                taskCollectionTask.setIdWorker(worker);
                taskCollectionTask = em.merge(taskCollectionTask);
                if (oldIdWorkerOfTaskCollectionTask != null) {
                    oldIdWorkerOfTaskCollectionTask.getTaskCollection().remove(taskCollectionTask);
                    oldIdWorkerOfTaskCollectionTask = em.merge(oldIdWorkerOfTaskCollectionTask);
                }
            }
            for (Settings settingsCollectionSettings : worker.getSettingsCollection()) {
                Worker oldIdWorkerOfSettingsCollectionSettings = settingsCollectionSettings.getIdWorker();
                settingsCollectionSettings.setIdWorker(worker);
                settingsCollectionSettings = em.merge(settingsCollectionSettings);
                if (oldIdWorkerOfSettingsCollectionSettings != null) {
                    oldIdWorkerOfSettingsCollectionSettings.getSettingsCollection().remove(settingsCollectionSettings);
                    oldIdWorkerOfSettingsCollectionSettings = em.merge(oldIdWorkerOfSettingsCollectionSettings);
                }
            }
            for (Note noteCollectionNote : worker.getNoteCollection()) {
                Worker oldIdAuthorOfNoteCollectionNote = noteCollectionNote.getIdAuthor();
                noteCollectionNote.setIdAuthor(worker);
                noteCollectionNote = em.merge(noteCollectionNote);
                if (oldIdAuthorOfNoteCollectionNote != null) {
                    oldIdAuthorOfNoteCollectionNote.getNoteCollection().remove(noteCollectionNote);
                    oldIdAuthorOfNoteCollectionNote = em.merge(oldIdAuthorOfNoteCollectionNote);
                }
            }
            for (Worker workerCollectionWorker : worker.getWorkerCollection()) {
                Worker oldIdBossOfWorkerCollectionWorker = workerCollectionWorker.getIdBoss();
                workerCollectionWorker.setIdBoss(worker);
                workerCollectionWorker = em.merge(workerCollectionWorker);
                if (oldIdBossOfWorkerCollectionWorker != null) {
                    oldIdBossOfWorkerCollectionWorker.getWorkerCollection().remove(workerCollectionWorker);
                    oldIdBossOfWorkerCollectionWorker = em.merge(oldIdBossOfWorkerCollectionWorker);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findWorker(worker.getId()) != null) {
                throw new PreexistingEntityException("Worker " + worker + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Worker worker) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Worker persistentWorker = em.find(Worker.class, worker.getId());
            Post idPostOld = persistentWorker.getIdPost();
            Post idPostNew = worker.getIdPost();
            Team idTeamOld = persistentWorker.getIdTeam();
            Team idTeamNew = worker.getIdTeam();
            Worker idBossOld = persistentWorker.getIdBoss();
            Worker idBossNew = worker.getIdBoss();
            Collection<Right> rightCollectionOld = persistentWorker.getRightCollection();
            Collection<Right> rightCollectionNew = worker.getRightCollection();
            Collection<Assignment> assignmentCollectionOld = persistentWorker.getAssignmentCollection();
            Collection<Assignment> assignmentCollectionNew = worker.getAssignmentCollection();
            Collection<Task> taskCollectionOld = persistentWorker.getTaskCollection();
            Collection<Task> taskCollectionNew = worker.getTaskCollection();
            Collection<Settings> settingsCollectionOld = persistentWorker.getSettingsCollection();
            Collection<Settings> settingsCollectionNew = worker.getSettingsCollection();
            Collection<Note> noteCollectionOld = persistentWorker.getNoteCollection();
            Collection<Note> noteCollectionNew = worker.getNoteCollection();
            Collection<Worker> workerCollectionOld = persistentWorker.getWorkerCollection();
            Collection<Worker> workerCollectionNew = worker.getWorkerCollection();
            List<String> illegalOrphanMessages = null;
            for (Right rightCollectionOldRight : rightCollectionOld) {
                if (!rightCollectionNew.contains(rightCollectionOldRight)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Right " + rightCollectionOldRight + " since its idWorker field is not nullable.");
                }
            }
            for (Assignment assignmentCollectionOldAssignment : assignmentCollectionOld) {
                if (!assignmentCollectionNew.contains(assignmentCollectionOldAssignment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assignment " + assignmentCollectionOldAssignment + " since its idWorker field is not nullable.");
                }
            }
            for (Task taskCollectionOldTask : taskCollectionOld) {
                if (!taskCollectionNew.contains(taskCollectionOldTask)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Task " + taskCollectionOldTask + " since its idWorker field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPostNew != null) {
                idPostNew = em.getReference(idPostNew.getClass(), idPostNew.getId());
                worker.setIdPost(idPostNew);
            }
            if (idTeamNew != null) {
                idTeamNew = em.getReference(idTeamNew.getClass(), idTeamNew.getId());
                worker.setIdTeam(idTeamNew);
            }
            if (idBossNew != null) {
                idBossNew = em.getReference(idBossNew.getClass(), idBossNew.getId());
                worker.setIdBoss(idBossNew);
            }
            Collection<Right> attachedRightCollectionNew = new ArrayList<Right>();
            for (Right rightCollectionNewRightToAttach : rightCollectionNew) {
                rightCollectionNewRightToAttach = em.getReference(rightCollectionNewRightToAttach.getClass(), rightCollectionNewRightToAttach.getId());
                attachedRightCollectionNew.add(rightCollectionNewRightToAttach);
            }
            rightCollectionNew = attachedRightCollectionNew;
            worker.setRightCollection(rightCollectionNew);
            Collection<Assignment> attachedAssignmentCollectionNew = new ArrayList<Assignment>();
            for (Assignment assignmentCollectionNewAssignmentToAttach : assignmentCollectionNew) {
                assignmentCollectionNewAssignmentToAttach = em.getReference(assignmentCollectionNewAssignmentToAttach.getClass(), assignmentCollectionNewAssignmentToAttach.getId());
                attachedAssignmentCollectionNew.add(assignmentCollectionNewAssignmentToAttach);
            }
            assignmentCollectionNew = attachedAssignmentCollectionNew;
            worker.setAssignmentCollection(assignmentCollectionNew);
            Collection<Task> attachedTaskCollectionNew = new ArrayList<Task>();
            for (Task taskCollectionNewTaskToAttach : taskCollectionNew) {
                taskCollectionNewTaskToAttach = em.getReference(taskCollectionNewTaskToAttach.getClass(), taskCollectionNewTaskToAttach.getId());
                attachedTaskCollectionNew.add(taskCollectionNewTaskToAttach);
            }
            taskCollectionNew = attachedTaskCollectionNew;
            worker.setTaskCollection(taskCollectionNew);
            Collection<Settings> attachedSettingsCollectionNew = new ArrayList<Settings>();
            for (Settings settingsCollectionNewSettingsToAttach : settingsCollectionNew) {
                settingsCollectionNewSettingsToAttach = em.getReference(settingsCollectionNewSettingsToAttach.getClass(), settingsCollectionNewSettingsToAttach.getId());
                attachedSettingsCollectionNew.add(settingsCollectionNewSettingsToAttach);
            }
            settingsCollectionNew = attachedSettingsCollectionNew;
            worker.setSettingsCollection(settingsCollectionNew);
            Collection<Note> attachedNoteCollectionNew = new ArrayList<Note>();
            for (Note noteCollectionNewNoteToAttach : noteCollectionNew) {
                noteCollectionNewNoteToAttach = em.getReference(noteCollectionNewNoteToAttach.getClass(), noteCollectionNewNoteToAttach.getId());
                attachedNoteCollectionNew.add(noteCollectionNewNoteToAttach);
            }
            noteCollectionNew = attachedNoteCollectionNew;
            worker.setNoteCollection(noteCollectionNew);
            Collection<Worker> attachedWorkerCollectionNew = new ArrayList<Worker>();
            for (Worker workerCollectionNewWorkerToAttach : workerCollectionNew) {
                workerCollectionNewWorkerToAttach = em.getReference(workerCollectionNewWorkerToAttach.getClass(), workerCollectionNewWorkerToAttach.getId());
                attachedWorkerCollectionNew.add(workerCollectionNewWorkerToAttach);
            }
            workerCollectionNew = attachedWorkerCollectionNew;
            worker.setWorkerCollection(workerCollectionNew);
            worker = em.merge(worker);
            if (idPostOld != null && !idPostOld.equals(idPostNew)) {
                idPostOld.getWorkerCollection().remove(worker);
                idPostOld = em.merge(idPostOld);
            }
            if (idPostNew != null && !idPostNew.equals(idPostOld)) {
                idPostNew.getWorkerCollection().add(worker);
                idPostNew = em.merge(idPostNew);
            }
            if (idTeamOld != null && !idTeamOld.equals(idTeamNew)) {
                idTeamOld.getWorkerCollection().remove(worker);
                idTeamOld = em.merge(idTeamOld);
            }
            if (idTeamNew != null && !idTeamNew.equals(idTeamOld)) {
                idTeamNew.getWorkerCollection().add(worker);
                idTeamNew = em.merge(idTeamNew);
            }
            if (idBossOld != null && !idBossOld.equals(idBossNew)) {
                idBossOld.getWorkerCollection().remove(worker);
                idBossOld = em.merge(idBossOld);
            }
            if (idBossNew != null && !idBossNew.equals(idBossOld)) {
                idBossNew.getWorkerCollection().add(worker);
                idBossNew = em.merge(idBossNew);
            }
            for (Right rightCollectionNewRight : rightCollectionNew) {
                if (!rightCollectionOld.contains(rightCollectionNewRight)) {
                    Worker oldIdWorkerOfRightCollectionNewRight = rightCollectionNewRight.getIdWorker();
                    rightCollectionNewRight.setIdWorker(worker);
                    rightCollectionNewRight = em.merge(rightCollectionNewRight);
                    if (oldIdWorkerOfRightCollectionNewRight != null && !oldIdWorkerOfRightCollectionNewRight.equals(worker)) {
                        oldIdWorkerOfRightCollectionNewRight.getRightCollection().remove(rightCollectionNewRight);
                        oldIdWorkerOfRightCollectionNewRight = em.merge(oldIdWorkerOfRightCollectionNewRight);
                    }
                }
            }
            for (Assignment assignmentCollectionNewAssignment : assignmentCollectionNew) {
                if (!assignmentCollectionOld.contains(assignmentCollectionNewAssignment)) {
                    Worker oldIdWorkerOfAssignmentCollectionNewAssignment = assignmentCollectionNewAssignment.getIdWorker();
                    assignmentCollectionNewAssignment.setIdWorker(worker);
                    assignmentCollectionNewAssignment = em.merge(assignmentCollectionNewAssignment);
                    if (oldIdWorkerOfAssignmentCollectionNewAssignment != null && !oldIdWorkerOfAssignmentCollectionNewAssignment.equals(worker)) {
                        oldIdWorkerOfAssignmentCollectionNewAssignment.getAssignmentCollection().remove(assignmentCollectionNewAssignment);
                        oldIdWorkerOfAssignmentCollectionNewAssignment = em.merge(oldIdWorkerOfAssignmentCollectionNewAssignment);
                    }
                }
            }
            for (Task taskCollectionNewTask : taskCollectionNew) {
                if (!taskCollectionOld.contains(taskCollectionNewTask)) {
                    Worker oldIdWorkerOfTaskCollectionNewTask = taskCollectionNewTask.getIdWorker();
                    taskCollectionNewTask.setIdWorker(worker);
                    taskCollectionNewTask = em.merge(taskCollectionNewTask);
                    if (oldIdWorkerOfTaskCollectionNewTask != null && !oldIdWorkerOfTaskCollectionNewTask.equals(worker)) {
                        oldIdWorkerOfTaskCollectionNewTask.getTaskCollection().remove(taskCollectionNewTask);
                        oldIdWorkerOfTaskCollectionNewTask = em.merge(oldIdWorkerOfTaskCollectionNewTask);
                    }
                }
            }
            for (Settings settingsCollectionOldSettings : settingsCollectionOld) {
                if (!settingsCollectionNew.contains(settingsCollectionOldSettings)) {
                    settingsCollectionOldSettings.setIdWorker(null);
                    settingsCollectionOldSettings = em.merge(settingsCollectionOldSettings);
                }
            }
            for (Settings settingsCollectionNewSettings : settingsCollectionNew) {
                if (!settingsCollectionOld.contains(settingsCollectionNewSettings)) {
                    Worker oldIdWorkerOfSettingsCollectionNewSettings = settingsCollectionNewSettings.getIdWorker();
                    settingsCollectionNewSettings.setIdWorker(worker);
                    settingsCollectionNewSettings = em.merge(settingsCollectionNewSettings);
                    if (oldIdWorkerOfSettingsCollectionNewSettings != null && !oldIdWorkerOfSettingsCollectionNewSettings.equals(worker)) {
                        oldIdWorkerOfSettingsCollectionNewSettings.getSettingsCollection().remove(settingsCollectionNewSettings);
                        oldIdWorkerOfSettingsCollectionNewSettings = em.merge(oldIdWorkerOfSettingsCollectionNewSettings);
                    }
                }
            }
            for (Note noteCollectionOldNote : noteCollectionOld) {
                if (!noteCollectionNew.contains(noteCollectionOldNote)) {
                    noteCollectionOldNote.setIdAuthor(null);
                    noteCollectionOldNote = em.merge(noteCollectionOldNote);
                }
            }
            for (Note noteCollectionNewNote : noteCollectionNew) {
                if (!noteCollectionOld.contains(noteCollectionNewNote)) {
                    Worker oldIdAuthorOfNoteCollectionNewNote = noteCollectionNewNote.getIdAuthor();
                    noteCollectionNewNote.setIdAuthor(worker);
                    noteCollectionNewNote = em.merge(noteCollectionNewNote);
                    if (oldIdAuthorOfNoteCollectionNewNote != null && !oldIdAuthorOfNoteCollectionNewNote.equals(worker)) {
                        oldIdAuthorOfNoteCollectionNewNote.getNoteCollection().remove(noteCollectionNewNote);
                        oldIdAuthorOfNoteCollectionNewNote = em.merge(oldIdAuthorOfNoteCollectionNewNote);
                    }
                }
            }
            for (Worker workerCollectionOldWorker : workerCollectionOld) {
                if (!workerCollectionNew.contains(workerCollectionOldWorker)) {
                    workerCollectionOldWorker.setIdBoss(null);
                    workerCollectionOldWorker = em.merge(workerCollectionOldWorker);
                }
            }
            for (Worker workerCollectionNewWorker : workerCollectionNew) {
                if (!workerCollectionOld.contains(workerCollectionNewWorker)) {
                    Worker oldIdBossOfWorkerCollectionNewWorker = workerCollectionNewWorker.getIdBoss();
                    workerCollectionNewWorker.setIdBoss(worker);
                    workerCollectionNewWorker = em.merge(workerCollectionNewWorker);
                    if (oldIdBossOfWorkerCollectionNewWorker != null && !oldIdBossOfWorkerCollectionNewWorker.equals(worker)) {
                        oldIdBossOfWorkerCollectionNewWorker.getWorkerCollection().remove(workerCollectionNewWorker);
                        oldIdBossOfWorkerCollectionNewWorker = em.merge(oldIdBossOfWorkerCollectionNewWorker);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = worker.getId();
                if (findWorker(id) == null) {
                    throw new NonexistentEntityException("The worker with id " + id + " no longer exists.");
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
            Worker worker;
            try {
                worker = em.getReference(Worker.class, id);
                worker.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The worker with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Right> rightCollectionOrphanCheck = worker.getRightCollection();
            for (Right rightCollectionOrphanCheckRight : rightCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Worker (" + worker + ") cannot be destroyed since the Right " + rightCollectionOrphanCheckRight + " in its rightCollection field has a non-nullable idWorker field.");
            }
            Collection<Assignment> assignmentCollectionOrphanCheck = worker.getAssignmentCollection();
            for (Assignment assignmentCollectionOrphanCheckAssignment : assignmentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Worker (" + worker + ") cannot be destroyed since the Assignment " + assignmentCollectionOrphanCheckAssignment + " in its assignmentCollection field has a non-nullable idWorker field.");
            }
            Collection<Task> taskCollectionOrphanCheck = worker.getTaskCollection();
            for (Task taskCollectionOrphanCheckTask : taskCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Worker (" + worker + ") cannot be destroyed since the Task " + taskCollectionOrphanCheckTask + " in its taskCollection field has a non-nullable idWorker field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Post idPost = worker.getIdPost();
            if (idPost != null) {
                idPost.getWorkerCollection().remove(worker);
                idPost = em.merge(idPost);
            }
            Team idTeam = worker.getIdTeam();
            if (idTeam != null) {
                idTeam.getWorkerCollection().remove(worker);
                idTeam = em.merge(idTeam);
            }
            Worker idBoss = worker.getIdBoss();
            if (idBoss != null) {
                idBoss.getWorkerCollection().remove(worker);
                idBoss = em.merge(idBoss);
            }
            Collection<Settings> settingsCollection = worker.getSettingsCollection();
            for (Settings settingsCollectionSettings : settingsCollection) {
                settingsCollectionSettings.setIdWorker(null);
                settingsCollectionSettings = em.merge(settingsCollectionSettings);
            }
            Collection<Note> noteCollection = worker.getNoteCollection();
            for (Note noteCollectionNote : noteCollection) {
                noteCollectionNote.setIdAuthor(null);
                noteCollectionNote = em.merge(noteCollectionNote);
            }
            Collection<Worker> workerCollection = worker.getWorkerCollection();
            for (Worker workerCollectionWorker : workerCollection) {
                workerCollectionWorker.setIdBoss(null);
                workerCollectionWorker = em.merge(workerCollectionWorker);
            }
            em.remove(worker);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Worker> findWorkerEntities() {
        return findWorkerEntities(true, -1, -1);
    }

    public List<Worker> findWorkerEntities(int maxResults, int firstResult) {
        return findWorkerEntities(false, maxResults, firstResult);
    }

    private List<Worker> findWorkerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Worker as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Worker findWorker(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Worker.class, id);
        } finally {
            em.close();
        }
    }

    public int getWorkerCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from Worker as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
