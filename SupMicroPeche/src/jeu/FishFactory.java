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
import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class FishFactory {

    private Jeu jeu;
    private GestionDBFish db;
    private ArrayList<Fish> fishList;

    public FishFactory(Jeu jeu) {
        this.jeu = jeu;
        this.db = new GestionDBFish();
        this.fishList = new ArrayList<Fish>() ;
    }

    // @override
    public Fish createEntity(String type) {
        this.jeu.loadEntitiesFromDB();
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
        
        if(this.fishList.isEmpty()){
            f.setIsHost(1);
        } else {
            f.setIsHost(0);
        }
        
        int newId = PushPlayerInDBWhenCreated(f);
        f.setId(newId);
        this.fishList.add(f);
        
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
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Fishes (fish_type, x, y, sens, health, isHost) VALUES (?, ?, ?, ?, ?, ?)",
                                                                   Statement.RETURN_GENERATED_KEYS);
            
//            requete.setString(1, f.getId());
//            System.out.println("id :" + f.getId());
            requete.setString(1, f.getFishType());
            requete.setInt(2, f.getX());
            requete.setInt(3, f.getY());
            requete.setBoolean(4, f.getSens());
            requete.setInt(5, f.getHealthBar());
            requete.setInt(6, f.getIsHost());
            
            requete.executeUpdate();
            
            ResultSet rs = requete.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // retourne l’id auto-généré
            }
            
            requete.close();
//            conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Fish> getFishList() {
        return fishList;
    }

    public void setFishList(ArrayList<Fish> fishList) {
        this.fishList = fishList;
    }
    
    

}
