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
public abstract class Fish extends Entity {

    protected boolean toucheGauche, toucheDroite, toucheBas, toucheHaut;
    private String fishType;
    private float knockBack;
    private GestionDBFish db;
    private int isHost;
    
    public Fish(GestionDBFish db) {
        try {
            this.spriteDroite = ImageIO.read(getClass().getResource("../assets/ClownfishRight.png"));
            this.spriteGauche = ImageIO.read(getClass().getResource("../assets/ClownfishLeft.png"));
            this.sprite = spriteDroite; // par défaut, le poisson regarde à droite
        } catch (IOException ex) {
            Logger.getLogger(Fish.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 170;
        this.y = 314;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        this.sens = true;
        this.healthBar = 3;
        this.db = db;
        this.id = id;
        System.out.println("Player créé avec id = " + this.id);
        
   
    }
    
    protected abstract void deplacer();

    public void miseAJour() {
        // ========== DEPLACEMENT ==============================================
        // La methode "deplacer" est propre à chaque sous-classe de Fish et est
        // donc détaillée dans chaque sous-classe (ClownFish, GlobeFish,
        // SwordFish, WhaleFish).
        //
        // Il est cependant nécessaire d'initialiser la méthode abstraite dans
        // la classe mère Fish (voir ci-dessus : protected abstract void).
        
        deplacer();
        
        // ========== COLLISION ================================================
        if (x > 576 - sprite.getWidth()) {       // collision avec le bord droit
            x = 576 - sprite.getWidth();
        }
        if (x < 0) {                            // collision avec le bord gauche
            x = 0;
        }
        if (y > 324 - sprite.getHeight()) {        // collision avec le bord Bas
            y = 324 - sprite.getHeight();
        }
        if (y < 50+ sprite.getHeight()) {         // collision avec le bord haut
            y = 50+ sprite.getHeight();
        }
        
        // ========== MAJ DE LA BASE DE DONNEES ================================
//        db.UpdateBase(this);
        // test
        if (hasChanged()){
            db.UpdateBase(this);
            resetChangedFlag();
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

    public int getHealthBar() {
        return healthBar;
    }

    public String getFishType() {
        return fishType;
    }

    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    public int getIsHost() {
        return isHost;
    }

    public void setIsHost(int isHost) {
        this.isHost = isHost;
    }
    
    
    
}

