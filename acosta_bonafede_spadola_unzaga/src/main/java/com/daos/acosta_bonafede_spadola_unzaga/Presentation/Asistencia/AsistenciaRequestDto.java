package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AsistenciaRequestDto {
    @NotNull(message = "El ID del asistido es obligatorio")
    private Long idAsistido;

    @NotNull(message = "El ID del ración es obligatorio")
    private Integer idRacion;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDateTime fechaEntrega;
}

