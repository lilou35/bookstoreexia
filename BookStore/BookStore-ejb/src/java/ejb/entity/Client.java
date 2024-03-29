/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "clientid")
    private Integer clientid;
    @Size(max = 20)
    @Column(name = "clientlogin")
    @NotEmpty
    private String clientlogin;
    @Size(max = 20)
    @Column(name = "clientmdp")
    @NotEmpty
    private String clientmdp;
    @Size(max = 20)
    @Column(name = "clientnom")
    @NotEmpty
    private String clientnom;
    @Size(max = 20)
    @Column(name = "clientprenom")
    @NotEmpty
    private String clientprenom;
    @Size(max = 20)
    @Column(name = "clientmail")
    private String clientmail;
    @Size(max = 40)
    @Column(name = "clientrue")
    @NotEmpty
    private String clientrue;
    @Column(name = "clientcodepostal")
    @NotNull
    private Integer clientcodepostal;
    @Size(max = 20)
    @Column(name = "clientville")
    @NotEmpty
    private String clientville;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client" , fetch= FetchType.EAGER)
    private List<Commande> commandeList =  new ArrayList<Commande>(0);

    public Client() {
    }

    public Client(Integer clientid) {
        this.clientid = clientid;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getClientlogin() {
        return clientlogin;
    }

    public void setClientlogin(String clientlogin) {
        this.clientlogin = clientlogin;
    }

    public String getClientmdp() {
        return clientmdp;
    }

    public void setClientmdp(String clientmdp) {
        this.clientmdp = clientmdp;
    }

    public String getClientnom() {
        return clientnom;
    }

    public void setClientnom(String clientnom) {
        this.clientnom = clientnom;
    }

    public String getClientprenom() {
        return clientprenom;
    }

    public void setClientprenom(String clientprenom) {
        this.clientprenom = clientprenom;
    }

    public String getClientmail() {
        return clientmail;
    }

    public void setClientmail(String clientmail) {
        this.clientmail = clientmail;
    }

    public String getClientrue() {
        return clientrue;
    }

    public void setClientrue(String clientrue) {
        this.clientrue = clientrue;
    }

    public Integer getClientcodepostal() {
        return clientcodepostal;
    }

    public void setClientcodepostal(Integer clientcodepostal) {
        this.clientcodepostal = clientcodepostal;
    }

    public String getClientville() {
        return clientville;
    }

    public void setClientville(String clientville) {
        this.clientville = clientville;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientid != null ? clientid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientid == null && other.clientid != null) || (this.clientid != null && !this.clientid.equals(other.clientid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Client[ clientid=" + clientid + " ]";
    }
    
}
