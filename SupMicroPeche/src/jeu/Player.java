/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Player extends Entity {

    protected boolean toucheGauche, toucheDroite, toucheBas, toucheHaut;
    private String name;
    private float speed;
    private int healthBar;
    private float knockBack;
    
    public Player() {
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../resources/ClownfishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../resources/ClownfishLeft.png"));
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
    }

    @Override
    public void miseAJour() {
        if (this.toucheGauche) {
            x -= 5;
        }
        if (this.toucheDroite) {
            x += 5;
        }
        if (this.toucheBas) {
            y += 5;
        }
        if (this.toucheHaut) {
            y -= 5;
        }
        
        
        if (x > 576 - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = 576 - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if (y > 324 - sprite.getHeight()) { // collision avec le bord Bas de la scene
            y = 324 - sprite.getHeight();
        }
        if (y < 50+ sprite.getHeight()) { // collision avec le bord haut de la scene
            y = 50+ sprite.getHeight();
        }
    }

    
    public void useAbility(){

    }
    
    
    
    
    public void setToucheGauche(boolean etat) {
        this.toucheGauche = etat;
        if (etat) {
            this.sprite = spriteGauche;
        }
    }
    
    public void setToucheDroite(boolean etat) {
        this.toucheDroite = etat;
        if (etat) {
            this.sprite = spriteDroite;
        }
    }    
    
    public void setToucheBas(boolean etat) {
        this.toucheBas = etat;
    }
    public void setToucheHaut(boolean etat) {
        this.toucheHaut = etat;
    }
    
}

