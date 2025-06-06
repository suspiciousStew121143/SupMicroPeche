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
public class WhaleFish extends Fish{
    
    public WhaleFish(GestionDBFish db){
        super(db);
        
        this.x = 100;
        this.y = 250;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        this.speed = 2;
        this.setFishType("Whale");
        
        
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/WhaleFishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/WhaleFishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(WhaleFish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void deplacer(){
        int speed = this.getSpeed();
        
        if (this.toucheGauche) {
            x -= speed;
            this.setSens(true);
        }
        if (this.toucheDroite) {
            x += speed;
            this.setSens(false);
        }
        if (this.toucheBas) {
            y += speed;
        }
        if (this.toucheHaut) {
            y -= speed;
        }
    }
    
    @Override
    public void useAbility(){
        //if 
    }
    
}
