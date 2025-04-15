/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author atanguy
 */
public class Jeu {

    private BufferedImage decor;
    private int score;
    private Waste aWaste;
    private Fish aFish;
    private Boat aBoat;
    
    private GlobeFish aGlobeFish;


    public Jeu() {
        this.aWaste = new Waste();
        this.aFish = new Fish();

        this.aBoat = new Boat();
        this.aGlobeFish = new GlobeFish();
        try {
            this.decor = ImageIO.read(getClass().getResource("../assets/Background.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.score = 0;
    }

    public void rendu(Graphics2D contexte) {
        // 1. Rendu du décor
        contexte.drawImage(this.decor, 0, 0, null);

        // 2. Rendu du sprites
        contexte.drawImage(this.aWaste.sprite, (int) this.aWaste.getX(), (int) this.aWaste.getY(), null);
        contexte.drawImage(this.aFish.sprite, (int) this.aFish.getX(), (int) this.aFish.getY(), null);
        contexte.drawImage(this.aBoat.sprite, (int) this.aBoat.getX(), (int) this.aBoat.getY(), null);
        contexte.drawImage(this.aGlobeFish.sprite, (int) this.aGlobeFish.getX(), (int) this.aGlobeFish.getY(), null);

        // 3. Rendu du textes
        contexte.drawString("Score : " + score, 10, 20);
    }

    public void miseAJour() {
        // 1. MAJ du poisson en fonction des commandes des joueurs

        // 2. MAJ des autres éléments (objets, monstres, etc.)
        this.aWaste.miseAJour();
        this.aBoat.miseAJour();
        this.aGlobeFish.miseAJour();
        // 3. Gérer les intéractions (collisions et autres règles)
        if (this.aWaste.getY() > 324 - aWaste.getHeight()) {
            this.aWaste.lancer();
        }
        if (this.aBoat.getX() > 576 ) {
            this.aBoat.lancer();
        }
    }

    public boolean estTermine() {        // Renvoie vrai si la partie est terminée (gagnée ou perdue)

        return FishCollideWaste();
    }

    public Fish getFish() {
        return aFish;
    }

    public void incrementScore() {
        this.score += 1;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean FishCollideWaste() {
        return !((aWaste.getX() >= aFish.getX() + aFish.getWidth()) // trop à droite
                || (aWaste.getX() + aWaste.getHeight() <= aFish.getX()) // trop à gauche
                || (aWaste.getY() >= aFish.getY() + aFish.getHeight()) // trop en bas
                || (aWaste.getY() + aWaste.getWidth() <= aFish.getY())); // trop en haut
    }

}