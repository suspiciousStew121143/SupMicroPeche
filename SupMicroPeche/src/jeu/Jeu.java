/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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
    public BoatFactory aBoatFactory;
    
    private ArrayList<Colonne> colonnes;
    
    private boolean hasJoinedGame;
    
    private Random rand = new Random();

    private Carte carte;

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
        this.carte = new Carte();
        

        // ========================
        // -- CREATION D'ENTITES --
        // ========================
        // Uiliser l'usine à entités
        this.aBoatFactory = new BoatFactory(this);
        this.aBoatFactory.startFactory();       //Démarre l'usine à bateau pour en produire automatiquement

        this.anItemFactory = new ItemFactory(this);
         
        // this.aBD = new GestionBD();   
        
        this.colonnes = new ArrayList<>();    
        
        for (int i = 0; i < 10; i++) {      // Création des 10 colonnes au départ
            Colonne c = new Colonne();   
            c.setX(this.decor.getWidth()*i/10 + (int) (c.getWidth()/2));
            c.setY(this.decor.getHeight() - (int) c.getHeight());
            colonnes.add(c);
        }
    }

    public void rendu(Graphics2D contexte) {
        // 1. Decor
        this.carte.rendu(contexte);

//         2. Sprites
        for (Item item : this.anItemFactory.getItemList()) {
            item.rendu(contexte);
        }

        for (Player player : this.playerList) {
            player.rendu(contexte);
        }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.rendu(contexte);
        }
        
        for (Colonne c : colonnes) { //Affichage des colonnes
            c.rendu(contexte);
        }
        
        // 3. Texte
        contexte.drawString("Score : " + score, 10, 20);
    }

    public void miseAJour() {
        // 1. MAJ du poisson en fonction des commandes des joueurs

        // 2. MAJ des autres éléments (objets, monstres, etc.)
        for (Item item : this.anItemFactory.getItemList()) {
            item.miseAJour(colonnes);
            
        }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.miseAJour();
            if (BoatCollideItem()) {
                this.aBoatFactory.deleteEntity(boat.getId());
            }
            // Lâcher de déchets
            if (boat.getTimer() > 100) {
                this.anItemFactory.createEntity((int) boat.getX(), (int) boat.getY(), (int) boat.getHeight());
                boat.setTimer(0);
            }
        }

        for (Player player : this.playerList) {
            player.miseAJour();
        }
        

        // 3. Gérer les interactions (collisions et autres règles)
        fishCollideItem();

        // Lâcher de déchêts
        for (Boat boat : this.aBoatFactory.getBoatList()) {
            if (boat.getTimer() > 100) {
                this.anItemFactory.createEntity((int) boat.getX(), (int) boat.getY(), (int) boat.getHeight());
                boat.setTimer(0);
            }
        }

    }

    public boolean estTermine() {
//        return fishCollideItem();
        return false; // On ne termine plus le jeu lorsque le joueur touche un déchêt
    }

    public void incrementScore() {
        this.score += 1;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // COLLISIONS
    public void fishCollideItem() {
        if (!playerList.isEmpty()) {
            if (!anItemFactory.getItemList().isEmpty()) { // On effectue une vérification pour éviter de parcourir deux listes vides
                for (Player player : playerList) {
                    for (Item item : anItemFactory.getItemList()) {
                        if (!((item.getX() >= player.getX() + player.getWidth()) // Trop à droite
                                || (item.getX() + item.getWidth() <= player.getX()) // Trop à gauche
                                || (item.getY() >= player.getY() + player.getHeight()) // Trop en bas
                                || (item.getY() + item.getHeight() <= player.getY()))) // Trop en haut
                        {
                            if (player.getToucheEspace() && item instanceof Collectable && !player.HasAPickUp() && player.getToucheEspaceFrontMontant()) {
                                Collectable c = (Collectable) (item); // On cast l'objet en question en collectable pour pouvoir utiliser les méthodes
                                player.pickUp(c);
                                player.setToucheEspaceFrontMontant(false);
                                System.out.println("Un objet a été récupéré");
                            }
                        }
                    }

                    // On s'occupe du lacher d'item ici
                    if (player.getToucheEspace() && player.HasAPickUp() && player.getToucheEspaceFrontMontant()) {
                        player.dropPickUp();
                        player.setToucheEspaceFrontMontant(false);
                        System.out.println("Un objet a été laché");
                    }
                }
            }
        }
    }

    public boolean BoatCollideItem() {
        for (Boat boat : this.aBoatFactory.getBoatList()) {
            for (Item item : anItemFactory.getItemList()) {
                if (!((item.getX() >= boat.getX() + boat.getWidth())    // Trop à droite
                    || (item.getX() + item.getWidth() <= boat.getX())    // Trop à gauche
                    || (item.getY() >= boat.getY() + boat.getHeight())   // Trop en bas
                    || (item.getY() + item.getHeight() <= boat.getY()))) // Trop en haut
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
    
    public void activerColonnesAleatoires() { //Permet d'activer les colonnes qui vont apparaitre
        // D'abord, désactive toutes les colonnes
        for (Colonne c : colonnes) {
            c.setActive(false);
        }
        // Choisis 2 indices différents au hasard
        int first = rand.nextInt(colonnes.size());
        int second;
        do {
            second = rand.nextInt(colonnes.size());
        } while (second == first);
        colonnes.get(first).setActive(true);
        colonnes.get(second).setActive(true);
    }    

}
