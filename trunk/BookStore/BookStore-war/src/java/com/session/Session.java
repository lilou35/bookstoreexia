/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import ejb.entity.Client;
import ejb.entity.Commande;

/**
 *
 * @author Wikola
 */
public interface Session {
    
    public Client getClient() ;

    public void setClient(Client client) ;

    public Commande getCommande() ;

    public void setCommande(Commande commande) ;
    
    public void deconnexion();
    
}
