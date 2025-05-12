/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 *
 * @author Louis
 */
public class Collectable extends Item {

    private boolean isPickedUp;
    
    public Collectable() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Collectable(int x_boat, int y_boat, int Height_boat) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = x_boat;
        this.y = y_boat+Height_boat;
    }

    
    @Override
    public void miseAJour() {
        y = y + 3;
    }
}

