/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author lkerguil
 */
public class BoatFactory {

    private Jeu jeu;
    private ArrayList<Boat> boatList;
    
    public BoatFactory(Jeu jeu) {
        this.jeu = jeu;
        this.boatList = new ArrayList<Boat>();
    }

    public void createEntity() {
        String id = ReadListAndCreateId();
        Boat b = new Boat(id);
        this.boatList.add(b);
        PushBoatInBDWhenCreated(b);
    }
    
    public String ReadListAndCreateId(){
        int nb_boat = this.boatList.size()+1;
        System.out.println(nb_boat);
        String id = "B" + nb_boat;
        return id;
    }

    public void PushBoatInBDWhenCreated(Boat b){   
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requete = connexion.prepareStatement("INSERT INTO Boat VALUES (?, ?, ?, ?)");
            requete.setString(1, b.getId());
            requete.setInt(2, b.getX());
            requete.setInt(3, b.getY());
            requete.setBoolean(4, b.getSens());
            requete.executeUpdate();

            requete.close();
            connexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  

    public ArrayList<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(ArrayList<Boat> boatList) {
        this.boatList = boatList;
    }
    
    
    
}
