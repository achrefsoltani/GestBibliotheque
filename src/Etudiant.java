/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class Etudiant extends Adherent {
    private String classe;
    
   
    
   /* @Override
     public void affiche(){
         super.affiche();
         System.out.print("le classe est"+classe);
     }*/

    public Etudiant(int idAdh, String nom, String prenom, String sexe, String adresse, int telephone, String email) {
        super(idAdh, nom, prenom, sexe, adresse, telephone, email);
    }
    
}
