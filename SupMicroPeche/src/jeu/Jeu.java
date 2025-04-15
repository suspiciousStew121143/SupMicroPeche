/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<Entity> entityList;
    private Waste aWaste;
    private Fish aFish;
    private Boat aBoat;
    
  

    public Jeu() {
        
        this.aGlobeFish = new GlobeFish();
        this.aBoat = new Boat();
        this.aWaste = new Waste(aBoat);
        this.aFish = new Fish();
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

        // // 2. Rendu des sprites
        // int n = this.entityList.size();
        // for (int i=0; i<n; i++ ) {
        //     Entity e = this.entityList.get(i);
        //     contexte.drawImage(e.sprite, (int) e.getX(), (int) e.getY(), null);
        // }
        // 2. Rendu du sprites
        contexte.drawImage(this.aBoat.sprite, (int) this.aBoat.getX(), (int) this.aBoat.getY(), null);
        contexte.drawImage(this.aWaste.sprite, (int) this.aWaste.getX(), (int) this.aWaste.getY(), null);
        contexte.drawImage(this.aFish.sprite, (int) this.aFish.getX(), (int) this.aFish.getY(), null);
        contexte.drawImage(this.aGlobeFish.sprite, (int) this.aGlobeFish.getX(), (int) this.aGlobeFish.getY(), null);

        // 3. Rendu du textes
        contexte.drawString("Score : " + score, 10, 20);
    }

    public void miseAJour() {
        // 1. MAJ du poisson en fonction des commandes des joueurs

        // 2. MAJ des autres éléments (objets, monstres, etc.)
        
        // int n = this.entityList.size();
        // for (int i=0; i<n; i++ ) {
        //     Entity e = this.entityList.get(i);
        //     e.miseAJour();
        // }
        this.aBoat.miseAJour(aBoat);
        this.aWaste.miseAJour();
        this.aGlobeFish.miseAJour();
        // 3. Gérer les intéractions (collisions et autres règles)
        if (this.aWaste.getY() > 324 - aWaste.getHeight()) {
            this.aWaste.lancer(aBoat);
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

    
    // COLLISIONS
    public boolean FishCollideWaste() {
        return !((aWaste.getX() >= aFish.getX() + aFish.getWidth()) // trop à droite
                || (aWaste.getX() + aWaste.getHeight() <= aFish.getX()) // trop à gauche
                || (aWaste.getY() >= aFish.getY() + aFish.getHeight()) // trop en bas
                || (aWaste.getY() + aWaste.getWidth() <= aFish.getY())); // trop en haut
    }
    
    public ArrayList<Entity> getList() {
        return this.entityList;
    }
    
    public void addEntity(Entity anEntity) {
        this.entityList.add(anEntity);
    }
    
//    public void collide(ActionEvent e) {
//        int n = this.listeEntities.size();
//        for (int i=0; i<n; i ++) {
//            double bottomLeftCornerX = this.listeEntities.get(i).getX();
//            double bottomRightCornerX = this.listeEntities.get(i).getX();
//            double topLeftCornerX = this.listeEntities.get(i).getX();
//            double topRightCornerX = this.listeEntities.get(i).getX();
//        }
//    }

    

}