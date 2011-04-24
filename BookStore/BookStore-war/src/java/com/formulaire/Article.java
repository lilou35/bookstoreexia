/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formulaire;

import ejb.entity.Livre;

/**
 *
 * @author Wikola
 */
public class Article {
    
    private Livre livre;
    private int qtt;

    public Article(Livre livre, int qtt) {
        this.livre = livre;
        this.qtt = qtt;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }
    
    
}
