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
    public ItemFactory anItemFactory;
    private BoatFactory aBoatFactory;
    private FishFactory aFishFactory;
    private Fish localFish;
    private long lastSyncTime = 0;
    
    private boolean hasJoinedGame;
    // private GestionBD aBD;

    public Jeu() {
        // Initialisation du score
        this.score = 0;

        // Initialisation du boolean pour éviter de rejoindre plusieurs fois la partie
        this.hasJoinedGame = false;
        
        // Initialisation du décor
        try {
            this.decor = ImageIO.read(getClass().getResource("../assets/Background.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Initialisation des usines à entités
        this.aBoatFactory = new BoatFactory(this);
        this.anItemFactory = new ItemFactory(this);
        this.aFishFactory = new FishFactory(this);
        
        // La création du poisson local (celui qui appartient à telle ou telle
        // fenêtre, se fait désormais dans FenetreDeJeu (plus logique)
    }
   

    public void rendu(Graphics2D contexte) {
        // 1. Decor
        if (this.decor != null) {
            contexte.drawImage(this.decor, 0, 0, null);
        }

        // 2. Sprites
        for (Item item : this.anItemFactory.getItemList()) {
            contexte.drawImage(item.sprite, (int) item.getX(), (int) item.getY(), null);
        }

        for (Fish fish : this.aFishFactory.getFishList()) {
            contexte.drawImage(fish.sprite, (int) fish.getX(), (int) fish.getY(), null);
        }
        // for (Fish fish : this.aFishFactory.getFishList()) {
        //     fish.rendu(contexte);
        // }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            contexte.drawImage(boat.sprite, (int) boat.getX(), (int) boat.getY(), null);
        }

        // 3. Texte
        contexte.drawString("Score : " + score, 10, 20);
        
    }

    public void miseAJour() {
        // ===== ITEMS ====
        for (Item item : this.anItemFactory.getItemList()) {
            item.miseAJour();
        }
        
        // ===== PLAYERS =====
        for (Fish fish : this.aFishFactory.getFishList()) {
            if (this.localFish == fish){
                fish.miseAJour();
            }
        }
        
        // ====== BOATS ======
        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.miseAJour();
        }
         // Lâcher de déchêts
        for (Boat boat : this.aBoatFactory.getBoatList()) {
            if(boat.getClock() > 100){
                this.anItemFactory.createEntity((int) boat.getX(),(int) boat.getY(), (int) boat.getHeight());
                boat.setClock(0);
            }
        }
        loadEntitiesFromDB();
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
        for (Fish fish : this.aFishFactory.getFishList()) {
            for (Item item : anItemFactory.getItemList()) {
                if (!((item.getX() >= fish.getX() + fish.getWidth())    // Trop à droite
                    || (item.getX() + item.getWidth() <= fish.getX())    // Trop à gauche
                    || (item.getY() >= fish.getY() + fish.getHeight())   // Trop en bas
                    || (item.getY() + item.getHeight() <= fish.getY()))) // Trop en haut
                {
                    return false; // Collision détectée
                }
            }
        }
        return false; // Aucune collision détectée
    }
    
    public void loadEntitiesFromDB(){
        GestionDBBoat dbBoat = new GestionDBBoat();
        dbBoat.syncBoatList(this.aBoatFactory.getBoatList());
        
        GestionDBFish dbFish = new GestionDBFish();
        dbFish.syncFishList(this.aFishFactory.getFishList());
    }

    public BoatFactory getBoatFactory() {
        return aBoatFactory;
    }

    public FishFactory getFishFactory() {
        return aFishFactory;
    }

    public Fish getLocalFish() {
        return localFish;
    }

    public void setLocalFish(Fish localFish) {
        this.localFish = localFish;
    }    
 
}
