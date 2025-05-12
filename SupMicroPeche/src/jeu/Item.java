/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;


/**
 * Exemple de classe lutin
 *
 * @author Louis
 */
public class Item extends Entity {
    

    public Item() {

    }

    
    @Override
    public void miseAJour() {
        y = y + 5;
    }      


//    public void lancer(Boat b) {
//        this.x = b.getX();
//        this.y = b.getY()+b.getHeight();
//    }

}

