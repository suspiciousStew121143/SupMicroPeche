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
    public ArrayList<Item> itemList;
    
    public ItemFactory(Jeu jeu) {
        this.jeu = jeu;
        this.itemList = new ArrayList<Item>();
    }

    // @override
    public void createEntity(int x_boat, int y_boat, int Height_boat) {
        Collectable c = new Collectable(x_boat, y_boat,Height_boat);
        this.itemList.add(c);
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

}
