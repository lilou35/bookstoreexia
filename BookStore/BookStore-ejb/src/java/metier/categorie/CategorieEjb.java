/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.categorie;

import ejb.entity.Categorie;
import ejb.transition.CategorieJpaController;
import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void updateCategorie(Categorie Categorie){
        try {
            jpaCategorie.edit(Categorie);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(CategorieEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CategorieEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategorieEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCategorie(Categorie Categorie){
        try {
            jpaCategorie.create(Categorie);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(CategorieEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategorieEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
