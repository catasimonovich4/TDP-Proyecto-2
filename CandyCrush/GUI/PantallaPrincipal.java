package GUI;

import javax.swing.*;

import Logica.Juego;
import temas.Tema;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PantallaPrincipal extends JFrame {
    private BufferedImage imagenDeFondo;
    private Juego mi_juego;

    public PantallaPrincipal(Juego mi_juego) {
        try {
            imagenDeFondo = ImageIO.read(new File("temas/inicio/fondo.png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mi_juego=mi_juego;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Configuramos el botón 1
        JButton botonArgentina = new JButton("Jugar");
        botonArgentina.setSize(30, 30);
        botonArgentina.setBackground(Color.CYAN);

        botonArgentina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre:", "Nombre del Jugador", JOptionPane.PLAIN_MESSAGE);
                    while(nombreJugador.equals("")){
                        JOptionPane.showMessageDialog(null,"El nombre no puede estar vacio");
                         nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre:", "Nombre del Jugador", JOptionPane.PLAIN_MESSAGE);
                    }
                    mi_juego.crearPuntaje(nombreJugador);
                    notificarEleccion(Tema.TEMA_ALTERNATIVO);
            }

        });


        // Configuramos el botón 2
        JButton botonCandy = new JButton("Jugar");
        botonCandy.setSize(30, 30);
        botonCandy.setBackground(Color.CYAN);

        botonCandy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre:", "Nombre del Jugador", JOptionPane.PLAIN_MESSAGE);
                    while(nombreJugador.equals("")){
                        JOptionPane.showMessageDialog(null,"El nombre no puede estar vacio");
                         nombreJugador = JOptionPane.showInputDialog(null, "Ingresa tu nombre:", "Nombre del Jugador", JOptionPane.PLAIN_MESSAGE);
                    }
                    mi_juego.crearPuntaje(nombreJugador);
                    notificarEleccion(Tema.TEMA_CLASICO);
            }
        });

        // Creamos un panel para organizar los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2));
        panelBotones.add(botonArgentina);
        panelBotones.add(botonCandy);

        // Creamos un panel para colocar la imagen de fondo y los botones
        JPanel panelContenedor = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenDeFondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panelContenedor.setSize(200, 200);
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.add(panelBotones, BorderLayout.SOUTH);

        getContentPane().add(panelContenedor);
    }

    public void notificarEleccion(int eleccion){
        mi_juego.iniciarTema(eleccion);
    }
    
}

