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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "livre")
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l")})
public class Livre implements Serializable {
    @Column(name = "livreparution")
    @Temporal(TemporalType.TIMESTAMP)
    private Date livreparution;
    @Size(max = 50)
    @Column(name = "livreetat")
    private String livreetat;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "livreid")
    private Integer livreid;
    @Size(max = 20)
    @Column(name = "livretitre")
    private String livretitre;
    @Lob
    @Size(max = 65535)
    @Column(name = "livreresume")
    private String livreresume;
    @Column(name = "livrenbvente")
    private Integer livrenbvente;
    @Lob
    @Size(max = 65535)
    @Column(name = "livresommaire")
    private String livresommaire;
    @Size(max = 20)
    @Column(name = "livrecouverture")
    private String livrecouverture;
    @Column(name = "livrestock")
    private Integer livrestock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "livreprix")
    private Float livreprix;
    @Column(name = "livrestockalerte")
    private Integer livrestockalerte;
    @Size(max = 20)
    @Column(name = "livreediteur")
    private String livreediteur;
    @ManyToMany(mappedBy = "livreList", fetch= FetchType.EAGER)
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
        return "ejb.entity.Livre[ livreid=" + livreid + " ]";
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
