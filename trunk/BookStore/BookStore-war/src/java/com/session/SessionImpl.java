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
public class SessionImpl implements Session {
        
    private Client client;
    
    private Panier panier = new Panier();
    

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
    
    
    

    @Override
    public void deconnexion() {
        panier = new Panier();;
        client = null;
    }
    
    
    
}
