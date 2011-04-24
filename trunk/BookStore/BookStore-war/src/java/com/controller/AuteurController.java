/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import metier.auteur.AuteurEjbLocal;
import metier.livre.LivreEjbLocal;
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
@RequestMapping(value="/auteur/*")
public class AuteurController {

    
    
    private AuteurEjbLocal AuteurEjbLocal;

    @RequestMapping(value="auteurs.htm", method=RequestMethod.GET)
    public ModelAndView accueil(){
        ModelAndView mv = new ModelAndView("auteur/auteurListe");

        mv.addObject("auteurs", AuteurEjbLocal.selectionnerAuteur(-1, -1));
          
        return mv;
       
    }
    
    @RequestMapping(value="auteur.htm", method=RequestMethod.GET)
    public ModelAndView afficherAuteur(@RequestParam(value="id", required=true) int idAuteur){
        ModelAndView mv = new ModelAndView("auteur/auteur");
        System.out.print("######################## nb livre: "+ AuteurEjbLocal.selectionnerAuteur(idAuteur).getLivreList().size() + "###########################");
        mv.addObject("auteur", AuteurEjbLocal.selectionnerAuteur(idAuteur));
        
        return mv;
       
    }
    
    

    public void setAuteurEjbLocal(AuteurEjbLocal AuteurEjbLocal) {
        this.AuteurEjbLocal = AuteurEjbLocal;
    }

    
   

   



   
}
