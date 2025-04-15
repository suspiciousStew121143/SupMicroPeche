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
 * Exemple de classe lutin
 *
 * @author Louis
 */
public class Waste extends Entity {

    public Waste() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../assets/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Waste.class.getName()).log(Level.SEVERE, null, ex);
        }
        lancer();
    }

    
    @Override
    public void miseAJour() {
        y = y + 5;
    }


    public void lancer() {
        this.x = 15 + Math.random() * 576;
        this.y = -27;
    }

}

