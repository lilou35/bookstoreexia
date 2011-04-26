/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.Libraire;

import ejb.entity.Libraire;
import ejb.transition.LibraireJpaController;
import ejb.transition.exceptions.IllegalOrphanException;
import ejb.transition.exceptions.NonexistentEntityException;
import ejb.transition.exceptions.PreexistingEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;

/**
 *
 * @author FloFlo
 */
@Stateless(mappedName="LibraireEjb")
public class LibraireEjb implements LibraireEjbRemote, LibraireEjbLocal {

    LibraireJpaController jpaLibraire = new LibraireJpaController();

    public List<Libraire> loginLibraire(String login , String pass){
        return jpaLibraire.login(login, pass);
    }
    public List<Libraire> loginUniqueLibraire(String login){
        return jpaLibraire.loginUnique(login);
    }
    
    public void updateLibraire(Libraire Libraire){
        try {
            jpaLibraire.edit(Libraire);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(LibraireEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(LibraireEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LibraireEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addLibraire(Libraire Libraire){
        try {
            jpaLibraire.create(Libraire);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(LibraireEjb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LibraireEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
