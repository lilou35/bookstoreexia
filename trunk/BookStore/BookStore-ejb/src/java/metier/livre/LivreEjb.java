/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.livre;

import ejb.entity.Livre;
import ejb.transition.LivreJpaController;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="LivreEjb")
public class LivreEjb implements LivreEjbRemote, LivreEjbLocal {

    LivreJpaController jpaLivre = new LivreJpaController();

    public List<Livre> selectionnerLivre(int min, int max){
        System.out.print("###################### selectionnerLivre #################");
        List<Livre> livres = new ArrayList<Livre>(0);
        livres.addAll(jpaLivre.findLivreEntities());
//        Livre livre = new Livre(1);
//        livre.setLivretitre("lalalala");
//        List<Livre> livres = new ArrayList<Livre>(0);
//        livres.add(livre);
        System.out.print("###################### nb Livre: "+ livres.size()+" #################");
        return livres;
    }

   public String about(){
       return "tu es bon ";
   }
 
}
