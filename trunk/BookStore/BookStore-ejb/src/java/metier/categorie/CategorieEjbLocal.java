/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier.categorie;


import ejb.entity.Categorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Wikola
 */
@Local 
public interface CategorieEjbLocal {
     

    public List<Categorie> selectionnerCategories(int min, int max);
    public Categorie selectionnerCategorie(int id);
    public void updateCategorie(Categorie Categorie);
    public void addCategorie(Categorie Categorie);
}
