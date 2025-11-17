package com.daos.acosta_bonafede_spadola_unzaga.Presentation.ciudades;
import org.springframework.hateoas.RepresentationModel;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Ciudad;


/**
 * Objeto utilizado para construir la respuesta de los servicios
 * 
 *
 */
public class CiudadResponseDTO extends RepresentationModel<CiudadResponseDTO> {

	private Long id;
	private String nombre;
	
	
	
	
	public CiudadResponseDTO(Ciudad pojo) {
		super();
		this.nombre=pojo.getNombre();
		this.id=pojo.getId();
		
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	@Override
	public String toString() {
		return id+" - "+ nombre ;
	}
	
}
