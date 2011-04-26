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
    
    private Livre convertToLivre(LivreAdmin livreAdmin){
        Livre livre = new Livre();
        livre.setCategorie(categorieEjbLocal.selectionnerCategorie(livreAdmin.getCategorie()));
        livre.setLivrecouverture(livreAdmin.getLivrecouverture());
        livre.setLivreediteur(livreAdmin.getLivreediteur());
        livre.setLivreetat(livreAdmin.getLivreetat());
        livre.setLivreid(livreAdmin.getLivreid());
        livre.setLivrenbvente(livreAdmin.getLivrenbvente());
        livre.setLivreparution(livreAdmin.getLivreparution());
        livre.setLivreprix(livreAdmin.getLivreprix());
        livre.setLivreresume(livreAdmin.getLivreresume());
        livre.setLivresommaire(livreAdmin.getLivresommaire());
        livre.setLivrestock(livreAdmin.getLivrestock());
        livre.setLivrestockalerte(livreAdmin.getLivrestockalerte());
        livre.setLivretitre(livreAdmin.getLivretitre());
        return livre;
    } 
    
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
        
        Livre livre = this.convertToLivre(livreForm);//récup un objet Livre
        LivreEjbLocal.addLivre(livre);// commit + récup l'id
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteur");
        mv.addObject("livre", livre);
        List<Auteur> auteurs = auteurEjbLocal.selectionnerAuteur(-1, -1);
        auteurs.removeAll(livre.getAuteurList());
        mv.addObject("auteurs", auteurs);
        
        return mv;
       
    }
    
    
    
    
    @RequestMapping(value="ajoutAuteur.htm", method=RequestMethod.POST)
    public ModelAndView ajoutAuteurALivreAjax(@RequestParam(value="nomAuteur", required=true) String nom, 
                                                @RequestParam(value="prenomAuteur", required=true) String prenom,
                                                @RequestParam(value="idLivre", required=true) int idLivre){
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        
        if(nom.isEmpty()|| prenom.isEmpty()){
            mv.addObject("erreur", "les 2 champs sont obligatoires");
            mv.addObject("nom", nom);
            mv.addObject("prenom", prenom);
        }
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        Auteur auteur =  new Auteur(-1);
        auteur.setAuteurnom(nom);
        auteur.setAuteurprenom(prenom);
        
        //TODO flo (ajouter l'auteur en base ) je ne sais pas si juste en ajoutant l'auteur dans le livre il persistera le nouvel auteur
        
        if(!livre.getAuteurList().contains(auteur)){
            livre.getAuteurList().add(auteur);
        }
        
        //TODO flo merttre à jour livre
        
        
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
