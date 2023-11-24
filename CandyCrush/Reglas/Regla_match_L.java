package Reglas;

import java.util.Set;

import Entidades.Entidad;

public class Regla_match_L extends Regla_match_letra {

    @Override
    protected boolean cargar_letra_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_L_en_entidades_a_revisar(entidades_a_revisar);
    }

    @Override
    protected boolean cargar_letra_rotada_90_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_L_rotada_90_en_entidades_a_revisar(entidades_a_revisar);
    }

    @Override
    protected boolean cargar_letra_rotada_180_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_L_rotada_180_en_entidades_a_revisar(entidades_a_revisar);
    }

    @Override
    protected boolean cargar_letra_rotada_270_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        entidades_a_revisar.clear();
        return cargar_L_rotada_270_en_entidades_a_revisar(entidades_a_revisar);
    }
    
    //carga en las entidades a revisar a las que forman una L con la entidad a chequear
    //retorna true si la L se pudo cargar exitosamente (la L estaba en el rango del tablero)
    private boolean cargar_L_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_arriba_de_a_chequear;
        Entidad entidad_arriba_de_a_chequear_a_2;
        Entidad entidad_a_derecha_de_a_chequear;
        Entidad entidad_a_derecha_de_a_chequear_a_2;
        boolean en_rango_L = en_rango(fila_entidad_a_chequear-2, columna_entidad_a_chequear) &&
                             en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+2);

        if(en_rango_L){
            entidad_arriba_de_a_chequear = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_arriba_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear-2][columna_entidad_a_chequear];
            entidad_a_derecha_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_a_derecha_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+2];
            entidades_a_revisar.add(entidad_arriba_de_a_chequear);
            entidades_a_revisar.add(entidad_arriba_de_a_chequear_a_2);
            entidades_a_revisar.add(entidad_a_derecha_de_a_chequear);
            entidades_a_revisar.add(entidad_a_derecha_de_a_chequear_a_2);
        }
        return en_rango_L;
    }

    //carga en las entidades a revisar a las que forman una "_|" (L rotada 90 grados) con la entidad a chequear
    //retorna true si la "_|" se pudo cargar exitosamente (la _| estaba en el rango del tablero)
    private boolean cargar_L_rotada_90_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_arriba_de_a_chequear;
        Entidad entidad_arriba_de_a_chequear_a_2;
        Entidad entidad_a_izquierda_de_a_chequear;
        Entidad entidad_a_izquierda_de_a_chequear_a_2;
        boolean en_rango_L_rotada_90 = en_rango(fila_entidad_a_chequear-2, columna_entidad_a_chequear) &&
                                       en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-2);
        if(en_rango_L_rotada_90){
            entidad_arriba_de_a_chequear = matriz_entidades[fila_entidad_a_chequear-1][columna_entidad_a_chequear];
            entidad_arriba_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear-2][columna_entidad_a_chequear];
            entidad_a_izquierda_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_izquierda_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-2];
            entidades_a_revisar.add(entidad_arriba_de_a_chequear);
            entidades_a_revisar.add(entidad_arriba_de_a_chequear_a_2);
            entidades_a_revisar.add(entidad_a_izquierda_de_a_chequear);
            entidades_a_revisar.add(entidad_a_izquierda_de_a_chequear_a_2);
        }
        return en_rango_L_rotada_90;
    }

     //carga en las entidades a revisar a las que forman una "¬" (¬ rotada 180 grados) con la entidad a chequear
    //retorna true si la "¬" se pudo cargar exitosamente (la ¬ estaba en el rango del tablero)
    private boolean cargar_L_rotada_180_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_abajo_de_a_chequear;
        Entidad entidad_abajo_de_a_chequear_a_2;
        Entidad entidad_a_izquierda_de_a_chequear;
        Entidad entidad_a_izquierda_de_a_chequear_a_2;
        boolean en_rango_L_rotada_180 = en_rango(fila_entidad_a_chequear+2, columna_entidad_a_chequear) &&
                                        en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear-2);
        if(en_rango_L_rotada_180){
            entidad_abajo_de_a_chequear = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_abajo_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear+2][columna_entidad_a_chequear];
            entidad_a_izquierda_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-1];
            entidad_a_izquierda_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear-2];
            entidades_a_revisar.add(entidad_abajo_de_a_chequear);
            entidades_a_revisar.add(entidad_abajo_de_a_chequear_a_2);
            entidades_a_revisar.add(entidad_a_izquierda_de_a_chequear);
            entidades_a_revisar.add(entidad_a_izquierda_de_a_chequear_a_2);
        }
        return en_rango_L_rotada_180;
    }

     //carga en las entidades a revisar a las que forman una "|¨" (L rotada 180 grados) con la entidad a chequear
    //retorna true si la "|¨" se pudo cargar exitosamente (la |¨ estaba en el rango del tablero)
    private boolean cargar_L_rotada_270_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar) {
        Entidad entidad_abajo_de_a_chequear;
        Entidad entidad_abajo_de_a_chequear_a_2;
        Entidad entidad_a_derecha_de_a_chequear;
        Entidad entidad_a_derecha_de_a_chequear_a_2;
        boolean en_rango_L_rotada_270 = en_rango(fila_entidad_a_chequear+2, columna_entidad_a_chequear) &&
                                        en_rango(fila_entidad_a_chequear, columna_entidad_a_chequear+2);
                    
        if(en_rango_L_rotada_270){
            entidad_abajo_de_a_chequear = matriz_entidades[fila_entidad_a_chequear+1][columna_entidad_a_chequear];
            entidad_abajo_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear+2][columna_entidad_a_chequear];
            entidad_a_derecha_de_a_chequear = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+1];
            entidad_a_derecha_de_a_chequear_a_2 = matriz_entidades[fila_entidad_a_chequear][columna_entidad_a_chequear+2];
            entidades_a_revisar.add(entidad_abajo_de_a_chequear);
            entidades_a_revisar.add(entidad_abajo_de_a_chequear_a_2);
            entidades_a_revisar.add(entidad_a_derecha_de_a_chequear);
            entidades_a_revisar.add(entidad_a_derecha_de_a_chequear_a_2);
        }
        return en_rango_L_rotada_270;
    }
}
