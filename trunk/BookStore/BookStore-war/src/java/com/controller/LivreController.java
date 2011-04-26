/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import com.formulaire.LivreAdmin;
import ejb.entity.Auteur;
import ejb.entity.Livre;
import java.util.List;
import javax.validation.Valid;
import metier.auteur.AuteurEjbLocal;
import metier.categorie.CategorieEjbLocal;
import metier.livre.LivreEjbLocal;
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
@RequestMapping(value="/livre/*")
public class LivreController {

    
    
    private LivreEjbLocal LivreEjbLocal;
    
    private CategorieEjbLocal categorieEjbLocal;
    
    private AuteurEjbLocal auteurEjbLocal;

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
        List<Livre> livres = LivreEjbLocal.selectionnerLivre(lettre, -1, -1);
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
        List<Livre> livres = LivreEjbLocal.selectionnerLivre(lettre, -1, -1);
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
        ModelAndView mv = new ModelAndView("admin/livre/livreAjouter");
        LivreAdmin livre = new LivreAdmin(-1);
        livre.setLivrestock(20);//TODO NicoExia paramètre
        livre.setLivrestockalerte(5);//TODO NicoExia paramètre
        livre.setLivreetat("Nouveauté");
        mv.addObject("livre", livre);
        mv.addObject("categories", categorieEjbLocal.selectionnerCategories(-1, -1));
        return mv;
       
    }
    
    @RequestMapping(value="ajouterLivreAdmin.htm", method=RequestMethod.POST)
    public ModelAndView AjouterLivreAdmin(@Valid @ModelAttribute("livre") LivreAdmin livreForm, BindingResult binder){

        if(binder.hasErrors()){
            return this.afficherAjouterLivreAdmin();
        }
        //TODO flo convertir le livreAdmin en Livre
        //TODO flo ajouter livreForm en base il n'a pas d'auteur j'ai aussi besoin de recup l'id normalement il modifie l'objet que tu lui envoi mais il te le retourne pas
        Livre livre = LivreEjbLocal.selectionnerLivre(1);
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteur");
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        
        return mv;
       
    }
    
    private List<Auteur> chargementDesAuteurNonEcrivain(Livre livre){
        List<Auteur> auteurs = auteurEjbLocal.selectionnerAuteur(-1, -1);//TODO flo orde alphabétique pour les auteurs
        auteurs.removeAll(livre.getAuteurList());
        return auteurs;
        
    }
    
    @RequestMapping(value="ajoutAuteurALivre.htm", method=RequestMethod.GET)
    public ModelAndView ajoutAuteurALivreAjax(@RequestParam(value="idAuteur", required=true) int idAuteur, @RequestParam(value="idLivre", required=true) int idLivre){
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
        if(!livre.getAuteurList().contains(auteur)){
            livre.getAuteurList().add(auteur);
        }
        
        //TODO flo merttre à jour livre
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    
    @RequestMapping(value="retirerAuteurALivre.htm", method=RequestMethod.GET)
    public ModelAndView retirerAuteurALivreAjax(@RequestParam(value="idAuteur", required=true) int idAuteur, @RequestParam(value="idLivre", required=true) int idLivre){
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
        if(livre.getAuteurList().contains(auteur)){
            livre.getAuteurList().remove(auteur);
        }
        
        //TODO flo merttre à jour livre
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    
    
    
    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

    public void setAuteurEjbLocal(AuteurEjbLocal auteurEjbLocal) {
        this.auteurEjbLocal = auteurEjbLocal;
    }

    public void setCategorieEjbLocal(CategorieEjbLocal categorieEjbLocal) {
        this.categorieEjbLocal = categorieEjbLocal;
    }

   

   



   
}
