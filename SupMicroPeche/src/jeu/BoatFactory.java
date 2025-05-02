/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

/**
 *
 * @author lkerguil
 */
public class BoatFactory {

    private Jeu jeu;

    public BoatFactory(Jeu jeu) {
        this.jeu = jeu;
    }

    // @override
    public Boat createEntity() {
        Boat b = new Boat();
        this.jeu.getBoatList().add(b);
        return b;

    }

}
