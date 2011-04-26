/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.Libraire;

import ejb.entity.Libraire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface LibraireEjbLocal {
     

    public List<Libraire> login(String login , String pass);
    public List<Libraire> loginUnique(String login);
    public void updateLibraire(Libraire Libraire);
    public void addLibraire(Libraire Libraire);
}
