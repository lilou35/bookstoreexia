/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.auteur;

import ejb.entity.Auteur;
import ejb.transition.AuteurJpaController;
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
    
    public void updateAuteur(Auteur Auteur){
        try {
            jpaAuteur.edit(Auteur);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(AuteurEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AuteurEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuteurEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addAuteur(Auteur Auteur){
        try {
            jpaAuteur.create(Auteur);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(AuteurEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuteurEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
