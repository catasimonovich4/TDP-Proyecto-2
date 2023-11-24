package Entidades;

import java.util.HashSet;
import java.util.LinkedList;

import Logica.Tablero;

public class Gelatina extends Entidad {
	
	protected Caramelo caramelo_interno;
	
	public Gelatina(Tablero tablero, Caramelo caramelo, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "imagenes/gelatina/", true);
		caramelo_interno = caramelo;
		puntajeEntidad=100+caramelo_interno.getPuntajeEntidad();
	}
	
	public Gelatina(Tablero tablero, Caramelo caramelo, int fila, int columna, int color, boolean visible) {
		super(tablero, fila, columna, color, "imagenes/gelatina/", visible);
		caramelo_interno = caramelo;
		puntajeEntidad=100+caramelo_interno.getPuntajeEntidad();
	}
	
	public void set_caramelo_interno(Caramelo caramelo) {
		caramelo_interno = caramelo;
	}
	
	public Caramelo get_caramelo_interno() {
		return caramelo_interno;
	}
		

	//Operaciones interfaz enfocable

	@Override
	public void enfocar() {
		caramelo_interno.enfocar();
	}
	
	@Override
	public void desenfocar() {
		caramelo_interno.desenfocar();
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
		return false;
	}

	public boolean puede_recibir(Gelatina gelatina) {
		return true;
	}

	public void intercambiar(Entidad entidad) {
		entidad.intercambiar_con(this);
	}

	public void intercambiar_con(Caramelo caramelo) {
		intercambiar_caramelo_y_gelatina(caramelo, this);		
	}

	public void intercambiar_con(CarameloTdp2 carameloTdp2){}

	public void intercambiar_con(Glaseado glaseado) {
				
	}

	public void intercambiar_con(Potenciador potenciador) {
		
	}

	public void intercambiar_con(Gelatina gelatina) {
		Caramelo caramelo_interno_this = caramelo_interno;
		Caramelo caramelo_interno_gelatina = gelatina.get_caramelo_interno();
		int nueva_fila_caramelo_interno_gelatina = caramelo_interno_this.get_fila();
		int nueva_columna_caramelo_interno_gelatina = caramelo_interno_this.get_columna();
		int nueva_fila_caramelo_interno_this = caramelo_interno_gelatina.get_fila();
		int nueva_columna_caramelo_interno_this = caramelo_interno_gelatina.get_columna();
		caramelo_interno_this.cambiar_posicion_y_animar(nueva_fila_caramelo_interno_this, nueva_columna_caramelo_interno_this);
		caramelo_interno_gelatina.cambiar_posicion_y_animar(nueva_fila_caramelo_interno_gelatina, nueva_columna_caramelo_interno_gelatina);
		this.caramelo_interno = caramelo_interno_gelatina;
		gelatina.set_caramelo_interno(caramelo_interno_this);
	}


	//Operaciones interfaz matchable

	public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}

	public boolean aplica_match_con(Caramelo c) {
		return caramelo_interno.get_color() == c.get_color();
	}

	public boolean aplica_match_con(Potenciador p) {
		return caramelo_interno.get_color() == p.get_color();
	}

	public boolean aplica_match_con(Glaseado g) {
		return false;
	}

	public boolean aplica_match_con(Gelatina g) {
		Caramelo caramelo_interno_g = g.get_caramelo_interno();
		Caramelo caramelo_interno_this = caramelo_interno;
		return caramelo_interno_g.get_color() == caramelo_interno_this.get_color();
	}

	
	//Operaciones interfaz detonable
	
	public void detonar(){
		super.detonar();
		caramelo_interno.detonar();
	}
	
	public boolean detona_por_adyacencia_con(Caramelo caramelo){
		return false;
	}
	
	public boolean detona_por_adyacencia_con(Glaseado glaseado){
		return false;
	}
	
	public boolean detona_por_adyacencia_con(Potenciador potenciador){
		return false;
	}
	
	public boolean detona_por_adyacencia_con(Gelatina gelatina){
		return false;
	}
	
	public boolean detona_de_a_dos(Entidad entidad){
		return entidad.detona_de_a_dos_con(caramelo_interno);
	}

	public boolean detona_de_a_dos_con(Caramelo caramelo){
		return false;
	}
	
	public boolean detona_de_a_dos_con(Glaseado glaseado){
		return false;
	}
	
	public boolean detona_de_a_dos_con(Potenciador potenciador){
		return false;
	}
	
	public boolean detona_de_a_dos_con(Gelatina gelatina){
		return false;
	}
	
	//Operaciones abstractas de entidad efectivizadas
	
	public Caramelo get_caramelo(){
		Caramelo a_retornar = null;
		if(!detonada){
			a_retornar = caramelo_interno.get_caramelo();
		}
		return a_retornar;
	}
	public void set_caramelo(Caramelo caramelo_a_insertar){
		if(!detonada){
			set_caramelo_interno(caramelo_a_insertar);
		}
		else{
			tablero.reubicar(caramelo_a_insertar);
		}
	}
	
	@Override
	public void reposicionar_en_eje_z(){
		entidad_grafica.reposicionarse_al_frente_en_eje_z();
	}

	public boolean se_produce_detonacion_adyacente(Entidad entidad){
		return entidad.es_detonable_por_adyacencia_con(caramelo_interno);
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

	public HashSet<Entidad> get_entidades_alcanzadas_por_detonacion(Entidad[][] entidades){
		HashSet<Entidad> entidades_alcanzadas_por_detonacion = new HashSet<Entidad>();
		entidades_alcanzadas_por_detonacion.addAll(efecto_detonacion(entidades));
		entidades_alcanzadas_por_detonacion.addAll(calcular_detonados_por_adyacencia(entidades));
		return entidades_alcanzadas_por_detonacion;
	}

	public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades){
		LinkedList<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		detonados_por_efecto=caramelo_interno.efecto_detonacion(entidades);
		return detonados_por_efecto;
	}

	@Override
	public LinkedList<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades) {
		LinkedList<Entidad> detonados_por_adyacencia = new LinkedList<Entidad>();
		detonados_por_adyacencia=caramelo_interno.calcular_detonados_por_adyacencia(entidades);
		return detonados_por_adyacencia;
	}
}
