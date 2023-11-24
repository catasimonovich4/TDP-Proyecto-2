package Entidades;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Timer;
import java.util.TimerTask;

import Animadores.AnimadorCambioFoco;
import Logica.Tablero;
import temas.TemaCelda;

public class CarameloTdp2 extends Caramelo{

    //Tiempo limite en segundos
    private static final int TIEMPO_LIMITE = 60; 
    private Timer timer;
    private int tiempo_restante;
    

    public CarameloTdp2(Tablero tablero, int fila, int columna, int color) {
        super(tablero, fila, columna, color, "imagenes/caramelos_tdp/tdp2/", true);
        iniciarTimer();
	}

    //Operaciones interfaz matchable

	public boolean se_produce_match_con(Entidad e) {
		return false;
	}
	
	public boolean aplica_match_con(Caramelo c) {
		return false;
	}
	
	public boolean aplica_match_con(Potenciador p) {
		return false;
	}
	
	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Gelatina g) {
		return false;
	}

    //Operaciones interfaz detonable

	public void detonar(){
		super.detonar();
	}

	public boolean detona_de_a_dos(Entidad entidad){
		return false;
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

    //Operaciones
	public Caramelo get_caramelo(){
		return null;
	}

	protected void set_estado(int color) {
		this.estado = color;
	}

	public void set_caramelo(Caramelo caramelo_a_insertar){
		tablero.reubicar(caramelo_a_insertar);
	}

	@Override
	public void reposicionar_en_eje_z(){
		entidad_grafica.reposicionarse_al_frente_en_eje_z();
	}

	public boolean se_produce_detonacion_adyacente(Entidad entidad){
		return entidad.es_detonable_por_adyacencia_con(this);
	}

    public boolean es_detonable_por_adyacencia_con(Caramelo caramelo){
		return true;
	}

    public boolean es_detonable_por_adyacencia_con(Glaseado glaseado){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(Potenciador potenciador){
		return true;
	}
    
    public boolean es_detonable_por_adyacencia_con(Gelatina gelatina){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(CarameloTdp2 caramelo_tdp2) {
        return false;
    }

	public HashSet<Entidad> get_entidades_alcanzadas_por_detonacion(Entidad[][] entidades) {
		HashSet<Entidad> entidades_alcanzadas_por_detonacion= new HashSet<Entidad>();
		entidades_alcanzadas_por_detonacion.addAll(efecto_detonacion(entidades));
		entidades_alcanzadas_por_detonacion.addAll(calcular_detonados_por_adyacencia(entidades));
		return entidades_alcanzadas_por_detonacion;
		
	}
	public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades){
		LinkedList<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		detonados_por_efecto.add(this);
		return detonados_por_efecto;
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

    //Operaciones Timer
    private void iniciarTimer() {
        timer = new Timer();
        tiempo_restante = TIEMPO_LIMITE;

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                tiempo_restante--;

                if (tiempo_restante == 40) {
					cambiar_imagen_caramelo_tdp2(3);
					imagenes_representativas[0] = "imagenes/caramelos_tdp/tdp2/" + 3 + ".png";
					imagenes_representativas[1] = "imagenes/caramelos_tdp/tdp2/" + 3 + "-resaltado.png";
                }

                if (tiempo_restante == 20) {
                    cambiar_imagen_caramelo_tdp2(4);
					imagenes_representativas[0] = "imagenes/caramelos_tdp/tdp2/" + 4 + ".png";
					imagenes_representativas[1] = "imagenes/caramelos_tdp/tdp2/" + 4 + "-resaltado.png";
                }

                 // El tiempo ha expirado, implementa la lógica de reinicio del nivel
                if(tiempo_restante == 0) {
                    detonar();
					tablero.get_Juego().terminar_partida();
                }
            }
        }, 1000, 1000); // Programa la tarea para ejecutarse cada segundo

    }

    public void reiniciarNivel() {
        tablero.get_Juego().resetear_nivel();
    }

    private void cambiar_imagen_caramelo_tdp2(int color) {
        entidad_grafica.notificarse_cambio_estado_carameloTdp2(color);
    }

	private String get_tema_de_path() {
		TemaCelda tema_celda = entidad_grafica.get_tema();
		String tema_path = tema_celda.get_path_imagen(this);

		String[] path_parts = tema_path.split("/");

		for (int i=0; i < path_parts.length; i++) {
			if (path_parts[i].equals("clasico") || path_parts[i].equals("alternativo")) {
				tema_path = path_parts[i];
			} 
		}
		return tema_path;
	}
}

