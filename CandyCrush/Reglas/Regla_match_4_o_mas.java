package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;
import Entidades.Potenciador;
import Entidades.PotenciadorHorizontal;
import Entidades.PotenciadorVertical;

public class Regla_match_4_o_mas extends ReglaAbstracta{

    protected boolean match_horizontal;
    protected boolean match_vertical;


    @Override
    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidad_movida) {
        inicializar_atributos_entidad_y_matriz(entidades, entidad_movida);
        EfectoRegla efecto_regla = new EfectoRegla();
        
        match_horizontal = cargar_match_horizontal_en_entidades_a_detonar(efecto_regla);
        match_vertical = cargar_match_vertical_en_entidades_a_detonar(efecto_regla);

        if(match_horizontal || match_vertical){
            generar_potenciador_y_agregar_a_incorporar(efecto_regla);
        }
        return efecto_regla;
    }


    @Override
    protected void generar_potenciador_y_agregar_a_incorporar(EfectoRegla efecto_regla){
        Potenciador a_incorporar;
        if(match_horizontal)
            a_incorporar = new PotenciadorVertical(null, 
                                                 fila_entidad_a_chequear,
                                                 columna_entidad_a_chequear,
                                                 color_entidad_a_chequear,
                                                 false);
        else
            a_incorporar = new PotenciadorHorizontal(null, 
                                                 fila_entidad_a_chequear,
                                                 columna_entidad_a_chequear,
                                                 color_entidad_a_chequear,
                                                 false);
        efecto_regla.agregar_entidad_a_incorporar(a_incorporar);
    }

    protected boolean cargar_match_horizontal_en_entidades_a_detonar(EfectoRegla efecto_regla){
        Set<Entidad> entidades_en_match = new HashSet<Entidad>();
        boolean hay_match_horizontal;
        cargar_match_a_izquierda(entidades_en_match);
        cargar_match_a_derecha(entidades_en_match);
        entidades_en_match.add(entidad_a_chequear_match);
        hay_match_horizontal = entidades_en_match.size() >= 4;
        if(hay_match_horizontal){
            agregar_entidades_a_detonar(entidades_en_match, efecto_regla);
        }
        return hay_match_horizontal;
    }

    protected void cargar_match_a_izquierda(Set<Entidad> set_a_cargarle_el_match){
        int columna_a_izquierda = columna_entidad_a_chequear;
        boolean hay_match = true;
        Entidad entidad_a_comparar;
        while(columna_a_izquierda > 0 && hay_match){
            columna_a_izquierda--;
            entidad_a_comparar = matriz_entidades[fila_entidad_a_chequear][columna_a_izquierda];
            hay_match = entidad_a_chequear_match.se_produce_match_con(entidad_a_comparar);
            if(hay_match){
                set_a_cargarle_el_match.add(entidad_a_comparar);
            }
        }
    }

    protected void cargar_match_a_derecha(Set<Entidad> set_a_cargarle_el_match){
        int columna_a_derecha = columna_entidad_a_chequear;
        boolean hay_match = true;
        Entidad entidad_a_comparar;
        while(columna_a_derecha < matriz_entidades.length-1 && hay_match){
            columna_a_derecha++;
            entidad_a_comparar = matriz_entidades[fila_entidad_a_chequear][columna_a_derecha];
            hay_match = entidad_a_chequear_match.se_produce_match_con(entidad_a_comparar);
            if(hay_match){
                set_a_cargarle_el_match.add(entidad_a_comparar);
            }
        }
    }
    
    protected boolean cargar_match_vertical_en_entidades_a_detonar(EfectoRegla efecto_regla){
        Set<Entidad> entidades_en_match = new HashSet<Entidad>();
        boolean hay_match_vertical;
        cargar_match_arriba(entidades_en_match);
        cargar_match_abajo(entidades_en_match);
        entidades_en_match.add(entidad_a_chequear_match);
        hay_match_vertical = entidades_en_match.size() >= 4;

        if(hay_match_vertical){
            agregar_entidades_a_detonar(entidades_en_match, efecto_regla);
        }
        return hay_match_vertical;
    }

    protected void cargar_match_arriba(Set<Entidad> set_a_cargarle_el_match){
        int fila_arriba = fila_entidad_a_chequear;
        boolean hay_match = true;
        Entidad entidad_a_comparar;
        while(fila_arriba > 0 && hay_match){
            fila_arriba--;
            entidad_a_comparar = matriz_entidades[fila_arriba][columna_entidad_a_chequear];
            hay_match = entidad_a_chequear_match.se_produce_match_con(entidad_a_comparar);
            if(hay_match){
                set_a_cargarle_el_match.add(entidad_a_comparar);
            }
        }
    }

    protected void cargar_match_abajo(Set<Entidad> set_a_cargarle_el_match){
        int fila_abajo = fila_entidad_a_chequear;
        boolean hay_match = true;
        Entidad entidad_a_comparar;
        while(fila_abajo < matriz_entidades[0].length-1 && hay_match){
            fila_abajo++;
            entidad_a_comparar = matriz_entidades[fila_abajo][columna_entidad_a_chequear];
            hay_match = entidad_a_chequear_match.se_produce_match_con(entidad_a_comparar);
            if(hay_match){
                set_a_cargarle_el_match.add(entidad_a_comparar);
            }
        }
    }
}
