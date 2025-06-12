/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class GestionDBItem {
    
    private String adresseBase;
    private String user;
    private String motdepasse;
    private Connection connexion;
    private Jeu jeu;

    public GestionDBItem(Jeu jeu) {
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
        this.jeu = jeu;
    }

    public void insertInBase(Waste w) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Item (id, x, y, boat_id) VALUES (?, ?, ?, ?)");

            requete.setInt(1, w.getId());
            requete.setInt(2, w.getX());
            requete.setInt(3, w.getY());
            requete.setInt(4, w.getBoat().getId());
            
            requete.executeUpdate();
            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateBase(Waste w) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("UPDATE Item SET x = ?, y = ?, boat_id = ? WHERE id = ?");
            
            requete.setInt(1, w.getX());
            requete.setInt(2, w.getY());
            requete.setInt(3, w.getBoat().getId());
            requete.setInt(4, w.getId());
            requete.executeUpdate();
            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void syncWasteList(ArrayList<Waste> currentWastes, ArrayList<Boat> currentBoats) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("SELECT id, x, y, boat_id FROM Item");
            ResultSet rs = requete.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                int boatId = rs.getInt("boat_id");
                Boat associatedBoat = null;

                // On recherche le bateau associé dans la liste courante
                for (Boat b : currentBoats) {
                    if (b.getId() == boatId) {
                        associatedBoat = b;
                        break;
                    }
                }

                Waste matchingWaste = null;
                for (Waste w : currentWastes) {
                    if (w.getId() == id) {
                        matchingWaste = w;
                        break;
                    }
                }

                if (matchingWaste != null) {
                    // Mettre à jour la position et le bateau associé si nécessaire
                    matchingWaste.setX(x);
                    matchingWaste.setY(y);
                    matchingWaste.setBoat(associatedBoat);
                } else {
                    Waste w = new Waste(associatedBoat, this);
                    w.setId(id);
                    w.setX(x);
                    w.setY(y);
                    w.setBoat(associatedBoat);
                    currentWastes.add(w);
                }
            }

            rs.close();
            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteWastesFromDB() {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("DELETE FROM Item");
            requete.executeUpdate();
            requete.close();
            System.out.println("Tous les déchets ont été supprimés.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
