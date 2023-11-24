package Logica;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import Entidades.Entidad;
import GUI.EntidadGrafica;
import GUI.PantallaPrincipal;
import GUI.Ventana;
import temas.Tema;
import temas.alternativo.TemaAlternativo;
import temas.clasico.TemaClasico;

/**
 * Modela el comportamiento del Juego.
 * Ofrece servicios para comunicar los diferentes elementos que conforman la lógica de la aplicación con la gráfica de la misma.
 */
public class Juego {
	
	public static final int ARRIBA = 15000;
	public static final int ABAJO = 15001;
	public static final int IZQUIERDA = 15002;
	public static final int DERECHA = 15003;
	
	protected Tablero tablero;
	protected Nivel nivel;
	protected Tiempo reloj;
	protected Tema tema;
	protected Ventana ventana;
	private PantallaPrincipal pantalla_principal;
	
	protected int vidas = 5;
	protected int numero_nivel;
	protected int eleccion;
	protected int movimientos;
	protected Puntaje puntajeActual;

	
	protected String [] objetivos = new String[10];
	protected String [] reglas = new String[10];

	public Juego() {
		tablero = new Tablero(this);
		numero_nivel = 1;
		nivel = GeneradorNivel.cargar_nivel_y_tablero(numero_nivel, tablero, objetivos, reglas);
		reloj = new Tiempo(Integer.parseInt(objetivos[0].substring(1)),this);
		nivel.set_objetivos(objetivos, reloj); 
		mostrar_pantallaPrincipal();
	}

	//Carga Pantalla principal
	protected void mostrar_pantallaPrincipal(){
		pantalla_principal = new PantallaPrincipal(this);
		pantalla_principal.setVisible(true);
	}

	public void iniciarTema(int eleccion){
		if(eleccion==Tema.TEMA_ALTERNATIVO){
			tema=new TemaAlternativo();
			ventana = new Ventana(this,nivel,tema);
			pantalla_principal.dispose();
			ventana.inicializar();
			tablero.asociar_entidades_logicas_y_graficas();
			tablero.fijar_jugador(nivel.get_fila_inicial_jugador(), nivel.get_columna_inicial_jugador());
			ventana.mostrar();
			
		}
		if(eleccion==Tema.TEMA_CLASICO){
			tema=new TemaClasico();
			pantalla_principal.dispose();
			ventana = new Ventana(this,nivel,tema);
			ventana.inicializar();
			tablero.asociar_entidades_logicas_y_graficas();
			tablero.fijar_jugador(nivel.get_fila_inicial_jugador(), nivel.get_columna_inicial_jugador());
			ventana.mostrar();
		}
		
	}

	
	//Logica para tablero

	public void mover_jugador(int d) {
		tablero.mover_jugador(d);
	}
	
	public void intercambiar_entidades(int d) {
		tablero.intercambiar_entidades(d);
	}
	
	//Asociacion de entidades graficas

	public void asociar_entidad_logica_y_grafica(Entidad entidad){
		EntidadGrafica entidad_grafica = ventana.agregar_entidad(entidad);
		entidad.set_entidad_grafica(entidad_grafica);
	}

	//Objetivos
	
	public void restar_movimiento(){
		ventana.restar_movimiento();
	}
	
	public void actualizaObjetivos(HashMap<Integer,Integer> entidades_detonadas){
		ventana.actualizaObjetivos(entidades_detonadas);
	}

	public void notificar_tiempo_agotado(){
		ventana.notificar_tiempo_agotado();
	}

	public Nivel get_nivel() {
		return nivel;
	}

	public void resetear_nivel(){
		ventana.ocultar();
		vidas--;
		nivel = GeneradorNivel.cargar_nivel_y_tablero(numero_nivel, tablero, objetivos, reglas);
		reloj.resetearTiempoRestante();
		nivel.set_objetivos(objetivos, reloj);
		ventana = new Ventana(this,nivel,tema);
		ventana.inicializar();
		tablero.asociar_entidades_logicas_y_graficas();
		tablero.fijar_jugador(nivel.get_fila_inicial_jugador(), nivel.get_columna_inicial_jugador());
		ventana.mostrar();

	}

	public void terminar_partida() {
		ventana.mostrarMensajeFinDelJuego();
		dejarDeContarPuntaje();
	}

	public void siguienteNivel(){
		ventana.ocultar();
		numero_nivel++;
		nivel = GeneradorNivel.cargar_nivel_y_tablero(numero_nivel, tablero, objetivos, reglas);
		reloj = new Tiempo(Integer.parseInt(objetivos[0].substring(1)),this);
		nivel.set_objetivos(objetivos, reloj);
		ventana = new Ventana(this,nivel,tema);
		ventana.inicializar();
		tablero.asociar_entidades_logicas_y_graficas();
		tablero.fijar_jugador(nivel.get_fila_inicial_jugador(), nivel.get_columna_inicial_jugador());
		ventana.mostrar();
	}

	
	public int get_filas() {
		return tablero.get_filas();
	}

    public int get_columnas() {
        return tablero.get_columnas();
    }

	public int get_vidas() {
		return vidas;
	}

	public Tablero getTablero(){
		return tablero;
	}

	public void crearPuntaje(String nombre){
		 puntajeActual= new Puntaje(nombre);
	}

	public void recibirPuntaje(int puntaje){
		puntajeActual.ActualizarPuntaje(puntaje);
		ventana.actualizarPuntaje(puntajeActual.getPuntaje());
	}

	public void dejarDeContarPuntaje(){
		puntajeActual.guardarPuntaje(puntajeActual.getPuntaje());
	}

	public int getPuntajeActual(){
		return puntajeActual.getPuntaje();
	}
	public List<String> obtenerPuntuaciones(){
		return puntajeActual.obtenerPuntuaciones();
	}

	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Juego();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

}
