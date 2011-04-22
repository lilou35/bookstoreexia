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
public class SessionImpl implements Session {
    
    private Client client;
    
    private Commande commande;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public void deconnexion() {
        commande = null;
        client = null;
    }
    
    
    
}
