
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */
public class SqlService {
    private Connection cx ;
    private String pilote;
    private String login;
    private String pass;
    private String url;
    
    
    /* le oconstructeur*/
    public SqlService(String url, String pilote, String login, String pass){
        this.pilote = pilote;
        this.login = login;
        this.pass = pass;
        this.url = url;
        
    }
    /* une methode qui affiche true si il ya une connexion affiche false si il nya pas de connexion */
    public boolean connection(){
        try { /* dans try si il y a une connexion*/
            Class.forName(pilote);/* chargemnet driver*/
           cx = DriverManager.getConnection(url, login, pass);
           return true;
            
            
            /* dans ces deux cas return false*/
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getException());
            return false;
        } catch (SQLException ex1) {
            System.err.println(ex1.getMessage());
            return false;
        }
    }
    /*méthode pour la déconnexion */
    public boolean deconnexion(){
        try {
            cx.close(); /*pour la déconnexion  de la base donnée*/
            return true;
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
}
