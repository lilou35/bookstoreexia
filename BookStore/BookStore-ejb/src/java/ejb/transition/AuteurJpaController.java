/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import ejb.entity.Auteur;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.entity.Livre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wikola
 */
public class AuteurJpaController {

    public AuteurJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Auteur auteur) throws PreexistingEntityException, Exception {
        if (auteur.getLivreList() == null) {
            auteur.setLivreList(new ArrayList<Livre>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Livre> attachedLivreList = new ArrayList<Livre>();
            for (Livre livreListLivreToAttach : auteur.getLivreList()) {
                livreListLivreToAttach = em.getReference(livreListLivreToAttach.getClass(), livreListLivreToAttach.getLivreid());
                attachedLivreList.add(livreListLivreToAttach);
            }
            auteur.setLivreList(attachedLivreList);
            em.persist(auteur);
            for (Livre livreListLivre : auteur.getLivreList()) {
                livreListLivre.getAuteurList().add(auteur);
                livreListLivre = em.merge(livreListLivre);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAuteur(auteur.getAuteurid()) != null) {
                throw new PreexistingEntityException("Auteur " + auteur + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Auteur auteur) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Auteur persistentAuteur = em.find(Auteur.class, auteur.getAuteurid());
            List<Livre> livreListOld = persistentAuteur.getLivreList();
            List<Livre> livreListNew = auteur.getLivreList();
            List<Livre> attachedLivreListNew = new ArrayList<Livre>();
            for (Livre livreListNewLivreToAttach : livreListNew) {
                livreListNewLivreToAttach = em.getReference(livreListNewLivreToAttach.getClass(), livreListNewLivreToAttach.getLivreid());
                attachedLivreListNew.add(livreListNewLivreToAttach);
            }
            livreListNew = attachedLivreListNew;
            auteur.setLivreList(livreListNew);
            auteur = em.merge(auteur);
            for (Livre livreListOldLivre : livreListOld) {
                if (!livreListNew.contains(livreListOldLivre)) {
                    livreListOldLivre.getAuteurList().remove(auteur);
                    livreListOldLivre = em.merge(livreListOldLivre);
                }
            }
            for (Livre livreListNewLivre : livreListNew) {
                if (!livreListOld.contains(livreListNewLivre)) {
                    livreListNewLivre.getAuteurList().add(auteur);
                    livreListNewLivre = em.merge(livreListNewLivre);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auteur.getAuteurid();
                if (findAuteur(id) == null) {
                    throw new NonexistentEntityException("The auteur with id " + id + " no longer exists.");
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
            Auteur auteur;
            try {
                auteur = em.getReference(Auteur.class, id);
                auteur.getAuteurid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auteur with id " + id + " no longer exists.", enfe);
            }
            List<Livre> livreList = auteur.getLivreList();
            for (Livre livreListLivre : livreList) {
                livreListLivre.getAuteurList().remove(auteur);
                livreListLivre = em.merge(livreListLivre);
            }
            em.remove(auteur);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Auteur> findAuteurEntities() {
        return findAuteurEntities(true, -1, -1);
    }

    public List<Auteur> findAuteurEntities(int maxResults, int firstResult) {
        return findAuteurEntities(false, maxResults, firstResult);
    }

    private List<Auteur> findAuteurEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Auteur.class));
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

    public Auteur findAuteur(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Auteur.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuteurCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Auteur> rt = cq.from(Auteur.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
