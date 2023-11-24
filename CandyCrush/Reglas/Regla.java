package Reglas;

import Entidades.Entidad;

public interface Regla {

      public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidad_a_chequear_match);

    }
