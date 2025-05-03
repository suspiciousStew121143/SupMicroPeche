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
    protected String Id;
    protected int healthBar;
    protected Boolean sens;

    
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
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setHealth(int h){
        this.healthBar = h;
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
    
    public String getId(){
        return Id;
    }
    
    public Boolean getSens(){
        return sens;
    }
    
    public void setId(String id){
        this.Id = id;
    }
}
