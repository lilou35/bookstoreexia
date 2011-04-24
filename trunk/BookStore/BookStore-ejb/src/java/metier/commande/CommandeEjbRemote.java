/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.commande;

import ejb.entity.Commande;
import ejb.entity.CommandePK;
import javax.ejb.Remote;

/**
 *
 * @author Wikola
 */
@Remote
public interface CommandeEjbRemote {
//    public List<Livre> selectionnerLivre(int min, int max);
    public Commande selectionnerCommande(CommandePK commandePK);
    public String about();
}
