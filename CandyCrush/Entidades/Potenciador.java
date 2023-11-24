package Entidades;


import java.util.HashSet;

import Logica.Tablero;

public abstract class Potenciador extends Caramelo {
	
	protected Potenciador(Tablero tablero, int fila, int columna, int color, String path_imagenes, boolean visible) {
		super(tablero, fila, columna, color, path_imagenes, visible);
	}

	//Operaciones interfaz detonable
	@Override
	public boolean detona_de_a_dos(Entidad entidad) {
		return entidad.detona_de_a_dos_con(this);
	}

	@Override
	public boolean detona_de_a_dos_con(Potenciador potenciador){
		return true;
	}

}