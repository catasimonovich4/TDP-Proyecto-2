package Logica;
import java.util.LinkedList;
import java.util.List;

import Entidades.*;

/**
 * Modela el tablero de la aplicación. Mantiene control sobre las entidades, y provee acceso a ellas.
 */
/* public class TableroViejo {

	protected Juego mi_juego;
	protected Entidad [][] entidades;
	protected Nivel objetivos;
	protected List<Entidad> entidades_asociadas;

	protected int filas;
	protected int columnas;
	protected int fila_jugador;
	protected int columna_jugador;
	protected int movimientos;
	protected int size_label = 80;

	protected boolean completado = false;

	public TableroViejo(Juego j) {
		mi_juego = j;
		filas = 0;
		columnas = 0;
		entidades_asociadas = new LinkedList<Entidad>();
	}


	public void resetar_tablero(int f, int c) {
		filas = f;
		columnas = c;
		fila_jugador = 0;
		columna_jugador = 0;
		entidades = new Entidad[f][c];
	}


	//setters

	public void set_nivel() {
		objetivos = mi_juego.get_nivel();
	}

	public void recibirMovimientos(int mov){
		movimientos=mov;
	}


	//getters

	public int get_filas() {
		return filas;
	}
	@@ -62,62 +36,48 @@ public int get_columnas() {
		return columnas;
	}

	public Entidad get_entidad(int f, int c) {
		return entidades[f][c];
	}


	//agregar y asociar entidades 

	public void agregar_entidad(Entidad e) {
		entidades[e.get_fila()][e.get_columna()] = e;
		e.set_tablero(this);
	}


	public void agregar_nueva_entidad(Entidad nueva){
		agregar_entidad(nueva);
		mi_juego.asociar_entidad_logica_y_grafica(nueva);
		if(nueva.debe_caer()){
			nueva.caer();
		}
		if(!nueva.es_visible()){
			nueva.aparecer();
		}
	}

	public void agregar_entidad_y_asociada(Gelatina g) {
		entidades[g.get_fila()][g.get_columna()] = g;
		entidades_asociadas.add(g.get_caramelo_interno());
	}

	public void asociar_entidades_logicas_y_graficas() {
		Entidad entidad;

		for (int f=0; f<filas; f++) {
			for (int c=0; c<columnas; c++) {
				entidad = entidades[f][c];
				mi_juego.asociar_entidad_logica_y_grafica(entidad);
			}
		}

		for(Entidad entidad_a: entidades_asociadas) {
			mi_juego.asociar_entidad_logica_y_grafica(entidad_a);
		}
	}


	//movimientos jugador y entidades

	public void fijar_jugador(int f, int c) {
		entidades[f][c].enfocar();
		entidades[fila_jugador][columna_jugador].desenfocar();
		fila_jugador = f;
		columna_jugador = c;
	}

	public void mover_jugador(int d) {
		switch(d) {
			case Juego.ABAJO:{
				mover_jugador(fila_jugador + 1, columna_jugador);
				break;
	@@ -137,276 +97,126 @@ public void mover_jugador(int d) {
		}
	}

	public void intercambiar(int d) {
		switch(d) {
			case Juego.ABAJO:{
				intercambiar(fila_jugador + 1, columna_jugador);
				break;
			}
			case Juego.ARRIBA:{
				intercambiar(fila_jugador - 1, columna_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				intercambiar(fila_jugador, columna_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				intercambiar(fila_jugador, columna_jugador + 1);
				break;
			}
		}
	}


	//Control matches

	public boolean revisar_detonar_match(int nueva_fila, int nueva_columna) {
		Entidad entidad_movida = entidades[nueva_fila][nueva_columna];
		Entidad entidad_1;
		Entidad entidad_2;

		List<Entidad> entidades_en_match = new LinkedList<Entidad>();
		int matches_horizontales = 0;
		int matches_verticales = 0;


		//revisa los 2 de la izquierda de la nueva posicion
		if (en_rango(nueva_fila,nueva_columna-2))
		{
			entidad_1 = entidades[nueva_fila][nueva_columna-1];
			entidad_2 = entidades[nueva_fila][nueva_columna-2];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila][nueva_columna-1]);
				entidades_en_match.add(entidades[nueva_fila][nueva_columna-2]);	
				matches_horizontales++;
			}
		}

		//revisa los 2 de la derecha de la nueva posicion
		if (en_rango(nueva_fila,nueva_columna+2))
		{
			entidad_1 = entidades[nueva_fila][nueva_columna+1];
			entidad_2 = entidades[nueva_fila][nueva_columna+2];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila][nueva_columna+1]);
				entidades_en_match.add(entidades[nueva_fila][nueva_columna+2]);	
				matches_horizontales++;
			}
		}

		//revisa a la izq y der de la nueva posicion
		if (en_rango(nueva_fila,nueva_columna-1) && en_rango(nueva_fila,nueva_columna+1))
		{
			entidad_1 = entidades[nueva_fila][nueva_columna-1];
			entidad_2 = entidades[nueva_fila][nueva_columna+1];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila][nueva_columna-1]);
				entidades_en_match.add(entidades[nueva_fila][nueva_columna+1]);	
				matches_horizontales++;
			}
		}

		//revisa los 2 de arriba de la nueva posicion
		if (en_rango(nueva_fila-2,nueva_columna))
		{
			entidad_1 = entidades[nueva_fila-1][nueva_columna];
			entidad_2 = entidades[nueva_fila-2][nueva_columna];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila-1][nueva_columna]);
				entidades_en_match.add(entidades[nueva_fila-2][nueva_columna]);	
				matches_verticales++;
			}
		}

		//revisa los 2 de abajo de la nueva posicion
		if (en_rango(nueva_fila+2,nueva_columna))
		{
			entidad_1 = entidades[nueva_fila+1][nueva_columna];
			entidad_2 = entidades[nueva_fila+2][nueva_columna];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila+1][nueva_columna]);
				entidades_en_match.add(entidades[nueva_fila+2][nueva_columna]);	
				matches_verticales++;
			}
		}

		//revisa arriba y abajo de la nueva posicion
		if (en_rango(nueva_fila-1,nueva_columna) && en_rango(nueva_fila+1,nueva_columna))
		{
			entidad_1 = entidades[nueva_fila-1][nueva_columna];
			entidad_2 = entidades[nueva_fila+1][nueva_columna];

			if(machean(entidad_movida, entidad_1, entidad_2)){
				entidades_en_match.add(entidades[nueva_fila-1][nueva_columna]);
				entidades_en_match.add(entidades[nueva_fila+1][nueva_columna]);	
				matches_verticales++;
			}
		}

		set_nivel();
		//completado = objetivos.objetivos_completos(en_match);
		//JOptionPane.showMessageDialog(null, objetivos.get_total_caramelos());
		mi_juego.actualizarObjetivos(objetivos.get_total_caramelos(),objetivos.get_total_entidad());

		entidades_en_match.add(entidad_movida);
		return detonar_match(nueva_fila, nueva_columna, matches_verticales, matches_horizontales, entidades_en_match);
	}


	//metodos locales a tablero

	private boolean machean(Entidad origen, Entidad entidad_1, Entidad entidad_2){
		return origen.machea(entidad_1) && origen.machea(entidad_2);
	}

	private void mover_jugador(int nueva_fila, int nueva_columna) {
		if ( en_rango(nueva_fila,nueva_columna) ) {
			entidades[nueva_fila][nueva_columna].enfocar();
			entidades[fila_jugador][columna_jugador].desenfocar();
			fila_jugador = nueva_fila;
			columna_jugador = nueva_columna;
		}
	}

	private boolean detonar_match(int nueva_fila, 
								  int nueva_columna, 
								  int matches_verticales, 
								  int matches_horizontales, 
								  List<Entidad> entidades_en_match)
	{

		boolean hubo_match = true;
		Entidad nuevo_potenciador = null;
		Entidad entidad_movida = entidades[nueva_fila][nueva_columna];

		//hubo match de 5		
		if (matches_horizontales >= 1 && matches_verticales >= 1) {
			nuevo_potenciador = new Envuelto(nueva_fila, nueva_columna, entidad_movida.get_color());
		}

		//hubo match de 4
		else if(matches_verticales >=2){	  
			nuevo_potenciador = new RayadoHorizontal(nueva_fila, nueva_columna, entidad_movida.get_color()); 
		}
		else if(matches_horizontales >=2){	   
			nuevo_potenciador = new RayadoVertical(nueva_fila, nueva_columna, entidad_movida.get_color()); 
		}

		//no hubo match
		else if (matches_horizontales == 0 && matches_verticales == 0) {
			hubo_match = false;
		} 


		if(hubo_match){
			for (Entidad entidad : entidades_en_match){ 
				entidad.detonar();
			}

			if(nuevo_potenciador!=null){
				nuevo_potenciador.ocultar();
				agregar_nueva_entidad(nuevo_potenciador);
			}

			borrar_detonados();
			imprimirTablero();
			revisar_nuevos_matches();
		}
		return hubo_match;
	}

	private void revisar_nuevos_matches() {
		for(int f=0;f<filas;f++){
			for(int c=0;c<columnas;c++){
				revisar_detonar_match(f, c);
			}
		}
	}

	private void borrar_detonados(){
		for(int f=0;f<filas;f++){
			for(int c=0;c<columnas;c++){
				if(entidades[f][c].esta_detonada())
				entidades[f][c].bajar_caramelos();
			}
		}
	}

	public void reubicar(Entidad e) {
		int nueva_fila = e.get_fila();
		int nueva_columna = e.get_columna();
		entidades[nueva_fila][nueva_columna] = e;
	}

	private boolean en_rango(int nueva_fila, int nueva_columna) {
		return ((0 <= nueva_fila) && (nueva_fila < filas) && (0 <= nueva_columna) && (nueva_columna < columnas));
	}

	private void intercambiar(int fila_destino, int columna_destino) {
		int fila_origen = fila_jugador;
		int columna_origen = columna_jugador;
		Entidad entidad_origen, entidad_destino;
		boolean huboMatch;

		if (en_rango(fila_destino, columna_destino)) {	

			entidad_origen = entidades[fila_origen][columna_origen];
			entidad_destino = entidades[fila_destino][columna_destino];

			if (entidades[fila_origen][columna_origen].es_posible_intercambiar( entidades[fila_destino][columna_destino] )) {

				cambiar_posicion_jugador(fila_destino, columna_destino);
				entidad_origen.intercambiar(entidad_destino);

				if (entidades[fila_origen][columna_origen].detona_de_a_dos_con(entidades[fila_destino][columna_destino])) 
				{
					Entidad e1 = entidades[fila_origen][columna_origen];
					Entidad e2 = entidades[fila_destino][columna_destino];
					e1.detonar();
					e2.detonar();
					borrar_detonados();
					revisar_nuevos_matches();
				}
				else {
					huboMatch = revisar_detonar_match(fila_destino, columna_destino) | revisar_detonar_match(fila_origen, columna_origen);
					if(!huboMatch){
						cambiar_posicion_jugador(fila_origen, columna_origen);
						entidad_origen.intercambiar(entidad_destino);
					}else{

						mi_juego.restar_movimiento();
						if(completado){
							mi_juego.siguienteNivel();
						}
					}		
				}
			}
		}
	}

	protected void cambiar_posicion_jugador(int nueva_fila, int nueva_columna) {
		fila_jugador = nueva_fila;
		columna_jugador = nueva_columna;
	}

	public void imprimirTablero(){
		for(int f=0;f<filas;f++){
			for(int c=0; c<columnas; c++){
				System.out.print(entidades[f][c].get_color()+"  ");
			}
			System.out.println();
		}
		System.out.println();
	}

} */ //Fin clase tablero