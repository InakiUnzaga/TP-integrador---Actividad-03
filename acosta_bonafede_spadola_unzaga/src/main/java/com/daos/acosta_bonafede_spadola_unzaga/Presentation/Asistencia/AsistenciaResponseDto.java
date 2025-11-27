package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AsistenciaResponseDto extends RepresentationModel<AsistenciaResponseDto> {
    private Long id;
    private LocalDateTime fechaEntrega;
    private String nombreAsistido;
    private String descripcionReceta;
    private LocalDateTime fechaVencimientoRacion;
    
    public AsistenciaResponseDto(Asistencia asistencia) {
        this.id = asistencia.getId();
        this.fechaEntrega = asistencia.getFechaEntrega(); // Se asume este getter en Asistencia
        
        // Asumimos que Asistencia tiene una relación con Asistido para obtener el nombre completo
        if (asistencia.getAsistido() != null) {            
            this.nombreAsistido = asistencia.getAsistido().getNombre(); 
        } else {
            this.nombreAsistido = "Desconocido";
        }
    }
    
    
}
