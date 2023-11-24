package temas;
import java.awt.Color;

public interface Tema extends TemaCelda{

    public static final int TEMA_CLASICO = 0;
    public static final int TEMA_ALTERNATIVO = 1;

    public String get_path_fondo();
    public String get_path_icono_vidas(int cant);
    public Color get_color_tablero();
    
}
