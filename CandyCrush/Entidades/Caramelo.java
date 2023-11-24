package Entidades;

import java.util.HashSet;
import java.util.LinkedList;


import Logica.Tablero;

public class Caramelo extends Entidad {
	
	public Caramelo(Tablero tablero, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "imagenes/caramelos/", true);
		puntajeEntidad=Puntaje.getPuntaje(color);
	}
	
	public Caramelo(Tablero tablero, int fila, int columna, int color, boolean visible) {
		super(tablero, fila, columna, color, "imagenes/caramelos/", visible);
		puntajeEntidad=Puntaje.getPuntaje(color);
	}

	protected Caramelo(Tablero tablero, int fila, int columna, int color, String path_imagenes, boolean visible) {
		super(tablero, fila, columna, color, path_imagenes, visible);
		puntajeEntidad=Puntaje.getPuntaje(color);
	}

	public void ubicar_entidad_grafica_fuera_de_tablero(){
		entidad_grafica.reposicionarse_fuera_de_tablero();
	}

	
	//Operaciones interfaz intercambiable

	public boolean es_posible_intercambiar(Entidad entidad) {
		return entidad.puede_recibir(this);
	}
	
	public boolean puede_recibir(Caramelo caramelo) {
		return true;
	}

	public boolean puede_recibir(Glaseado glaseado) {
		return false;
	}

	public boolean puede_recibir(Potenciador potenciador) {
		return true;
	}

	public boolean puede_recibir(Gelatina gelatina) {
		return true;
	}
	
	public void intercambiar(Entidad entidad) {
		entidad.intercambiar_con(this);
	}
	
	public void intercambiar_con(Caramelo caramelo) {
		intercambiar_entidad_y_entidad(this, caramelo);
	}

	public void intercambiar_con(CarameloTdp2 caramelotTdp2) {
		intercambiar_entidad_y_entidad(this, caramelotTdp2);
	}

	public void intercambiar_con(Potenciador potenciador) {
		intercambiar_entidad_y_entidad(this, potenciador);
	}
	
	public void intercambiar_con(Glaseado glaseado) {
		
	}

	public void intercambiar_con(Gelatina gelatina) {
		intercambiar_caramelo_y_gelatina(this, gelatina);
	}


	//Operaciones interfaz matchable
	
	public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}
	
	public boolean aplica_match_con(Caramelo c) {
		return color == c.get_color();
	}
	
	public boolean aplica_match_con(Potenciador p) {
		return color == p.get_color();
	}
	
	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Gelatina g) {
		return color == g.get_caramelo_interno().get_color();
	}


	//Operaciones interfaz detonable

	public boolean detona_de_a_dos(Entidad entidad){
		return entidad.detona_de_a_dos_con(this);
	}

	public boolean detona_de_a_dos_con(Caramelo caramelo){
		return false;
	}

	public boolean detona_de_a_dos_con(Glaseado glaseado){
		return false;
	}

	public boolean detona_de_a_dos_con(Potenciador potenciador){
		return potenciador.detona_de_a_dos_con(this);
	}

	public boolean detona_de_a_dos_con(Gelatina gelatina){
		return false;
	}


	//Operaciones abstractas de entidad efectivizadas

	public Caramelo get_caramelo(){
		Caramelo a_retornar = null;
		if(!detonada){
			a_retornar = this;
		}
		return a_retornar;
	}

	public void set_caramelo(Caramelo caramelo_a_insertar){
		tablero.reubicar(caramelo_a_insertar);
	}


	@Override
	public HashSet<Entidad> get_entidades_alcanzadas_por_detonacion(Entidad[][] entidades) {
		HashSet<Entidad> entidades_alcanzadas_por_detonacion= new HashSet<Entidad>();
		entidades_alcanzadas_por_detonacion.addAll(efecto_detonacion(entidades));
		entidades_alcanzadas_por_detonacion.addAll(calcular_detonados_por_adyacencia(entidades));
		return entidades_alcanzadas_por_detonacion;
	}

	public boolean se_produce_detonacion_adyacente(Entidad entidad){
		return entidad.es_detonable_por_adyacencia_con(this);
	}

    public boolean es_detonable_por_adyacencia_con(Caramelo caramelo){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(Glaseado glaseado){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(Potenciador potenciador){
		return false;
	}
    
    public boolean es_detonable_por_adyacencia_con(Gelatina gelatina){
		return false;
	}

	public boolean es_detonable_por_adyacencia_con(CarameloTdp2 caramelo_tdp2) {
		return false;
	}

	public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades){
		return new LinkedList<Entidad>();
	}

	@Override
	public LinkedList<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades) {
		LinkedList<Entidad> detonados_por_adyacencia = new LinkedList<Entidad>();
		int variar_posicion=-1;

		for(int i=0;i<2;i++){
			if(tablero.en_rango(fila+variar_posicion, columna) && se_produce_detonacion_adyacente(entidades[fila+variar_posicion][columna])){
			detonados_por_adyacencia.add(entidades[fila+variar_posicion][columna]);
			}
			if(tablero.en_rango(fila,columna+variar_posicion) && se_produce_detonacion_adyacente(entidades[fila][columna+variar_posicion])){
				detonados_por_adyacencia.add(entidades[fila][columna+variar_posicion]);
			}
			variar_posicion=1;
		}
		return detonados_por_adyacencia;
	}
}