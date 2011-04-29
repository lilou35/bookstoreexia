/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import ejb.entity.Commande;
import ejb.entity.CommandePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.entity.Livre;
import ejb.entity.Journal;
import ejb.entity.Client;
import ejb.entity.Commande_;
import java.util.Date;

/**
 *
 * @author Wikola
 */
public class CommandeJpaController {

    public CommandeJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commande commande) throws PreexistingEntityException, Exception {
        if (commande.getCommandePK() == null) {
            commande.setCommandePK(new CommandePK());
        }
        commande.getCommandePK().setClientid(commande.getClient().getClientid());
        commande.getCommandePK().setJournalId(commande.getJournal().getJournalId());
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
            Journal journal = commande.getJournal();
            if (journal != null) {
                journal = em.getReference(journal.getClass(), journal.getJournalId());
                commande.setJournal(journal);
            }
            Client client = commande.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getClientid());
                commande.setClient(client);
            }
            em.persist(commande);
            if (livre != null) {
                livre.getCommandeList().add(commande);
                livre = em.merge(livre);
            }
            if (journal != null) {
                journal.getCommandeList().add(commande);
                journal = em.merge(journal);
            }
            if (client != null) {
                client.getCommandeList().add(commande);
                client = em.merge(client);
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
        commande.getCommandePK().setClientid(commande.getClient().getClientid());
        commande.getCommandePK().setJournalId(commande.getJournal().getJournalId());
        commande.getCommandePK().setLivreid(commande.getLivre().getLivreid());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande persistentCommande = em.find(Commande.class, commande.getCommandePK());
            Livre livreOld = persistentCommande.getLivre();
            Livre livreNew = commande.getLivre();
            Journal journalOld = persistentCommande.getJournal();
            Journal journalNew = commande.getJournal();
            Client clientOld = persistentCommande.getClient();
            Client clientNew = commande.getClient();
            if (livreNew != null) {
                livreNew = em.getReference(livreNew.getClass(), livreNew.getLivreid());
                commande.setLivre(livreNew);
            }
            if (journalNew != null) {
                journalNew = em.getReference(journalNew.getClass(), journalNew.getJournalId());
                commande.setJournal(journalNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getClientid());
                commande.setClient(clientNew);
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
            if (journalOld != null && !journalOld.equals(journalNew)) {
                journalOld.getCommandeList().remove(commande);
                journalOld = em.merge(journalOld);
            }
            if (journalNew != null && !journalNew.equals(journalOld)) {
                journalNew.getCommandeList().add(commande);
                journalNew = em.merge(journalNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getCommandeList().remove(commande);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getCommandeList().add(commande);
                clientNew = em.merge(clientNew);
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
            Journal journal = commande.getJournal();
            if (journal != null) {
                journal.getCommandeList().remove(commande);
                journal = em.merge(journal);
            }
            Client client = commande.getClient();
            if (client != null) {
                client.getCommandeList().remove(commande);
                client = em.merge(client);
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

    public List<Commande> findCommande(int commandeId){
        EntityManager em = getEntityManager();
        try {
            //création requete criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Commande> rt = cq.from(Commande.class);

            //restriction de la requete
            cq.select(rt)
                .where(cb.equal(rt.get(Commande_.commandeid), commandeId));

            //resultat de la requete          
            Query q = em.createQuery(cq);
            return q.getResultList();
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
    
    public int lastCommandeId() {
        EntityManager em = getEntityManager();
        try {
            //création requete criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Commande> rt = cq.from(Commande.class);
            
            //restriction de la requete
            cq.select(rt)
                .orderBy(cb.desc(rt.get(Commande_.commandeid))); 
            
            //resultat de la requete          
            Query q = em.createQuery(cq);
            List<Commande> listCommande = q.getResultList();
            if(listCommande.isEmpty()){
                return 1; //si aucune commande alors on commence par 1
            }
            else { return listCommande.get(0).getCommandeid();}
        } finally {
            em.close();
        }
    }

    public List<Commande> GroupByCommandeId (String etat, Date date){
        EntityManager em = getEntityManager();
        try {
            //création requete criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Commande> rt = cq.from(Commande.class);
            
            //restriction de la requete
            cq.select(rt);
                cq.groupBy(rt.get(Commande_.commandeid), rt.get(Commande_.commandeetat));
                if(!etat.isEmpty() && date!=null){
                    cq.having(cb.equal(rt.get(Commande_.commandeetat), etat));
                }
            
            //resultat de la requete          
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
