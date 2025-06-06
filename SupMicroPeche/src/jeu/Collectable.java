/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics2D;
import java.io.IOException;
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
    
    public Collectable() {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isPickedUp = false;
    }
    
    public Collectable(int x_boat, int y_boat, int Height_boat) {
        try {
            this.sprite = ImageIO.read(getClass().getResource("../resources/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Collectable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = x_boat;
        this.y = y_boat+Height_boat;
        this.isPickedUp = false;
    }

    
    @Override
    public void miseAJour() {
        if(!this.isPickedUp){ // On vérifie que l'objet n'est pas en état de "disparition"
            y = y + 3;
        }
    }
    
    public void rendu(Graphics2D contexte) {
        if(!this.isPickedUp){ // On vérifie aussi que l'objet n'est pas en état de "disparition"
            contexte.drawImage(this.sprite, (int) x, (int) y, null);
        }
    }
    
    public void rendu(Graphics2D contexte, int x_player, int y_player) {
        if(this.isPickedUp){ // On vérifie aussi que l'objet est porté par un des joueurs et on va le dessiner dans ce cas
            contexte.drawImage(this.sprite,x_player-2, y_player +10, null);
        }
    }
    

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public void setIsPickedUp(boolean isPickedUp) {
        this.isPickedUp = isPickedUp;
    }
    
    
}

