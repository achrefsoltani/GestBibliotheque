/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TOSHIBA
 */

import java.util.Vector;
public class ServiceEnseignant {
    public static void main(String agrs[]){
     String pilote="com.mysql.cj.jdbc.Driver";
     String url="jdbc:mysql://localhost:3306/bibliotheque?zeroDateTimeBehavior=CONVERT_TO_NULL";
     String login="root";
     String pass="";
     SqlService service = new SqlService(pilote,url,login,pass);
     
     
    }
    
}
