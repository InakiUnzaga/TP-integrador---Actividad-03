package com.daos.acosta_bonafede_spadola_unzaga.Presentation.AssistedPresentation;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistido;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaResponseDto;

@Relation(collectionRelation = "asistidos")
public class AsistidoResponseDTO extends RepresentationModel<AsistidoResponseDTO> {
    
    private Long id;
    private Long dni;
    private String nombre;
    private String domicilio;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private Long idCiudad;
    private String nombreCiudad;
   
    private List<AsistenciaResponseDto> asistencias;
    
   
    public AsistidoResponseDTO(Asistido pojo) {
        this.id = pojo.getId();
        this.dni = pojo.getDni();
        this.nombre = pojo.getNombre(); 
        this.domicilio = pojo.getDomicilio(); 
        this.fechaNacimiento = pojo.getFechaNacimiento();
        this.edad = pojo.getEdad(); 
        
        if (pojo.getCiudad() != null) {
            this.idCiudad = pojo.getCiudad().getId();
            this.nombreCiudad = pojo.getCiudad().getNombre();
        }
        
        //si tiene Asistencias las carga
        if (pojo.getAsistencias() != null) {
            this.asistencias = pojo.getAsistencias().stream()
                                  .map(AsistenciaResponseDto::new)
                                  .collect(Collectors.toList());
        } else {
            this.asistencias = List.of();
        }
    }

    // --- Getters     
    public Long getId() { return id; }
    public Long getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDomicilio() { return domicilio; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public Integer getEdad() { return edad; }
    public Long getIdCiudad() { return idCiudad; }
    public String getNombreCiudad() { return nombreCiudad; }
}
