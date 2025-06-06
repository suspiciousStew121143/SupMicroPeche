/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe lutin
 *
 * @author guillaume.laurent
 */
public class Player extends Entity {

    protected boolean toucheGauche, toucheDroite, toucheBas, toucheHaut, toucheA, cooldownA, soin,toucheEspace, toucheEspaceFrontMontant;
    protected double fishStep, fishDash;
    private String name;
    private float speed;
    private int healthBar;
    private float knockBack;
    private Collectable collectable;
    private boolean hasAPickUp;
    
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
        this.toucheA = false;
        this.cooldownA = false;
        this.hasAPickUp = false;
        this.fishStep=1;        //Multiplicateur de vitesse propre à chaque poisson      
        this.toucheEspace = false;
    }

    @Override
    public void miseAJour() {
        if (this.toucheGauche) {
            x -= fishStep * 5;
        }
        if (this.toucheDroite) {
            x += fishStep * 5;
        }
        if (this.toucheBas) {
            y += fishStep * 5;
        }
        if (this.toucheHaut) {
            y -= fishStep * 5;
        }
        
        
        if (x > Parameters.windowLength - sprite.getWidth()) { // collision avec le bord droit de la scene
            x = Parameters.windowLength - sprite.getWidth();
        }
        if (x < 0) { // collision avec le bord gauche de la scene
            x = 0;
        }
        if (y > Parameters.windowHeight - sprite.getHeight()) { // collision avec le bord Bas de la scene
            y = Parameters.windowHeight - sprite.getHeight();
        }
        if (y < 50+ sprite.getHeight()) { // collision avec le bord haut de la scene
            y = 50+ sprite.getHeight();
        }
    }
    
    public void rendu(Graphics2D contexte) {
        // Dans le cas où un objet est ramassé on le dessine relativement en dessous du joueur comme s'il était porté
        if(this.hasAPickUp){
            this.getCollectable().rendu(contexte,this.getX(), this.getY());
        }
        
        // On dessine ensuite le joueur pour qu'il apparaisse au dessus du collectable
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }

    public void pickUp(Collectable c){
        this.setCollectable(c);
        this.setHasAPickUp(true);
        c.setIsPickedUp(true);
    }
    
    public void dropPickUp(){
        this.setHasAPickUp(false);
        this.getCollectable().setIsPickedUp(false);
        this.getCollectable().setX(this.getX());
        this.getCollectable().setY(this.getY()+15);
        
        //On va "réinitialiser" le collectable en un objet vide, pour éviter de le conserver dans les attributs du joueur
        this.setCollectable(new Collectable());
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
    public void setToucheA(boolean etat) {
        this.toucheA = etat;
    }

    public boolean isToucheA() {
        return this.toucheA;
    }

    public void setCooldownA(boolean valeur) { //Permet d'activer/désactiver le cooldown
        this.cooldownA = valeur;
    }

    public boolean isCooldownA() { //Permet de savoir si la touche A est en cooldown
        return this.cooldownA;
    }
    
    public void setToucheEspace(boolean toucheEspace) {
        this.toucheEspace = toucheEspace;
    }

    public boolean getToucheGauche() {
        return toucheGauche;
    }

    public boolean getToucheDroite() {
        return toucheDroite;
    }

    public boolean getToucheBas() {
        return toucheBas;
    }

    public boolean getToucheHaut() {
        return toucheHaut;
    }

    public boolean getToucheEspace() {
        return toucheEspace;
    }

    public Collectable getCollectable() {
        return collectable;
    }

    public void setCollectable(Collectable collectable) {
        this.collectable = collectable;
    }

    public boolean HasAPickUp() {
        return hasAPickUp;
    }

    public void setHasAPickUp(boolean hasAPickUp) {
        this.hasAPickUp = hasAPickUp;
    }

    public boolean getToucheEspaceFrontMontant() {
        return toucheEspaceFrontMontant;
    }

    public void setToucheEspaceFrontMontant(boolean toucheEspaceFrontMontant) {
        this.toucheEspaceFrontMontant = toucheEspaceFrontMontant;
    }
    
    
    
}

