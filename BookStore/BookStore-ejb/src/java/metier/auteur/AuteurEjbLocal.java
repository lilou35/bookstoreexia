/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.auteur;


import ejb.entity.Auteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface AuteurEjbLocal {
     

    public List<Auteur> selectionnerAuteur(int min, int max);
       
    public Auteur selectionnerAuteur(int id);
    public void updateAuteur(Auteur Auteur);
    public Auteur addAuteur(Auteur Auteur);
}
