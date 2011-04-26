/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.livre;

import ejb.entity.Livre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface LivreEjbLocal {
    public String about();
    public Livre selectionnerLivre(int id);
    public List<Livre> selectionnerLivre(int min, int max);
    public List<Livre> topDix(int nbr);
    public List<Livre> selectionnerLivre(String recherche, Integer initLigne, Integer maxLigne);
    public void updateLivre(Livre Livre);
    public void addLivre(Livre Livre);
}
