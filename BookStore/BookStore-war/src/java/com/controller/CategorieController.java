/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import metier.categorie.CategorieEjbLocal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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

    public void setCategorieEjbLocal(CategorieEjbLocal CategorieEjbLocal) {
        this.CategorieEjbLocal = CategorieEjbLocal;
    }



    

   

   



   
}
