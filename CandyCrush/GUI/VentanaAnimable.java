package GUI;

/**
 * Define las operaciones esperables por sobre una ventana que permite animar los cambios de sus entidades.
 * Un ventana animable podrá llevar adelante la animación de movimiento o de cambio de estado, de una entidad.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 */
public interface VentanaAnimable {

	public void animar_intercambio(Celda celda);
	
	public void animar_cambio_foco(Celda celda);
	
	public void animar_detonacion(Celda celda);
	
	public void animar_caida(Celda celda);
	
	public void animar_cambio_visibilidad(Celda celda);

	public void animar_cambio_estado(Celda celda, int color);
	
	public void eliminar_celda(Celda celda);
}
