package Entidades;

import Logica.*;
import GUI.EntidadGrafica;

public abstract class Entidad implements EntidadLogica, Intercambiable, Enfocable, Matchable, Detonable, Ocultable, DetonablePorAdyacencia{
	protected Tablero tablero;
	protected EntidadGrafica entidad_grafica;

	protected int fila;
	protected int columna;
	protected int color;
	protected int puntajeEntidad;
	protected int estado;
	protected Puntaje puntaje;
	
	protected boolean enfocada;
	protected boolean detonada;
	protected boolean visible;


	protected String [] imagenes_representativas;

	protected Entidad(Tablero tablero, int fila, int columna, int color, String path_imagenes, boolean visible) {
		this.tablero = tablero;
		this.fila = fila;
		this.columna = columna;
		this.color = color;
		this.enfocada = false;
		this.detonada = false;
		this.visible = visible;
		cargar_imagenes_representativas(path_imagenes);
	}
		
	protected void cargar_imagenes_representativas(String path_img) {
		imagenes_representativas = new String [5]; 
		imagenes_representativas[0] = path_img + color +".png";
		imagenes_representativas[1] = path_img + color + "-resaltado.png";
		imagenes_representativas[2] = "/imagenes/explosion/11.png";
		imagenes_representativas[3] = path_img + estado + "-resaltado.png";
	}

	public int getPuntajeEntidad(){
		return puntajeEntidad;
	}
	// Operaciones Entidad Logica (Entidad <-- Celda)
	
	public int get_fila() {
		return fila;
	}
	
	public int get_columna() {
		return columna;
	}
	
	public boolean get_visibilidad() {
		return visible;
	}
	
	public Tablero get_tablero() {
		return tablero;
	}

	public boolean get_enfoque(){
		return enfocada;
	}
	
	public String get_imagen_representativa() {
		int indice = 0;
		indice += (enfocada ? 1 : 0);
		indice += (detonada ? 2 : 0);
		indice += (enfocada && (estado == 3 || estado == 4) ? 3 : 0);
		return imagenes_representativas[indice];
	}
	
	// Operaciones Enfocable (Entidad <-- Juego)
	
	public void enfocar() {
		enfocada = true;
		entidad_grafica.notificarse_cambio_foco();
	}
	
	public void desenfocar() {
		enfocada = false;
		entidad_grafica.notificarse_cambio_foco();
	}
	
	// Operaciones Detonable (Entidad <-- Juego)
	
	public void detonar() {
		detonada = true;
		entidad_grafica.notificarse_detonacion();
	}
	
	// Operaciones Ocultable (Entidad <-- Tablero)
	
	public void mostrar() {
		visible = true;
		entidad_grafica.notificarse_cambio_visibilidad();
	}
	
	public void ocultar() {
		visible = false;
		entidad_grafica.notificarse_cambio_visibilidad();
	}

	public void actualizar_imagen_caramelo_tdp2(int color) {
		
	} 
	
	// Operaciones para cambio de posiciones
	
	public void cambiar_posicion(int nueva_fila, int nueva_columna) {
		fila = nueva_fila;
		columna = nueva_columna;
	}
	
	public void cambiar_posicion_y_animar(int nueva_fila, int nueva_columna) {
		cambiar_posicion(nueva_fila, nueva_columna);
		entidad_grafica.notificarse_intercambio();
	}

	public void caer() {
		entidad_grafica.notificarse_caida();
	}

	
	// Operaciones para comandos y consulta de atributos 
	
	public void set_entidad_grafica(EntidadGrafica e) {
		entidad_grafica = e;
	}
	
	public int get_color() {
		return color;
	}

	public void set_tablero(Tablero tablero){
		this.tablero = tablero;
	}

	
	// Operaciones locales a Entidad
	
	protected void intercambiar_entidad_y_entidad(Entidad origen, Entidad destino) {
		int nueva_fila_origen = destino.get_fila();
		int nueva_columna_origen = destino.get_columna();
		destino.cambiar_posicion_y_animar(origen.get_fila(), origen.get_columna());
		origen.cambiar_posicion_y_animar(nueva_fila_origen, nueva_columna_origen);
		tablero.reubicar(origen);
		tablero.reubicar(destino);
	}
	
	protected void intercambiar_caramelo_y_gelatina(Caramelo caramelo, Gelatina gelatina) {
		Caramelo interno = gelatina.get_caramelo_interno();
		int fila_gelatina = gelatina.get_fila();
		int columna_gelatina = gelatina.get_columna();
		interno.cambiar_posicion_y_animar(caramelo.get_fila(),caramelo.get_columna());
		caramelo.cambiar_posicion_y_animar(fila_gelatina, columna_gelatina);
		tablero.reubicar(interno);
		gelatina.set_caramelo_interno(caramelo);
	}

	public abstract void set_caramelo(Caramelo caramelo_a_insertar);

	public abstract Caramelo get_caramelo();

	public void ubicar_entidad_grafica_fuera_de_tablero(){
		entidad_grafica.reposicionarse_fuera_de_tablero();
	}

	public void reposicionar_en_eje_z(){
		//Las entidades por defecto no se superponen
	}

	

}
