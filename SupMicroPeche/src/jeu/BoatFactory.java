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

    private ArrayList<Boat> boatList;
    private ArrayList<Boat> boatToDeleteList;
    
    public BoatFactory(Jeu jeu) {
        this.boatList = new ArrayList<>();
        this.boatToDeleteList = new ArrayList<>();
    }

    public void createEntity() {
        String id = ReadListAndCreateId();
        Boat b = new Boat(id);
        this.boatList.add(b);
        PushBoatInBDWhenCreated(b);
    }
    
    public void deleteEntity(String id) {   //Permet de delete un bateau de la liste et de la base de donnée
        for (Boat b : this.boatList) {
            if (b.getId().equals(id)) {
                this.boatList.remove(b);
                DeleteBoatInBDWhenCreated(b);
                break;
            }
        }
    }
    
    public String ReadListAndCreateId(){
        int nb_boat = this.boatList.size()+1;
        String id = "B" + nb_boat;
//        System.out.println(id);
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
    
    public void DeleteBoatInBDWhenCreated(Boat b){   //Permet de supprimer un bateau de la base de donnée
        try {
            Connection connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
            PreparedStatement requete = connexion.prepareStatement("DELETE FROM Boat WHERE id = ?");
            requete.setString(1, b.getId());
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

    public ArrayList<Boat> getBoatToDeleteList() {
        return boatToDeleteList;
    }

    public void addBoatToDeleteList(Boat boatToDelete) {
        this.boatToDeleteList.add(boatToDelete);
    }
    
    public void boatSuppression(){
        for (Boat b : boatToDeleteList){
            this.deleteEntity(b.getId()); 
        }
        this.boatToDeleteList = new ArrayList<>();
    }
    
    public void startFactory() {  //Permet de créer un bateau automatiquement toutes les tant de secondes aléatoirement sur un intervalle
        int delay = 5000 + (int) (Math.random() * 15000); // entre 5000ms (5s) et 20000ms (20s)
        
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                BoatFactory.this.createEntity(); // Crée le bateau
                startFactory(); // Programme la création suivante
            }
        }, delay);
    }
}
