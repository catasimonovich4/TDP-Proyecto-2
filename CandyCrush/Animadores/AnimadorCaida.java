package Animadores;

import GUI.Celda;
import ManejadorAnimaciones.CentralAnimaciones;

public class AnimadorCaida extends AnimadorMovimiento{

	public AnimadorCaida(CentralAnimaciones manager, int step, int delay, Celda celda) {
		super(manager, step, delay, celda);
		prioridad = PrioridadAnimaciones.PRIORIDAD_CAIDA; 	
		this.setName("Hilo animador de caida");	
	}	

	public void run() {
		int size_label = celda_animada.get_size_label();
		int pos_x_actual = celda_animada.getX();
		int pos_y_actual = celda_animada.getY();
		
		int paso_en_x = 0;
		int paso_en_y = 0;
		
		if (pos_x_actual != pos_x_destino) {
			paso_en_x = (pos_x_actual < pos_x_destino ? 1 : -1);
		}
		
		if (pos_y_actual != pos_y_destino) {
			paso_en_y = (pos_y_actual < pos_y_destino ? 1 : -1);
		}
		
		while( (pos_x_actual != pos_x_destino) || (pos_y_actual != pos_y_destino) ) {
			pos_x_actual += paso_en_x * step;
			pos_y_actual += paso_en_y * step;
			
			celda_animada.setBounds(pos_x_actual, pos_y_actual, size_label, size_label);
			
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		manager.notificarse_finalizacion_animador(this);
	}
}
