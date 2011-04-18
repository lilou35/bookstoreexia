/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.livre;

import ejb.entity.Livre;
import ejb.transition.LivreJpaController;
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

        return jpaLivre.findLivreEntities(max, min);
    }

   public String about(){
       return "tu es bon ";
   }
 
}
