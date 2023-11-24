package Reglas;


import java.util.Iterator;
import java.util.Set;

import Entidades.Entidad;
import Entidades.Potenciador;

public abstract class ReglaAbstracta implements Regla {

    protected Entidad[][] matriz_entidades;
    protected Entidad entidad_a_chequear_match;
    protected int fila_entidad_a_chequear;
    protected int columna_entidad_a_chequear;
    protected int color_entidad_a_chequear;

    protected void inicializar_atributos_entidad_y_matriz(Entidad[][] matriz_entidades, Entidad entidad_a_chequear_match){
        this.matriz_entidades = matriz_entidades;
        this.entidad_a_chequear_match = entidad_a_chequear_match;
        fila_entidad_a_chequear = entidad_a_chequear_match.get_fila();
        columna_entidad_a_chequear = entidad_a_chequear_match.get_columna();
        color_entidad_a_chequear = entidad_a_chequear_match.get_color();
    }
    
    protected void agregar_entidades_a_detonar(Set<Entidad> entidades_a_detonar, EfectoRegla efecto_regla){
        efecto_regla.agregar_entidades_a_detonar(entidades_a_detonar);
    }
    

    protected abstract void generar_potenciador_y_agregar_a_incorporar(EfectoRegla efecto_regla);
    
    protected boolean se_verifica_match(Set<Entidad> entidades_a_revisar){
        boolean hay_match = true;
        Entidad entidad_a_comparar;
        Iterator<Entidad> iterador_entidades_a_revisar = entidades_a_revisar.iterator();
        while(iterador_entidades_a_revisar.hasNext() && hay_match){
            entidad_a_comparar = iterador_entidades_a_revisar.next();
            hay_match = entidad_a_chequear_match.se_produce_match_con(entidad_a_comparar);
        }
        return hay_match;
    }

    public boolean en_rango(int fila, int columna){
		boolean en_rango_fila = (0 <= fila) && (fila < matriz_entidades.length);
		boolean en_rango_columna = (0 <= columna) && (columna < matriz_entidades[0].length);
		return (en_rango_fila && en_rango_columna);
	}

}