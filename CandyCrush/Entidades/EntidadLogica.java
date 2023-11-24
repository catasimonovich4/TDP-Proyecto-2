package Entidades;

/**
 * Define los mensaje posibles de solicitar por sobre las entidades lógicas de la aplicación.
 */
public interface EntidadLogica {
	
	public int get_fila();

	public int get_columna();
	
	public boolean get_visibilidad();

	public String get_imagen_representativa();
}
