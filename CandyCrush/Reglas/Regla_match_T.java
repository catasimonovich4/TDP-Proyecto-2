package Reglas;

import java.util.Set;

import Entidades.Entidad;

public class Regla_match_T extends Regla_match_letra {
    
    @Override
    protected boolean cargar_letra_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_T_en_entidades_a_revisar(entidades_a_revisar);
    }

    @Override
    protected boolean cargar_letra_rotada_90_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_T_rotada_90_en_entidades_a_revisar(entidades_a_revisar);
    }
  
    @Override
    protected boolean cargar_letra_rotada_180_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_T_rotada_180_en_entidades_a_revisar(entidades_a_revisar);
    }

    @Override
    protected boolean cargar_letra_rotada_270_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_T_rotada_270_en_entidades_a_revisar(entidades_a_revisar);
    }


    private boolean cargar_T_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_a_izquierda_de_movida;
        Entidad entidad_a_derecha_de_movida;
        Entidad entidad_abajo_de_movida;
        Entidad entidad_abajo_de_movida_a_2;

        boolean en_rango_T = en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-1) &&
                             en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+1) &&
                             en_rango(fila_entidad_a_chequear+2, columna_entidad_a_chequear);

        if(en_rango_T){
            entidad_a_izquierda_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_derecha_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_abajo_de_movida = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_abajo_de_movida_a_2 = matriz_entidades[fila_entidad_a_chequear+2][columna_entidad_a_chequear];
            entidades_a_revisar.add(entidad_a_izquierda_de_movida);
            entidades_a_revisar.add(entidad_a_derecha_de_movida);
            entidades_a_revisar.add(entidad_abajo_de_movida);
            entidades_a_revisar.add(entidad_abajo_de_movida_a_2);
        }
        return en_rango_T;
    }

    private boolean cargar_T_rotada_90_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_arriba_de_movida;
        Entidad entidad_abajo_de_movida;
        Entidad entidad_a_derecha_de_movida;
        Entidad entidad_a_derecha_de_movida_a_2;
        boolean en_rango_T_rotada_90 = en_rango(fila_entidad_a_chequear-1, columna_entidad_a_chequear) &&
                                       en_rango(fila_entidad_a_chequear+1, columna_entidad_a_chequear) &&
                                       en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+2);
        if(en_rango_T_rotada_90){
            entidad_arriba_de_movida = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_abajo_de_movida = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_a_derecha_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_a_derecha_de_movida_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+2];
            entidades_a_revisar.add(entidad_arriba_de_movida);
            entidades_a_revisar.add(entidad_abajo_de_movida);
            entidades_a_revisar.add(entidad_a_derecha_de_movida);
            entidades_a_revisar.add(entidad_a_derecha_de_movida_a_2);
        }
        return en_rango_T_rotada_90;
    }

    private boolean cargar_T_rotada_180_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_a_izquierda_de_movida;
        Entidad entidad_a_derecha_de_movida;
        Entidad entidad_arriba_de_movida;
        Entidad entidad_arriba_de_movida_a_2;
        boolean en_rango_T_rotada_180 = en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-1) &&
                                        en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+1) &&
                                        en_rango(fila_entidad_a_chequear-2, columna_entidad_a_chequear);
        if(en_rango_T_rotada_180){
            entidad_a_izquierda_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_derecha_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_arriba_de_movida = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_arriba_de_movida_a_2 = matriz_entidades[fila_entidad_a_chequear-2][columna_entidad_a_chequear];
            entidades_a_revisar.add(entidad_a_izquierda_de_movida);
            entidades_a_revisar.add(entidad_a_derecha_de_movida);
            entidades_a_revisar.add(entidad_arriba_de_movida);
            entidades_a_revisar.add(entidad_arriba_de_movida_a_2);
        }
        return en_rango_T_rotada_180;
    }

    private boolean cargar_T_rotada_270_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_arriba_de_movida;
        Entidad entidad_abajo_de_movida;
        Entidad entidad_a_izquierda_de_movida;
        Entidad entidad_a_izquierda_de_movida_a_2;
        boolean en_rango_T_rotada_270 = en_rango(-1, columna_entidad_a_chequear) &&
                                        en_rango(fila_entidad_a_chequear+1, columna_entidad_a_chequear) &&
                                        en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-2);
        if(en_rango_T_rotada_270){
            entidad_arriba_de_movida = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_abajo_de_movida = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_a_izquierda_de_movida = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_izquierda_de_movida_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-2];
            entidades_a_revisar.add(entidad_arriba_de_movida);
            entidades_a_revisar.add(entidad_abajo_de_movida);
            entidades_a_revisar.add(entidad_a_izquierda_de_movida);
            entidades_a_revisar.add(entidad_a_izquierda_de_movida_a_2);
        }
        return en_rango_T_rotada_270;
    }
}
