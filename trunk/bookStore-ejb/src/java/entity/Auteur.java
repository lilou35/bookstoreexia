/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "auteur")
@NamedQueries({
    @NamedQuery(name = "Auteur.findAll", query = "SELECT a FROM Auteur a")})
public class Auteur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "auteurid")
    private Integer auteurid;
    @Column(name = "auteurnom")
    private String auteurnom;
    @Column(name = "auteurprenom")
    private String auteurprenom;
    @ManyToMany(mappedBy = "auteurList")
    private List<Livre> livreList;

    public Auteur() {
    }

    public Auteur(Integer auteurid) {
        this.auteurid = auteurid;
    }

    public Integer getAuteurid() {
        return auteurid;
    }

    public void setAuteurid(Integer auteurid) {
        this.auteurid = auteurid;
    }

    public String getAuteurnom() {
        return auteurnom;
    }

    public void setAuteurnom(String auteurnom) {
        this.auteurnom = auteurnom;
    }

    public String getAuteurprenom() {
        return auteurprenom;
    }

    public void setAuteurprenom(String auteurprenom) {
        this.auteurprenom = auteurprenom;
    }

    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auteurid != null ? auteurid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auteur)) {
            return false;
        }
        Auteur other = (Auteur) object;
        if ((this.auteurid == null && other.auteurid != null) || (this.auteurid != null && !this.auteurid.equals(other.auteurid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Auteur[auteurid=" + auteurid + "]";
    }

}
