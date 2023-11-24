package Logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import Entidades.Caramelo;
import Entidades.CarameloProvisorio;
import Entidades.Entidad;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Reglas.EfectoRegla;
import Reglas.Regla;
import Reglas.Regla_match_3;
import Reglas.Regla_match_4_o_mas;
import Reglas.Regla_match_L;
import Reglas.Regla_match_T;

public class Tablero implements TableroJuego{
	
	protected Juego juego;
	protected Entidad [][] entidades;
	protected List<Entidad> entidades_asociadas;

	protected List<Regla> reglas_de_match;
	
	protected int filas;
	protected int columnas;
	
	protected int fila_jugador;
	protected int columna_jugador;

	protected int movimientos;
	
	public Tablero(Juego juego) {
		this.juego = juego;
		filas = 0;
		columnas = 0;
	}

	// Operaciones para Tablero Juego (Tablero <-- Juego)
	
	public int get_filas() {
		return filas;
	}
	
	public int get_columnas() {
		return columnas;
	}

	public Juego get_Juego() {
		return juego;
	}

	public void recibirMovimientos(int mov){
		movimientos=mov;
	}

	public void recibirReglas(String[] reg) {
		String regla;
		reglas_de_match = new LinkedList<>();
		for (int i=0; i < reg.length; i++) {
			if (reg[i] != null) {
				regla = reg[i];
				switch (regla) {
					case "R3": {
						Regla_match_3 r3 = new Regla_match_3();
						reglas_de_match.add(r3);
						break;
					}
					case "R4": {
						Regla_match_4_o_mas r4 = new Regla_match_4_o_mas();
						reglas_de_match.add(r4);
						break;
					}
					case "RT": {
						Regla_match_T rt = new Regla_match_T();
						reglas_de_match.add(rt);
						break;
					}
					case "RL": {
						Regla_match_L rl = new Regla_match_L();
						reglas_de_match.add(rl);
						break;
					}
				}
			}
		}
		
	}
	
	public void asociar_entidades_logicas_y_graficas() {
		Entidad entidad;
			
		for (int f=0; f<filas; f++) {
			for (int c=0; c<columnas; c++) {
				entidad = entidades[f][c];
				juego.asociar_entidad_logica_y_grafica(entidad);
			}
		}

		for(Entidad entidad_a: entidades_asociadas) {
			juego.asociar_entidad_logica_y_grafica(entidad_a);
		}
		
		reposicionar_entidades_a_superponer();
		//imprimirTablero();
	}

	protected void reposicionar_entidades_a_superponer(){
		for (int f=0; f<filas; f++) {
			for (int c=0; c<columnas; c++) {
				entidades[f][c].reposicionar_en_eje_z();
			}
		}
	}
	
	public void resetar_tablero(int cant_filas, int cant_columnas) {
		filas = cant_filas;
		columnas = cant_columnas;
		fila_jugador = 0;
		columna_jugador = 0;
		entidades = new Entidad[cant_filas][cant_columnas];
		entidades_asociadas = new LinkedList<Entidad>();
	}
	
	public void agregar_entidad(Entidad e) {
		entidades[e.get_fila()][e.get_columna()] = e;
	}
	
	public void agregar_entidad_y_asociada(Gelatina g) {
		entidades[g.get_fila()][g.get_columna()] = g;
		entidades_asociadas.add(g.get_caramelo_interno());
	}
	
	public void fijar_jugador(int fila_destino, int columna_destino) {
		entidades[fila_destino][columna_destino].enfocar();
		entidades[fila_jugador][columna_jugador].desenfocar();
		fila_jugador = fila_destino;
		columna_jugador = columna_destino;
	}
	
	public void mover_jugador(int direccion) {
		switch(direccion) {
			case Juego.ABAJO:{
				mover_jugador(fila_jugador + 1, columna_jugador);
				break;
			}
			case Juego.ARRIBA:{
				mover_jugador(fila_jugador - 1, columna_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				mover_jugador(fila_jugador, columna_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				mover_jugador(fila_jugador, columna_jugador + 1);
				break;
			}
		}
	}
	
	public void intercambiar_entidades(int direccion) {
		switch(direccion) {
			case Juego.ABAJO:{
				intercambiar_entidades_y_transicionar(fila_jugador + 1, columna_jugador);
				break;
			}
			case Juego.ARRIBA:{
				intercambiar_entidades_y_transicionar(fila_jugador - 1, columna_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				intercambiar_entidades_y_transicionar(fila_jugador, columna_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				intercambiar_entidades_y_transicionar(fila_jugador, columna_jugador + 1);
				break;
			}
		}
	}
	
	// Operaciones para Tablero Notificable (Tablero <-- Entidad)
	
	public void reubicar(Entidad e) {
		int nueva_fila = e.get_fila();
		int nueva_columna = e.get_columna();
		entidades[nueva_fila][nueva_columna] = e;
	}
	
	// Operaciones locales
	
	private void mover_jugador(int fila_destino, int columna_destino) {
		if (en_rango(fila_destino,columna_destino)) {
			entidades[fila_destino][columna_destino].enfocar();
			entidades[fila_jugador][columna_jugador].desenfocar();
			fila_jugador = fila_destino;
			columna_jugador = columna_destino;
		}
	}
	
	private void intercambiar_entidades_y_transicionar(int fila_destino, int columna_destino) {
		int fila_origen = fila_jugador;
		int columna_origen = columna_jugador;
		Entidad entidad_origen, entidad_destino;
		EfectosDeTransicion efecto_intercambio;
		
		if (en_rango(fila_destino, columna_destino)) {	
			entidad_origen = entidades[fila_origen][columna_origen];
			entidad_destino = entidades[fila_destino][columna_destino];
			
			if (entidad_origen.es_posible_intercambiar(entidad_destino)) {
				cambiar_posicion_jugador(fila_destino, columna_destino);
				entidad_origen.intercambiar(entidad_destino);
				entidad_origen = entidades[fila_origen][columna_origen];
				entidad_destino = entidades[fila_destino][columna_destino];

				efecto_intercambio = calcular_efectos_por_intercambio(entidad_origen, entidad_destino);
				if(efecto_intercambio.existen_entidades_a_detonar()){
					juego.restar_movimiento();
					while (efecto_intercambio.existen_entidades_a_detonar()) {
						transicionar_proximo_estado(efecto_intercambio);
						verificar_matches_luego_de_caida(efecto_intercambio);
					}
				}
				else{
					entidad_origen = entidades[fila_origen][columna_origen];
					entidad_destino = entidades[fila_destino][columna_destino];
					cambiar_posicion_jugador(fila_origen, columna_origen);
					entidad_origen.intercambiar(entidad_destino);
				}
			}
		}
	}
	
	public void verificar_matches_luego_de_caida(EfectosDeTransicion efecto_transicion){
		boolean existen_entidades_a_detonar = false;
		Iterator<Regla> reglas_a_aplicar = reglas_de_match.iterator();
		Regla regla;
		EfectoRegla efecto_regla;
		do{
			regla = reglas_a_aplicar.next();
			for(int f = 0; f < filas && !existen_entidades_a_detonar; f++){
				for(int c = 0; c < columnas && !existen_entidades_a_detonar; c++){
					efecto_regla = regla.verificar_regla(entidades, entidades[f][c]);
					agregar_a_efecto_detonacion(efecto_regla,efecto_transicion);
					agregar_a_incorporar(efecto_regla,efecto_transicion);
					existen_entidades_a_detonar = efecto_transicion.existen_entidades_a_detonar();
				}
			}
		}while(reglas_a_aplicar.hasNext() && !existen_entidades_a_detonar);
	}

	
	public boolean en_rango(int fila, int columna){
		boolean en_rango_fila = (0 <= fila) && (fila < filas);
		boolean en_rango_columna = (0 <= columna) && (columna < columnas);
		return (en_rango_fila && en_rango_columna);
	}
	
	protected void cambiar_posicion_jugador(int nueva_fila, int nueva_columna) {
		fila_jugador = nueva_fila;
		columna_jugador = nueva_columna;
	}
	
	protected EfectosDeTransicion calcular_efectos_por_intercambio(Entidad entidad_origen, Entidad entidad_destino){
		EfectosDeTransicion efecto_transicion = new EfectosDeTransicion();
		if (entidad_origen.detona_de_a_dos(entidad_destino)) {
			agregar_detonacion_de_a_dos(efecto_transicion, entidad_origen, entidad_destino);
		}else {
			verificar_match(efecto_transicion, entidad_origen);
			verificar_match(efecto_transicion, entidad_destino);
		}
		return efecto_transicion;
	}

	private void agregar_detonacion_de_a_dos(EfectosDeTransicion efecto_transicion, Entidad origen, Entidad destino){
		Set<Entidad> entidades_alcanzadas_por_detonacion = origen.get_entidades_alcanzadas_por_detonacion(entidades);
		pasar_a_efecto_transicion(entidades_alcanzadas_por_detonacion, efecto_transicion);

		entidades_alcanzadas_por_detonacion = destino.get_entidades_alcanzadas_por_detonacion(entidades);
		pasar_a_efecto_transicion(entidades_alcanzadas_por_detonacion, efecto_transicion);

		efecto_transicion.agregar_entidad_a_detonar_y_reemplazar(origen);
		efecto_transicion.agregar_entidad_a_detonar_y_reemplazar(destino);
	}

	protected void verificar_match(EfectosDeTransicion efecto_transicion, Entidad entidad_a_chequear_match){
		Iterator<Regla> iterador_reglas_de_match = reglas_de_match.iterator();
		Regla regla_a_aplicar;
		EfectoRegla efecto_regla;
		boolean hubo_match = false;
		while(iterador_reglas_de_match.hasNext() && !hubo_match){
			regla_a_aplicar = iterador_reglas_de_match.next();
			efecto_regla = regla_a_aplicar.verificar_regla(entidades, entidad_a_chequear_match);
			hubo_match = efecto_regla.hubo_match();
			if(hubo_match){
				agregar_a_efecto_detonacion(efecto_regla,efecto_transicion);
				agregar_a_incorporar(efecto_regla,efecto_transicion);
			}
		}
	}

	private void agregar_a_efecto_detonacion(EfectoRegla efecto_regla, EfectosDeTransicion efecto_transicion){
		Set<Entidad> entidades_a_detonar = efecto_regla.get_entidades_verificadas_a_detonar();
		pasar_a_efecto_transicion(entidades_a_detonar, efecto_transicion);

		for(Entidad entidad_a_detonar: entidades_a_detonar){
			Set<Entidad> alcanzadas_por_detonacion = entidad_a_detonar.get_entidades_alcanzadas_por_detonacion(entidades);
			pasar_a_efecto_transicion(alcanzadas_por_detonacion, efecto_transicion);
		}	
	}

	private void pasar_a_efecto_transicion(Collection<Entidad> alcanzadas_por_detonacion, EfectosDeTransicion efecto_transicion){
		for(Entidad e: alcanzadas_por_detonacion){
			efecto_transicion.agregar_entidad_a_detonar_y_reemplazar(e);
		}
	}

	private void agregar_a_incorporar(EfectoRegla efecto_regla, EfectosDeTransicion efecto_transicion){
		Entidad a_ser_reemplazada;
		int fila_donde_incorporar;
		int columna_donde_incorporar;

		/*
		 * Las entidades que seran reemplazadas por las que se incorporarán se eliminan
		 * de entidades_a_reemplazar, pues su reemplazo ya es conocido (la entidad_a_incorporar)
		 */
		for(Entidad entidad_a_incorporar: efecto_regla.get_entidades_a_incorporar()){
			fila_donde_incorporar = entidad_a_incorporar.get_fila();
			columna_donde_incorporar = entidad_a_incorporar.get_columna();
			a_ser_reemplazada = entidades[fila_donde_incorporar][columna_donde_incorporar];
			efecto_transicion.eliminar_de_entidades_a_reemplazar(a_ser_reemplazada);
			efecto_transicion.agregar_entidad_de_reemplazo(entidad_a_incorporar);
		}
	}
	

	protected void transicionar_proximo_estado(EfectosDeTransicion efecto_transicion) {
		detonar(efecto_transicion.entidades_a_detonar());
		agregar_entidades_nuevas(efecto_transicion.entidades_a_incorporar());
		aplicar_caida_y_reubicar(efecto_transicion.entidades_a_reemplazar());
		actualizaObjetivos(efecto_transicion);
		efecto_transicion.vaciar_efectos();
		entidades[fila_jugador][columna_jugador].enfocar();
		
	}
	
	protected void actualizaObjetivos(EfectosDeTransicion efecto_intercambio){
		HashMap<Integer,Integer> conjuntos_de_entidades_detonadas= new HashMap<>();
		for(int i=1;i<11;i++){
			conjuntos_de_entidades_detonadas.put(i,0);
		}
		for(Entidad e: efecto_intercambio.entidades_a_detonar()){
			Integer numero_de_color=e.get_color();
			int valor=conjuntos_de_entidades_detonadas.get(numero_de_color);
			conjuntos_de_entidades_detonadas.put(e.get_color(),valor +1);
		}
		juego.actualizaObjetivos(conjuntos_de_entidades_detonadas);
	}

	protected void detonar(Collection<Entidad> entidades_a_detonar) {
		int acumulador_puntaje=0;
		for(Entidad e: entidades_a_detonar) {
			e.desenfocar();
			e.detonar();
			acumulador_puntaje+=e.getPuntajeEntidad();
		}
		juego.recibirPuntaje(acumulador_puntaje);
	}
	
	protected void agregar_entidades_nuevas(Collection<Entidad> entidades_a_incorporar) {
		for(Entidad e: entidades_a_incorporar) {
			e.set_tablero(this);
			entidades[e.get_fila()][e.get_columna()] = e;
			juego.asociar_entidad_logica_y_grafica(e);
			e.mostrar();
		}
	}
	
	protected void aplicar_caida_y_reubicar(Collection<Entidad> entidades_a_reemplazar) {
		Collection<Entidad> entidades_a_reemplazar_en_siguiente_iteracion = new HashSet<Entidad>();
		Caramelo caramelo_de_reemplazo;	 //caramelo dentro del tablero que reemplazará a la entidad correspondiente
		Caramelo caramelo_provisorio;    //caramelo que indicara que una entidad cedió su caramelo en una caída y se le
										 //debe insertar otro
		Caramelo caramelo_nuevo;		 //si no hay caramelo_de_reemplazo, se creará e insertará caramelo_nuevo
		boolean existe_reemplazo;
		

		for (Entidad entidad_a_reemplazar : entidades_a_reemplazar) {

			caramelo_de_reemplazo = obtener_caramelo_de_reemplazo(entidad_a_reemplazar);
			existe_reemplazo = caramelo_de_reemplazo != null;

			if(existe_reemplazo){
				caramelo_provisorio = reemplazar_por_caramelo_provisorio(caramelo_de_reemplazo);
				entidades_a_reemplazar_en_siguiente_iteracion.add(caramelo_provisorio);
				insertar_caramelo(entidad_a_reemplazar, caramelo_de_reemplazo);
				
				if(caramelo_de_reemplazo.get_enfoque()){
					caramelo_de_reemplazo.desenfocar();
				}
				caramelo_de_reemplazo.caer();	
			}
			else{
				caramelo_nuevo = insertar_caramelo_nuevo(entidad_a_reemplazar);
				caramelo_nuevo.ubicar_entidad_grafica_fuera_de_tablero();
				caramelo_nuevo.caer();
			}
		}	
		
		if(!entidades_a_reemplazar_en_siguiente_iteracion.isEmpty()){
			aplicar_caida_y_reubicar(entidades_a_reemplazar_en_siguiente_iteracion);
		}
	}
	
	private Caramelo obtener_caramelo_de_reemplazo(Entidad entidad_a_reemplazar) {
		int fila_entidad_a_reemplazar = entidad_a_reemplazar.get_fila();
		int columna_entidad_a_reemplazar = entidad_a_reemplazar.get_columna();
		Caramelo caramelo_de_reemplazo = null;
		boolean encontre_reemplazo = false;
		for(int f = fila_entidad_a_reemplazar - 1; f>=0 && !encontre_reemplazo; f--){
			caramelo_de_reemplazo = entidades[f][columna_entidad_a_reemplazar].get_caramelo();
			encontre_reemplazo = caramelo_de_reemplazo != null;
		}
		return caramelo_de_reemplazo;
	}
	
	private Caramelo insertar_caramelo_nuevo(Entidad entidad_a_insertarle_nuevo_caramelo) {
		int fila_entidad_a_reemplazar = entidad_a_insertarle_nuevo_caramelo.get_fila();
		int columna_entidad_a_reemplazar = entidad_a_insertarle_nuevo_caramelo.get_columna();
		Caramelo caramelo_nuevo = generar_caramelo_aleatorio(fila_entidad_a_reemplazar, columna_entidad_a_reemplazar);
		insertar_caramelo(entidad_a_insertarle_nuevo_caramelo, caramelo_nuevo);
		juego.asociar_entidad_logica_y_grafica(caramelo_nuevo);
		return caramelo_nuevo;
	}

	private Caramelo generar_caramelo_aleatorio(int fila, int columna){
		Random random = new Random();
		int color_random = random.nextInt(6) + 1;
		return new Caramelo(this, fila, columna, color_random,true);
	}
	
	private Caramelo reemplazar_por_caramelo_provisorio(Caramelo caramelo_a_reemplazar) { 
		int fila_caramelo_a_reemplazar = caramelo_a_reemplazar.get_fila();
		int columna_caramelo_a_reemplazar = caramelo_a_reemplazar.get_columna();
		int color_caramelo_a_reemplazar = caramelo_a_reemplazar.get_color(); 
		CarameloProvisorio caramelo_provisorio = new CarameloProvisorio(this, 
							  fila_caramelo_a_reemplazar,
							  columna_caramelo_a_reemplazar, 
							  color_caramelo_a_reemplazar);
	
		insertar_caramelo(caramelo_a_reemplazar, caramelo_provisorio);
		return caramelo_provisorio;
	}

	private void insertar_caramelo(Entidad entidad_donde_insertar, Caramelo caramelo_a_insertar){
		int fila_donde_insertar = entidad_donde_insertar.get_fila();
		int columna_donde_insertar = entidad_donde_insertar.get_columna();
		caramelo_a_insertar.cambiar_posicion(fila_donde_insertar, columna_donde_insertar);
		entidades[fila_donde_insertar][columna_donde_insertar].set_caramelo(caramelo_a_insertar);
	}


}
