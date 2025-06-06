/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;


/**
 * 
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
}
