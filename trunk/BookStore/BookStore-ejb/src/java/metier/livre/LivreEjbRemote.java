/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.livre;

import ejb.entity.Livre;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Wikola
 */
@Remote
public interface LivreEjbRemote {
    public List<Livre> selectionnerLivre(int min, int max);
    public Livre selectionnerLivre(int id);
    public List<Livre> topDix(int nbr);
    public List<Livre> selectionnerLivre(String recherche, Integer initLigne, Integer maxLigne);
    public String about();
    public void updateLivre(Livre Livre);
    public Livre addLivre(Livre Livre);
}
