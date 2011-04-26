/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import ejb.entity.Livre;
import java.util.List;
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
@RequestMapping(value="/livre/*")
public class LivreController {

    
    
    private LivreEjbLocal LivreEjbLocal;

    @RequestMapping(value="chargementLivre.htm", method=RequestMethod.POST)
    public ModelAndView chargementLivreAjax(@RequestParam(value="id", required=true) int idLivre){
        ModelAndView mv = new ModelAndView("livre/livreAjax");
        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
        return mv;
       
    }
    
    
    @RequestMapping(value="livreListe.htm", method=RequestMethod.GET)
    public ModelAndView afficherListeLivre(){
        ModelAndView mv = new ModelAndView("livre/livreListe");

        mv.addObject("message", "Selectionner une Lettre");

        return mv;   
    }
    
    
    @RequestMapping(value="livreListe.htm", method=RequestMethod.POST)
    public ModelAndView afficherListeLivreParLettre(@RequestParam(value="lettre", required=true) String lettre){
        ModelAndView mv = new ModelAndView("livre/livreListe");
        List<Livre> livres = LivreEjbLocal.selectionnerLivre(-1, -1);//TODO flo rechercher sur le tritre like lettre%
        mv.addObject("livres", livres); 
        if(livres.isEmpty()){
            mv.addObject("message", "il n'y a pas de livre commançant par:");
        }
        else{
            mv.addObject("message", "Vous recherchez tout les livre commançant par:");
        }
        
        mv.addObject("lettre", lettre);
        return mv;
       
    }

    
     /*
     * #####################################################################################################################
     *                                      Separation de l'administration
     * #####################################################################################################################
     */
    //TODO NicoExia ajouter la vérification client = admin
    
       
    
    @RequestMapping(value="livreListeAdmin.htm", method=RequestMethod.GET)
    public ModelAndView afficherListeLivreAdmin(){
        ModelAndView mv = new ModelAndView("admin/livre/livreListe");

        mv.addObject("message", "Selectionner une Lettre");

        return mv;   
    }
    
    
    @RequestMapping(value="livreListeAdmin.htm", method=RequestMethod.POST)
    public ModelAndView afficherListeLivreParLettreAdmin(@RequestParam(value="lettre", required=true) String lettre){
        ModelAndView mv = new ModelAndView("admin/livre/livreListe");
        List<Livre> livres = LivreEjbLocal.selectionnerLivre(-1, -1);//TODO flo rechercher sur le tritre like lettre%
        mv.addObject("livres", livres); 
        if(livres.isEmpty()){
            mv.addObject("message", "il n'y a pas de livre commançant par:");
        }
        else{
            mv.addObject("message", "Vous recherchez tout les livre commançant par:");
        }
        
        mv.addObject("lettre", lettre);
        return mv;
       
    }
    
    @RequestMapping(value="chargementLivreAdmin.htm", method=RequestMethod.POST)
    public ModelAndView chargementLivreAdminAjax(@RequestParam(value="id", required=true) int idLivre){
        ModelAndView mv = new ModelAndView("admin/livre/livreAjax");
        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
        return mv;
       
    }
    
//    @RequestMapping(value="modifierLivreAdmin.htm", method=RequestMethod.POST)
//    public ModelAndView afficherModifierLivreAdmin(@RequestParam(value="id", required=true) int idLivre){
//        ModelAndView mv = new ModelAndView("admin/livre/livreAjax");
//        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
//        return mv;
//       
//    }
    
    @RequestMapping(value="ajouterLivreAdmin.htm", method=RequestMethod.GET)
    public ModelAndView afficherAjouterLivreAdmin(){
        ModelAndView mv = new ModelAndView("admin/livre/livreAjout");
        Livre livre = new Livre(-1);
        livre.setLivrestock(20);//TODO NicoExia paramètre
        livre.setLivrestockalerte(5);//TODO NicoExia paramètre
        mv.addObject("livre", livre);
        return mv;
       
    }
    
    
    
    
    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

   

   



   
}