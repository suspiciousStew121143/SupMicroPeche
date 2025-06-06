/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * 
 *
 * @author Louis
 */
public class Waste extends Item {

    public Waste() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Waste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Waste(int x_boat, int y_boat, int Height_boat) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Waste.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = x_boat;
        this.y = y_boat+Height_boat;
    }

    
    @Override
    public void miseAJour() {
    }
    
    @Override
    public void miseAJour(ArrayList<Colonne> colonnes) {  //Permet de renvoyer les Waste vers le ciel lorqu'ils sont alignés avec une colonne

        for (Colonne c : colonnes) {
            if (c.isActive()) {  // On ne regarde que les colonnes actives
                if ((x < (c.getX() + c.getWidth())) && (x > (c.getX()-this.sprite.getWidth()))) {
                    y = y - 30;
                } else {
                    y = y + 3;
                }
            }
        }
    }
    
}

