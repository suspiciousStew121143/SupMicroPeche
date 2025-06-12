/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author pcastani
 */
public class ClownFish extends Player{
    
    public ClownFish(){
        spriteSheet = new BufferedImage[2];
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource("../resources/Clownfish_32x32.png"));
            for (int i = 0; i < 2; i++) {
                int x = i * 32;
                spriteSheet[i] = tileset.getSubimage(x, 0, 32, 32); // On utilise la même méthode que pour la tilemap pour découper l'image
            }
            this.sprite = spriteSheet[1]; // par défaut, le joueur est orienté vers la droite
            this.isGoingRight = true;
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.x = 170;
        this.y = 314;
        this.toucheGauche = false;
        this.toucheDroite = false;
        this.toucheBas = false;
        this.toucheHaut = false;
        fishStep=2;
    }
    
    @Override
    public void useAbility(){
        //if 
    }
    
}
