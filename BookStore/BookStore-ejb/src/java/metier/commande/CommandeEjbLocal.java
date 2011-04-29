/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.commande;

import ejb.entity.Commande;
import ejb.entity.CommandePK;
import java.util.Date;
import java.util.List;
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
    public void commander(Commande commande);
    public void decommander(Commande commande);
    public List<Commande> listCommande(int commandeId);
    public int verifCommande(Commande commande);
    public List<Commande> listCommandeGroupBy (String etat, Date date);
    public void updateCommande(Commande commande);
//    public List<Livre> selectionnerLivre(int min, int max);
}
