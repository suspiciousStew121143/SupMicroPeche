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
 * @author eleroy
 */
public class Boat extends Entity{
    
    private int a=1;
    private GestionDBBoat db;
    
    public Boat(String id, GestionDBBoat db){
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/Boat_Right.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/Boat_Left.png"));
            this.sprite = spriteDroite; // par défaut, le bâteau est orienté vers la droite
        } catch (IOException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
        this.sens = true;
        this.db = db;
        lancer();
    }   
    
   
    @Override
    public void miseAJour() {
        if(x>=(576-this.getWidth())){
            a=-1;
            this.sprite = this.spriteGauche;
        }    
        else if(x<=0){
            a=1; 
            this.sprite = this.spriteDroite;
        }
        x=x+a*3;
        db.UpdateBase(this);
    }
    
    @Override
    public void lancer() {
        this.x = 1;
        this.y = 50;
    }  
}
