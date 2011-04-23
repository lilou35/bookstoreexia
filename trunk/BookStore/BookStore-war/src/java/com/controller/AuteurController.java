/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import metier.livre.LivreEjbLocal;
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
public class AuteurController {

    
    
    private LivreEjbLocal LivreEjbLocal;

    @RequestMapping(value="accueil.htm", method=RequestMethod.GET)
    public ModelAndView accueil(){
        ModelAndView mv = new ModelAndView("index");
       // try{
             
            //String lol = LivreEjbLocal.toString();
            //String lol = LivreEjbLocal.about();
            mv.addObject("livres", LivreEjbLocal.selectionnerLivre(0, 10));
            //mv.addObject("livres", "coucocuoucouc");
            
//       }
//       catch(UndeclaredThrowableException e)
//        {
//
//             mv.addObject("livres", "Une exception s'est produite : " + e.getUndeclaredThrowable());
//        }
        return mv;
       
    }

    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

   

   



   
}
