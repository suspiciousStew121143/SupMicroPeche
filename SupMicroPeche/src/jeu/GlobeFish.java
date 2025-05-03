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
public class GlobeFish extends Players{
    
    
    public GlobeFish(){
        this.x = 100;
        this.y = 250;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        try {
            this.sprite = ImageIO.read(getClass().getResource("../assets/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(GlobeFish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override 
    public void miseAJour(){
        if (this.toucheGauche) {
            x -= 2;
        }
        if (this.toucheDroite) {
            x += 2;
        }
        if (this.toucheBas) {
            y += 2;
        }
        if (this.toucheHaut) {
            y -= 2;
        }
        
        if (x > 576 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 576 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if (y > 324 - sprite.getHeight()) { // collision avec le bord Haut de la scene
            y = 324 - sprite.getHeight();
        }
        if (y < 0) { // collision avec le bord inferieur de la scene
            y = 0;
        }
    }
    
    @Override
    public void useAbility(){
        //if 
    }
    
    
}
