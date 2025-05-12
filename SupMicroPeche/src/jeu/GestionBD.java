/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;
//package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pcastani
 */
public class GestionBD extends Jeu{
    
    //private float timer;
    
//    public GestionBD(){
//        lancerExecutionPeriodique();
//    }
//    
//    public void lancerExecutionPeriodique() {
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                PushBase();
//            }
//        }, 0, 40); // Démarre tout de suite, répète toutes les 40 ms
//    }
    
    
    
    public void PullBase() {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requeteJoueur = connexion.prepareStatement("SELECT * FROM joueur");
            ResultSet resultat = requeteJoueur.executeQuery();
            
            while (resultat.next()) {
                int coord_x = resultat.getInt("x");
                System.out.println(coord_x);
            }

            requeteJoueur.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public void PushBase(ArrayList<Boat> boatList) {
            // Normalement la méthode push les infos de toutes les entités dans la BD
            // Là elle ne marche que pour les bateaux
            // Il faut réussir à mettre un Timer et à l'utiliser régulièrement pour voir si elle fonctionne bien
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requete = connexion.prepareStatement("UPDATE Boat SET x = ?, y = ?, sens = ? WHERE id = ?");
            for (int k = 0; k < boatList.size(); k++) {
                Boat b = boatList.get(k);
                requete.setInt(1, b.getX());
                requete.setInt(2, b.getY());
                requete.setBoolean(3, b.getSens());
                requete.setString(4, b.getId());

                requete.executeUpdate();
            }

            requete.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
