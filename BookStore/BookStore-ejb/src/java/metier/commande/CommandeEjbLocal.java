/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.commande;

import ejb.entity.Commande;
import ejb.entity.CommandePK;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface CommandeEjbLocal {
    public String about();
    public Commande selectionnerCommande(CommandePK commandePK);
    public Integer lastCommandeId();
    public Integer newCommandeId();
//    public List<Livre> selectionnerLivre(int min, int max);
}
