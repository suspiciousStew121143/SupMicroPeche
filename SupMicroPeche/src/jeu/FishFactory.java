/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author lkerguil
 */
public class FishFactory {

    private Jeu jeu;
    private GestionDBFish db;

    public FishFactory(Jeu jeu) {
        this.jeu = jeu;
        this.db = new GestionDBFish();
    }

    // @override
    public Fish createEntity(String type) {
//        String id = ReadListAndCreateId();
        Fish f;
        switch (type) {
        case "clown":
            f = new ClownFish(db);
            break;
        case "globe":
            f = new GlobeFish(db);
            break;
        case "sword":
            f = new SwordFish(db);
            break;
        case "whale":
            f = new WhaleFish(db);
            break;
        default:
            f = new ClownFish(db);
            break;
        }
        
        int newId = PushPlayerInDBWhenCreated(f);
        f.setId(newId);
        
        this.jeu.getFishList().add(f);
        return f;
    }
    
//    public String ReadListAndCreateId(){
//        int nb_fish = this.jeu.getFishList().size()+1;
//        System.out.println(nb_fish);
//        String id = "F" + nb_fish;
//        return id;
//    }

    public int PushPlayerInDBWhenCreated(Fish f){   
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Fishes (fish_type, x, y, sens, health) VALUES (?, ?, ?, ?, ?)",
                                                                   Statement.RETURN_GENERATED_KEYS);
            
//            requete.setString(1, f.getId());
//            System.out.println("id :" + f.getId());
            requete.setString(1, f.getFishType());
            requete.setInt(2, f.getX());
            requete.setInt(3, f.getY());
            requete.setBoolean(4, f.getSens());
            requete.setInt(5, f.getHealthBar());
            
            requete.executeUpdate();
            
            ResultSet rs = requete.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retourne l’id auto-généré
            }
            
            requete.close();
            connexion.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }  

}
