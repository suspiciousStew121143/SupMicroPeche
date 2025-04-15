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
public class Entity {
    protected BufferedImage sprite;
    protected BufferedImage spriteDroite;
    protected BufferedImage spriteGauche;
    protected double x, y;
    
    public void miseAJour() {
    }
    
    public void lancer() {
    }
    
    public void rendu(Graphics2D contexte) {
        contexte.drawImage(this.sprite, (int) x, (int) y, null);
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getHeight() {
        return sprite.getHeight();
    }

    public double getWidth() {
        return sprite.getWidth();
    }
    
}
