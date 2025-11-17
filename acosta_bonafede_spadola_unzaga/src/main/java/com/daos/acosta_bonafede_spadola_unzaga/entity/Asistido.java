package com.daos.acosta_bonafede_spadola_unzaga.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity 
public class Asistido{	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private Long dni;
	private String nombre;
	private String domicilio;	  
	private LocalDate fechaNacimiento;
    private Integer edad; 
    
    // Realcion con Ciudad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id", nullable = false) 
    private Ciudad ciudad;
    
    private LocalDate fechaRegistro;
    @NotNull
    private boolean estaActiva = true;
    
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDireccion(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }  
    
    public boolean isEstaActiva() { return estaActiva; }
    public void setEstaActiva(boolean estaActiva) { this.estaActiva = estaActiva; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
       
    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
    
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edadRegistro) { this.edad = edadRegistro; }

	public Asistido(Long id, Long dni, String nombre, String domicilio, LocalDate fechaNacimiento, Integer edad,
			Ciudad ciudad, LocalDate fechaRegistro, @NotNull boolean estaActiva) {
		super();
		this.id = id;
		this.dni = dni;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.ciudad = ciudad;
		this.fechaRegistro = fechaRegistro;
		this.estaActiva = estaActiva;
	}

	public Asistido() {}
     
}