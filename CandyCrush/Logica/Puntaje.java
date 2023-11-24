package Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class Puntaje {
	int puntajeJugador;
	String jugador;
	public Puntaje(String nombreJugador) {
		jugador = nombreJugador;
		puntajeJugador= 0;
	}

	public int ActualizarPuntaje(int puntaje){
		puntajeJugador =puntajeJugador + puntaje;
		return puntajeJugador;
	}

    public int getPuntaje(){
        return this.puntajeJugador;
    }
    
	public void guardarPuntaje(int nuevoPuntaje) {
		try {
	        List<TopJugadores> puntuacionesConUsuario = leerPuntaje();
	        TopJugadores nuevaEntrada = new TopJugadores(jugador, puntajeJugador);
	        puntuacionesConUsuario.add(nuevaEntrada);
	        Collections.sort(puntuacionesConUsuario);
	        int maxPuntuaciones =5;
	        if (puntuacionesConUsuario.size() > maxPuntuaciones) {
	            puntuacionesConUsuario = puntuacionesConUsuario.subList(0, maxPuntuaciones);
	        }
	        escribirPuntaje(puntuacionesConUsuario);
	        mostrarPuntuaciones();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }

    private List<TopJugadores> leerPuntaje() throws IOException {
        List<TopJugadores> puntuacionesConUsuario = new ArrayList<TopJugadores>();
        try (BufferedReader br = new BufferedReader(new FileReader("./Puntaje.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String usuario = partes[0].trim();
                    int puntuacion = Integer.parseInt(partes[1].trim());
                    puntuacionesConUsuario.add(new TopJugadores(usuario, puntuacion));
                }
            }
        } catch (FileNotFoundException e) {
        }
        return puntuacionesConUsuario;
    }

    private  void escribirPuntaje(List<TopJugadores> puntuacionesConUsuario) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./Puntaje.txt"))) {
            for (TopJugadores entrada : puntuacionesConUsuario) {
                bw.write(entrada.getUsuario() + "," + entrada.getPuntuacion());
                bw.newLine();
            }
        }
    }
    public  void mostrarPuntuaciones() {
        try {
            List<TopJugadores> puntuacionesConUsuario = leerPuntaje();
            StringBuilder mensaje = new StringBuilder("Puntuaciones:\n");
            for (TopJugadores registro : puntuacionesConUsuario) {
                mensaje.append(registro.getUsuario()).append(": ").append(registro.getPuntuacion()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Puntuaciones", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }
    
    public List<String> obtenerPuntuaciones() {
        List<String> listaPuntuaciones = new ArrayList<>();
    
        try {
            List<TopJugadores> puntuacionesConUsuario = leerPuntaje();
            for (TopJugadores registro : puntuacionesConUsuario) {
                String mensaje = registro.getUsuario() + ": " + registro.getPuntuacion();
                listaPuntuaciones.add(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaPuntuaciones;
    }

    private static class TopJugadores implements Comparable<TopJugadores> {
        private String jugador;
        private int puntuajeJugador;

        public TopJugadores(String nombre, int puntaje) {
            this.jugador = nombre;
            this.puntuajeJugador = puntaje;
        }
        public String getUsuario() {
            return jugador;
        }
        public int getPuntuacion() {
            return puntuajeJugador;
        }
        public int compareTo(TopJugadores otroRegistro) {
            return Integer.compare(otroRegistro.getPuntuacion(), this.puntuajeJugador);
        }
    }

}