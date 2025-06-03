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
import java.sql.Statement;

/**
 *
 * @author lkerguil
 */
public class BoatFactory {

    private Jeu jeu;
    private GestionDBBoat db;

    public BoatFactory(Jeu jeu) {
        this.jeu = jeu;
        this.db = new GestionDBBoat();
    }

    // @override
    public Boat createEntity() {
        Boat b = new Boat(db);
        
        int newId = PushBoatInDBWhenCreated(b);
        b.setId(newId);
        
        this.jeu.getBoatList().add(b);
        return b;
    }
    
//    public String ReadListAndCreateId(){
//        // Vérifie combien de Boat sont dans la boatList et crée un ID en conséquence.
//        //          ------------ EXEMPLE ------------------
//        // SI       1 Boat est dans la liste avec l'id = B1
//        // ALORS    le prochain Boat créé aura    l'id = B2
//        int nb_boat = this.jeu.getBoatList().size()+1;
//        System.out.println(nb_boat);
//        String id = "B" + nb_boat;
//        return id;
//    }

    public int PushBoatInDBWhenCreated(Boat b){   
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Boat (boat_type, x, y, sens) VALUES (?, ?, ?, ?)",
                                                                   Statement.RETURN_GENERATED_KEYS);
            
//            requete.setInt(1, b.getId());
            requete.setString(1, b.getBoatType());
            requete.setInt(2, b.getX());
            requete.setInt(3, b.getY());
            requete.setBoolean(4, b.getSens());
            
            requete.executeUpdate();
            
            ResultSet rs = requete.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retourne l’id auto-généré
            }

            requete.close();
            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }  
    
    
}
