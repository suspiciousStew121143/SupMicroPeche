/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

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
        this.boatList = new ArrayList<>();
    }

    public Boat createEntity() {
        Boat b = new Boat();
        this.boatList.add(b);
        return b;
    }

    public ArrayList<Boat> getBoatList() {
        return boatList;
    }

    
}
