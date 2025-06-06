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
public class ClownFish extends Fish{

    
    public ClownFish(GestionDBFish db){
        super(db); // Appelle le constructeur mère Player
        
        this.x = 100;
        this.y = 250;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        this.speed = 5;
        this.setFishType("Clown");
        
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/ClownfishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/ClownfishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(ClownFish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void deplacer(){
        int speed = this.getSpeed();
        
        if (this.toucheGauche) {
            x -= speed;
            this.sprite = this.spriteGauche;
            markChanged();
        }
        if (this.toucheDroite) {
            x += speed;
            this.sprite = this.spriteDroite;
            markChanged();
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
