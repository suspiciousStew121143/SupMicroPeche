
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
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author pcastani
 */
public class GestionDBBoat {

    private String adresseBase;
    private String user;
    private String motdepasse;
    private Connection connexion;

    public GestionDBBoat() {
        System.out.println("GestionDBBoat est appelée");
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";

        try {
            System.out.println("tentative de connexion");
            this.connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);

        } catch (SQLException ex) {
            System.out.println("Erreur");
            ex.printStackTrace();
        }

    }

    public void UpdateBase(Boat b) {
        try {
            PreparedStatement requete = this.connexion.prepareStatement("UPDATE Boat SET x = ?, y = ?, sens = ? WHERE id = ?");

            requete.setInt(1, b.getX());
            //System.out.println(b.getX());
            requete.setInt(2, b.getY());
            requete.setBoolean(3, b.getSens());
            requete.setInt(4, b.getId());

            requete.executeUpdate();
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public void InsertInBase(Boat b) {
        try {
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Boat VALUES (?, ?, ?, ?)");
            requete.setInt(1, b.getId());
            requete.setInt(2, b.getX());
            requete.setInt(3, b.getY());
            requete.setBoolean(4, b.getSens());
            requete.executeUpdate();

            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
   