package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Entidades.Caramelo;
import Entidades.CarameloTdp1;
import Entidades.CarameloTdp2;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Entidades.Color;

/**
 * Simula el comportamiento real de un Generador de Nivel, cableando la generación de entidades de forma manual.
 * Se espera que la clase permita parsear el contenido de un archivo de texto, desde donde se generará efectivamente el nivel.
 * En esta versión, se permiten:
 * - Caramelos de todos los colores.
 * - Potenciadores de color Ladrillo
 * - Glaseados de color transparanete.
 */
public class GeneradorNivel {

	public static void leer_archivo_texto(int nivel, Tablero tablero, String [] objetivos, String[] reglas) { 
		try {
			//Ruta del archivo .txt
			String filePath = "CandyCrush/niveles/nivel_" + nivel + ".txt";

			//Crear un objeto Scanner para leer el archivo
			Scanner scanner = new Scanner(new File(filePath));

			//Leer las dimensiones de la matriz
			int filas = scanner.nextInt();
			int columnas = scanner.nextInt();
			tablero.resetar_tablero(filas, columnas);

			//Leer la cantidad de movimientos
			objetivos[3] = scanner.next().substring(1);
			int movimientos = Integer.parseInt(objetivos[3]);
			tablero.recibirMovimientos(movimientos);

			//Leer el tiempo
			String tiempo = scanner.next();
			objetivos[0] = tiempo;

			//Leer los objetivos a cumplir 
			String obje_nivel = scanner.next();
			if (obje_nivel.contains("-")) {
				String[] parts = obje_nivel.split("-");
				objetivos[1] = parts[0]; 
				objetivos[2] = parts[1]; 
			} else {
				if (obje_nivel.contains("Z") || (obje_nivel.contains("J"))) {
				objetivos[2] = obje_nivel; 
				objetivos[1] = null;
				} else {
					objetivos[1] = obje_nivel;
					objetivos[2] = null;
				}
			}	
			
			//Leer las reglas a aplicar
			String regla_nivel = scanner.next();
			
			if (regla_nivel.contains("-")) {
				String[] parts = regla_nivel.split("-");
				for (int i=0; i < parts.length; i++) {
					reglas[i] = parts[i];
				}
			} else {
				reglas[0] = regla_nivel;
			}
			tablero.recibirReglas(reglas);

			//Leer la distribucion de los caramelos en la matrix
			for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
					String linea = scanner.next();
                    char caramelo = linea.charAt(0);
					switch(caramelo) {
						case 'Y': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.AMARILLO));
							break;
						}
						case 'R': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.ROJO));
							break;
						}
						case 'G': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.VERDE));
							break;
						}
						case 'B': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.AZUL));
							break;
						}
						case 'O': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.LADRILLO));
							break;
						}
						case 'P': {
							tablero.agregar_entidad(new Caramelo(tablero,i,j, Color.VIOLETA));
							break;
						}
						case 'Z': {
							tablero.agregar_entidad(new Glaseado(tablero,i,j, Color.BLANCO));
							break;
						}
						case 'C': {
							char color = linea.charAt(1); 
							switch (color) {
								case 'Y': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.AMARILLO));
									break;
								}
								case 'R': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.ROJO));
									break;
								} 
								case 'G': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.VERDE));
									break;
								}
								case 'B': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.AZUL));
									break;
								}
								case 'O': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.LADRILLO));
									break;
								}
								case 'P': {
									tablero.agregar_entidad(new CarameloTdp1(tablero,i,j, Color.VIOLETA));
									break;
								}
							}
							break;
						}
						case 'J': {
							char gelatina = linea.charAt(1);
							Caramelo interno;
							switch(gelatina) {
								case 'Y': {
									interno = new Caramelo(tablero,i, j, Color.AMARILLO);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
								case 'R': {
									interno = new Caramelo(tablero,i, j, Color.ROJO);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
								case 'G': {
									interno = new Caramelo(tablero,i, j, Color.VERDE);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
								case 'B': {
									interno = new Caramelo(tablero,i, j, Color.AZUL);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
								case 'O': {
									interno = new Caramelo(tablero,i, j, Color.LADRILLO);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
								case 'P': {
									interno = new Caramelo(tablero,i, j, Color.VIOLETA);
									tablero.agregar_entidad_y_asociada(new Gelatina(tablero,interno,i,j, Color.TRANSPARENTE));
									break;
								}
							}
							break;
						}
						case 'M': {
							tablero.agregar_entidad(new CarameloTdp2(tablero, i, j, Color.NEGRO));
						}
					}
                }
			}
			//Cerrar el scanner
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se pudo encontrar el archivo");
			e.printStackTrace();
		}
	}


	public static Nivel cargar_nivel_y_tablero(int numero_nivel, Tablero tablero, String [] objetivos, String[] reglas) {
		leer_archivo_texto(numero_nivel, tablero, objetivos, reglas);
		Nivel nivel= new Nivel(2, 2);
		nivel.recibirReglas(reglas);
		return nivel;
	}
	
}