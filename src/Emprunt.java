
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Emprunt {
    protected Etudiant etudiant;
    protected Livre livre;
    protected Date dateemp;
    protected Date dateretour;

    public Emprunt(Etudiant etudiant, Livre livre, Date dateemp, Date dateretour) {
        this.etudiant = etudiant;
        this.livre = livre;
        this.dateemp = dateemp;
        this.dateretour = dateretour;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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
    
    
}
