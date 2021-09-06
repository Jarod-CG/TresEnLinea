/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jarod
 */
public class Player {
    private String name;
    private int points;
    private char symbol;
    
    public Player(){
        
    }

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.points = 0;
    }
    
    

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public void increasePoints (){
        this.points ++;
    }
    
    
}
