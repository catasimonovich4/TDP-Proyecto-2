package Logica;

import java.util.LinkedList;

/**
 * Modela la clase nivel. Se espera que el mismo permita observar los objetivos esperados para el nivel.
 * También se espera que permita mantener cuenta de los objetivos cumplidos y no, y que permita que el juego pueda consultar si
 * los objetivos fueron cumplimentados, para transicionar de nivel.
 */
public class Nivel {
	
	protected int fila_inicial_jugador;
	protected int columna_inicial_jugador;

	protected int movimientos;
	protected int total_caramelos_objetivo;
	protected int total_entidades_objetivo_especial;

	protected String tipo_entidad;
	protected String objetivo1;
	protected String objetivo2;
	
	protected Tiempo temporizador;

	protected LinkedList<String> reglas_de_match;
	
	
	public Nivel(int f, int c) {
		fila_inicial_jugador = f;
		columna_inicial_jugador = c;
	}


	//getters

	public int get_fila_inicial_jugador() {
		return fila_inicial_jugador;
	}
	
	public int get_columna_inicial_jugador() {
		return columna_inicial_jugador;
	}
	
	public int getMovimientos(){
		return movimientos;
	}
	
	public int get_cantidad_caramelos_objetivo() {
		return total_caramelos_objetivo;
	}

	public int get_cantidad_glaseados_objetivo() {
		return total_entidades_objetivo_especial;
	}

	

	public String get_objetivo1() {
		return objetivo1;
	}
	
	public String get_objetivo2() {
		return objetivo2;
	}
	
	public Tiempo getTiempo(){
		return temporizador;
	}

	public char get_color_caramelo_objetivo(){
		char color_objetivo = objetivo1.charAt(0);
		return color_objetivo ;
	}

	public void recibirReglas(String[] reg) {
		String regla;
		reglas_de_match = new LinkedList<>();
		for (int i=0; i < reg.length; i++) {
			if (reg[i] != null) {
				regla = reg[i];
				switch (regla) {
					case "R3": {
						reglas_de_match.add("Match de 3");
						break;
					}
					case "R4": {
						reglas_de_match.add("Match de 4");
						break;
					}
					case "RT": {
						reglas_de_match.add("Match en fotma de T");
						break;
					}
					case "RL": {
						reglas_de_match.add("Match en forma de L");
						break;
					}
				}
			}
		}

	}

	public LinkedList<String> get_reglas(){
		return reglas_de_match;
	}


	//setters

	public void set_objetivos(String [] objetivos, Tiempo temporizador) {
		this.temporizador = temporizador;
		movimientos = Integer.parseInt(objetivos[3]);
		objetivo1 = objetivos[1];
		objetivo2 = objetivos[2];
		if(objetivo1!=null){
			total_caramelos_objetivo = Integer.parseInt(objetivo1.substring(1));
		}
		if(objetivo2!=null){
			//objetivo especial se refiere a glaseado o gelatina
			total_entidades_objetivo_especial = Integer.parseInt(objetivo2.substring(1));
		}
	}

}
