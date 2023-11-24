package Reglas;

import Entidades.Entidad;
import Entidades.Potenciador;

public class Regla_match_3 extends ReglaAbstracta {

    @Override
    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidad_movida) {
        inicializar_atributos_entidad_y_matriz(entidades, entidad_movida);
        EfectoRegla efecto_regla = new EfectoRegla();
        boolean hubo_match;
        hubo_match = chequear_a_izquierda(efecto_regla);
        hubo_match = hubo_match || chequear_a_derecha(efecto_regla);
        hubo_match = hubo_match || chequear_lados(efecto_regla);
        hubo_match = hubo_match || chequear_arriba(efecto_regla);
        hubo_match = hubo_match || chequear_abajo(efecto_regla);
        hubo_match = hubo_match || chequear_el_de_arriba_y_el_de_abajo(efecto_regla);

        return efecto_regla;
    }

    protected boolean chequear_a_izquierda(EfectoRegla efecto_regla){
        Entidad entidad_a_izquierda_de_a_chequear;
        Entidad entidad_a_izquierda_de_a_chequear_a_2;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-2)){
            entidad_a_izquierda_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_izquierda_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-2];
            hay_match = verificar_match(entidad_a_izquierda_de_a_chequear,entidad_a_izquierda_de_a_chequear_a_2);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_izquierda_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_izquierda_de_a_chequear_a_2);
            }
        }
        return hay_match;
    }

    protected boolean chequear_a_derecha(EfectoRegla efecto_regla){
        Entidad entidad_a_derecha_de_a_chequear;
        Entidad entidad_a_derecha_de_a_chequear_a_2;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+2)){
            entidad_a_derecha_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_a_derecha_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+2];
            hay_match = verificar_match(entidad_a_derecha_de_a_chequear,entidad_a_derecha_de_a_chequear_a_2);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_derecha_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_derecha_de_a_chequear_a_2);
            }
        }
        return hay_match;
    }

    protected boolean chequear_lados(EfectoRegla efecto_regla){
        Entidad entidad_a_izquierda_de_a_chequear;
        Entidad entidad_a_derecha_de_a_chequear;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+1) &&
           en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-1))
        {
            entidad_a_izquierda_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_derecha_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            hay_match = verificar_match(entidad_a_izquierda_de_a_chequear,entidad_a_derecha_de_a_chequear);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_izquierda_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_a_derecha_de_a_chequear);
            }
        }
        return hay_match;
    }

    protected boolean chequear_arriba(EfectoRegla efecto_regla){
        Entidad entidad_arriba_de_a_chequear;
        Entidad entidad_arriba_de_a_chequear_a_2;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear-2, columna_entidad_a_chequear)){
            entidad_arriba_de_a_chequear = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_arriba_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear-2][columna_entidad_a_chequear];
            hay_match = verificar_match(entidad_arriba_de_a_chequear,entidad_arriba_de_a_chequear_a_2);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_arriba_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_arriba_de_a_chequear_a_2);
            }
        }
        return hay_match;
    }

    protected boolean chequear_abajo(EfectoRegla efecto_regla){
        Entidad entidad_abajo_de_a_chequear;
        Entidad entidad_abajo_de_a_chequear_a_2;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear+2, columna_entidad_a_chequear)){
            entidad_abajo_de_a_chequear = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_abajo_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear+2][columna_entidad_a_chequear];
            hay_match = verificar_match(entidad_abajo_de_a_chequear,entidad_abajo_de_a_chequear_a_2);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_abajo_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_abajo_de_a_chequear_a_2);
            }
        }
        return hay_match;
    }

    protected boolean chequear_el_de_arriba_y_el_de_abajo(EfectoRegla efecto_regla){
        Entidad entidad_arriba_de_a_chequear;
        Entidad entidad_abajo_de_a_chequear;
        boolean hay_match = false;
        if(en_rango(fila_entidad_a_chequear+1, columna_entidad_a_chequear) &&
           en_rango(fila_entidad_a_chequear-1, columna_entidad_a_chequear))
        {
            entidad_arriba_de_a_chequear = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_abajo_de_a_chequear = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            hay_match = verificar_match(entidad_arriba_de_a_chequear,entidad_abajo_de_a_chequear);
            if(hay_match){
                efecto_regla.agregar_entidad_a_detonar(entidad_a_chequear_match);
                efecto_regla.agregar_entidad_a_detonar(entidad_arriba_de_a_chequear);
                efecto_regla.agregar_entidad_a_detonar(entidad_abajo_de_a_chequear);
            }
        }
        return hay_match;
    }

    protected boolean verificar_match(Entidad entidad_1, Entidad entidad_2){
        return entidad_1.se_produce_match_con(entidad_a_chequear_match) &&
               entidad_2.se_produce_match_con(entidad_a_chequear_match);
    }

    @Override
    protected void generar_potenciador_y_agregar_a_incorporar(EfectoRegla efecto_regla) { }
    
}
