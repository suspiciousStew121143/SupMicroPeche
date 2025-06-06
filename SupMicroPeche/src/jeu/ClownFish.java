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
public class ClownFish extends Player{
    
    public ClownFish(){
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/ClownfishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/ClownfishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 170;
        this.y = 314;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        fishStep=2;
    }
    
    @Override
    public void useAbility(){
        //if 
    }
    
}
