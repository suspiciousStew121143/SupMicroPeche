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

    public ArrayList<Collectable> collectableList;
    private ArrayList<Collectable> collectableToDeleteList;
    
    public ItemFactory(Jeu jeu) {
        this.collectableList = new ArrayList<>();
        this.collectableToDeleteList = new ArrayList<>();
    }

    // @override
    public void createEntity(int x_boat, int y_boat, int Height_boat) {
        Collectable c = new Collectable(x_boat, y_boat,Height_boat);
        this.collectableList.add(c);
    }

    public void deleteEntity(Collectable collectable){
        collectableList.remove(collectable); // Cette méthode est à intégrer dans la gestion de base de données à la manière du delete de Boat
    }
    
    public ArrayList<Collectable> getCollectableList() {
        return collectableList;
    }

    public void setCollectableList(ArrayList<Collectable> collectableList) {
        this.collectableList = collectableList;
    }

    public ArrayList<Collectable> getCollectableToDeleteList() {
        return collectableToDeleteList;
    }

    public void addCollectableToDeleteList(Collectable itemToDelete) {
        this.collectableToDeleteList.add(itemToDelete);
    }
    
    public void CollectableSuppression(){
        for (Collectable collectable : collectableToDeleteList){
            this.deleteEntity(collectable); 
        }
        this.collectableToDeleteList = new ArrayList<>();
    }
}
