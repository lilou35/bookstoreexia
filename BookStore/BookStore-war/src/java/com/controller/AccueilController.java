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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Wikola
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/accueil/*")
public class AccueilController {

    
    @Autowired
    Session session;
    
    private LivreEjbLocal LivreEjbLocal;

    @RequestMapping(value="accueil.htm", method=RequestMethod.GET)
    public ModelAndView accueil(){
        ModelAndView mv = new ModelAndView("index");

        mv.addObject("livres", LivreEjbLocal.topDix(10));

        return mv;
       
    }

    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

   

   



   
}
