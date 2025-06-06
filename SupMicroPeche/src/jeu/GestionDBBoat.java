
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
import java.util.ArrayList;

/**
 *
 * @author pcastani
 */
public class GestionDBBoat {

    private String adresseBase;
    private String user;
    private String motdepasse;
    private Connection connexion;
    private long lastSyncTime = 0;
    private Jeu jeu;

    public GestionDBBoat() {
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";
        this.jeu = jeu;

    }

    public void UpdateBase(Boat b) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("UPDATE Boat SET boat_type = ?, x = ?, y = ?, sens = ?, clock = ? WHERE id = ?");

            requete.setString(1, b.getBoatType());
            requete.setInt(2, b.getX());
            //System.out.println(b.getX());
            requete.setInt(3, b.getY());
            requete.setBoolean(4, b.getSens());
            requete.setInt(5, b.getClock());
            requete.setInt(6, b.getId());

            requete.executeUpdate();
            requete.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void InsertInBase(Boat b) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Boat VALUES (?, ?, ?, ?, ?, ?)");
            requete.setInt(1, b.getId());
            requete.setString(2, b.getBoatType());
            requete.setInt(3, b.getX());
            requete.setInt(4, b.getY());
            requete.setBoolean(5, b.getSens());
            requete.setInt(6, b.getClock());
            requete.executeUpdate();

            requete.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void syncBoatList(ArrayList<Boat> currentBoats) {
        try (Connection conn = SingletonJDBC.getInstance().getConnection(); PreparedStatement requete = conn.prepareStatement("SELECT id, boat_type, x, y, sens, clock FROM Boat"); ResultSet rs = requete.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String boatType = rs.getString("boat_type");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                boolean sens = rs.getBoolean("sens");
                String type = rs.getString("boat_type");
                int clock = rs.getInt("clock");

                Boat matchingBoat = null;
                for (Boat b : currentBoats) {
                    if (b.getId() == id) {
                        matchingBoat = b;
                        break;
                    }
                }

                if (matchingBoat != null) {
                    if (matchingBoat.getX() != x || matchingBoat.getY() != y
                            || matchingBoat.getSens() != sens || !matchingBoat.getBoatType().equals(type)) {

                        matchingBoat.setX(x);
                        matchingBoat.setY(y);
                        matchingBoat.setSens(sens);
                        matchingBoat.setBoatType(type);
                        matchingBoat.setClock(clock);
                    }
                } else {
                    Boat b = new Boat(jeu, this);
                    b.setId(id);
                    b.setX(x);
                    b.setY(y);
                    b.setSens(sens);
                    b.setBoatType(type);
                    b.setClock(clock);
                    currentBoats.add(b);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteBoatsFromDB() {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("DELETE FROM Boat");
            requete.executeUpdate();
            requete.close();
            System.out.println("Tous les bateaux ont été supprimés.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
