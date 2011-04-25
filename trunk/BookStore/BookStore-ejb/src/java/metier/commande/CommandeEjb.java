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

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="CommandeEjb")
public class CommandeEjb implements CommandeEjbRemote, CommandeEjbLocal {

    CommandeJpaController jpaCommande = new CommandeJpaController();

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
    
   public String about(){
       return "tu es bon ";
   }
 
}
