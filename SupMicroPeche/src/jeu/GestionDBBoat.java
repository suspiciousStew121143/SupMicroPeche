
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

    public GestionDBBoat() {
        System.out.println("GestionDBBoat est appelée");
        this.adresseBase = "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche";
        this.user = "etudiant";
        this.motdepasse = "YTDTvj9TR3CDYCmP";

    }

    public void UpdateBase(Boat b) {
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("UPDATE Boat SET x = ?, y = ?, sens = ? WHERE id = ?");

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
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Boat VALUES (?, ?, ?, ?)");
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

    public void syncBoatList(ArrayList<Boat> currentBoats) {
        try (Connection conn = SingletonJDBC.getInstance().getConnection(); PreparedStatement requete = conn.prepareStatement("SELECT id, x, y, sens, boat_type FROM Boat"); ResultSet rs = requete.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                boolean sens = rs.getBoolean("sens");
                String type = rs.getString("boat_type");

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
                    }
                } else {
                    Boat b = new Boat(this);
                    b.setId(id);
                    b.setX(x);
                    b.setY(y);
                    b.setSens(sens);
                    b.setBoatType(type);
                    currentBoats.add(b);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
