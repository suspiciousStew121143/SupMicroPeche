/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author pcastani
 */
public class SwordFish extends Player {
    
    public SwordFish(){
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/ClownfishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/ClownfishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 200;
        this.y = 314;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        fishStep=1.5;
        fishDash=15;
    }

    
    @Override
    public void miseAJour(){
        double dash=0;
        if (this.toucheA) {     //Appuyer sur la touche A pour déclencher le dash
            dash = fishDash;
            }
        if (this.toucheGauche) {
            x -= fishStep * 5+dash;
        }
        if (this.toucheDroite) {
            x += fishStep * 5+dash;
        }
        if (this.toucheBas) {
            y += fishStep * 5+dash;
        }
        if (this.toucheHaut) {
            y -= fishStep * 5+dash;
        }
        
        
        if (x > 576 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 576 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if (y > 324 - sprite.getHeight()) { // collision avec le bord Bas de la scene
            y = 324 - sprite.getHeight();
        }
        if (y < 50+ sprite.getHeight()) { // collision avec le bord haut de la scene
            y = 50+ sprite.getHeight();
        }
    }
    
    
}
