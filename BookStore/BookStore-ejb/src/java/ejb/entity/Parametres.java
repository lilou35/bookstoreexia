/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "parametres")
@NamedQueries({
    @NamedQuery(name = "Parametres.findAll", query = "SELECT p FROM Parametres p")})
public class Parametres implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "paranblivrepage")
    private Integer paranblivrepage;
    @Column(name = "paranblivreaccueil")
    private Integer paranblivreaccueil;
    @Column(name = "paraimage")
    private Boolean paraimage;
    @Column(name = "parastockdefault")
    private Integer parastockdefault;
    @Column(name = "paraalertedefault")
    private Integer paraalertedefault;

    public Parametres() {
    }

    public Parametres(Integer paranblivrepage) {
        this.paranblivrepage = paranblivrepage;
    }

    public Integer getParanblivrepage() {
        return paranblivrepage;
    }

    public void setParanblivrepage(Integer paranblivrepage) {
        this.paranblivrepage = paranblivrepage;
    }

    public Integer getParanblivreaccueil() {
        return paranblivreaccueil;
    }

    public void setParanblivreaccueil(Integer paranblivreaccueil) {
        this.paranblivreaccueil = paranblivreaccueil;
    }

    public Boolean getParaimage() {
        return paraimage;
    }

    public void setParaimage(Boolean paraimage) {
        this.paraimage = paraimage;
    }

    public Integer getParastockdefault() {
        return parastockdefault;
    }

    public void setParastockdefault(Integer parastockdefault) {
        this.parastockdefault = parastockdefault;
    }

    public Integer getParaalertedefault() {
        return paraalertedefault;
    }

    public void setParaalertedefault(Integer paraalertedefault) {
        this.paraalertedefault = paraalertedefault;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paranblivrepage != null ? paranblivrepage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametres)) {
            return false;
        }
        Parametres other = (Parametres) object;
        if ((this.paranblivrepage == null && other.paranblivrepage != null) || (this.paranblivrepage != null && !this.paranblivrepage.equals(other.paranblivrepage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Parametres[ paranblivrepage=" + paranblivrepage + " ]";
    }
    
}
