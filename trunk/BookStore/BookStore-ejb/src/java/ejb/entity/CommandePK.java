/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Wikola
 */
@Embeddable
public class CommandePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "clientid")
    private int clientid;
    @Basic(optional = false)
    @Column(name = "livreid")
    private int livreid;
    @Basic(optional = false)
    @Column(name = "journalId")
    private int journalId;

    public CommandePK() {
    }

    public CommandePK(int clientid, int livreid, int journalId) {
        this.clientid = clientid;
        this.livreid = livreid;
        this.journalId = journalId;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getLivreid() {
        return livreid;
    }

    public void setLivreid(int livreid) {
        this.livreid = livreid;
    }

    public int getJournalId() {
        return journalId;
    }

    public void setJournalId(int journalId) {
        this.journalId = journalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clientid;
        hash += (int) livreid;
        hash += (int) journalId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommandePK)) {
            return false;
        }
        CommandePK other = (CommandePK) object;
        if (this.clientid != other.clientid) {
            return false;
        }
        if (this.livreid != other.livreid) {
            return false;
        }
        if (this.journalId != other.journalId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.entity.CommandePK[clientid=" + clientid + ", livreid=" + livreid + ", journalId=" + journalId + "]";
    }

}
