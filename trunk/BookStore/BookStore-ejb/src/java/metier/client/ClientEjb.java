/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.client;

import ejb.entity.Client;
import ejb.transition.ClientJpaController;
import java.util.List;

import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="ClientEjb")
public class ClientEjb implements ClientEjbRemote, ClientEjbLocal {

    ClientJpaController jpaClient = new ClientJpaController();

    public List<Client> login(String login , String pass){
        return jpaClient.findClientEntities();
    }

  
 
}
