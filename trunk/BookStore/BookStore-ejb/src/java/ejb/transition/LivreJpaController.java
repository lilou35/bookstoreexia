/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.entity.Auteur;
import ejb.entity.Categorie;
import ejb.entity.Commande;
import ejb.entity.Livre;
import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wikola
 */
public class LivreJpaController {

    public LivreJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Livre livre) {
        if (livre.getAuteurList() == null) {
            livre.setAuteurList(new ArrayList<Auteur>());
        }
        if (livre.getCommandeList() == null) {
            livre.setCommandeList(new ArrayList<Commande>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorie categorie = livre.getCategorie();
            if (categorie != null) {
                categorie = em.getReference(categorie.getClass(), categorie.getCategorieid());
                livre.setCategorie(categorie);
            }
            List<Auteur> attachedAuteurList = new ArrayList<Auteur>();
            for (Auteur auteurListAuteurToAttach : livre.getAuteurList()) {
                auteurListAuteurToAttach = em.getReference(auteurListAuteurToAttach.getClass(), auteurListAuteurToAttach.getAuteurid());
                attachedAuteurList.add(auteurListAuteurToAttach);
            }
            livre.setAuteurList(attachedAuteurList);
            List<Commande> attachedCommandeList = new ArrayList<Commande>();
            for (Commande commandeListCommandeToAttach : livre.getCommandeList()) {
                commandeListCommandeToAttach = em.getReference(commandeListCommandeToAttach.getClass(), commandeListCommandeToAttach.getCommandePK());
                attachedCommandeList.add(commandeListCommandeToAttach);
            }
            livre.setCommandeList(attachedCommandeList);
            em.persist(livre);
            if (categorie != null) {
                categorie.getLivreList().add(livre);
                categorie = em.merge(categorie);
            }
            for (Auteur auteurListAuteur : livre.getAuteurList()) {
                auteurListAuteur.getLivreList().add(livre);
                auteurListAuteur = em.merge(auteurListAuteur);
            }
            for (Commande commandeListCommande : livre.getCommandeList()) {
                Livre oldLivreOfCommandeListCommande = commandeListCommande.getLivre();
                commandeListCommande.setLivre(livre);
                commandeListCommande = em.merge(commandeListCommande);
                if (oldLivreOfCommandeListCommande != null) {
                    oldLivreOfCommandeListCommande.getCommandeList().remove(commandeListCommande);
                    oldLivreOfCommandeListCommande = em.merge(oldLivreOfCommandeListCommande);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Livre livre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livre persistentLivre = em.find(Livre.class, livre.getLivreid());
            Categorie categorieOld = persistentLivre.getCategorie();
            Categorie categorieNew = livre.getCategorie();
            List<Auteur> auteurListOld = persistentLivre.getAuteurList();
            List<Auteur> auteurListNew = livre.getAuteurList();
            List<Commande> commandeListOld = persistentLivre.getCommandeList();
            List<Commande> commandeListNew = livre.getCommandeList();
            List<String> illegalOrphanMessages = null;
            for (Commande commandeListOldCommande : commandeListOld) {
                if (!commandeListNew.contains(commandeListOldCommande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Commande " + commandeListOldCommande + " since its livre field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categorieNew != null) {
                categorieNew = em.getReference(categorieNew.getClass(), categorieNew.getCategorieid());
                livre.setCategorie(categorieNew);
            }
            List<Auteur> attachedAuteurListNew = new ArrayList<Auteur>();
            for (Auteur auteurListNewAuteurToAttach : auteurListNew) {
                auteurListNewAuteurToAttach = em.getReference(auteurListNewAuteurToAttach.getClass(), auteurListNewAuteurToAttach.getAuteurid());
                attachedAuteurListNew.add(auteurListNewAuteurToAttach);
            }
            auteurListNew = attachedAuteurListNew;
            livre.setAuteurList(auteurListNew);
            List<Commande> attachedCommandeListNew = new ArrayList<Commande>();
            for (Commande commandeListNewCommandeToAttach : commandeListNew) {
                commandeListNewCommandeToAttach = em.getReference(commandeListNewCommandeToAttach.getClass(), commandeListNewCommandeToAttach.getCommandePK());
                attachedCommandeListNew.add(commandeListNewCommandeToAttach);
            }
            commandeListNew = attachedCommandeListNew;
            livre.setCommandeList(commandeListNew);
            livre = em.merge(livre);
            if (categorieOld != null && !categorieOld.equals(categorieNew)) {
                categorieOld.getLivreList().remove(livre);
                categorieOld = em.merge(categorieOld);
            }
            if (categorieNew != null && !categorieNew.equals(categorieOld)) {
                categorieNew.getLivreList().add(livre);
                categorieNew = em.merge(categorieNew);
            }
            for (Auteur auteurListOldAuteur : auteurListOld) {
                if (!auteurListNew.contains(auteurListOldAuteur)) {
                    auteurListOldAuteur.getLivreList().remove(livre);
                    auteurListOldAuteur = em.merge(auteurListOldAuteur);
                }
            }
            for (Auteur auteurListNewAuteur : auteurListNew) {
                if (!auteurListOld.contains(auteurListNewAuteur)) {
                    auteurListNewAuteur.getLivreList().add(livre);
                    auteurListNewAuteur = em.merge(auteurListNewAuteur);
                }
            }
            for (Commande commandeListNewCommande : commandeListNew) {
                if (!commandeListOld.contains(commandeListNewCommande)) {
                    Livre oldLivreOfCommandeListNewCommande = commandeListNewCommande.getLivre();
                    commandeListNewCommande.setLivre(livre);
                    commandeListNewCommande = em.merge(commandeListNewCommande);
                    if (oldLivreOfCommandeListNewCommande != null && !oldLivreOfCommandeListNewCommande.equals(livre)) {
                        oldLivreOfCommandeListNewCommande.getCommandeList().remove(commandeListNewCommande);
                        oldLivreOfCommandeListNewCommande = em.merge(oldLivreOfCommandeListNewCommande);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = livre.getLivreid();
                if (findLivre(id) == null) {
                    throw new NonexistentEntityException("The livre with id " + id + " no longer exists.");
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
            Livre livre;
            try {
                livre = em.getReference(Livre.class, id);
                livre.getLivreid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Commande> commandeListOrphanCheck = livre.getCommandeList();
            for (Commande commandeListOrphanCheckCommande : commandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Livre (" + livre + ") cannot be destroyed since the Commande " + commandeListOrphanCheckCommande + " in its commandeList field has a non-nullable livre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categorie categorie = livre.getCategorie();
            if (categorie != null) {
                categorie.getLivreList().remove(livre);
                categorie = em.merge(categorie);
            }
            List<Auteur> auteurList = livre.getAuteurList();
            for (Auteur auteurListAuteur : auteurList) {
                auteurListAuteur.getLivreList().remove(livre);
                auteurListAuteur = em.merge(auteurListAuteur);
            }
            em.remove(livre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Livre> findLivreEntities() {
        return findLivreEntities(true, -1, -1);
    }

    public List<Livre> findLivreEntities(int maxResults, int firstResult) {
        return findLivreEntities(false, maxResults, firstResult);
    }

    private List<Livre> findLivreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livre.class));
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

    public Livre findLivre(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Livre.class, id);
        } finally {
            em.close();
        }
    }

    public int getLivreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livre> rt = cq.from(Livre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
