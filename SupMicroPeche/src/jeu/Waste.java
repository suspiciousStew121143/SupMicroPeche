/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe lutin
 *
 * @author Louis
 */
public class Waste extends Entity {
    
    private Boat b;
    private GestionDBItem db;

    public Waste(Boat b, GestionDBItem db) {
        this.db = db;
        
        try {
            this.sprite = ImageIO.read(getClass().getResource("../assets/washer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Waste.class.getName()).log(Level.SEVERE, null, ex);
        }
//        lancer(b);
    }

    
    @Override
    public void miseAJour() {
        y = y + 5;
        
        if (hasChanged()){
            db.updateBase(this);
            resetChangedFlag();
        }
    }

    public Boat getBoat() {
        return b;
    }

    public void setBoat(Boat b) {
        this.b = b;
    }

    
    

}

