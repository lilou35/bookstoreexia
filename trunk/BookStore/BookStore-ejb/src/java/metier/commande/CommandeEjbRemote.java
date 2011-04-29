/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.commande;

import ejb.entity.Commande;
import ejb.entity.CommandePK;
import ejb.entity.Journal;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Wikola
 */
@Remote
public interface CommandeEjbRemote {
//    public List<Livre> selectionnerLivre(int min, int max);
    public Commande selectionnerCommande(CommandePK commandePK);
    public Integer lastCommandeId();
    public Integer newCommandeId();
    public void commander(Commande commande);
    public void decommander(Commande commande);
    public List<Commande> listCommande(int commandeId);
    public int verifCommande(Commande commande);
    public String about();
    public List<Commande> listCommandeGroupBy (String etat, Date date);
    public void updateCommande(Commande commande);
    public Journal addJournal(Journal journal);
    public void updateJournal(Journal journal);
}
