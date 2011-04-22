/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.client;


import ejb.entity.Client;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Wikola
 */
@Remote
public interface ClientEjbRemote {
   public List<Client> login(String login , String pass);
}
