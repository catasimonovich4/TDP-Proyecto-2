package Animadores;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import GUI.Celda;
import ManejadorAnimaciones.CentralAnimaciones;

public class AnimadorEstado implements Animador {
    
	protected CentralAnimaciones manager;
	protected Celda celda_animada;
	protected int prioridad;
	protected String path_imagen_estado;

	public AnimadorEstado(CentralAnimaciones manager, Celda celda, int color) {
		this.manager = manager;
		this.celda_animada = celda;
		this.prioridad = PrioridadAnimaciones.PRIORIDAD_ESTADO;
		path_imagen_estado = cargar_imagen(celda.get_imagen_representativa(), color);
	}
	
	public Celda get_celda_asociada() {
		return celda_animada;
	}
	
	public int get_prioridad() {
		return prioridad;
	}

	public void comenzar_animacion() {
		int size_label = celda_animada.get_size_label();
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource(path_imagen_estado));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(size_label, size_label, Image.SCALE_REPLICATE);
		Icon icono_escalado = new ImageIcon(imagen_escalada);
		celda_animada.setIcon(icono_escalado);
		manager.notificarse_finalizacion_animador(this);
	}

    private String cargar_imagen(String img_path, int color) {
		String[] path_parts = img_path.split("/");

		for (int i=0; i < path_parts.length; i++) {
			if (path_parts[i].equals("10.png") || path_parts[i].equals("3.png")) {
				path_parts[i] = Integer.toString(color) + ".png";
			} 
		}

		// Unir las partes actualizadas para obtener el nuevo path
        path_imagen_estado = String.join("/", path_parts);
        return path_imagen_estado;
    } 
}
