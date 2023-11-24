package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;

public class EfectoRegla {
    protected HashSet<Entidad> entidades_verificadas_a_detonar;
    protected HashSet<Entidad> entidades_a_incorporar;

    public EfectoRegla(){
        entidades_verificadas_a_detonar = new HashSet<Entidad>();
        entidades_a_incorporar = new HashSet<Entidad>();
    }

    public Set<Entidad> get_entidades_verificadas_a_detonar(){
        return entidades_verificadas_a_detonar;
    }

    public Set<Entidad> get_entidades_a_incorporar(){
        return entidades_a_incorporar;
    }

    public void agregar_entidad_a_incorporar(Entidad entidad_a_detonar){
        entidades_a_incorporar.add(entidad_a_detonar);
    }
    
    public void agregar_entidad_a_detonar(Entidad entidad_a_detonar){
        entidades_verificadas_a_detonar.add(entidad_a_detonar);
    }

    
    public void agregar_entidades_a_incorporar(Set<Entidad> entidades_a_incorporar){
        entidades_a_incorporar.addAll(entidades_a_incorporar);
    }
    

    public void agregar_entidades_a_detonar(Set<Entidad> entidades_a_incorporar){
        entidades_verificadas_a_detonar.addAll(entidades_a_incorporar);
    }

    public boolean hubo_match(){
        return !entidades_verificadas_a_detonar.isEmpty();
    }
}
