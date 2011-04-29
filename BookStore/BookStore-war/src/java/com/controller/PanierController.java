/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.controller;




import com.session.Session;
import metier.livre.LivreEjbLocal;
import ejb.entity.Livre;
import java.util.List;
import com.formulaire.Article;
import com.session.Panier;
import ejb.entity.Commande;
import ejb.entity.CommandePK;
import java.util.ArrayList;
import metier.commande.CommandeEjbLocal;
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
    private CommandeEjbLocal commandeEjbLocal;
    
    @Autowired
    Session session;

    @RequestMapping(value="ajoutLivre.htm", method=RequestMethod.POST)
    public ModelAndView chargementLivreAjax(@RequestParam(value="id", required=true) int idLivre){
        
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
       // System.out.print("############" + livre.getLivretitre() + "###############");
        session.getPanier().ajouterLivre(livre);
        
        ModelAndView mv = new ModelAndView("panier/livreAjoutAjax");
        mv.addObject("panier", session.getPanier().getAffichagePanier() );
        return mv;
       
    }
    
    
     @RequestMapping(value="panier.htm", method=RequestMethod.GET)
    public ModelAndView afficherPanier(){
        
        ModelAndView mv = new ModelAndView("panier/panier");
        List<Article> panier = session.getPanier().getPanier();
        mv.addObject("panier", panier);
        mv.addObject("total", calculPrixTotal(panier));
        
        return mv;
       
    }
     
     private ModelAndView afficherArticles(String messageCarte){
        ModelAndView mv = new ModelAndView("panier/articles");
        List<Article> panier = session.getPanier().getPanier();
        mv.addObject("panier", panier);
        mv.addObject("messageCarte", messageCarte);
        mv.addObject("total", calculPrixTotal(panier));
                
        return mv;
     }
     
     
    @RequestMapping(value="plus.htm", method=RequestMethod.POST)
    public ModelAndView plusQttArticle(@RequestParam(value="id", required=true) int idLivre){
         
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        
        session.getPanier().ajouterLivre(livre);
        
        return afficherArticles("");
       
    }
    
    @RequestMapping(value="moins.htm", method=RequestMethod.POST)
    public ModelAndView moinsQttArticle(@RequestParam(value="id", required=true) int idLivre){
         
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        
        session.getPanier().moinsUnQttLivre(livre);
        
        return afficherArticles("");
       
    }
    
    @RequestMapping(value="retirer.htm", method=RequestMethod.POST)
    public ModelAndView retitrerArticle(@RequestParam(value="id", required=true) int idLivre){
         
        Livre livre = LivreEjbLocal.selectionnerLivre(idLivre);
        
        session.getPanier().retirerLivre(livre);
        
        return afficherArticles("");
       
    }
     
     
     private float calculPrixTotal(List<Article> articles){
         float total = 0;
         for(Article article : articles){
             total = total + article.getLivre().getLivreprix() * article.getQtt();
         }
         total = total * 100;
         total = Math.round(total);
         total = total / 100;
         return total;
     }
     
     
     
     @RequestMapping(value="terminerCommande.htm", method=RequestMethod.POST)
    public ModelAndView terminerCommande(@RequestParam(value="carte", required=true) String carte){
        
         if(session.getClient()==null){
             return afficherArticles("vous devez vous inscrire");
         }
         if(session.getPanier().getPanier().isEmpty()){
             return afficherArticles("Vous devez avoir au moins 1 livre dans le panier");
         }
         
         if(carte.length()< 10){
             return afficherArticles("Vous n'avez pas saisie 10 chiffres");            
         }
         
         
         //création de la commande
         
         List<Commande> commandes = new ArrayList<Commande>(0);
         int id = commandeEjbLocal.newCommandeId();//TODO flo si il n'y a pas de commande
         for(Article article: session.getPanier().getPanier()){
             CommandePK commandePK = new CommandePK();
             commandePK.setClientid(session.getClient().getClientid());
             commandePK.setJournalId(-1);
             commandePK.setLivreid(article.getLivre().getLivreid());
             Commande commande = new Commande(commandePK);
             commande.setClient(session.getClient());
             commande.setCommandedate(null);// valeur par défaut dans la base
             commande.setCommandeetat("validée");
             commande.setJournal(null);
             commande.setLivre(article.getLivre());
             commande.setCommandeid(id);
             commande.setCommandequantite(article.getQtt());
             
             commandeEjbLocal.commander(commande);
             commandes.add(commande);
         }
        
         System.out.print("############### numéro de carte:" + carte + "###############");
         
         ModelAndView mv = new ModelAndView("commande/detailCommande");
         mv.addObject("commandes", commandes);//commandeEjbLocal.selectionnerCommande(id) ); //TODO flo selectionner par commandeid
         
         session.setPanier(new Panier());
         return mv;
       
    }

    public void setLivreEjbLocal(LivreEjbLocal LivreEjbLocal) {
        this.LivreEjbLocal = LivreEjbLocal;
    }

    public void setCommandeEjbLocal(CommandeEjbLocal commandeEjbLocal) {
        this.commandeEjbLocal = commandeEjbLocal;
    }

   

}
