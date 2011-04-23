/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wikola
 */
@Entity
@Table(name = "commande")
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")})
public class Commande implements Serializable {
    @Column(name = "commandedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commandedate;
    @Column(name = "commandedatelivraison")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commandedatelivraison;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommandePK commandePK;
    @Column(name = "commandeid")
    private Integer commandeid;
    @Column(name = "commandequantite")
    private Integer commandequantite;
    @Size(max = 20)
    @Column(name = "commandeetat")
    private String commandeetat;
    @JoinColumn(name = "livreid", referencedColumnName = "livreid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Livre livre;
    @JoinColumn(name = "journalId", referencedColumnName = "journalId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Journal journal;
    @JoinColumn(name = "clientid", referencedColumnName = "clientid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client client;

    public Commande() {
    }

    public Commande(CommandePK commandePK) {
        this.commandePK = commandePK;
    }

    public Commande(int clientid, int livreid, int journalId) {
        this.commandePK = new CommandePK(clientid, livreid, journalId);
    }

    public CommandePK getCommandePK() {
        return commandePK;
    }

    public void setCommandePK(CommandePK commandePK) {
        this.commandePK = commandePK;
    }

    public Integer getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(Integer commandeid) {
        this.commandeid = commandeid;
    }

    public Integer getCommandequantite() {
        return commandequantite;
    }

    public void setCommandequantite(Integer commandequantite) {
        this.commandequantite = commandequantite;
    }

    public String getCommandeetat() {
        return commandeetat;
    }

    public void setCommandeetat(String commandeetat) {
        this.commandeetat = commandeetat;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandePK != null ? commandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.commandePK == null && other.commandePK != null) || (this.commandePK != null && !this.commandePK.equals(other.commandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Commande[ commandePK=" + commandePK + " ]";
    }

    public Date getCommandedate() {
        return commandedate;
    }

    public void setCommandedate(Date commandedate) {
        this.commandedate = commandedate;
    }

    public Date getCommandedatelivraison() {
        return commandedatelivraison;
    }

    public void setCommandedatelivraison(Date commandedatelivraison) {
        this.commandedatelivraison = commandedatelivraison;
    }
    
}
