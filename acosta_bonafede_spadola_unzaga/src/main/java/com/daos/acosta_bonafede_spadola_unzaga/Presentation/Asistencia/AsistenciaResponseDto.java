package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

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
}
