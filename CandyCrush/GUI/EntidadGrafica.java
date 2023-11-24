package GUI;

import temas.TemaCelda;

public interface EntidadGrafica {
	public TemaCelda get_tema();

	public void notificarse_intercambio();

	public void notificarse_cambio_foco();
	
	public void notificarse_detonacion();
	
	public void notificarse_caida();
	
	public void notificarse_cambio_visibilidad();

	public void reposicionarse_fuera_de_tablero();

	public void reposicionarse_al_frente_en_eje_z();

	public void notificarse_cambio_estado_carameloTdp2(int color);
}
