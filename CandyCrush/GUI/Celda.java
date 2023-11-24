package GUI;

import java.awt.Container;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Entidades.EntidadLogica;
import temas.TemaCelda;


public class Celda extends JLabel implements EntidadGrafica {
	
	protected VentanaAnimable ventana;
	protected EntidadLogica entidad_logica;
	protected TemaCelda tema;
	protected int size_label;

	public Celda(VentanaAnimable ventana_animable, EntidadLogica entidad_logica, int size_label, TemaCelda tema,  boolean visible) {
		super();

		this.ventana = ventana_animable;
		this.entidad_logica = entidad_logica;
		this.size_label = size_label;
		this.setVisible(visible);
		this.tema=tema;

		int ubicacion_eje_x = entidad_logica.get_columna() * size_label;
		int ubicacion_eje_y = entidad_logica.get_fila() * size_label;
		int alto = size_label;
		int ancho = size_label;
		
		setBounds(ubicacion_eje_x, ubicacion_eje_y, ancho, alto);
		fijar_imagen_escalada_para_celda(tema.get_path_imagen(entidad_logica));
	}


	public String get_imagen_representativa(){
		return tema.get_path_imagen(entidad_logica);
	}

	public TemaCelda get_tema() {
		return tema;
	}

	//Codigo fede
	
	public EntidadLogica get_entidad_logica() {
		return entidad_logica;
	}
	
	public int get_size_label() {
		return size_label;
	}
	
	public void eliminar_de_ventana() {
		ventana.eliminar_celda(this);
	}
	
	// Operaciones para Entidad Grafica (Celda <-- Entidad Lógica)
	
	public void notificarse_intercambio(){
		ventana.animar_intercambio(this);
	}
	
	public void notificarse_cambio_foco() {
		ventana.animar_cambio_foco(this);
	}
	
	public void notificarse_detonacion() {
		ventana.animar_detonacion(this);
	}
	
	public void notificarse_caida() {
		ventana.animar_caida(this);
	}
	
	public void notificarse_cambio_visibilidad() {
		ventana.animar_cambio_visibilidad(this);
	}

	public void notificarse_cambio_estado_carameloTdp2(int color) {
		ventana.animar_cambio_estado(this, color);
	}
	
	// Operaciones locales a celda
	
	protected void fijar_imagen_escalada_para_celda(String path_imagen) {
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource(path_imagen));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		setIcon(icono_imagen_escalado);
	}

	public void reposicionarse_fuera_de_tablero(){
		int ubicacion_eje_x = entidad_logica.get_columna() * size_label;	//TODO: ubicar donde corresponda para que se visualice la caida en el orden correspondiente
		int ubicacion_eje_y = -3 * size_label;
		int alto = size_label;
		int ancho = size_label;
		setBounds(ubicacion_eje_x, ubicacion_eje_y, ancho, alto);
	}

	public void reposicionarse_al_frente_en_eje_z(){
		Container padre_de_celda = this.getParent();
		padre_de_celda.setComponentZOrder(this,0);
	}
}
