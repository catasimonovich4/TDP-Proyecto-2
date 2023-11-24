package Logica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tiempo extends JLabel {

    private int tiempoRestante;
    private Timer timer;
    private Juego juego;
    private int tiempoTotal;

    public Tiempo(int segundos, Juego juego) {
        this.juego=juego;
        tiempoTotal=segundos;
        tiempoRestante = tiempoTotal;
        this.setText("Tiempo restante: " + tiempoRestante + " segundos.");
    }

     public void iniciarTiempo() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    setText("Tiempo restante: " + tiempoRestante + " segundos.");
                }else{
                    if (tiempoRestante == 0) {
                        timer.stop();
                        juego.notificar_tiempo_agotado();
                        setText("Tiempo restante: " + tiempoRestante + " segundos.");
                       
                    }
                }
            }
        });
        timer.start();
    }  

    public int getTiempoRestante(){
        return tiempoRestante;
    }

    public void parar(){
        timer.stop();
    }

    public void resetearTiempoRestante(){
        if(tiempoTotal!=0){
            tiempoRestante=tiempoTotal;
        }
    }


}
