/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

import ejb.entity.Client;
/**
 *
 * @author Wikola
 */
public interface Session {
       
    public Client getClient() ;

    public void setClient(Client client) ;

    public Panier getPanier() ;

    public void setPanier(Panier panier) ;
    
    public void deconnexion();
    
}
