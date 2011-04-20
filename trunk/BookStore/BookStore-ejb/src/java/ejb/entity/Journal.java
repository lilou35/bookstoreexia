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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "journal")
@NamedQueries({
    @NamedQuery(name = "Journal.findAll", query = "SELECT j FROM Journal j"),
    @NamedQuery(name = "Journal.findByJournalId", query = "SELECT j FROM Journal j WHERE j.journalId = :journalId"),
    @NamedQuery(name = "Journal.findByJournaldate", query = "SELECT j FROM Journal j WHERE j.journaldate = :journaldate")})
public class Journal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "journalId")
    private Integer journalId;
    @Lob
    @Column(name = "journaldescription")
    private String journaldescription;
    @Column(name = "journaldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date journaldate;
    @JoinColumn(name = "libraireid", referencedColumnName = "libraireid")
    @ManyToOne(optional = false)
    private Libraire libraire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "journal")
    private List<Commande> commandeList;

    public Journal() {
    }

    public Journal(Integer journalId) {
        this.journalId = journalId;
    }

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public String getJournaldescription() {
        return journaldescription;
    }

    public void setJournaldescription(String journaldescription) {
        this.journaldescription = journaldescription;
    }

    public Date getJournaldate() {
        return journaldate;
    }

    public void setJournaldate(Date journaldate) {
        this.journaldate = journaldate;
    }

    public Libraire getLibraire() {
        return libraire;
    }

    public void setLibraire(Libraire libraire) {
        this.libraire = libraire;
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
        hash += (journalId != null ? journalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Journal)) {
            return false;
        }
        Journal other = (Journal) object;
        if ((this.journalId == null && other.journalId != null) || (this.journalId != null && !this.journalId.equals(other.journalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.Journal[journalId=" + journalId + "]";
    }

}
