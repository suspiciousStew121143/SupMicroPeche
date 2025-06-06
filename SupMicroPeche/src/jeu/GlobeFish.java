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
public class GlobeFish extends Player{
    
    
    public GlobeFish(){
        try {
            this.sprite = ImageIO.read(getClass().getResource("../assets/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(GlobeFish.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 100;
        this.y = 250;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        fishStep=0.5;
    }
    
    @Override 
    public void miseAJour(){
    }
    
    @Override
    public void useAbility(){
        if (this.toucheA) {     //Appuyer sur la touche A pour déclencher le soin
            soin = true;
            } 
    }
    
    
}
