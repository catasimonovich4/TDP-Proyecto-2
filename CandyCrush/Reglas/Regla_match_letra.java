package Reglas;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import Entidades.Entidad;
import Entidades.Envuelto;
import Entidades.Potenciador;

public abstract class Regla_match_letra extends ReglaAbstracta{
    
    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidad_a_chequear_match) {
        
        inicializar_atributos_entidad_y_matriz(entidades, entidad_a_chequear_match);

        Set<Entidad> entidades_a_revisar = new HashSet<Entidad>();
        EfectoRegla efecto_regla = new EfectoRegla();
        boolean letra_esta_dentro_del_rango;

        letra_esta_dentro_del_rango = cargar_letra_en_entidades_a_revisar(entidades_a_revisar);
        if(letra_esta_dentro_del_rango && se_verifica_match(entidades_a_revisar)){
            entidades_a_revisar.add(entidad_a_chequear_match);
            agregar_entidades_a_detonar(entidades_a_revisar,efecto_regla);
            generar_potenciador_y_agregar_a_incorporar(efecto_regla);
        }

        letra_esta_dentro_del_rango = cargar_letra_rotada_90_en_entidades_a_revisar(entidades_a_revisar);
        if(letra_esta_dentro_del_rango && se_verifica_match(entidades_a_revisar)){
            entidades_a_revisar.add(entidad_a_chequear_match);
            agregar_entidades_a_detonar(entidades_a_revisar, efecto_regla);
            generar_potenciador_y_agregar_a_incorporar(efecto_regla);
        }

        letra_esta_dentro_del_rango = cargar_letra_rotada_180_en_entidades_a_revisar(entidades_a_revisar);
        if(letra_esta_dentro_del_rango && se_verifica_match(entidades_a_revisar)){
            entidades_a_revisar.add(entidad_a_chequear_match);
            agregar_entidades_a_detonar(entidades_a_revisar, efecto_regla);
            generar_potenciador_y_agregar_a_incorporar(efecto_regla);
        }

        letra_esta_dentro_del_rango = cargar_letra_rotada_270_en_entidades_a_revisar(entidades_a_revisar);
        if(letra_esta_dentro_del_rango && se_verifica_match(entidades_a_revisar)){
            entidades_a_revisar.add(entidad_a_chequear_match);
            agregar_entidades_a_detonar(entidades_a_revisar, efecto_regla);
            generar_potenciador_y_agregar_a_incorporar(efecto_regla);
        }

        return efecto_regla;
    }

    @Override
    protected void generar_potenciador_y_agregar_a_incorporar(EfectoRegla efecto_regla) {
        Potenciador a_incorporar = new Envuelto(null, 
                                                fila_entidad_a_chequear, 
                                                columna_entidad_a_chequear, 
                                                color_entidad_a_chequear,
                                                false);
        efecto_regla.agregar_entidad_a_incorporar(a_incorporar);
    }

    protected abstract boolean cargar_letra_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar);
    protected abstract boolean cargar_letra_rotada_90_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar);
    protected abstract boolean cargar_letra_rotada_180_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar);
    protected abstract boolean cargar_letra_rotada_270_en_entidades_a_revisar(Set<Entidad> entidades_a_revisar);
    

}
