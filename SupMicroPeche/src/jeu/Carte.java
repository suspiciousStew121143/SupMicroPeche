/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Exemple de classe carte
 *
 * @author guillaume.laurent
 */
public class Carte {

    private int largeur = 32;
    private int hauteur = 18;
    private int tailleTuile = 80;
    private BufferedImage[] tuiles;
    
    private int[][] decor = {
        {0, 1, 2, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 3},
        {20, 21, 22, 3, 3, 23, 24, 25, 26, 27, 28, 29, 30, 31, 3, 32, 33, 34, 34, 34, 34, 34, 34, 35, 36},
        {37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61},
        {62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86},
        {84, 84, 84, 84, 87, 88, 89, 84, 84, 84, 84, 90, 84, 84, 84, 84, 84, 84, 84, 84, 84, 91, 92, 84, 84},
        {84, 84, 84, 84, 93, 94, 95, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 96, 97, 98, 99, 84},
        {100, 101, 84, 102, 103, 104, 105, 84, 84, 106, 84, 84, 84, 107, 84, 84, 84, 84, 84, 84, 108, 109, 110, 111, 112},
        {113, 114, 115, 116, 117, 118, 84, 119, 120, 121, 122, 84, 123, 124, 125, 84, 84, 84, 84, 84, 126, 127, 128, 129, 130},
        {131, 132, 133, 134, 135, 136, 84, 137, 138, 139, 140, 84, 141, 142, 143, 84, 84, 144, 84, 84, 84, 145, 146, 147, 148},
        {149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 84, 84, 160, 84, 161, 162, 163, 164, 165, 84, 166, 167, 168, 169},
        {170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 84, 84, 181, 84, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191},
        {192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216},
        {217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 224, 237, 238, 239, 240},
        {241, 242, 243, 244, 245, 246, 224, 224, 247, 248, 224, 224, 224, 249, 250, 224, 224, 251, 224, 224, 224, 252, 253, 254, 255}
    };

    public Carte() {
        tuiles = new BufferedImage[256];
        try {
            BufferedImage tileset = ImageIO.read(getClass().getResource("../resources/tileset_80x80_clean.png"));
            for (int i = 0; i < tuiles.length; i++) {
                int x = (i % 10) * tailleTuile;
                int y = (i / 10) * tailleTuile;
                tuiles[i] = tileset.getSubimage(x, y, tailleTuile, tailleTuile);
            }
        } catch (IOException ex) {
            Logger.getLogger(Carte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void miseAJour() {

    }

    public void rendu(Graphics2D contexte) {
        for (int i = 0; i < decor.length; i++) {
            for (int j = 0; j < decor[0].length; j++) {
                contexte.drawImage(tuiles[decor[i][j]], j * tailleTuile, i * tailleTuile, null);
            }
        }
    }

}
