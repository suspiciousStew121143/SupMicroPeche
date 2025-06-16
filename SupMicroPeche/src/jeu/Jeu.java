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
    public BubbleFactory aBubbleFactory;

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
        this.aBoatFactory = new BoatFactory();
        this.aBoatFactory.startFactory();       //Démarre l'usine à bateau pour en produire automatiquement

        this.anItemFactory = new ItemFactory();
        this.aBubbleFactory = new BubbleFactory();

        // this.aBD = new GestionBD();   
        this.colonnes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {      // Création des 10 colonnes au départ
            Colonne c = new Colonne();
            c.setX(Parameters.windowLength * i / 10 + (int) (c.getWidth() / 2));
            c.setY(Parameters.windowHeight - (int) c.getHeight());
            colonnes.add(c);
        }
    }

    public void rendu(Graphics2D contexte) {
        // 1. Decor
        this.carte.rendu(contexte);

//         2. Sprites
        for (Item item : this.anItemFactory.getCollectableList()) {
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

        for (Bubble bubble : aBubbleFactory.getBubbleList()) {
            bubble.rendu(contexte);
        }
        if (aBoatFactory.getCounter_animation()<= 7 && aBoatFactory.getCounter_animation() >= 0) {
            aBoatFactory.boatExplosion(contexte);
        }

        // 3. Texte
        contexte.drawString("Score : " + score, 10, 20);
    }

    public void miseAJour() {
        // 1. MAJ du poisson en fonction des commandes des joueurs

        // 2. MAJ des autres éléments (objets, monstres, etc.)
        for (Item item : this.anItemFactory.getCollectableList()) {
            item.miseAJour(colonnes);

        }

        for (Boat boat : this.aBoatFactory.getBoatList()) {
            boat.miseAJour();
            // Lâcher de déchets
            if (boat.getTimer() > 100) {
                this.anItemFactory.createEntity((int) boat.getX(), (int) boat.getY(), (int) boat.getHeight());
                boat.setTimer(0);
            }
        }
        for (Bubble bubble : aBubbleFactory.getBubbleList()) {
            bubble.miseAJour(rand);
            // Lâcher de déchets
        }

        for (Colonne c : colonnes) {
            if (c.isActive()) {  // On ne regarde que les colonnes actives
                int x = c.getX();
                int y = c.getY();
                this.aBubbleFactory.bubblePop(rand.nextInt(1, 2), Parameters.windowHeight - (300), (int) x, (int) (y + 20), (int) (x + rand.nextInt(-10, 10) + c.getWidth() / 2));
                // Tous les arguments donnés à bubblePop peuvent paraître complexes mais en somme on fait partir les bulles du haut de la colonne 
                // jusqu'à la zone où les déchets sont renvoyés, avec de l'aléatoire pour un côté plus "organique"
            }
        }

        this.aBoatFactory.boatSuppression();
        this.anItemFactory.CollectableSuppression();
        this.aBubbleFactory.bubbleSuppression();

        for (Player player : this.playerList) {
            player.miseAJour();
        }

        // 3. Gérer les interactions (collisions et autres règles)
        fishCollideItem();

        boatCollideItem();

        removeItemOutOfMap();

        fillBubbleToDeleteList();

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
        if (!playerList.isEmpty() && !anItemFactory.getCollectableList().isEmpty()) { // On effectue une vérification pour éviter de parcourir deux listes vides
            for (Player player : playerList) {
                for (Item item : anItemFactory.getCollectableList()) {
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

                // On s'occupe du lacher d'collectable ici
                if (player.getToucheEspace() && player.HasAPickUp() && player.getToucheEspaceFrontMontant()) {
                    player.dropPickUp();
                    player.setToucheEspaceFrontMontant(false);
                    System.out.println("Un objet a été laché");
                }
            }
        }
    }

    public void boatCollideItem() {
        if (!playerList.isEmpty() && !anItemFactory.getCollectableList().isEmpty()) {
            for (Boat boat : this.aBoatFactory.getBoatList()) {
                for (Collectable collectable : anItemFactory.getCollectableList()) {
                    if (collectable.getToTheSky()) {
                        if (!((collectable.getX() >= boat.getX() + boat.getWidth()) // Trop à droite
                                || (collectable.getX() + collectable.getWidth() <= boat.getX()) // Trop à gauche
                                || (collectable.getY() >= boat.getY() + boat.getHeight()) // Trop en bas
                                || (collectable.getY() + collectable.getHeight() <= boat.getY()))) // Trop en haut
                        {
                            aBoatFactory.setBoat_x(boat.getX());
                            aBoatFactory.setBoat_y(boat.getY());
                            aBoatFactory.setCounter_animation(0);
                            this.aBoatFactory.addBoatToDeleteList(boat);
                        }
                    }

                }
            }
        }
        aBoatFactory.boatSuppression();
    }

    public void removeItemOutOfMap() {
        if (!anItemFactory.getCollectableList().isEmpty()) {
            for (Collectable collectable : anItemFactory.getCollectableList()) {
                if ((collectable.getY() > Parameters.windowHeight)
                        || (collectable.getY() < -30)) {
                    this.anItemFactory.addCollectableToDeleteList(collectable);
                }
            }
        }

    }

    public void fillBubbleToDeleteList() {
        for (Bubble b : aBubbleFactory.getBubbleList()) {
            if (b.getToBeDeleted()) {
                this.aBubbleFactory.getBubbleToDeleteList().add(b);
            }
        }
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
        Colonne c1 = colonnes.get(first);
        Colonne c2 = colonnes.get(second);
        c1.setActive(true);
        c2.setActive(true);
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
