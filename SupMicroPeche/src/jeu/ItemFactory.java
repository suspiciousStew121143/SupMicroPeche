/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class ItemFactory {

    
    private Jeu jeu;
    public ArrayList<Waste> itemList;
    private GestionDBItem db;
    
    public ItemFactory(Jeu jeu) {
        this.jeu = jeu;
        this.itemList = new ArrayList<Waste>();
        this.db = new GestionDBItem(jeu);
    }

    public void createEntity(Boat boat) {
        Waste w = new Waste(boat, db);
        w.setBoat(boat);
        
        int xOfBoat = boat.getX();
        int yOfBoat = boat.getY();
        int heightOfBoat = boat.sprite.getHeight();
        
        w.setX(xOfBoat);
        w.setY(yOfBoat);
        
        System.out.println("item boat : " + w.getBoat());
        System.out.println("item x : " + w.getX());
        System.out.println("item y : " + w.getY());
        
        int newId = PushItemInDBWhenCreated(w);
        w.setId(newId);
                
        this.itemList.add(w);
    }

    
    public int PushItemInDBWhenCreated(Waste w){   
        try {
            Connection conn = SingletonJDBC.getInstance().getConnection();
            PreparedStatement requete = conn.prepareStatement("INSERT INTO Item (x, y) VALUES (?, ?)",
                                                                   Statement.RETURN_GENERATED_KEYS);
            System.out.println("PushInDB ITEMFACTORY : waste" + w);
            System.out.println("PushInDB ITEMFACTORY : waste X " + w.getX());
            System.out.println("PushInDB ITEMFACTORY : waste Y " + w.getY());
            System.out.println(w.getBoat());
            int dropAtX = w.getBoat().getX();
            int dropAtY = w.getBoat().getY();
            
            requete.setInt(1, dropAtX);
            requete.setInt(2, dropAtY);
            
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

    public ArrayList<Waste> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Waste> itemList) {
        this.itemList = itemList;
    }
    
    

}
