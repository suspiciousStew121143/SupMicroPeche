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
    private ArrayList<Item> itemList;
    private ArrayList<Fish> fishList;
    private ArrayList<Boat> boatList;
    private BoatFactory aBoatFactory;

    public Jeu() {
        // ===========================
        // -- INITIALISATION DU JEU --
        // ===========================

        // Initialisation du score
        this.score = 0;

        // Initialisation des listes
        this.boatList = new ArrayList<>();
        this.fishList = new ArrayList<>();
        this.itemList = new ArrayList<>();

        // Initialisation du décor
        try {
            this.decor = ImageIO.read(getClass().getResource("../assets/Background.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ========================
        // -- CREATION D'ENTITES --
        // ========================
        // Uiliser l'usine à entités
        
        this.aBoatFactory = new BoatFactory(this);
        aBoatFactory.createEntity();
        
    }
   

    public void rendu(Graphics2D contexte) {
        // 1. Decor
        if (this.decor != null) {
            contexte.drawImage(this.decor, 0, 0, null);
        }

        // 2. Sprites
        for (Item item : this.itemList) {
            contexte.drawImage(item.sprite, (int) item.getX(), (int) item.getY(), null);
        }

        for (Fish fish : this.fishList) {
            contexte.drawImage(fish.sprite, (int) fish.getX(), (int) fish.getY(), null);
        }

        for (Boat boat : this.boatList) {
            contexte.drawImage(boat.sprite, (int) boat.getX(), (int) boat.getY(), null);
        }

        // 3. Texte
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
        // this.aBoat.miseAJour(aBoat);
        // this.aWaste.miseAJour();
        // // 3. Gérer les intéractions (collisions et autres règles)
        // if (this.aWaste.getY() > 324 - aWaste.getHeight()) {
        //     this.aWaste.lancer(aBoat);
        // }
        for (Item item : this.itemList) {
            item.miseAJour();
        }

        for (Boat boat : this.boatList) {
            boat.miseAJour();
        }

        for (Fish fish : this.fishList) {
            fish.miseAJour();
        }

        // 3. Gérer les interactions (collisions et autres règles)
    }

    public boolean estTermine() {
        return fishCollideItem();
    }

    public void incrementScore() {
        this.score += 1;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // COLLISIONS
    public boolean fishCollideItem() {
        for (Fish fish : fishList) {
            for (Item item : itemList) {
                if (!((item.getX() >= fish.getX() + fish.getWidth())    // Trop à droite
                    || (item.getX() + item.getWidth() <= fish.getX())    // Trop à gauche
                    || (item.getY() >= fish.getY() + fish.getHeight())   // Trop en bas
                    || (item.getY() + item.getHeight() <= fish.getY()))) // Trop en haut
                {
                    return true; // Collision détectée
                }
            }
        }
        return false; // Aucune collision détectée
    }

    // Getters pour accéder aux listes
    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public ArrayList<Fish> getFishList() {
        return this.fishList;
    }

    public ArrayList<Boat> getBoatList() {
        return this.boatList;
    }

    public BoatFactory getBoatFactory() {
        return aBoatFactory;
    }
    
}
