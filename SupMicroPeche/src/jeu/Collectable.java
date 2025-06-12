/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 *
 * @author Louis
 */
public class Collectable extends Item {

    private boolean isPickedUp;
    private boolean toTheSky; // C'est une private joke

    public Collectable() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isPickedUp = false;
        this.toTheSky = false;
    }

    public Collectable(int x_boat, int y_boat, int Height_boat) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = x_boat;
        this.y = y_boat + Height_boat;
        this.isPickedUp = false;
    }

    @Override
    public void miseAJour(ArrayList<Colonne> colonnes) {
        if (!this.isPickedUp) { // On vérifie que l'objet n'est pas en état de "disparition"
            for (Colonne c : colonnes) {
                if (c.isActive()) {  // On ne regarde que les colonnes actives
                    if ((x < (c.getX() + c.getWidth())) && (x > (c.getX() - this.sprite.getWidth())) && y > Parameters.windowHeight-300) { 
                        // Cette condition sert à activer le processus d'un déchet qui remonte pour les bateaux
                        // On regarde si le Collectable est suffisament proche d'une colonne active, si c'est vrai il part.
                        this.toTheSky = true;
                    }

                }
            }
            if (this.toTheSky) {
                y = y - 30;
            } else {
                y = y + 3;
            }
        }
    }

    public void rendu(Graphics2D contexte) {
        if (!this.isPickedUp) { // On vérifie aussi que l'objet n'est pas en état de "disparition"
            contexte.drawImage(this.sprite, (int) x, (int) y, null);
        }
    }

    public void rendu(Graphics2D contexte, int x_player, int y_player) {
        if (this.isPickedUp) { // On vérifie aussi que l'objet est porté par un des joueurs et on va le dessiner dans ce cas
            contexte.drawImage(this.sprite, x_player - 2, y_player + 10, null);
        }
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setIsPickedUp(boolean isPickedUp) {
        this.isPickedUp = isPickedUp;
    }

    public boolean getToTheSky() {
        return toTheSky;
    }

    public void setToTheSky(boolean toTheSky) {
        this.toTheSky = toTheSky;
    }

    
}
