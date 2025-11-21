package com.daos.acosta_bonafede_spadola_unzaga.mapper;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaResponseDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaMapper {

    public AsistenciaResponseDto toDto(Asistencia asistencia) {
        return AsistenciaResponseDto.builder()
                .id(asistencia.getId())
                .fechaEntrega(asistencia.getFechaEntrega())
                .nombreAsistido(asistencia.getAsistido().getNombre())
                .descripcionReceta(asistencia.getRacion().getReceta().getNombre())
                .fechaVencimientoRacion(asistencia.getRacion().getFechaVencimiento())
                .build();
    }
}
