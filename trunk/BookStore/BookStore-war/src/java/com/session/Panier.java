/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import com.formulaire.Article;
import ejb.entity.Livre;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wikola
 */
public class Panier {
    
    private List<Livre> livres = new ArrayList<Livre>(0);
    private List<Integer> qtt =  new ArrayList<Integer>(0);
    
    private String affichagePanier;

    
      
    

   public void ajouterLivre(Livre livre){
       if(livres.contains(livre)){
           int index = livres.indexOf(livre);
           qtt.set(index, qtt.get(index)+1);
       }
       else{
           livres.add(livre);
           qtt.add(1);
       }
       miseAJourPanier();
   }
   
  public void modifierQtt(Livre livre , int nvQtt){
      int index = livres.indexOf(livre);
      qtt.set(index, nvQtt);
      miseAJourPanier();
  }
  
  public boolean retirerLivre(Livre livre){
      if(livres.contains(livre)){
          int index = livres.indexOf(livre);
          livres.remove(livre);
          qtt.remove(index);
          miseAJourPanier();
          return true;
      }
      else{
          return false;
      }
      
  }
  
  
  public List<Article> getPanier(){
      List<Article> articles =  new ArrayList<Article>(0);
      for(Livre livre : livres){
          Article article = new Article(livre, qtt.get(livres.indexOf(livre)));
          articles.add(article);
      }
      return articles;
      
  }
  
  
  public String miseAJourPanier(){
      String panier = "Votre Panier: <br/><br/><br/>";
      for(Livre livre : livres){
          panier= panier + livre.getLivretitre() +" <br/> Quantité: "+ qtt.get(livres.indexOf(livre))+ "<hr/>"; 
      }
      affichagePanier = panier;
      System.out.print("##########panier: "+ panier +"########################");
      return panier;
      
  }

    public String getAffichagePanier() {
        return affichagePanier;
    }

   
    
    
    
}
