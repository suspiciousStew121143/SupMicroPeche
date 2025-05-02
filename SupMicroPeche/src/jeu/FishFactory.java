/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

/**
 *
 * @author lkerguil
 */
public class FishFactory {

    private Jeu jeu;

    public FishFactory(Jeu jeu) {
        this.jeu = jeu;
    }

    // @override
    public void createEntity() {
        Fish f = new Fish();
        f.lancer();
        this.jeu.getFishList().add(f);

    }

}
