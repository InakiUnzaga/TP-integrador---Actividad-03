package com.daos.acosta_bonafede_spadola_unzaga.Presentation.ciudades;

import java.util.List;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Provincia;

public class CiudadesBuscarForm {
	private String nombre;
	private Long provinciaSeleccionada;
	
	
	private List<Provincia> provincias;

	
	
	public List<Provincia> getProvincias() {
		return provincias;
	}
	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
	public Long getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}
	public void setProvinciaSeleccionada(Long provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}
	
	public String getNombre() {
		if(nombre!=null && nombre.length()>0)
			return nombre;
		else
			return null;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
