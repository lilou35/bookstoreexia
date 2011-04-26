/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import ejb.entity.Categorie;
import javax.validation.Valid;
import metier.categorie.CategorieEjbLocal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wikola
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/categorie/*")
public class CategorieController {

    
    
    private CategorieEjbLocal CategorieEjbLocal;

    @RequestMapping(value="categories.htm", method=RequestMethod.GET)
    public ModelAndView afficherCategories(){
        ModelAndView mv = new ModelAndView("categorie/categorieListe");
        mv.addObject("categories", CategorieEjbLocal.selectionnerCategories(-1, -1)); 
        return mv;
       
    }
    
    @RequestMapping(value="categorie.htm", method=RequestMethod.GET)
    public ModelAndView afficherCategorie(@RequestParam(value="id", required=true) int idCategorie){
        ModelAndView mv = new ModelAndView("categorie/categorie");
        
        mv.addObject("categorie", CategorieEjbLocal.selectionnerCategorie(idCategorie)); 
        return mv;
       
    }
    
    
    
    
    /*
     * #####################################################################################################################
     *                                      Separation de l'administration
     * #####################################################################################################################
     */
    //TODO NicoExia ajouter la vérification client = admin
    @RequestMapping(value="adminCategories.htm", method=RequestMethod.GET)
    public ModelAndView afficherAdminCategories(String message){
        
        ModelAndView mv = new ModelAndView("admin/categorie/categorieListe");
        mv.addObject("valide", message);
        mv.addObject("categories", CategorieEjbLocal.selectionnerCategories(-1, -1)); 
        return mv;
       
    }
    
    
    
    @RequestMapping(value="adminCategorie.htm", method=RequestMethod.GET)
    public ModelAndView afficherAdminCategorie(@RequestParam(value="id", required=true) int idCategorie){
        ModelAndView mv = new ModelAndView("admin/categorie/categorie");
        
        mv.addObject("categorie", CategorieEjbLocal.selectionnerCategorie(idCategorie)); 
        return mv;
       
    }
    
    @RequestMapping(value="adminCategorieModifier.htm", method=RequestMethod.POST)
    public ModelAndView modifierCategorieAdmin(@Valid @ModelAttribute("categorie") Categorie categorieForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherAdminCategorie(categorieForm.getCategorieid());
        }
        
        //TODO test flo Mette ajour la base avec categorieform
        CategorieEjbLocal.updateCategorie(categorieForm);
        
        return afficherAdminCategories("Modifiactions effectuées");
    
    }
    
    
    
    @RequestMapping(value="adminCategorieAjouter.htm", method=RequestMethod.GET)
    public ModelAndView afficherAdminCategorieAjouter(){
        ModelAndView mv = new ModelAndView("admin/categorie/categorieAjout");
        
        mv.addObject("categorie",new Categorie(-1)); 
        return mv;
       
    }
    
    @RequestMapping(value="adminCategorieAjouter.htm", method=RequestMethod.POST)
    public ModelAndView AjouterCategorieAdmin(@Valid @ModelAttribute("categorie") Categorie categorieForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherAdminCategorieAjouter();
        }
        
        //TODO test flo creer une nouvelle categorie avec categorieForm
            CategorieEjbLocal.addCategorie(categorieForm);
            System.out.print("############## catégorie id: "+ categorieForm.getCategorieid() + "#################");
            
        return afficherAdminCategories("Ajout de la catégorie "+categorieForm.getCategorietype()+" effectuées");
    
    }
    
    

    public void setCategorieEjbLocal(CategorieEjbLocal CategorieEjbLocal) {
        this.CategorieEjbLocal = CategorieEjbLocal;
    }



    

   

   



   
}
