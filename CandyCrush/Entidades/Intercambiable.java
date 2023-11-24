package Entidades;

/**
 * Define los mensaje posibles de solicitar por sobre las entidades de la aplicación, 
 * respecto de su capacidad de intercambiarse con otras entidades en el tablero.

 *
 */
public interface Intercambiable {
	
	public boolean es_posible_intercambiar(Entidad entidad);
	
	public boolean puede_recibir(Caramelo caramelo);

	public boolean puede_recibir(Glaseado glaseado);

	public boolean puede_recibir(Potenciador potenciador);

	public boolean puede_recibir(Gelatina gelatina);
	
	public void intercambiar(Entidad entidad);
	
	public void intercambiar_con(Caramelo caramelo);

	public void intercambiar_con(CarameloTdp2 carameloTdp2);

	public void intercambiar_con(Glaseado glaseado);

	public void intercambiar_con(Potenciador potenciador);

	public void intercambiar_con(Gelatina gelatina);
		
}
