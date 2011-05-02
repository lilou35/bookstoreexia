/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import com.formulaire.LivreAdmin;
import com.session.Session;
import ejb.entity.Auteur;
import ejb.entity.Commande;
import ejb.entity.Livre;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import metier.auteur.AuteurEjbLocal;
import metier.categorie.CategorieEjbLocal;
import metier.livre.LivreEjbLocal;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private Session session;

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
    private ModelAndView refuser(){
        ModelAndView mv = new ModelAndView("admin/refuser");
        return mv;
    }
    
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
        if(!session.getAdmin()){
           return refuser(); 
        }
        ModelAndView mv = new ModelAndView("admin/livre/livreListe");

        mv.addObject("message", "Selectionner une Lettre");

        return mv;   
    }
    
    @RequestMapping(value="livreListeAdminLettre.htm", method=RequestMethod.GET)
    public ModelAndView afficherListeLivreParLettreGetAdmin(@RequestParam(value="lettre", required=true) String lettre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        return afficherListeLivreParLettrePostAdmin(lettre);
    }
    
    
     @RequestMapping(value="alerteLivreListe.htm", method=RequestMethod.GET)
    public ModelAndView afficherListeLivreEnAlerte(){
         if(!session.getAdmin()){
           return refuser(); 
        }
        ModelAndView mv = new ModelAndView("admin/livre/livreListe");
        List<Livre> livres = LivreEjbLocal.stockAlert();
        mv.addObject("livres", livres); 
        if(livres.isEmpty()){
            mv.addObject("message", "il n'y a pas de livre an alerte:");
        }
        else{
            mv.addObject("message", "Vous avez des livres en alerte:");
        }
        
        
        return mv;
       
    }
    
    @RequestMapping(value="livreListeAdmin.htm", method=RequestMethod.POST)
    public ModelAndView afficherListeLivreParLettrePostAdmin(@RequestParam(value="lettre", required=true) String lettre){
        if(!session.getAdmin()){
           return refuser(); 
        }
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
        if(!session.getAdmin()){
           return refuser(); 
        }
        ModelAndView mv = new ModelAndView("admin/livre/livreAjax");
        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
        return mv;
       
    }
    
    @RequestMapping(value="afficherModifierLivreAdmin.htm", method=RequestMethod.GET)
    public ModelAndView afficherModifierLivreAdmin(@RequestParam(value="id", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        ModelAndView mv = new ModelAndView("admin/livre/livreModifier");
        mv.addObject("livre", LivreEjbLocal.selectionnerLivre(idLivre));
        mv.addObject("categories", categorieEjbLocal.selectionnerCategories(-1, -1));
        return mv;
       
    }
    
    
    @RequestMapping(value="modifierLivreAdmin.htm", method=RequestMethod.POST)
    public ModelAndView modifierLivreAdmin(@Valid @ModelAttribute("livre") LivreAdmin livreForm, BindingResult binder){

        if(!session.getAdmin()){
           return refuser(); 
        }
        if(binder.hasErrors()){
            return this.afficherAjouterLivreAdmin();
        }
        
        Livre livre = this.convertToLivre(livreForm);//récup un objet Livre
        LivreEjbLocal.updateLivre(livre);// commit + récup l'id
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteur");
        mv.addObject("livre", livre);
        List<Auteur> auteurs = auteurEjbLocal.selectionnerAuteur(-1, -1);
        auteurs.removeAll(livre.getAuteurList());
        mv.addObject("auteurs", auteurs);
        
        return mv;
       
    }
    
    @RequestMapping(value="ajouterLivreAdmin.htm", method=RequestMethod.GET)
    public ModelAndView afficherAjouterLivreAdmin(){
        if(!session.getAdmin()){
           return refuser(); 
        }
        ModelAndView mv = new ModelAndView("admin/livre/livreAjouter");
        LivreAdmin livre = new LivreAdmin(-1);
        livre.setLivrestock(20);//TODO NicoExia paramètre
        livre.setLivrestockalerte(5);//TODO NicoExia paramètre
        livre.setLivreetat("Nouveauté");
        mv.addObject("livre", livre);
        mv.addObject("categories", categorieEjbLocal.selectionnerCategories(-1, -1));
        return mv;
       
    }
    
    
    
    private List<Auteur> chargementDesAuteurNonEcrivain(Livre livre){
        List<Auteur> auteurs = auteurEjbLocal.selectionnerAuteur(-1, -1);
        auteurs.removeAll(livre.getAuteurList());
        return auteurs;
        
    }
    

    
    @RequestMapping(value="ajouterLivreAdmin.htm", method=RequestMethod.POST)
    public ModelAndView ajouterLivreAdmin(@Valid @ModelAttribute("livre") LivreAdmin livreForm, BindingResult binder){

        if(!session.getAdmin()){
           return refuser(); 
        }
        if(binder.hasErrors()){
            return this.afficherAjouterLivreAdmin();
        }
        
        Livre livre = this.convertToLivre(livreForm);//récup un objet Livre
        livre = LivreEjbLocal.addLivre(livre);// commit + récup l'id
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteur");
        mv.addObject("livre", livre);
        List<Auteur> auteurs = auteurEjbLocal.selectionnerAuteur(-1, -1);
        auteurs.removeAll(livre.getAuteurList());
        mv.addObject("auteurs", auteurs);
        
        return mv;
       
    }
    
    
        
    @RequestMapping(value="ajoutAuteurALivre.htm", method=RequestMethod.GET)
    public ModelAndView ajoutAuteurALivreAjax(@RequestParam(value="idAuteur", required=true) int idAuteur, @RequestParam(value="idLivre", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        livre.setCommandeList(new ArrayList<Commande>(0));
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
        if(!livre.getAuteurList().contains(auteur)){
            
            livre.getAuteurList().add(auteur);
        }
        
        
        LivreEjbLocal.updateLivre(livre);
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    
    @RequestMapping(value="retirerAuteurALivre.htm", method=RequestMethod.GET)
    public ModelAndView retirerAuteurALivreAjax(@RequestParam(value="idAuteur", required=true) int idAuteur, @RequestParam(value="idLivre", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        livre.setCommandeList(new ArrayList<Commande>(0));
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
        if(livre.getAuteurList().contains(auteur)){
            livre.getAuteurList().remove(auteur);
        }
        
        
        LivreEjbLocal.updateLivre(livre);
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    @RequestMapping(value="ajoutAuteur.htm", method=RequestMethod.POST)
    public ModelAndView creerAuteurALivreAjax(@RequestParam(value="nomAuteur", required=true) String nom, 
                                                @RequestParam(value="prenomAuteur", required=true) String prenom,
                                                @RequestParam(value="idLivre", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        
        if(nom.isEmpty()|| prenom.isEmpty()){
            mv.addObject("erreur", "les 2 champs sont obligatoires");
            mv.addObject("nom", nom);
            mv.addObject("prenom", prenom);
        }
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        livre.setCommandeList(new ArrayList<Commande>(0));
        Auteur auteur =  new Auteur(-1);
        auteur.setAuteurnom(nom);
        auteur.setAuteurprenom(prenom);
        
        auteur = auteurEjbLocal.addAuteur(auteur);
        
        
        if(!livre.getAuteurList().contains(auteur)){
            livre.getAuteurList().add(auteur);
        }
        
        
        LivreEjbLocal.updateLivre(livre);
        
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    
    @RequestMapping(value="afficherModifierAuteur.htm", method=RequestMethod.POST)
    public ModelAndView afficherModifierAuteurAjax(@RequestParam(value="idAuteur", required=true) int idAuteur, 
                                                @RequestParam(value="idLivre", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
        
        mv.addObject("action", "Modifier");
        mv.addObject("nom", auteur.getAuteurnom());
        mv.addObject("prenom", auteur.getAuteurprenom());
        mv.addObject("auteurId", auteur.getAuteurid());
        
        
        
       Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
       
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    @RequestMapping(value="modifierAuteur.htm", method=RequestMethod.POST)
    public ModelAndView modifierAuteurAjax(@RequestParam(value="nomAuteur", required=true) String nom, 
                                                @RequestParam(value="prenomAuteur", required=true) String prenom,
                                                @RequestParam(value="idLivre", required=true) int idLivre,
                                                @RequestParam(value="idAuteur", required=true) int idAuteur){
        if(!session.getAdmin()){
           return refuser(); 
        }
        
        ModelAndView mv = new ModelAndView("admin/livre/livreAuteurAjax");
        Auteur auteur = auteurEjbLocal.selectionnerAuteur(idAuteur);
        
         if(nom.isEmpty()|| prenom.isEmpty()){
            mv.addObject("erreur", "les 2 champs sont obligatoires");
            mv.addObject("nom", nom);
            mv.addObject("prenom", prenom);
            mv.addObject("action", "Modifier");
        }
         else{
             auteur.setAuteurnom(nom);
            auteur.setAuteurprenom(prenom);

            auteurEjbLocal.updateAuteur(auteur);
         }
        
        
        
       Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
       
        mv.addObject("livre", livre);
        mv.addObject("auteurs", chargementDesAuteurNonEcrivain(livre));
        return mv;
       
    }
    
    
    @RequestMapping(value="supprimer.htm", method=RequestMethod.POST)
    public ModelAndView supprimerLivreAdmin(@RequestParam(value="id", required=true) int idLivre){
        if(!session.getAdmin()){
           return refuser(); 
        }
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        livre.setLivrestock(-1);
        LivreEjbLocal.updateLivre(livre);
        ModelAndView mv = new ModelAndView("admin/livre/supprimer");
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
