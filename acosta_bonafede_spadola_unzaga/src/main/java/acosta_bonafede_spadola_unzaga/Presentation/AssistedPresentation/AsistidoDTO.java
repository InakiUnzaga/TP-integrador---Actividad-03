package acosta_bonafede_spadola_unzaga.Presentation.AssistedPresentation;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import acosta_bonafede_spadola_unzaga.Entities.Asistido;

/**
 * DTO para la entrada de datos (POST/PUT) de Asistido.
 * Mapea los campos requeridos para el REST, con validaciones.
 */
public class AsistidoDTO {

    // Id (No insertable/editable por el usuario, solo para identificar en PUT)
    private Long id; 

    // Dni (numero entero positivo, no obligatorio)
    @Min(value = 1, message = "El DNI, si se provee, debe ser un número positivo.")
    private Long dni; // Se usa Long ya que Asistido/Persona usa Long para dni

    // Nombre completo (texto, obligatorio) - Será mapeado al apellido y nombre de Persona
    @NotBlank(message = "El nombre completo es obligatorio.")
    private String nombreCompleto;

    // Domicilio (texto, no obligatorio) - Mapea a 'direccion' de Persona
    private String domicilio; 

    // Fecha de nacimiento (fecha, no obligatorio)
    private LocalDate fechaNacimiento; 

    // Edad al momento de registrarse (numero entero positivo, obligatorio)
    @NotNull(message = "La edad al momento de registrarse es obligatoria.")
    @Min(value = 1, message = "La edad debe ser un número positivo mayor a 0.")
    private Integer edad; 

    // Id de la Ciudad donde Vive (Requerido)
    @NotNull(message = "El Id de la ciudad donde vive es obligatorio.")
    @Min(value = 1, message = "El Id de la ciudad debe ser un número positivo.")
    private Long idCiudad;

    /**
     * Convierte el DTO en la Entidad POJO (Asistido).
     */
    public Asistido toPojo() {
        Asistido a = new Asistido();
        // ID (para el PUT)
        a.setId(this.id); 
        
        // Mapeo de campos
        a.setDni(this.dni);
        a.setDireccion(this.domicilio); 
        a.setFechaNacimiento(this.fechaNacimiento);
        a.setEdadRegistro(this.edad); 
        
        // Manejo del Nombre Completo 
        String[] partes = this.nombreCompleto.split(" ", 2);
        a.setNombre(partes[0]);
        a.setApellido(partes.length > 1 ? partes[1] : "");
        
        // La Ciudad (relación) y Familia se setean en el RestController
        return a;
    }

    // --- Getters y Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDni() { return dni; }
    public void setDni(Long dni) { this.dni = dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public Long getIdCiudad() { return idCiudad; }
    public void setIdCiudad(Long idCiudad) { this.idCiudad = idCiudad; }
}