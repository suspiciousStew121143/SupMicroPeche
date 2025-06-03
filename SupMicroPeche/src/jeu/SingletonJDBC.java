/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lkerguil
 */
public class SingletonJDBC {
    // La classe est finale, car un singleton n'est pas censé avoir d'héritier.
    // L'utilisation du mot clé volatile empêche les effets de bord dus aux copies
    // de l'instance qui peuvent être modifiées dans le thread principal.
    private static volatile SingletonJDBC instance = null;

    // D'autres attributs, classiques et non statiques.
    private Connection connexion;

    // La présence d'un constructeur privé supprime le constructeur public
    // par défaut. Ainsi, seul le singleton peut s'instancier lui-même.
    private SingletonJDBC() {
        super();
        try {
            this.connexion = DriverManager.getConnection("jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche", "etudiant", "YTDTvj9TR3CDYCmP");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(-1);
        }
    }

    // Méthode renvoyant l'instance de la classe Singleton
    public final static SingletonJDBC getInstance() {

        if (SingletonJDBC.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads". Il est TRES important.
            synchronized (SingletonJDBC.class) {
                if (SingletonJDBC.instance == null) {
                    SingletonJDBC.instance = new SingletonJDBC();
                }
            }
        }

        return SingletonJDBC.instance;
    }

    public Connection getConnection()  {
    try {
        if (connexion == null || connexion.isClosed()) {
            connexion = DriverManager.getConnection(
                "jdbc:mariadb://nemrod.ens2m.fr:3306/2024-2025_s2_vs1_tp2_supmicropêche",
                "etudiant",
                "YTDTvj9TR3CDYCmP"
            );
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        System.exit(-1);
    }
        return connexion;
    }

}
