/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class ItemFactory {

    private Jeu jeu;
    private ArrayList<Item> itemList;
    
    public ItemFactory(Jeu jeu) {
        this.jeu = jeu;
        this.itemList = new ArrayList<>();
    }

    // @override
    public void createEntity() {
        Players f = new Players();
        f.lancer();
        this.jeu.getPlayersList().add(f);

    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    
}
