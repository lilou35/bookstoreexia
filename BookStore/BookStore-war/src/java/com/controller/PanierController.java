/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import com.session.Session;
import metier.livre.LivreEjbLocal;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/panier/*")
public class PanierController {

    private LivreEjbLocal LivreEjbLocal;
    
    @Autowired
    Session session;

    @RequestMapping(value="ajoutLivre.htm", method=RequestMethod.POST)
    public ModelAndView chargementLivreAjax(@RequestParam(value="id", required=true) int idLivre){
        
        //ajout dans la commande
        
        ModelAndView mv = new ModelAndView("panier/livreAjoutAjax");
        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
        return mv;
       
    }

    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

   

   



   
}
