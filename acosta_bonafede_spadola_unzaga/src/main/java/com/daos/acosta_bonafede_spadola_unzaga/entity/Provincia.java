package com.daos.acosta_bonafede_spadola_unzaga.entity;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "provincias")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String nombre;
	
	@OneToMany(mappedBy = "provincia")
	private List<Ciudad> ciudades;
	
	
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
	
}
