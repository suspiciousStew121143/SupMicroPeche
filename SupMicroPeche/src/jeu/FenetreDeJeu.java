package jeu;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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

    public FenetreDeJeu() {
        // initialisation de la fenetre
        this.setSize(Parameters.windowLength, Parameters.windowHeight);
        
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jLabel1 = new JLabel();
        this.jLabel1.setPreferredSize(new java.awt.Dimension(Parameters.windowLength, Parameters.windowHeight));
        this.setContentPane(this.jLabel1);
        this.pack();

        // Creation du buffer pour l'affichage du jeu et recuperation du contexte graphique
        this.framebuffer = new BufferedImage(Parameters.windowLength, Parameters.windowHeight, BufferedImage.TYPE_INT_ARGB);
        this.jLabel1.setIcon(new ImageIcon(framebuffer));
        this.contexte = this.framebuffer.createGraphics();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);

        // Création du jeu
        this.jeu = new Jeu();

        // Création du Timer qui appelle this.actionPerformed() toutes les 40ms
        this.timer = new Timer(40, this);
        this.timer.start();

        // Ajout d’un ecouteur clavier
        this.addKeyListener(this);
    }

    // Méthode appelée par le timer et qui effectue la boucle de jeu
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

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_D) {
            this.jeu.getPlayerList().get(0).setToucheDroite(true);
        }
        if (evt.getKeyCode() == evt.VK_Q) {
            this.jeu.getPlayerList().get(0).setToucheGauche(true);
        }
        if (evt.getKeyCode() == evt.VK_Z) {
            this.jeu.getPlayerList().get(0).setToucheHaut(true);
        }
        if (evt.getKeyCode() == evt.VK_S) {
            this.jeu.getPlayerList().get(0).setToucheBas(true);
        }
        if (evt.getKeyCode() == evt.VK_B) {
            this.jeu.getBoatFactory().createEntity();
        }
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (!this.jeu.getHasJoinedGame()){
                this.jeu.getPlayerList().add(new Player());
                this.jeu.setHasJoinedGame(true);
            }
        }
        if (evt.getKeyCode() == evt.VK_SPACE) {
            this.jeu.getPlayerList().get(0).setToucheEspace(true);
            this.jeu.getPlayerList().get(0).setToucheEspaceFrontMontant(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_D) {
            this.jeu.getPlayerList().get(0).setToucheDroite(false);
        }
        if (evt.getKeyCode() == evt.VK_Q) {
            this.jeu.getPlayerList().get(0).setToucheGauche(false);
        }
        if (evt.getKeyCode() == evt.VK_Z) {
            this.jeu.getPlayerList().get(0).setToucheHaut(false);
        }
        if (evt.getKeyCode() == evt.VK_S) {
            this.jeu.getPlayerList().get(0).setToucheBas(false);
        }
        if (evt.getKeyCode() == evt.VK_SPACE) {
            this.jeu.getPlayerList().get(0).setToucheEspace(false);
        }
    }
    
}
