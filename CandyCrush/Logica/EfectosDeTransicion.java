package Logica;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;

public class EfectosDeTransicion {
	
	protected Set<Entidad> entidades_a_detonar;
	protected Set<Entidad> entidades_a_incorporar;
	protected Set<Entidad> entidades_a_reemplazar;
	
	public EfectosDeTransicion() {
		entidades_a_detonar = new HashSet<>();
		entidades_a_incorporar = new HashSet<Entidad>();
		entidades_a_reemplazar = new HashSet<Entidad>();
	}
	
	public void agregar_entidad_a_detonar_y_reemplazar(Entidad entidad) {
		entidades_a_detonar.add(entidad);
		entidades_a_reemplazar.add(entidad);
	}
	
	public void agregar_entidad_de_reemplazo(Entidad entidad) {
		if(entidad!=null){
			entidades_a_incorporar.add(entidad);
		}
	}
	
	public void eliminar_de_entidades_a_reemplazar(Entidad entidad){
		entidades_a_reemplazar.remove(entidad);
	}

	public boolean existen_entidades_a_detonar() {
		return !entidades_a_detonar.isEmpty();
	}
	
	public boolean existen_entidades_a_incorporar() {
		return !entidades_a_incorporar.isEmpty();
	}
	
	public boolean existen_entidades_a_reemplazar() {
		return !entidades_a_reemplazar.isEmpty();
	}
	
	public Set<Entidad> entidades_a_detonar(){
		return entidades_a_detonar;
	}

	public Set<Entidad> entidades_a_incorporar(){
		return entidades_a_incorporar;
	}
	
	public Set<Entidad> entidades_a_reemplazar(){
		return entidades_a_reemplazar;
	}

	public void vaciar_efectos(){
		entidades_a_detonar.clear();
		entidades_a_incorporar.clear();
		entidades_a_reemplazar.clear();
	}

}
