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
public class Game {
    private Player[] players;
    //crear matriz de juego
    private char[][] matrix;
    private int turn;            //es un indice para saber si es el 1 o el 2
    private int winner;         // 0 , 1 , 2 empate, 3 nada

    public Game(String name1, String name2) {
        this.players = new Player[2];
        this.players[0] = new Player (name1, 'X');
        this.players[1] = new Player (name2, 'O');
        this.matrix = new char[3][3];
        this.turn = 0;
        this.winner = 3;
    }
    
    
    public void play (int i, int j){
        if (turn < 9 & winner == 3) {//en teria para este momento el tableor está lleno
            int index = this.turn % this.players.length;
            this.turn++;
            this.matrix[i][j] = this.players[index].getSymbol();
            evaluate();
            assignPoints();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
    
    
    
    
    public boolean restart (){
        if (turn == 9 | winner < 2 ){
            this.turn = 0;
            this.winner = 3;
            this.matrix = new char[3][3];  //esto porque es poca memoria
            //pero si fuese una matriz más grande lo ideal seria recorrerla e reiniciar los datos
            return true;
        }
        return false;
        
    }
    
    
    //revisa si alguien gana o es empate
    private void evaluate(){
        checkRows();
        checkColumns();
        checkDiag();
        if (this.turn == 9 & this.winner == 3){
            this.winner = 2;
        }
    }
    
    private void checkRows(){
        if (this.matrix[0][0] == this.matrix[0][1] & this.matrix[0][1] == this.matrix[0][2] & (this.matrix[0][0] == 'X' |this.matrix[0][0] == 'O')){
            whoWin(this.matrix[0][0]);
        }
        else if (this.matrix[1][0] == this.matrix[1][1] & this.matrix[1][1] == this.matrix[1][2]& (this.matrix[1][0] == 'X' |this.matrix[1][0] == 'O')){
            whoWin(this.matrix[1][0]);
        }
        else if (this.matrix[2][0] == this.matrix[2][1] & this.matrix[2][1] == this.matrix[2][2]& (this.matrix[2][0] == 'X' |this.matrix[2][0] == 'O')){
            whoWin(this.matrix[2][0]);
        } 
    }
    
    private void checkColumns(){
        if (this.matrix[0][0] == this.matrix[1][0] & this.matrix[1][0] == this.matrix[2][0]& (this.matrix[0][0] == 'X' |this.matrix[0][0] == 'O')){
            whoWin(this.matrix[0][0]);
        }
        else if (this.matrix[0][1] == this.matrix[1][1] & this.matrix[1][1] == this.matrix[2][1]& (this.matrix[0][1] == 'X' |this.matrix[0][1] == 'O')){
            whoWin(this.matrix[0][1]);
        }
        else if (this.matrix[0][2] == this.matrix[1][2] & this.matrix[1][2] == this.matrix[2][2]& (this.matrix[0][2] == 'X' |this.matrix[0][2] == 'O')){
            whoWin(this.matrix[0][2]);
        } 
    }
    
    private void checkDiag(){
        if (this.matrix[0][0] == this.matrix[1][1] & this.matrix[1][1] == this.matrix[2][2]& (this.matrix[0][0] == 'X' |this.matrix[0][0] == 'O')){
            whoWin(this.matrix[0][0]);
        }
        else if (this.matrix[0][2] == this.matrix[1][1] & this.matrix[1][1] == this.matrix[2][0]& (this.matrix[0][2] == 'X' |this.matrix[0][2] == 'O')){
            whoWin(this.matrix[0][2]);
    }
        
    
    
    
}

    private void whoWin(char symbol) {
        
        if (symbol == this.players[0].getSymbol()){
            this.winner = 0;
        }
        else if (symbol == this.players[1].getSymbol()){
            this.winner = 1;
        }
    }

    private void assignPoints() {
        if (winner == 0){
            this.players[0].increasePoints();
        }
        else if (winner == 1){
            this.players[1].increasePoints();
        }
    }
    
    
    
}