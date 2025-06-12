/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;    
import javax.imageio.ImageIO;

/**
 *
 * @author eleroy
 */
public class Colonne extends Entity{
    
    private boolean active = false;
    
    public Colonne() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("/resources/Column.png"));
        } catch (IOException ex) {
            Logger.getLogger(Waste.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Random rand = new Random();
//        this.x = rand.nextInt(576-this.sprite.getWidth()); // x entre 50 et 500
//        this.y = 324-this.sprite.getHeight(); // y entre 50 et 500
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void rendu(Graphics2D contexte) {
        if (active) {
            super.rendu(contexte);
        }
        // sinon, ne rien dessiner (colonne inactive)
    }
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
