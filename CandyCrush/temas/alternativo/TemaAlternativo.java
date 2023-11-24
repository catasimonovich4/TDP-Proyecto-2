package temas.alternativo;

import java.awt.Color;

import Entidades.EntidadLogica;
import temas.Tema;

public class TemaAlternativo implements Tema{


    @Override
    public String get_path_imagen(EntidadLogica e) {
        return "/temas/alternativo/"+e.get_imagen_representativa();
    }

    @Override
    public String get_path_fondo() {
        return "/temas/alternativo/imagenes/fondo/fondo2.png";
    }

    @Override
    public String get_path_icono_vidas(int cant) {
        return "/temas/alternativo/imagenes/vidas/" + cant + ".png";    
    }

    @Override
    public Color get_color_tablero() {
        return Color.BLACK;   
    }
    
}
