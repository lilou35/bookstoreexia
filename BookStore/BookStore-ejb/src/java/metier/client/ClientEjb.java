/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.client;

import ejb.entity.Client;
import ejb.transition.ClientJpaController;
import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="ClientEjb")
public class ClientEjb implements ClientEjbRemote, ClientEjbLocal {

    ClientJpaController jpaClient = new ClientJpaController();

    public List<Client> login(String login , String pass){
        return jpaClient.login(login, pass);
    }
    public List<Client> loginUnique(String login){
        return jpaClient.loginUnique(login);
    }
    
    public void updateClient(Client client){
        try {
            jpaClient.edit(client);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ClientEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ClientEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addClient(Client client){
        try {
            jpaClient.create(client);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(ClientEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ClientEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
 
}
