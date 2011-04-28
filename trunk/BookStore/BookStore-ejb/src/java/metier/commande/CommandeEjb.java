/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.commande;

import ejb.entity.Commande;
import ejb.entity.CommandePK;
import ejb.entity.Livre;
import ejb.transition.CommandeJpaController;
import ejb.transition.LivreJpaController;
import ejb.transition.exceptions.PreexistingEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="CommandeEjb")
public class CommandeEjb implements CommandeEjbRemote, CommandeEjbLocal {

    CommandeJpaController jpaCommande = new CommandeJpaController();
    LivreJpaController jpaLivre = new LivreJpaController();

//    public List<Commande> selectionnerCommande(int min, int max){
////        System.out.print("###################### selectionnerLivre #################");
////        List<Livre> livres = new ArrayList<Livre>(0);
////        livres.addAll(jpaLivre.findLivreEntities());
//////        Livre livre = new Livre(1);
//////        livre.setLivretitre("lalalala");
//////        List<Livre> livres = new ArrayList<Livre>(0);
//////        livres.add(livre);
////        System.out.print("###################### nb Livre: "+ livres.size()+" #################");
////        return livres;
//    }
    
    public Commande selectionnerCommande(CommandePK commandePK){
        return jpaCommande.findCommande(commandePK);
    }
    
    public Integer lastCommandeId(){
        return jpaCommande.lastCommandeId();
    }
    public Integer newCommandeId(){
        return (lastCommandeId()+ 1);
    }
    
    public int verifCommande(Commande commande){
        int stockApres = commande.getLivre().getLivrestock() - commande.getCommandequantite();
        if(stockApres < 0){//si commande > qte alors commande = qte
            commande.setCommandequantite(commande.getLivre().getLivrestock());
        }
        if(commande.getLivre().getLivrestock() <= 0){//si commande <= 0 alors il n'y a plus de commande
            commande = null;
        }
        return stockApres;
    }
    
    public void commander(Commande commande){
        try {
            Livre livre = commande.getLivre();
            livre.setLivrestock(livre.getLivrestock() - commande.getCommandequantite());
            livre.setLivrenbvente(livre.getLivrenbvente() + commande.getCommandequantite());
            jpaCommande.create(commande);
            jpaLivre.edit(livre);
            
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(CommandeEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandeEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void decommander(Commande commande){
        try {
            Livre livre = commande.getLivre();
            livre.setLivrestock(livre.getLivrestock() + commande.getCommandequantite());
            livre.setLivrenbvente(livre.getLivrenbvente() - commande.getCommandequantite());
            jpaCommande.destroy(commande.getCommandePK());
            jpaLivre.edit(livre);
            
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(CommandeEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CommandeEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Commande> listCommande(int commandeId){
        return jpaCommande.findCommande(commandeId);
    }
    
   public String about(){
       return "tu es bon ";
   }
 
}
