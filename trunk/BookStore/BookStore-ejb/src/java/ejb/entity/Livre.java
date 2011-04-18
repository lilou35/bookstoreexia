/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "livre")
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l")})
public class Livre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "livreid")
    private Integer livreid;
    @Column(name = "livretitre")
    private String livretitre;
    @Lob
    @Column(name = "livreresume")
    private String livreresume;
    @Column(name = "livrenbvente")
    private Integer livrenbvente;
    @Column(name = "livreparution")
    @Temporal(TemporalType.TIMESTAMP)
    private Date livreparution;
    @Lob
    @Column(name = "livresommaire")
    private String livresommaire;
    @Column(name = "livrecouverture")
    private String livrecouverture;
    @Column(name = "livrestock")
    private Integer livrestock;
    @Column(name = "livreprix")
    private Float livreprix;
    @Column(name = "livrestockalerte")
    private Boolean livrestockalerte;
    @Column(name = "livreediteur")
    private String livreediteur;
    @Column(name = "livreetat")
    private Integer livreetat;
    @ManyToMany(mappedBy = "livreList")
    private List<Auteur> auteurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "livre")
    private List<Commande> commandeList;
    @JoinColumn(name = "categorieid", referencedColumnName = "categorieid")
    @ManyToOne(optional = false)
    private Categorie categorie;

    public Livre() {
    }

    public Livre(Integer livreid) {
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

    public Date getLivreparution() {
        return livreparution;
    }

    public void setLivreparution(Date livreparution) {
        this.livreparution = livreparution;
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

    public Boolean getLivrestockalerte() {
        return livrestockalerte;
    }

    public void setLivrestockalerte(Boolean livrestockalerte) {
        this.livrestockalerte = livrestockalerte;
    }

    public String getLivreediteur() {
        return livreediteur;
    }

    public void setLivreediteur(String livreediteur) {
        this.livreediteur = livreediteur;
    }

    public Integer getLivreetat() {
        return livreetat;
    }

    public void setLivreetat(Integer livreetat) {
        this.livreetat = livreetat;
    }

    public List<Auteur> getAuteurList() {
        return auteurList;
    }

    public void setAuteurList(List<Auteur> auteurList) {
        this.auteurList = auteurList;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (livreid != null ? livreid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.livreid == null && other.livreid != null) || (this.livreid != null && !this.livreid.equals(other.livreid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Livre[livreid=" + livreid + "]";
    }

}
