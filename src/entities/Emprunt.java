/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "emprunt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprunt.findAll", query = "SELECT e FROM Emprunt e"),
    @NamedQuery(name = "Emprunt.findById", query = "SELECT e FROM Emprunt e WHERE e.id = :id"),
    @NamedQuery(name = "Emprunt.findByDateemp", query = "SELECT e FROM Emprunt e WHERE e.dateemp = :dateemp"),
    @NamedQuery(name = "Emprunt.findByDateretour", query = "SELECT e FROM Emprunt e WHERE e.dateretour = :dateretour")})
public class Emprunt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateemp")
    @Temporal(TemporalType.DATE)
    private Date dateemp;
    @Basic(optional = false)
    @Column(name = "dateretour")
    @Temporal(TemporalType.DATE)
    private Date dateretour;
    @JoinColumn(name = "id_etudiant", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Etudiant idEtudiant;
    @JoinColumn(name = "id_livre", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Livre idLivre;

    public Emprunt() {
    }

    public Emprunt(Integer id) {
        this.id = id;
    }

    public Emprunt(Integer id, Date dateemp, Date dateretour) {
        this.id = id;
        this.dateemp = dateemp;
        this.dateretour = dateretour;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateemp() {
        return dateemp;
    }

    public void setDateemp(Date dateemp) {
        this.dateemp = dateemp;
    }

    public Date getDateretour() {
        return dateretour;
    }

    public void setDateretour(Date dateretour) {
        this.dateretour = dateretour;
    }

    public Etudiant getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiant idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Livre getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Livre idLivre) {
        this.idLivre = idLivre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprunt)) {
            return false;
        }
        Emprunt other = (Emprunt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emprunt[ id=" + id + " ]";
    }
    
}
