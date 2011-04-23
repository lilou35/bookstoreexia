/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.categorie;

import ejb.entity.Categorie;
import ejb.transition.CategorieJpaController;
import java.util.List;

import javax.ejb.Stateless;

/**
 *
 * @author Wikola
 */
@Stateless(mappedName="CategorieEjb")
public class CategorieEjb implements CategorieEjbRemote, CategorieEjbLocal {

    CategorieJpaController jpaCategorie = new CategorieJpaController();

    public List<Categorie> selectionnerCategories(int min, int max){
        if(max == -1){
            return jpaCategorie.findCategorieEntities();
        }
        return jpaCategorie.findCategorieEntities(max, min);
    }
    
    public Categorie selectionnerCategorie(int id){
        return jpaCategorie.findCategorie(id);
    }

   

  
 
}
