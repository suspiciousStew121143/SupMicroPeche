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
    public ArrayList<Player> playerList;
    public ItemFactory anItemFactory;
    private BoatFactory aBoatFactory;
    
    private boolean hasJoinedGame;
    
    // private GestionBD aBD;

    public Jeu() {
        // ===========================
        // -- INITIALISATION DU JEU --
        // ===========================

        // Initialisation du score
        this.score = 0;

        // Initialisation des listes
        this.playerList = new ArrayList<>();

        // Initialisation du booléan pour éviter de rejoindre plusieurs fois la partie
        this.hasJoinedGame = false;
        
        // Initialisation du décor
        try {
            this.decor = ImageIO.read(getClass().getResource("../resources/Background.png"));
        } catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ========================
        // -- CREATION D'ENTITES --
        // ========================
        // Uiliser l'usine à entités
        
        this.aBoatFactory = new BoatFactory(this);
//        aBoatFactory.createEntity();

        this.anItemFactory = new ItemFactory(this);
       
        // this.aBD = new GestionBD();
        
    }
   

    public void rendu(Graphics2D contexte) {
        // 1. Decor
        if (this.decor != null) {
            contexte.drawImage(this.decor, 0, 0, null);
        }

        // 2. Sprites
        for (Item item : this.anItemFactory.getItemList()) {
            item.rendu(contexte);
        }

        for (Player player : this.playerList) {
            player.rendu(contexte);
        }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.rendu(contexte);
        }

        // 3. Texte
        contexte.drawString("Score : " + score, 10, 20);
    }

    public void miseAJour() {
        // 1. MAJ du poisson en fonction des commandes des joueurs

        // 2. MAJ des autres éléments (objets, monstres, etc.)
        
        for (Item item : this.anItemFactory.getItemList()) {
            item.miseAJour();
        }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.miseAJour();
        }

        for (Player player : this.playerList) {
            player.miseAJour();
        }

        // 3. Gérer les interactions (collisions et autres règles)
        
        // Lâcher de déchêts
        
        for (Boat boat : this.aBoatFactory.getBoatList()) {
            if(boat.getTimer() > 100){
                this.anItemFactory.createEntity((int) boat.getX(),(int) boat.getY(), (int) boat.getHeight());
                boat.setTimer(0);
            }
        }
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
        for (Player fish : playerList) {
            for (Item item : anItemFactory.getItemList()) {
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

    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    public BoatFactory getBoatFactory() {
        return aBoatFactory;
    }

    public ItemFactory getItemFactory() {
        return anItemFactory;
    }

    public boolean getHasJoinedGame() {
        return hasJoinedGame;
    }

    public void setHasJoinedGame(boolean hasJoinedGame) {
        this.hasJoinedGame = hasJoinedGame;
    }
    
    
}
