/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jeu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lkerguil
 */
public class BubbleFactory {

    private ArrayList<Bubble> bubbleList;
    private ArrayList<Bubble> bubbleToDeleteList;

    public BubbleFactory() {
        this.bubbleList = new ArrayList<>();
        this.bubbleToDeleteList = new ArrayList<>();
    }

    public void createEntity(int y_Destination, int x_Destination, int x_Start,int y_Start) {
        Bubble b = new Bubble(y_Destination,x_Destination,x_Start,y_Start);
        this.bubbleList.add(b);
    }

    public ArrayList<Bubble> getBubbleList() {
        return bubbleList;
    }

    public void setBubbleList(ArrayList<Bubble> bubbleList) {
        this.bubbleList = bubbleList;
    }

    public ArrayList<Bubble> getBubbleToDeleteList() {
        return bubbleToDeleteList;
    }

    public void addBubbleToDeleteList(Bubble bubbleToDelete) {
        this.bubbleToDeleteList.add(bubbleToDelete);
    }

    
    
    public void bubbleSuppression() {
        for (Bubble b : bubbleToDeleteList) {
            this.bubbleList.remove(b);
        }
        this.bubbleToDeleteList = new ArrayList<>();
    }
    
    public void bubblePop(int n_bubble, int y_Destination, int x_Destination, int y_Start,int x_Start){
            for (int i = 0; i < n_bubble; i++) {
                this.createEntity(y_Destination, x_Destination, y_Start, x_Start);
            }
    }
    
    public void startPopping(int nb_pops) {
        
    }
    
    

}
