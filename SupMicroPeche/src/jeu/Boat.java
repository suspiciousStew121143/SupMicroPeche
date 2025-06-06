/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
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
    private int Timer=0;
    
    public Boat(String id){
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../resources/Boat_Right.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../resources/Boat_Left.png"));
            this.sprite = spriteDroite; // par défaut, le bâteau est orienté vers la droite
            this.isGoingRight = true;
        } catch (IOException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Id = id;
        this.sens = true;
        lancer();
    }   
    
   
    @Override
    public void miseAJour() {
        if(x>=(Parameters.windowLength-this.getWidth())){
            a=-1;
            this.isGoingRight = false;
        }    
        else if(x<=0){
            a=1; 
            this.isGoingRight = true;
        }
        x=x+a*3;
        y = 180 + (int)(Math.sin(x/20)*4);
        this.Timer += 1;
    }
    
    @Override
    public void lancer() {
        this.x = 1;
        this.y = 50;
    }  

    public int getTimer() {
        return Timer;
    }

    public void setTimer(int Timer) {
        this.Timer = Timer;
    }
    
    @Override
    public void rendu(Graphics2D contexte) {
        if(this.isGoingRight){
            this.sprite = this.spriteDroite;
        } else {
            this.sprite = this.spriteGauche;
        }
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }
}
