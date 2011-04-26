/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.formulaire;

import java.util.Date;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Wikola
 */
public class LivreAdmin {

    
    
    private Date livreparution;

    @NotEmpty
    private String livreetat;


    private Integer livreid;

    @NotEmpty
    private String livretitre;

    private String livreresume;

    private Integer livrenbvente = 0;

    private String livresommaire;

    private String livrecouverture;

    @NotNull
    private Integer livrestock;

    @NotNull
    private Float livreprix;

    @NotNull
    private Integer livrestockalerte;

    private String livreediteur;
    
    @NotNull
    private int categorie;

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public LivreAdmin() {
    }
    
    

    public LivreAdmin(Integer livreid) {
        this.livreid = livreid;
    }



    public Integer getLivreid() {
        return livreid;
    }

    public void setLivreid(Integer livreid) {
        this.livreid = livreid;
    }

    public String getLivretitre() {
        return livretitre;
    }

    public void setLivretitre(String livretitre) {
        this.livretitre = livretitre;
    }

    public String getLivreresume() {
        return livreresume;
    }

    public void setLivreresume(String livreresume) {
        this.livreresume = livreresume;
    }

    public Integer getLivrenbvente() {
        return livrenbvente;
    }

    public void setLivrenbvente(Integer livrenbvente) {
        this.livrenbvente = livrenbvente;
    }

    public String getLivresommaire() {
        return livresommaire;
    }

    public void setLivresommaire(String livresommaire) {
        this.livresommaire = livresommaire;
    }

    public String getLivrecouverture() {
        return livrecouverture;
    }

    public void setLivrecouverture(String livrecouverture) {
        this.livrecouverture = livrecouverture;
    }

    public Integer getLivrestock() {
        return livrestock;
    }

    public void setLivrestock(Integer livrestock) {
        this.livrestock = livrestock;
    }

    public Float getLivreprix() {
        return livreprix;
    }

    public void setLivreprix(Float livreprix) {
        this.livreprix = livreprix;
    }

    public Integer getLivrestockalerte() {
        return livrestockalerte;
    }

    public void setLivrestockalerte(Integer livrestockalerte) {
        this.livrestockalerte = livrestockalerte;
    }

    public String getLivreediteur() {
        return livreediteur;
    }

    public void setLivreediteur(String livreediteur) {
        this.livreediteur = livreediteur;
    }


    public Date getLivreparution() {
        return livreparution;
    }

    public void setLivreparution(Date livreparution) {
        this.livreparution = livreparution;
    }

    public String getLivreetat() {
        return livreetat;
    }

    public void setLivreetat(String livreetat) {
        this.livreetat = livreetat;
    }
}
