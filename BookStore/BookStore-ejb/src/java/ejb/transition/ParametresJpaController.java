/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import ejb.entity.Parametres;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Wikola
 */
public class ParametresJpaController {

    public ParametresJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Parametres parametres) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(parametres);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParametres(parametres.getParanblivrepage()) != null) {
                throw new PreexistingEntityException("Parametres " + parametres + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Parametres parametres) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parametres = em.merge(parametres);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametres.getParanblivrepage();
                if (findParametres(id) == null) {
                    throw new NonexistentEntityException("The parametres with id " + id + " no longer exists.");
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
            Parametres parametres;
            try {
                parametres = em.getReference(Parametres.class, id);
                parametres.getParanblivrepage();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametres with id " + id + " no longer exists.", enfe);
            }
            em.remove(parametres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Parametres> findParametresEntities() {
        return findParametresEntities(true, -1, -1);
    }

    public List<Parametres> findParametresEntities(int maxResults, int firstResult) {
        return findParametresEntities(false, maxResults, firstResult);
    }

    private List<Parametres> findParametresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Parametres.class));
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

    public Parametres findParametres(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Parametres.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Parametres> rt = cq.from(Parametres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
