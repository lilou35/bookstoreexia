/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transaction;

import entity.Journal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Libraire;
import entity.Commande;
import java.util.ArrayList;
import java.util.List;
import transaction.exceptions.IllegalOrphanException;
import transaction.exceptions.NonexistentEntityException;
import transaction.exceptions.PreexistingEntityException;

/**
 *
 * @author Wikola
 */
public class JournalJpaController {

    public JournalJpaController() {
        emf = Persistence.createEntityManagerFactory("bookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Journal journal) throws PreexistingEntityException, Exception {
        if (journal.getCommandeList() == null) {
            journal.setCommandeList(new ArrayList<Commande>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libraire libraire = journal.getLibraire();
            if (libraire != null) {
                libraire = em.getReference(libraire.getClass(), libraire.getLibraireid());
                journal.setLibraire(libraire);
            }
            List<Commande> attachedCommandeList = new ArrayList<Commande>();
            for (Commande commandeListCommandeToAttach : journal.getCommandeList()) {
                commandeListCommandeToAttach = em.getReference(commandeListCommandeToAttach.getClass(), commandeListCommandeToAttach.getCommandePK());
                attachedCommandeList.add(commandeListCommandeToAttach);
            }
            journal.setCommandeList(attachedCommandeList);
            em.persist(journal);
            if (libraire != null) {
                libraire.getJournalList().add(journal);
                libraire = em.merge(libraire);
            }
            for (Commande commandeListCommande : journal.getCommandeList()) {
                Journal oldJournalOfCommandeListCommande = commandeListCommande.getJournal();
                commandeListCommande.setJournal(journal);
                commandeListCommande = em.merge(commandeListCommande);
                if (oldJournalOfCommandeListCommande != null) {
                    oldJournalOfCommandeListCommande.getCommandeList().remove(commandeListCommande);
                    oldJournalOfCommandeListCommande = em.merge(oldJournalOfCommandeListCommande);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJournal(journal.getJournalId()) != null) {
                throw new PreexistingEntityException("Journal " + journal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Journal journal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Journal persistentJournal = em.find(Journal.class, journal.getJournalId());
            Libraire libraireOld = persistentJournal.getLibraire();
            Libraire libraireNew = journal.getLibraire();
            List<Commande> commandeListOld = persistentJournal.getCommandeList();
            List<Commande> commandeListNew = journal.getCommandeList();
            List<String> illegalOrphanMessages = null;
            for (Commande commandeListOldCommande : commandeListOld) {
                if (!commandeListNew.contains(commandeListOldCommande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Commande " + commandeListOldCommande + " since its journal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (libraireNew != null) {
                libraireNew = em.getReference(libraireNew.getClass(), libraireNew.getLibraireid());
                journal.setLibraire(libraireNew);
            }
            List<Commande> attachedCommandeListNew = new ArrayList<Commande>();
            for (Commande commandeListNewCommandeToAttach : commandeListNew) {
                commandeListNewCommandeToAttach = em.getReference(commandeListNewCommandeToAttach.getClass(), commandeListNewCommandeToAttach.getCommandePK());
                attachedCommandeListNew.add(commandeListNewCommandeToAttach);
            }
            commandeListNew = attachedCommandeListNew;
            journal.setCommandeList(commandeListNew);
            journal = em.merge(journal);
            if (libraireOld != null && !libraireOld.equals(libraireNew)) {
                libraireOld.getJournalList().remove(journal);
                libraireOld = em.merge(libraireOld);
            }
            if (libraireNew != null && !libraireNew.equals(libraireOld)) {
                libraireNew.getJournalList().add(journal);
                libraireNew = em.merge(libraireNew);
            }
            for (Commande commandeListNewCommande : commandeListNew) {
                if (!commandeListOld.contains(commandeListNewCommande)) {
                    Journal oldJournalOfCommandeListNewCommande = commandeListNewCommande.getJournal();
                    commandeListNewCommande.setJournal(journal);
                    commandeListNewCommande = em.merge(commandeListNewCommande);
                    if (oldJournalOfCommandeListNewCommande != null && !oldJournalOfCommandeListNewCommande.equals(journal)) {
                        oldJournalOfCommandeListNewCommande.getCommandeList().remove(commandeListNewCommande);
                        oldJournalOfCommandeListNewCommande = em.merge(oldJournalOfCommandeListNewCommande);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = journal.getJournalId();
                if (findJournal(id) == null) {
                    throw new NonexistentEntityException("The journal with id " + id + " no longer exists.");
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
            Journal journal;
            try {
                journal = em.getReference(Journal.class, id);
                journal.getJournalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The journal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Commande> commandeListOrphanCheck = journal.getCommandeList();
            for (Commande commandeListOrphanCheckCommande : commandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Journal (" + journal + ") cannot be destroyed since the Commande " + commandeListOrphanCheckCommande + " in its commandeList field has a non-nullable journal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Libraire libraire = journal.getLibraire();
            if (libraire != null) {
                libraire.getJournalList().remove(journal);
                libraire = em.merge(libraire);
            }
            em.remove(journal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Journal> findJournalEntities() {
        return findJournalEntities(true, -1, -1);
    }

    public List<Journal> findJournalEntities(int maxResults, int firstResult) {
        return findJournalEntities(false, maxResults, firstResult);
    }

    private List<Journal> findJournalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Journal.class));
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

    public Journal findJournal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Journal.class, id);
        } finally {
            em.close();
        }
    }

    public int getJournalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Journal> rt = cq.from(Journal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
