/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.transition;

import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import ejb.entity.Client;
import ejb.entity.Client_;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ejb.entity.Commande;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Wikola
 */
public class ClientJpaController {

    public ClientJpaController() {
        emf = Persistence.createEntityManagerFactory("BookStore-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) throws PreexistingEntityException, Exception {
        if (client.getCommandeList() == null) {
            client.setCommandeList(new ArrayList<Commande>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Commande> attachedCommandeList = new ArrayList<Commande>();
            for (Commande commandeListCommandeToAttach : client.getCommandeList()) {
                commandeListCommandeToAttach = em.getReference(commandeListCommandeToAttach.getClass(), commandeListCommandeToAttach.getCommandePK());
                attachedCommandeList.add(commandeListCommandeToAttach);
            }
            client.setCommandeList(attachedCommandeList);
            em.persist(client);
            for (Commande commandeListCommande : client.getCommandeList()) {
                Client oldClientOfCommandeListCommande = commandeListCommande.getClient();
                commandeListCommande.setClient(client);
                commandeListCommande = em.merge(commandeListCommande);
                if (oldClientOfCommandeListCommande != null) {
                    oldClientOfCommandeListCommande.getCommandeList().remove(commandeListCommande);
                    oldClientOfCommandeListCommande = em.merge(oldClientOfCommandeListCommande);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClient(client.getClientid()) != null) {
                throw new PreexistingEntityException("Client " + client + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getClientid());
            List<Commande> commandeListOld = persistentClient.getCommandeList();
            List<Commande> commandeListNew = client.getCommandeList();
            List<String> illegalOrphanMessages = null;
            for (Commande commandeListOldCommande : commandeListOld) {
                if (!commandeListNew.contains(commandeListOldCommande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Commande " + commandeListOldCommande + " since its client field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Commande> attachedCommandeListNew = new ArrayList<Commande>();
            for (Commande commandeListNewCommandeToAttach : commandeListNew) {
                commandeListNewCommandeToAttach = em.getReference(commandeListNewCommandeToAttach.getClass(), commandeListNewCommandeToAttach.getCommandePK());
                attachedCommandeListNew.add(commandeListNewCommandeToAttach);
            }
            commandeListNew = attachedCommandeListNew;
            client.setCommandeList(commandeListNew);
            client = em.merge(client);
            for (Commande commandeListNewCommande : commandeListNew) {
                if (!commandeListOld.contains(commandeListNewCommande)) {
                    Client oldClientOfCommandeListNewCommande = commandeListNewCommande.getClient();
                    commandeListNewCommande.setClient(client);
                    commandeListNewCommande = em.merge(commandeListNewCommande);
                    if (oldClientOfCommandeListNewCommande != null && !oldClientOfCommandeListNewCommande.equals(client)) {
                        oldClientOfCommandeListNewCommande.getCommandeList().remove(commandeListNewCommande);
                        oldClientOfCommandeListNewCommande = em.merge(oldClientOfCommandeListNewCommande);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getClientid();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
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
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getClientid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Commande> commandeListOrphanCheck = client.getCommandeList();
            for (Commande commandeListOrphanCheckCommande : commandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Client (" + client + ") cannot be destroyed since the Commande " + commandeListOrphanCheckCommande + " in its commandeList field has a non-nullable client field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
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

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Client> loginUnique(Client client) {
        EntityManager em = getEntityManager();
        try {
            
            //création requete criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery( Client.class );
            Root<Client> clientRoot = cq.from(Client.class);
            
            //restriction de la requete
                cq.select(clientRoot)
                    .where(cb.equal(clientRoot.get(Client_.clientlogin),client.getClientlogin()));
            
            //resultat de la requete
            Query q = em.createQuery(cq);
            List<Client> listClient = new ArrayList<Client>(0);
                    listClient = q.getResultList();
            for(Client c : listClient)
            {
                if(c.getClientid().intValue() == client.getClientid().intValue() && listClient.size()==1){
                  listClient = new ArrayList<Client>(0);
                } 
            }
            return listClient;
        } finally {
            em.close();
        }
    }
    
    public List<Client> login(String login, String pass) {
        EntityManager em = getEntityManager();
        try {
            //création requete criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery( Client.class );
            Root<Client> clientRoot = cq.from(Client.class);
            
            //restriction de la requete
            cq.select(clientRoot)
                    .where(cb.equal(clientRoot.get(Client_.clientlogin), login), cb.equal(clientRoot.get(Client_.clientmdp), pass));
            
            //resultat de la requete
            Query q = em.createQuery(cq);
            return ((List<Client>) q.getResultList());
        } finally {
            em.close();
        }
    }

}
