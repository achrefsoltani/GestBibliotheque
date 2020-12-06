/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public abstract class Adherent {
    //les attributs:
    protected int idAdh;
    protected String nom;
    protected String prenom;
     protected String sexe;
    protected String adresse;
    protected int telephone;
    protected String email;
    
    //constructeur
    protected Adherent(int idAdh, String nom, String prenom,String sexe,String adresse,int telephone,String email){
        this.idAdh = idAdh;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.sexe = sexe;
        this.telephone = telephone;
        this.email = email;
        
    }

    public int getIdAdh() {
        return idAdh;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setIdAdh(int idAdh) {
        this.idAdh = idAdh;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Adherent{" + "idAdh=" + idAdh + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email + '}';
    }
   
    
    protected void affiche(){
        System.out.println("identifiant d'adherent:"+idAdh+"le nom d'adherent:"+nom+"le prénom:"+prenom);
    }
    
}
