package jeu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Exemple de fenetre de jeu en utilisant uniquement des commandes
 *
 * @author guillaume.laurent
 */
public class FenetreDeJeu extends JFrame implements ActionListener, KeyListener {

    private BufferedImage framebuffer;
    private Graphics2D contexte;
    private JLabel jLabel1;
    private Jeu jeu;
    private Timer timer;
    private Fish localFish;

    public FenetreDeJeu() {
        // initialisation de la fenetre
        this.setSize(576, 324);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(576, 324));
        this.setContentPane(this.jLabel1);
        this.pack();

        // Creation du buffer pour l'affichage du jeu et recuperation du contexte graphique
        this.framebuffer = new BufferedImage(this.jLabel1.getWidth(), this.jLabel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();

        // Création du jeu
        this.jeu = new Jeu();
        Fish f = this.jeu.getFishFactory().createEntity("clown");
        this.jeu.setLocalFish(f);

        // Création du Timer qui appelle this.actionPerformed() toutes les 40ms
        this.timer = new Timer(40, this);
        this.timer.start();

        // Ajout d’un ecouteur clavier
        this.addKeyListener(this);
        
        // test
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                Fish localFish = jeu.getLocalFish();
                
                if (jeu.getLocalFish() != null) {
                    new GestionDBFish().deleteFishFromDB(jeu.getLocalFish().getId());
                    
                    if (localFish.getIsHost() == 1){ // Si l'hôte quitte la partie
                        new GestionDBBoat().deleteBoatsFromDB(); // supprime tous les bateaux
                        ArrayList<Fish> fishList = jeu.getFishList();
                        GestionDBFish gestionDBFish = new GestionDBFish();
                        for (Fish fish : fishList) {
                            gestionDBFish.deleteFishFromDB(fish.getId()); // supprime tous les poissons
                        }
                    }
                }
            }
        });
    }


    // Méthode appelée par le timer et qui effectue la boucle de jeu
    @Override
    public void keyPressed(KeyEvent evt) {
        if (this.jeu.getLocalFish() == null) return;

        if (evt.getKeyCode() == evt.VK_D)this.jeu.getLocalFish().setToucheDroite(true);
        if (evt.getKeyCode() == evt.VK_Q)this.jeu.getLocalFish().setToucheGauche(true);
        if (evt.getKeyCode() == evt.VK_Z) this.jeu.getLocalFish().setToucheHaut(true);
        if (evt.getKeyCode() == evt.VK_S) this.jeu.getLocalFish().setToucheBas(true);

        // Créations (conservent la logique actuelle)
        if (evt.getKeyCode() == evt.VK_B) this.jeu.getBoatFactory().createEntity();
        if (evt.getKeyCode() == evt.VK_F) this.jeu.getFishFactory().createEntity("clown");
        if (evt.getKeyCode() == evt.VK_G) this.jeu.getFishFactory().createEntity("globe");
        if (evt.getKeyCode() == evt.VK_H) this.jeu.getFishFactory().createEntity("sword");
        if (evt.getKeyCode() == evt.VK_J) this.jeu.getFishFactory().createEntity("whale");
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (this.jeu.getLocalFish() == null) return;

        if (evt.getKeyCode() == evt.VK_D) this.jeu.getLocalFish().setToucheDroite(false);
        if (evt.getKeyCode() == evt.VK_Q) this.jeu.getLocalFish().setToucheGauche(false);
        if (evt.getKeyCode() == evt.VK_Z) this.jeu.getLocalFish().setToucheHaut(false);
        if (evt.getKeyCode() == evt.VK_S) this.jeu.getLocalFish().setToucheBas(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.jeu.miseAJour();
        this.jeu.rendu(contexte);
        this.jLabel1.repaint();
        if (this.jeu.estTermine()) {
            this.timer.stop();
        }
    }

    public static void main(String[] args) {
        FenetreDeJeu fenetre = new FenetreDeJeu();
        fenetre.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }
}
