/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import ejb.entity.Libraire;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.entity.Journal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wikola
 */
public class LibraireJpaController {

    public LibraireJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libraire libraire) throws PreexistingEntityException, Exception {
        if (libraire.getJournalList() == null) {
            libraire.setJournalList(new ArrayList<Journal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Journal> attachedJournalList = new ArrayList<Journal>();
            for (Journal journalListJournalToAttach : libraire.getJournalList()) {
                journalListJournalToAttach = em.getReference(journalListJournalToAttach.getClass(), journalListJournalToAttach.getJournalId());
                attachedJournalList.add(journalListJournalToAttach);
            }
            libraire.setJournalList(attachedJournalList);
            em.persist(libraire);
            for (Journal journalListJournal : libraire.getJournalList()) {
                Libraire oldLibraireOfJournalListJournal = journalListJournal.getLibraire();
                journalListJournal.setLibraire(libraire);
                journalListJournal = em.merge(journalListJournal);
                if (oldLibraireOfJournalListJournal != null) {
                    oldLibraireOfJournalListJournal.getJournalList().remove(journalListJournal);
                    oldLibraireOfJournalListJournal = em.merge(oldLibraireOfJournalListJournal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLibraire(libraire.getLibraireid()) != null) {
                throw new PreexistingEntityException("Libraire " + libraire + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libraire libraire) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libraire persistentLibraire = em.find(Libraire.class, libraire.getLibraireid());
            List<Journal> journalListOld = persistentLibraire.getJournalList();
            List<Journal> journalListNew = libraire.getJournalList();
            List<String> illegalOrphanMessages = null;
            for (Journal journalListOldJournal : journalListOld) {
                if (!journalListNew.contains(journalListOldJournal)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Journal " + journalListOldJournal + " since its libraire field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Journal> attachedJournalListNew = new ArrayList<Journal>();
            for (Journal journalListNewJournalToAttach : journalListNew) {
                journalListNewJournalToAttach = em.getReference(journalListNewJournalToAttach.getClass(), journalListNewJournalToAttach.getJournalId());
                attachedJournalListNew.add(journalListNewJournalToAttach);
            }
            journalListNew = attachedJournalListNew;
            libraire.setJournalList(journalListNew);
            libraire = em.merge(libraire);
            for (Journal journalListNewJournal : journalListNew) {
                if (!journalListOld.contains(journalListNewJournal)) {
                    Libraire oldLibraireOfJournalListNewJournal = journalListNewJournal.getLibraire();
                    journalListNewJournal.setLibraire(libraire);
                    journalListNewJournal = em.merge(journalListNewJournal);
                    if (oldLibraireOfJournalListNewJournal != null && !oldLibraireOfJournalListNewJournal.equals(libraire)) {
                        oldLibraireOfJournalListNewJournal.getJournalList().remove(journalListNewJournal);
                        oldLibraireOfJournalListNewJournal = em.merge(oldLibraireOfJournalListNewJournal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libraire.getLibraireid();
                if (findLibraire(id) == null) {
                    throw new NonexistentEntityException("The libraire with id " + id + " no longer exists.");
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
            Libraire libraire;
            try {
                libraire = em.getReference(Libraire.class, id);
                libraire.getLibraireid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libraire with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Journal> journalListOrphanCheck = libraire.getJournalList();
            for (Journal journalListOrphanCheckJournal : journalListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Libraire (" + libraire + ") cannot be destroyed since the Journal " + journalListOrphanCheckJournal + " in its journalList field has a non-nullable libraire field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(libraire);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libraire> findLibraireEntities() {
        return findLibraireEntities(true, -1, -1);
    }

    public List<Libraire> findLibraireEntities(int maxResults, int firstResult) {
        return findLibraireEntities(false, maxResults, firstResult);
    }

    private List<Libraire> findLibraireEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libraire.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Libraire findLibraire(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libraire.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibraireCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libraire> rt = cq.from(Libraire.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
