package Entidades;


import java.util.LinkedList;

import Logica.Tablero;
/**
 * Modela el comportamiento de los Potenciadores.
 *
 */
public class Envuelto extends Potenciador {

	public Envuelto(Tablero tablero, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "/imagenes/potenciadores/envueltos/", true);	
		puntajeEntidad=50;
	}
	public Envuelto(Tablero tablero, int fila, int columna, int color, boolean visible){
		super(tablero, fila, columna, color, "/imagenes/potenciadores/envueltos/", visible);
	}

	public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades){
		LinkedList<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
				if(tablero.en_rango(j, i)){
					detonados_por_efecto.add(entidades[i][j]);
				}
            }
        }
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

}

