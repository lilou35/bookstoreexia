/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.client;

import ejb.entity.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface ClientEjbLocal {
     

    public List<Client> login(String login , String pass);
}
