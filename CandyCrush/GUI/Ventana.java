package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Entidades.Caramelo;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Entidades.Glaseado;
import Logica.Juego;
import Logica.Nivel;
import Logica.Tiempo;
import temas.Tema;
import ManejadorAnimaciones.CentralAnimaciones;


public class Ventana extends JFrame implements VentanaAnimable, VentanaJuego, VentanaNotificable {
	
	protected Juego juego;
	protected Nivel nivel;
	protected Tiempo timer;
	protected Tema tema;

	protected int filas;
	protected int columnas;
	protected int animaciones_pendientes;

	protected int cantidad_caramelos_objetivo;
	protected int color_caramelo_objetivo;
	protected int cantidad_glaseados_objetivos;

	protected int vidas;
	protected int movimientos;
	protected int size_label = 90;
	
	
	protected boolean obj1;
	protected boolean obj2;

	protected boolean iniciador_tiempo=false;
	public boolean bloquear_teclado;
	

	protected JPanel panel_principal;
	protected JPanel panel_fondo;
	protected JPanel panel_objetivos;
	protected JPanel panel_puntaje;
	protected JPanel panel_reglas;
	protected JPanel panel_puntajes;
	
	protected ImageIcon fondo,vida;
    
	protected JLabel lbl_fondo;
	protected JLabel lbl_puntaje;
	protected JLabel lbl_movimientos;
	protected JLabel lbl_obj1;
	protected JLabel lbl_obj2;
	protected JLabel lbl_reglas;

	protected JButton botonReinicio;

	
	protected CentralAnimaciones central_animaciones;

	public Ventana (Juego j, Nivel nivel, Tema tema) {
		juego = j;
		this.nivel=nivel;
		this.tema = tema;
		
		filas = juego.get_filas();
		columnas = juego.get_columnas();
		vidas = juego.get_vidas();
		animaciones_pendientes = 0;
		bloquear_teclado=false;
		
		this.central_animaciones = new CentralAnimaciones(this);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				cierreDeJugo();
			}
		});
		
		configurarObjetivos();
		
	} 

	private void cierreDeJugo(){
		juego.dejarDeContarPuntaje();
	}


	//getters
	
	public int get_size_label() {
		return size_label;
	}

	public void inicializar() {
		if(vidas==0){
			juego.dejarDeContarPuntaje();
			mostrarMensajeFinDelJuego();
		}

		definirFrame();
		establecerImagenDeFondo();
		definirPanelFondo();
		definirPanelTablero();
		definirPanelObjetivos();
		definirPanelPuntaje();
		definirPanelReglas();
		definirPanelPuntajes();
		definirBotonReinicio();
		cargarPanelReglas();
		cargarPanelPuntajes(juego.obtenerPuntuaciones());
		cargarPanelObjetivos();
		cargarPanelFondo();
		cargarFrame();

		  KeyListener miOyenteDeTeclado =   (new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()) {
					case KeyEvent.VK_LEFT: 	{ if (!bloquear_teclado) juego.mover_jugador(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_RIGHT: { if (!bloquear_teclado) juego.mover_jugador(Juego.DERECHA); break; }
					case KeyEvent.VK_UP: 	{ if (!bloquear_teclado) juego.mover_jugador(Juego.ARRIBA);break; }
					case KeyEvent.VK_DOWN: 	{ if (!bloquear_teclado) juego.mover_jugador(Juego.ABAJO); break; }
					case KeyEvent.VK_W:		{ if (!bloquear_teclado) juego.intercambiar_entidades(Juego.ARRIBA); break; }
					case KeyEvent.VK_S:		{ if (!bloquear_teclado) juego.intercambiar_entidades(Juego.ABAJO); break; }
					case KeyEvent.VK_A:		{ if (!bloquear_teclado) juego.intercambiar_entidades(Juego.IZQUIERDA); break; }
					case KeyEvent.VK_D:		{ if (!bloquear_teclado) juego.intercambiar_entidades(Juego.DERECHA); break; } 
				}
			}
		});
		panel_principal.addKeyListener(miOyenteDeTeclado);
		panel_principal.setFocusable(true);
	}


	public void configurarObjetivos(){
		//obtiene la cantidad de caramelos que el jugador debe detonar para pasar de nivel
		cantidad_caramelos_objetivo=nivel.get_cantidad_caramelos_objetivo();

		//puede que no tenga que explotar caramelos, asi que verifica que la cantidad sea distinta de 0
		if(cantidad_caramelos_objetivo!=0){
			char color = nivel.get_color_caramelo_objetivo();

			switch(color) {
				case 'G': {
					color_caramelo_objetivo = 1;
					break;
				} 
				case 'O': {
					color_caramelo_objetivo = 2;
					break;
				} 
				case 'Y': {
					color_caramelo_objetivo = 3;
					break;
				} 
				case 'R': {
					color_caramelo_objetivo = 4;
					break;
				} 
				case 'B': {
					color_caramelo_objetivo = 5;
					break;
				} 
				case 'P': {
					color_caramelo_objetivo = 6;
					break;
				} 
			}
		}

		cantidad_glaseados_objetivos = nivel.get_cantidad_glaseados_objetivo();

		timer = nivel.getTiempo();

		if( timer.getTiempoRestante()!=0){
			iniciador_tiempo = true;
		}

		movimientos = nivel.getMovimientos();
	} 



	//Definicion componentes graficos
	
	public void definirFrame(){
		setResizable(true);	//TODO: cambiar despues de usar el debugger
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
	}
	
	public void establecerImagenDeFondo(){
		fondo = new ImageIcon(this.getClass().getResource(tema.get_path_fondo()));
	
		// Escala la imagen para que se ajuste al tamaño del contenedor
		Image image = fondo.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		fondo = new ImageIcon(image);
	
		lbl_fondo = new JLabel(fondo);
		lbl_fondo.setOpaque(true);
		lbl_fondo.setBounds(0, 0, getWidth(), getHeight());
	}

	public void definirPanelFondo(){
		panel_fondo = new JPanel();
        panel_fondo.setLayout(new BorderLayout());
		panel_fondo.setOpaque(true);
		panel_fondo.setBounds(350,0,getWidth()/2, getHeight());
        panel_fondo.setBackground(new Color(20, 20, 20, 100)); 
		Border roundedBorder = new LineBorder(new Color(255, 255, 255, 20), 4, true);
		Border shadowBorder = new EmptyBorder(5, 8, 5, 5);
		Border compoundBorder = new CompoundBorder(roundedBorder, shadowBorder);
		panel_fondo.setBorder(compoundBorder);
	}

	public void definirPanelTablero(){
		panel_principal= new JPanel();
        panel_principal.setBounds(40,150,size_label * filas, size_label * columnas);
		panel_principal.setLayout(null);
        panel_principal.setBackground(Color.WHITE);
	}

	public void definirPanelPuntaje(){
		panel_puntaje = new JPanel();
        panel_puntaje.setBounds(0,0,250,25);
		lbl_puntaje = new JLabel();
		lbl_puntaje.setText("Vas "+juego.getPuntajeActual()+ " puntos");
		panel_puntaje.add(lbl_puntaje);
	}

	public void definirPanelReglas(){
		panel_reglas=new JPanel();
		panel_reglas.setBounds(1100,0,250,70);
		panel_reglas.setLayout(new BoxLayout(panel_reglas, BoxLayout.Y_AXIS));
		
		JLabel titulo_panel_reglas= new JLabel();
		titulo_panel_reglas.setText("¡REGLAS ACTIVADAS!");
		panel_reglas.add(titulo_panel_reglas);
	}

	public void definirPanelPuntajes(){
		panel_puntajes=new JPanel();
		panel_puntajes.setBounds(1100,150,250,100);
		panel_puntajes.setLayout(new BoxLayout(panel_puntajes, BoxLayout.Y_AXIS));
		
		JLabel titulo_panel_puntajes= new JLabel();
		titulo_panel_puntajes.setText("¡Top 5 jugadores!");
		panel_puntajes.add(titulo_panel_puntajes);
	}

	public void definirBotonReinicio(){
		botonReinicio= new JButton();
		botonReinicio.setText("Reiniciar (esta acción te quitará una vida)");
		botonReinicio.setBounds(1050, 550,300,30 );
		botonReinicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				reiniciar();
            }
        });
	}

	public void definirPanelObjetivos(){
		panel_objetivos = new JPanel();
        panel_objetivos.setBounds(12,-5,panel_fondo.getWidth()-50, 150);
		panel_objetivos.setLayout(new GridLayout(2,1));
        panel_objetivos.setBackground(new Color(255, 255, 255, 128));
	}

	public void cargarPanelReglas(){
		LinkedList<String> lista_de_reglas= nivel.get_reglas();
		for(String s: lista_de_reglas){
			JLabel regla_activada = new JLabel();
			regla_activada.setText(s);
			panel_reglas.add(regla_activada);
		}
	}

	public void cargarPanelPuntajes(List<String> listaPuntuaciones){
		for(String s: listaPuntuaciones){
			JLabel puntaje = new JLabel();
			puntaje.setText(s);
			panel_puntajes.add(puntaje);
		}
	}

	//Cargar componentes graficos

	public void cargarPanelFondo(){
		JLayeredPane layeredPane2 = new JLayeredPane();
		layeredPane2.setBounds(350,0,getWidth()/2, getHeight());
		layeredPane2.add(panel_objetivos, Integer.valueOf(0));
		layeredPane2.add(panel_principal,Integer.valueOf(1));
		panel_fondo.add(layeredPane2,BorderLayout.CENTER);
	}

	public void cargarFrame(){
		JLayeredPane layeredPane1 = new JLayeredPane();
		layeredPane1.setBounds(0, 0, getWidth(), getHeight());
		layeredPane1.add(lbl_fondo, JLayeredPane.DEFAULT_LAYER);
		layeredPane1.add(panel_fondo, JLayeredPane.PALETTE_LAYER);
		add(panel_puntaje);
		add(panel_puntajes);
		add(panel_reglas);
		add(botonReinicio);
		add(layeredPane1);
	}

	public void cargarPanelObjetivos(){
		JPanel panelSuperior = new JPanel(new GridLayout(1,3));
		JPanel panelInferior = new JPanel(new GridLayout(1,3));

		//Vida
		//-- crea un panelVidas
		JPanel panelVida = new JPanel(new BorderLayout());
		panelVida.setBorder(new RoundBorder(10));
		//--carga su correspondiente imagen
		vida = new ImageIcon(this.getClass().getResource(tema.get_path_icono_vidas(vidas)));
		Image imgEscalada = vida.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		Icon iconoEscalado = new ImageIcon(imgEscalada);
		//--crea la label donde se colocara la imagen
		JLabel vidas= new JLabel();
		//--carga y define propiedades de la label
		vidas.setIcon(iconoEscalado);
		//-- carga los elementos a su panel
		panelVida.add(vidas, BorderLayout.CENTER);
		//fin panel vidas

		//Movimientos
		//-- crea un panelMovimientos
		JPanel panelMovimientos = new JPanel(new BorderLayout());
		panelMovimientos.setBorder(new RoundBorder(10));
		//-- crea y configura una label que contendra la cantidad de movimientos disponibles
		lbl_movimientos= new JLabel();
		lbl_movimientos.setBounds(50,50,3,3);
		lbl_movimientos.setText("Movimientos:" +movimientos);
		//-- carga los elementos a su panel
		panelMovimientos.add(lbl_movimientos, BorderLayout.CENTER);
		//fin panelMovimientos

		//Temporizador
		//-- configura propiedades del temporizador
		JPanel panelTemporizador = new JPanel(new BorderLayout());
		panelTemporizador.setBorder(new RoundBorder(10));
		if(iniciador_tiempo==true){
			timer.setBounds(30,20,10,3);
			timer.iniciarTiempo();
			panelTemporizador.add(timer);
		}
		//fin panel temporizador
	
		//Objetivo de tipo caramelo
		JPanel panelObjetivoCaramelo = new JPanel(new BorderLayout());
		panelObjetivoCaramelo.setBorder(new RoundBorder(10));
		if(cantidad_caramelos_objetivo!=0){
			//-- busca y configura la imagen del caramelo que corresponda
			Entidad e = new Caramelo(juego.getTablero(),-1, -1, color_caramelo_objetivo);
			ImageIcon caramelo = new ImageIcon(this.getClass().getResource(tema.get_path_imagen(e)));
			Image img = caramelo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			Icon icono = new ImageIcon(img);
			//-- crea una label para cargar la imagen y la configura
			lbl_obj1= new JLabel();
			lbl_obj1.setBounds(50,30,150,150);
			lbl_obj1.setIcon(icono);
			lbl_obj1.setText("Restantes:" + cantidad_caramelos_objetivo);
			//agrega los elementos a su panel
			panelObjetivoCaramelo.add(lbl_obj1);
		}
		//fin panel objetivo caramelo

		//Objetivo de tipo glaseado/gelatina
		JPanel panelObjetivoEspecial = new JPanel(new BorderLayout());
		panelObjetivoEspecial.setBorder(new RoundBorder(10));
		if(cantidad_glaseados_objetivos!=0){
			//-- busca y configura la imagen del caramelo que corresponda
			Entidad e = new Glaseado(juego.getTablero(),-1, -1, Entidades.Color.BLANCO);
			ImageIcon caramelo = new ImageIcon(this.getClass().getResource(tema.get_path_imagen(e)));
			Image img = caramelo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			Icon icono = new ImageIcon(img);
			//-- crea una label para cargar la imagen y la configura
			lbl_obj2= new JLabel();
			lbl_obj2.setBounds(50,30,150,150);
			lbl_obj2.setIcon(icono);
			lbl_obj2.setText("Glaseados restantes:" + cantidad_glaseados_objetivos);
			//agrega los elementos a su panel
			panelObjetivoEspecial.add(lbl_obj2);
		}
		//fin panel objetivo especial

		//--agrega a los paneles superior e inferior los elementos correspondiente
		panelSuperior.add(panelVida);
		panelSuperior.add(panelMovimientos);
		panelSuperior.add(panelTemporizador);
		
		panelInferior.add(panelObjetivoCaramelo);
		panelInferior.add(panelObjetivoEspecial);

		//-- carga los paneles superior e inferior a panel objetivos
		panel_objetivos.add(panelSuperior);
		panel_objetivos.add(panelInferior);
	}

	//Control objetivos
	//-- caramelos_detonados son caramelos comunes, entidades_detonadas se refiere a glaseados
	public void actualizaObjetivos(HashMap<Integer,Integer> entidades_detonadas){
		for(Integer color: entidades_detonadas.keySet()){
			if(color_caramelo_objetivo==color){
				if(cantidad_caramelos_objetivo!=0){
					cantidad_caramelos_objetivo=cantidad_caramelos_objetivo-entidades_detonadas.get(color);
					if(cantidad_caramelos_objetivo<0){
						cantidad_caramelos_objetivo=0;
					}
					lbl_obj1.setText("Caramelos restantes:" + cantidad_caramelos_objetivo);
				}
			}
			if(color==Integer.valueOf(8)){
				if(cantidad_glaseados_objetivos!=0){	
					cantidad_glaseados_objetivos=cantidad_glaseados_objetivos-entidades_detonadas.get(color);
					if(cantidad_glaseados_objetivos<0){
						cantidad_glaseados_objetivos=0;
					}
					if(lbl_obj2!=null){
						lbl_obj2.setText("Glaseados restantes:" + cantidad_glaseados_objetivos);
					}
				}
			}
		}
		if (cantidad_caramelos_objetivo <= 0 && cantidad_glaseados_objetivos<=0) {
				transicionar();
			}
	}

	public void actualizarPuntaje(int puntaje){
		lbl_puntaje.setText("Vas "+puntaje+ " puntos. ¡Muy bien! ;)");
	}

	public void notificar_tiempo_agotado(){
		ocultar();
		System.out.println("Llama al metodo");
		mostrarMensajeDeAviso("tiempo");
	}

	public void restar_movimiento(){
		if(movimientos!=0){
			movimientos--;
			lbl_movimientos.setText("Movimientos: " + movimientos);
		}
		if(movimientos==0){
			ocultar();
			mostrarMensajeDeAviso("movimientos");
		}
	}


	//Agregar entidades graficas 
	public EntidadGrafica agregar_entidad(EntidadLogica e) {
		Celda celda = new Celda(this, e, size_label, tema, e.get_visibilidad());
		panel_principal.add(celda);
		return celda;
	}
	
	//Notificaciones
	
	public void notificarse_animacion_en_progreso() {
		synchronized(this){
			animaciones_pendientes ++;
			bloquear_teclado = true;
		}
	}
	
	public void notificarse_animacion_finalizada() {
		synchronized(this){
			animaciones_pendientes --;
			bloquear_teclado = animaciones_pendientes > 0;
		}
	}

	@Override
	public void ocultar() {
		this.setVisible(false);
	}

	
	public void reiniciar() {
		juego.resetear_nivel();
	}


	@Override
	public void transicionar() {
		JOptionPane.showMessageDialog(null,"¡Felicitaciones! Pasaste de nivel :D");
		if(timer.getTiempoRestante()!=0){
			timer.parar();
		}
		juego.siguienteNivel();
	}


	public void animar_intercambio(Celda celda) {
		central_animaciones.animar_intercambio(celda);
	}
	
	public void animar_cambio_foco(Celda celda) {
		central_animaciones.animar_cambio_foco(celda);
	}
	
	public void animar_detonacion(Celda celda) {
		central_animaciones.animar_detonacion(celda);
	}
	
	public void animar_caida(Celda celda) {
		central_animaciones.animar_caida(celda);
	}
	
	public void animar_cambio_visibilidad(Celda celda) {
		central_animaciones.animar_cambio_visibilidad(celda);
	}

	public void animar_cambio_estado(Celda celda, int color) {
		central_animaciones.animar_cambio_estado_tdp2(celda, color);
	}
	
	public void eliminar_celda(Celda celda) {
		panel_principal.remove(celda);
		panel_principal.repaint();
	}


	@Override
	public void mostrar() {
		this.setVisible(true);
	}
	

	protected void mostrarMensajeDeAviso(String mensaje_situacion){
        UIManager.put("OptionPane.background", Color.white); 

        // Crear el panel 
        JPanel panel = new JPanel(new BorderLayout()); 
		
		System.out.println("Crea panel");

        // Crear el mensaje y configurar su apariencia
        JLabel mensaje = new JLabel("<html><font color='black'>¡Te quedaste sin "+ mensaje_situacion +"!</font></html>");
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mensaje, BorderLayout.CENTER);

        // Crear botones personalizados
        JButton reintentarButton = new JButton("Reintentar");
        JButton salirButton = new JButton("Salir");

        // Agregar acciones a los botones
        reintentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				reiniciar();
				((JDialog) SwingUtilities.getRoot((Component) e.getSource())).dispose();//Cierra el mensaje
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el programa
				juego.dejarDeContarPuntaje();
                System.exit(0);
            }
        });

        // Agregar botones al panel
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 fila, 2 columnas
        botonesPanel.add(reintentarButton);
        botonesPanel.add(salirButton);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Crear el JOptionPane personalizado
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        // Configurar el título de la ventana de diálogo
        JDialog dialog = optionPane.createDialog(":(");

        // Mostrar la ventana de diálogo
        dialog.setVisible(true);
	}


	public void mostrarMensajeFinDelJuego(){
		// Configurar el aspecto de la ventana de diálogo
        UIManager.put("OptionPane.messageForeground", Color.BLACK);

        // Crear el panel personalizado con tonos rosas
        JPanel panel = new JPanel(new BorderLayout());

        // Crear el mensaje y configurar su apariencia
        JLabel mensaje = new JLabel("<html><font color='black'>¡Alpiste, perdiste!</font></html>");
        mensaje.setHorizontalAlignment(JLabel.CENTER);
        panel.add(mensaje, BorderLayout.CENTER);

        // Crear botones personalizados
        JButton salirButton = new JButton("Salir");

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el programa
                System.exit(0);
            }
        });

        // Agregar botones al panel
        JPanel botonesPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 fila, 2 columnas
        botonesPanel.add(salirButton);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        // Crear el JOptionPane personalizado
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        // Configurar el título de la ventana de diálogo
        JDialog dialog = optionPane.createDialog(":(");

        // Mostrar la ventana de diálogo
        dialog.setVisible(true);
	}


	@Override
	public void resetear(Juego juego, int filas, int columnas) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'resetear'");
	}



	//Clase interna para aplicar borde redondeado a los paneles
	public class RoundBorder extends AbstractBorder {
    	private int radius;

    	public RoundBorder(int radius) {
        	this.radius = radius;
    	}

    	@Override
    	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        	Graphics2D g2d = (Graphics2D) g.create();
        	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        	g2d.setColor(Color.BLACK); // Puedes cambiar el color del borde según tus preferencias
        	g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        	g2d.dispose();
    	}

    	@Override
    	public Insets getBorderInsets(Component c) {
        	return new Insets(radius, radius, radius, radius);
    	}

    	@Override
    	public Insets getBorderInsets(Component c, Insets insets) {
        	insets.left = insets.top = insets.right = insets.bottom = radius;
        	return insets;
   		 
		}
	}

	static class TranslucentPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Configurar la transparencia
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Ajusta el valor de transparencia (0.0f a 1.0f)
            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }


} //Fin clase Ventana
