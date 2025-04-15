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
    
    public Boat(){
        try {
            this.sprite = ImageIO.read(getClass().getResource("../assets/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }
        lancer();
    }
    
   
    public void miseAJour(Boat b) {
        if(x>(576-b.getWidth())){
            a=-1;
        }    
        if(x<0){
            a=1; 
        }
        x=x+a*3;
    }
    
    public void lancer() {
        this.x = 1;
        this.y = 40;
    }  
}
