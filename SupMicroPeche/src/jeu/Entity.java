/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author atanguy
 */
public class Entity{
    protected BufferedImage sprite;
    protected BufferedImage spriteDroite;
    protected BufferedImage spriteGauche;
    protected int x, y;
    protected float time; // Temps depuis la création d'une entité, pour utiliser certaines Abilitys.
    protected int id;
    protected int healthBar;
    protected Boolean sens;
    protected int speed;
    protected Boolean hasChanged;

    
    public void miseAJour() {
    }
    
    public void lancer() {
    }
    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x){
//        this.x = x;
        // test
        if (this.x != x){
            this.x = x;
        }
        markChanged();
    }
    
    public void setY(int y){
//        this.y = y;
        // test
        if (this.y != y){
            this.y = y;
        }
        markChanged();
    }
    
    public void setHealth(int h){
//        this.healthBar = h;
        // test
        if (this.healthBar != h){
            this.healthBar = h;
        }
        markChanged();
    }
    
    public int getHealth(){
        return healthBar;
    }

    public double getHeight() {
        return sprite.getHeight();
    }

    public double getWidth() {
        return sprite.getWidth();
    }
    
    public int getId(){
        return id;
    }
    
    public Boolean getSens(){
        return sens;
    }

    public void setSens(Boolean sens) {
        this.sens = sens;
        markChanged();
    }
    
    public void setId(int id){
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void markChanged() {
        this.hasChanged = true;
    }

    public boolean hasChanged() {
        return hasChanged = true;
    }

    public void resetChangedFlag() {
        this.hasChanged = false;
    }
    
}
