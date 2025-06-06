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
public class SwordFish extends Fish {
    
    public SwordFish(GestionDBFish db){
        super(db);
        
        this.x = 100;
        this.y = 250;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        this.speed = 10;
        this.setFishType("Sword");
        
        
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../resources/SwordFishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../resources/SwordFishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(SwordFish.class.getName()).log(Level.SEVERE, null, ex);
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
