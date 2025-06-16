/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author eleroy
 */
public class Bubble extends Entity{
    
    private int y_Destination;
    private int x_Destination;
    private int y_Start;
    private int x_Start;
    private boolean toBeDeleted;
    
    public Bubble(int y_Destination, int x_Destination, int y_start,int x_start){
        spriteSheet = new BufferedImage[1];
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource("../resources/bubble.png"));
            for (int i = 0; i < 1; i++) {
                int x = i * 16;
                spriteSheet[i] = tileset.getSubimage(x, 0, 16, 16); // Pour l'instant bubble n'est pas animée, ça viendra dans le futur
            }
            this.sprite = spriteSheet[0];
            this.isGoingRight = true;
        } catch (IOException ex) {
            Logger.getLogger(Bubble.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Coordonnées de départ et d'arrivée de chaque bulle
        this.x_Start = x_start;
        this.y_Start = y_start;
        this.y_Destination = y_Destination;
        this.x_Destination = x_Destination ;
        
        this.x = x_Start ;
        this.y = y_Start ;
        
        this.toBeDeleted = false;
    }   
    
    
    public void miseAJour(Random rand) {
        this.x = x + rand.nextInt(-5,5);
        this.y -= 8;
        
        if (y<y_Destination){
            setToBeDeleted(true);
        }
    }

    public int getY_Destination() {
        return y_Destination;
    }

    public void setY_Destination(int y_Destination) {
        this.y_Destination = y_Destination;
    }

    public int getX_Destination() {
        return x_Destination;
    }

    public void setX_Destination(int x_Destination) {
        this.x_Destination = x_Destination;
    }

    public boolean getToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }
    
    
    
}
