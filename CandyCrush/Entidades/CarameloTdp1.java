package Entidades;

import java.util.LinkedList;

import Logica.Tablero;

public class CarameloTdp1 extends Potenciador {

    public CarameloTdp1(Tablero tablero, int fila, int columna, int color) {
        super(tablero, fila, columna, color, "imagenes/caramelos_tdp/tdp1/", true);
	}

    @Override
    public boolean detona_de_a_dos_con(Caramelo caramelo) {
        return color == caramelo.get_color();
    }

    @Override
    public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades) {
        LinkedList<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		for(int i=0;i< tablero.get_columnas();i++){
			detonados_por_efecto.add(entidades[fila][i]);
		}
        for(int i=0;i< tablero.get_filas();i++){
			detonados_por_efecto.add(entidades[i][columna]);
		}
        return detonados_por_efecto;
    }
    
}
