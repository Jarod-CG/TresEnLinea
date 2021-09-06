/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Game;
import Vista.PantallaJuego;
import Vista.PantallaMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author jarod
 */
//ActionListener es una interfaz
//va a estar pendiente de las pantallas o atributos de las pantallas que nosotros definamos
//verlo en la función _init_()
public class Controlador implements ActionListener {
    //depende de cómo lo hagan pueden tender UN controlador con todas las pantallas
    //o un controlador por pantalla, queda en ustedes
    private PantallaMenu menu;
    private PantallaJuego viewJuego;
    private Game game;
    
    public Controlador (){
        //instancio ambas pantallas
        this.menu = new PantallaMenu();
        this.viewJuego = new PantallaJuego();
        //IMPORTANTE : poner la primer pantalla que vayan a usar como visible
        this.menu.setVisible(true); 
        _init_();
    }
    
    //lo que hace es subscribirse a cada elemento
    //ESTE (this) controlaador estará pendiente de cada uno de esos botones
    //los botones o atributos que usemos del JFrame deben de estar publicos
    private void _init_(){
        //se subscribe a los del meni
        this.menu.btnJugar.addActionListener(this);
        this.menu.btnSalir.addActionListener(this);
        
        //se subscribe a los de la pantalla de juego
        this.viewJuego.btnJugar.addActionListener(this);
        this.viewJuego.btnSalir.addActionListener(this);
        
        //esto es para la matriz que generamos
        for (JButton[] btnMatriz : this.viewJuego.btnMatriz) {
            for (JButton btnMatriz1 : btnMatriz) {
                btnMatriz1.addActionListener(this);
            }
        }
        
        
    }

    //cada vez que ocurra una acción en algún elemento al que estemos subscritos vendrá a este metodo
    @Override
    public void actionPerformed(ActionEvent ae) {
        //debemos comparar si el evento generado tiene como origen alguno de nuestros botones
        if (ae.getSource().equals(this.menu.btnJugar)){
            //aqui corre el codigo cuando presionamos el boton Jugar en el Menu
            
            //tomaŕa el String de los textfield para crear las clases Player en Game
            String name1 = this.menu.txtJug1.getText();
            String name2 = this.menu.txtJug2.getText();
            //acá pasa los String al constructor de Game
            this.game = new Game(name1, name2);
            //está linea esconde la pantlla de Menu
            this.menu.dispose();
            //esto setea en la pantalla de Juego los nombres de los jugadores
            this.viewJuego.setNames(name1, name2);
            //me indica quien va a jugar
            //solo lo está haciendo visible
            //quien lo define es el Modelo.Game
            this.viewJuego.setWho(this.game.getPlayers()[this.game.getTurn()%2].getName());
            //la pantalla de Juego la pone visible
            this.viewJuego.setVisible(true);
        }
        else if (ae.getSource().equals(this.menu.btnSalir)){
            //presionamos el boton Salir en el Menu
            //cierra la aplicacción
            System.exit(0);
        }
        
        if (ae.getSource().equals(this.viewJuego.btnJugar)){
            //presionamos el boton JUgar en la pantalla de JUego
            //esta función reinicia el juego en Game y retorna un booleano si pudo
            if (this.game.restart()){
                //si es true, tambien limpia los valores la matriz que vemos
                //la matriz en Game y la de PantallaJuego son distintas
                //la de la pantalla solo representa lo que sucede en la logica
                this.viewJuego.clearMatrix();
            }
        }
        else if (ae.getSource().equals(this.viewJuego.btnSalir)){
            //boton salir en pantalla de Juego
            //cierra la aplicacion
            System.exit(0);
        }
        else {
                //recorre la matriz de juego, es de 3x3
                for (int i = 0; i < this.viewJuego.btnMatriz.length; i++) {
                    for (int j = 0; j < this.viewJuego.btnMatriz[i].length; j++) {
                        //acá averigua si alguno de esos botones fue presiona
                        //y si lo fue, procede a pasar los parametros a Game
                        if (ae.getSource().equals(this.viewJuego.btnMatriz[i][j])) {
                            //indica cual posicion i, j debe actualizar en la matriz
                            this.game.play(i, j);
                            //los cambios en Game los setea en Pantalla
                            this.viewJuego.setMatrix(this.game.getMatrix());
                            //setea en pantalla quien va jugando
                            this.viewJuego.setWho(this.game.getPlayers()[this.game.getTurn()%2].getName());
                            //si Winner en game es menor a 2, digamos 0 o 1, es porque hay un ganador
                            if (this.game.getWinner() < 2){
                                //si hay ganador pone en pantalla los puntos actualizados
                                this.viewJuego.setPoint(this.game.getPlayers()[0].getPoints(),this.game.getPlayers()[1].getPoints());
                                //pide el nombre del ganador
                                String name = this.game.getPlayers()[this.game.getWinner()].getName();
                                
                                //esto despliega la pantalla flotante
                                Component frame = null;
                                JOptionPane.showMessageDialog(frame, name + " has ganado!", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);

                            }
                        }
                    }
                }
            }
        
    }
    

   
    
}
