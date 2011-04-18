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
     public String about();
}
