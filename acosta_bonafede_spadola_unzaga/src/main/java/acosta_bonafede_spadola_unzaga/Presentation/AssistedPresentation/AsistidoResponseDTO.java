package acosta_bonafede_spadola_unzaga.Presentation.AssistedPresentation;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;

@Relation(collectionRelation = "asistidos")
public class AsistidoResponseDTO extends RepresentationModel<AsistidoResponseDTO> {
    
    private Long id;
    private Long dni;
    private String nombreCompleto;
    private String domicilio;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private Long idCiudad;
    private String nombreCiudad;

    public AsistidoResponseDTO(Asistido pojo) {
        this.id = pojo.getId();
        this.dni = pojo.getDni();
        // Combina los campos de Persona para el Nombre Completo
        this.nombreCompleto = pojo.getNombre() + " " + pojo.getApellido(); 
        this.domicilio = pojo.getDireccion(); 
        this.fechaNacimiento = pojo.getFechaNacimiento();
        this.edad = pojo.getEdadRegistro(); // Usamos el campo edadRegistro
        
        if (pojo.getCiudad() != null) {
            this.idCiudad = pojo.getCiudad().getId();
            this.nombreCiudad = pojo.getCiudad().getNombre();
        }
    }

    // --- Getters     
    public Long getId() { return id; }
    public Long getDni() { return dni; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getDomicilio() { return domicilio; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Integer getEdad() { return edad; }
    public Long getIdCiudad() { return idCiudad; }
    public String getNombreCiudad() { return nombreCiudad; }
}
