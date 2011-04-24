/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.auteur;

import ejb.entity.Auteur;
import ejb.transition.AuteurJpaController;
import java.util.List;

import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="AuteurEjb")
public class AuteurEjb implements AuteurEjbRemote, AuteurEjbLocal {

    AuteurJpaController jpaAuteur = new AuteurJpaController();

    public List<Auteur> selectionnerAuteur(int min, int max){
        if(max == -1){
            return jpaAuteur.findAuteurEntities();
        }
        return jpaAuteur.findAuteurEntities(max, min);
    }
    
    public Auteur selectionnerAuteur(int id){
        return jpaAuteur.findAuteur(id);
    }

   

  
 
}
