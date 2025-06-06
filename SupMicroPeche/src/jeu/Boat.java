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
    private int clock=0;
    private String boatType;
    private GestionDBBoat db;
    private Jeu jeu;
    
    public Boat(Jeu jeu, GestionDBBoat db){
        this.setBoatType("Classic");
        this.db = db;
        this.jeu = jeu;
        this.sens = false;
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/Boat_Right.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/Boat_Left.png"));
            this.sprite = spriteDroite; // par défaut, le bâteau est orienté vers la droite
        } catch (IOException ex) {
            Logger.getLogger(Boat.class.getName()).log(Level.SEVERE, null, ex);
        }
        lancer();
    }   
    
   
    @Override
    public void miseAJour() {
        // Ce bloc permet de vérifier le sens de la marche des bateaux lorsqu'on
        // lance une nouvelle fenêtre. Cela évite d'avoir des bateaux qui vont
        // en marche arrière lorsque leur sprite est orienté vers la gauche dans
        // la DB et qu'on lance une nouvelle partie qui "n'a pas conscience" de
        // l'orientation du sprite
//        if (!this.jeu.getBoatFactory().getBoatList().isEmpty()){
//            if (this.getSens()) { // true = gauche
//                a = -1;
//                this.sprite = this.spriteGauche;
//            } else { // false = droite
//                a = 1;
//                this.sprite = this.spriteDroite;
//            }
//        }
        if (this.getSens()) { // true = gauche
            a = -1;
            this.sprite = this.spriteGauche;
        } else { // false = droite
            a = 1;
            this.sprite = this.spriteDroite;
        }
        
        if(x>=(576-this.getWidth())){
            a=-1;
            this.sprite = this.spriteGauche;
            System.out.println("en arrière !");
            this.setSens(true);
        }    
        else if(x<=0){
            a=1; 
            this.sprite = this.spriteDroite;
            System.out.println("en avant !");
            this.setSens(false);
        }
        x=x+a*3;
        y = 50 + (int)(Math.sin(x/20)*4);
        this.clock += 1;

        if (hasChanged()){
            db.UpdateBase(this);
            resetChangedFlag();
        }
    }
    
    @Override
    public void lancer() {
        this.x = 1;
        this.y = 50;
    }  

     public int getClock() {
         return clock;
     }

     public void setClock(int clock) {
         this.clock = clock;
     }
    
    // @Override
    // public void rendu(Graphics2D contexte) {
    //     if(this.isGoingRight){
    //         this.sprite = this.spriteDroite;
    //     } else {
    //         this.sprite = this.spriteGauche;
    //     }
    //     contexte.drawImage(this.sprite, (int) x, (int) y, null);
    // }
    // }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }
    
    
}
