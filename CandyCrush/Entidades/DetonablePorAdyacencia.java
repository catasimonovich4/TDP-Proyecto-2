package Entidades;

import java.util.LinkedList;

public interface DetonablePorAdyacencia {


    public boolean se_produce_detonacion_adyacente(Entidad entidad);

    public boolean es_detonable_por_adyacencia_con(Caramelo caramelo);

    public boolean es_detonable_por_adyacencia_con(Glaseado glaseado);

    public boolean es_detonable_por_adyacencia_con(Potenciador potenciador);
    
    public boolean es_detonable_por_adyacencia_con(Gelatina gelatina);

    public boolean es_detonable_por_adyacencia_con(CarameloTdp2 caramelo_tdp2);

    public LinkedList<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades);
}
