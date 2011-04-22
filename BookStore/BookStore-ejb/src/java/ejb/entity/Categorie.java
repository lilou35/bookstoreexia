/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "categorie")
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "categorieid")
    private Integer categorieid;
    @Lob
    @Size(max = 65535)
    @Column(name = "categorietype")
    private String categorietype;
    @Lob
    @Size(max = 65535)
    @Column(name = "categoriedescription")
    private String categoriedescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
    private List<Livre> livreList;

    public Categorie() {
    }

    public Categorie(Integer categorieid) {
        this.categorieid = categorieid;
    }

    public Integer getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(Integer categorieid) {
        this.categorieid = categorieid;
    }

    public String getCategorietype() {
        return categorietype;
    }

    public void setCategorietype(String categorietype) {
        this.categorietype = categorietype;
    }

    public String getCategoriedescription() {
        return categoriedescription;
    }

    public void setCategoriedescription(String categoriedescription) {
        this.categoriedescription = categoriedescription;
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
        hash += (categorieid != null ? categorieid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.categorieid == null && other.categorieid != null) || (this.categorieid != null && !this.categorieid.equals(other.categorieid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Categorie[ categorieid=" + categorieid + " ]";
    }
    
}
