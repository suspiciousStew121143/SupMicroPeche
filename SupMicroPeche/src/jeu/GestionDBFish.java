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
import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class GestionDBFish {

    private Jeu jeu;
    private String adresseBase;
    private String user;
    private String motdepasse;
    private Connection connexion;
    private FishFactory aFishFactory;

    public GestionDBFish() {
        
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";

//        try {
//            System.out.println("tentative de connexion");
//            this.connexion = DriverManager.getConnection(this.adresseBase, this.user, this.motdepasse);
//
//        } catch (SQLException ex) {
//            System.out.println("Erreur");
//            ex.printStackTrace();
//        }
    }

    public void UpdateBase(Fish f) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("UPDATE Fishes SET x = ?, y = ?, sens = ?, health = ?, isHost = ? WHERE id = ?");
            
            requete.setInt(1, f.getX());
            requete.setInt(2, f.getY());
            requete.setBoolean(3, f.getSens());
            requete.setInt(4, f.getHealth());
            requete.setInt(5, f.getIsHost());
            requete.setInt(6, f.getId());

            requete.executeUpdate();
            requete.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void InsertInBase(Fish f) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Fishes (id, x, y, sens, health, isHost) VALUES (?, ?, ?, ?, ?, ?)");
            requete.setInt(1, f.getId());
            requete.setInt(2, f.getX());
            System.out.println(f.getX());
            requete.setInt(3, f.getY());
            requete.setBoolean(4, f.getSens());
            requete.setInt(5, f.getHealthBar());
            this.jeu = new Jeu();
            if (this.aFishFactory.getFishList().size() < 1) {
                requete.setInt(6, 1);
            } else {
                requete.setInt(6, 0);
            }
            requete.executeUpdate();

            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void syncFishList(ArrayList<Fish> currentFishes) {
        try (Connection conn = SingletonJDBC.getInstance().getConnection(); PreparedStatement requete = conn.prepareStatement("SELECT id, fish_type, x, y, sens, health, isHost FROM Fishes"); ResultSet rs = requete.executeQuery()) {
            
            // test
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String type = rs.getString("fish_type");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                boolean sens = rs.getBoolean("sens");
                int health = rs.getInt("health");
                int isHost = rs.getInt("isHost");

                
                // Trouver tous les poissons dans la fishList qui matchent avec la DB
                Fish matchingFish = null;
                for (Fish f : currentFishes) {
                    if (f.getId() == id) {
                        matchingFish = f;
                        break;
                    }
                }

                if (matchingFish != null) {
                    if (matchingFish.getX() != x || matchingFish.getY() != y
                            || matchingFish.getSens() != sens || !matchingFish.getFishType().equals(type)) {

                        matchingFish.setX(x);
                        matchingFish.setY(y);
                        matchingFish.setSens(sens);
                        matchingFish.setFishType(type);
                        matchingFish.setIsHost(isHost);
                    }
                } else {
                    Fish f;
                    switch (type.toLowerCase()) {
                        case "clown":
                            f = new ClownFish(this);
                            break;
                        case "globe":
                            f = new GlobeFish(this);
                            break;
                        case "sword":
                            f = new SwordFish(this);
                            break;
                        case "whale":
                            f = new WhaleFish(this);
                            break;
                        default:
                            System.out.println("Type de poisson inconnu : " + type + ", création par défaut.");
                            f = new ClownFish(this); // fallback par défaut
                            break;
                    }

                    f.setId(id);
                    f.setFishType(type);
                    f.setX(x);
                    f.setY(y);
                    f.setSens(sens);
                    f.setHealth(health);
                    f.setIsHost(isHost);

                    currentFishes.add(f);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //test
    public void deleteFishFromDB(int fishId) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("DELETE FROM Fishes WHERE id = ?");
            requete.setInt(1, fishId);
            requete.executeUpdate();
            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
