package acosta_bonafede_spadola_unzaga.Entities;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("ASISTIDO") 
public class Asistido extends Persona {	

    // Relación Familia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="nroFamilia", referencedColumnName = "nroFamilia", nullable = true) 
    private Familia familia;

    // Realcion con Ciudad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id", nullable = false) 
    private Ciudad ciudad;

    private LocalDate fechaRegistro;
    @NotNull
    private boolean estaActiva = true;
    
    // requisito: "Edad al momento de registrarse"
    private Integer edadRegistro; 


    public boolean isEstaActiva() { return estaActiva; }
    public void setEstaActiva(boolean estaActiva) { this.estaActiva = estaActiva; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    public Familia getFamilia() { return familia; }
    public void setFamilia(Familia familia) { this.familia = familia; }
    
    public Ciudad getCiudad() { return ciudad; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
    
    public Integer getEdadRegistro() { return edadRegistro; }
    public void setEdadRegistro(Integer edadRegistro) { this.edadRegistro = edadRegistro; }
    
    // Constructor
    public Asistido(String apellido, String nombre, String direccion, Long dni, LocalDate fechaNacimiento, String ocupacion) {
        super(apellido, nombre, direccion, dni, fechaNacimiento, ocupacion);
        this.fechaRegistro = LocalDate.now();
    }

    public Asistido() {}
  
}