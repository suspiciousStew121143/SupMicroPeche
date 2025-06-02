/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lkerguil
 */
public class GestionDBFish {
    private String adresseBase;
    private String user;
    private String motdepasse;
    private Connection connexion;

    public GestionDBFish() {
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

    public void UpdateBase(Fish f) {
        try {
            PreparedStatement requete = this.connexion.prepareStatement("UPDATE Fishes SET x = ?, y = ?, sens = ?, health = ? WHERE id = ?");

            requete.setInt(1, f.getX());
            requete.setInt(2, f.getY());
            requete.setBoolean(3, f.getSens());
            requete.setInt(4, f.getHealth());
            requete.setInt(5, f.getId());

            requete.executeUpdate();
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    public void InsertInBase(Fish f) {
        try {
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Fishes (id, x, y, sens, health) VALUES (?, ?, ?, ?, ?)");
            requete.setInt(1, f.getId());
            requete.setInt(2, f.getX());
            System.out.println(f.getX());
            requete.setInt(3, f.getY());
            requete.setBoolean(4, f.getSens());
            requete.setInt(5, f.getHealthBar());
            requete.executeUpdate();

            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
}
