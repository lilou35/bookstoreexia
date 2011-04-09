/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package transaction;

import entity.Commande;
import entity.CommandePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Livre;
import entity.Client;
import entity.Journal;
import transaction.exceptions.NonexistentEntityException;
import transaction.exceptions.PreexistingEntityException;

/**
 *
 * @author Wikola
 */
public class CommandeJpaController {

    public CommandeJpaController() {
        emf = Persistence.createEntityManagerFactory("bookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commande commande) throws PreexistingEntityException, Exception {
        if (commande.getCommandePK() == null) {
            commande.setCommandePK(new CommandePK());
        }
        commande.getCommandePK().setJournalId(commande.getJournal().getJournalId());
        commande.getCommandePK().setClientid(commande.getClient().getClientid());
        commande.getCommandePK().setLivreid(commande.getLivre().getLivreid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livre livre = commande.getLivre();
            if (livre != null) {
                livre = em.getReference(livre.getClass(), livre.getLivreid());
                commande.setLivre(livre);
            }
            Client client = commande.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientid());
                commande.setClient(client);
            }
            Journal journal = commande.getJournal();
            if (journal != null) {
                journal = em.getReference(journal.getClass(), journal.getJournalId());
                commande.setJournal(journal);
            }
            em.persist(commande);
            if (livre != null) {
                livre.getCommandeList().add(commande);
                livre = em.merge(livre);
            }
            if (client != null) {
                client.getCommandeList().add(commande);
                client = em.merge(client);
            }
            if (journal != null) {
                journal.getCommandeList().add(commande);
                journal = em.merge(journal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCommande(commande.getCommandePK()) != null) {
                throw new PreexistingEntityException("Commande " + commande + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commande commande) throws NonexistentEntityException, Exception {
        commande.getCommandePK().setJournalId(commande.getJournal().getJournalId());
        commande.getCommandePK().setClientid(commande.getClient().getClientid());
        commande.getCommandePK().setLivreid(commande.getLivre().getLivreid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande persistentCommande = em.find(Commande.class, commande.getCommandePK());
            Livre livreOld = persistentCommande.getLivre();
            Livre livreNew = commande.getLivre();
            Client clientOld = persistentCommande.getClient();
            Client clientNew = commande.getClient();
            Journal journalOld = persistentCommande.getJournal();
            Journal journalNew = commande.getJournal();
            if (livreNew != null) {
                livreNew = em.getReference(livreNew.getClass(), livreNew.getLivreid());
                commande.setLivre(livreNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientid());
                commande.setClient(clientNew);
            }
            if (journalNew != null) {
                journalNew = em.getReference(journalNew.getClass(), journalNew.getJournalId());
                commande.setJournal(journalNew);
            }
            commande = em.merge(commande);
            if (livreOld != null && !livreOld.equals(livreNew)) {
                livreOld.getCommandeList().remove(commande);
                livreOld = em.merge(livreOld);
            }
            if (livreNew != null && !livreNew.equals(livreOld)) {
                livreNew.getCommandeList().add(commande);
                livreNew = em.merge(livreNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getCommandeList().remove(commande);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getCommandeList().add(commande);
                clientNew = em.merge(clientNew);
            }
            if (journalOld != null && !journalOld.equals(journalNew)) {
                journalOld.getCommandeList().remove(commande);
                journalOld = em.merge(journalOld);
            }
            if (journalNew != null && !journalNew.equals(journalOld)) {
                journalNew.getCommandeList().add(commande);
                journalNew = em.merge(journalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CommandePK id = commande.getCommandePK();
                if (findCommande(id) == null) {
                    throw new NonexistentEntityException("The commande with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CommandePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande commande;
            try {
                commande = em.getReference(Commande.class, id);
                commande.getCommandePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commande with id " + id + " no longer exists.", enfe);
            }
            Livre livre = commande.getLivre();
            if (livre != null) {
                livre.getCommandeList().remove(commande);
                livre = em.merge(livre);
            }
            Client client = commande.getClient();
            if (client != null) {
                client.getCommandeList().remove(commande);
                client = em.merge(client);
            }
            Journal journal = commande.getJournal();
            if (journal != null) {
                journal.getCommandeList().remove(commande);
                journal = em.merge(journal);
            }
            em.remove(commande);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Commande> findCommandeEntities() {
        return findCommandeEntities(true, -1, -1);
    }

    public List<Commande> findCommandeEntities(int maxResults, int firstResult) {
        return findCommandeEntities(false, maxResults, firstResult);
    }

    private List<Commande> findCommandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commande.class));
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

    public Commande findCommande(CommandePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commande.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commande> rt = cq.from(Commande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
