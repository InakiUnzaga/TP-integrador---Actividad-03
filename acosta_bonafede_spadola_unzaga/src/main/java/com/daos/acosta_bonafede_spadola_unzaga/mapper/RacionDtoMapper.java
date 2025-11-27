package com.daos.acosta_bonafede_spadola_unzaga.mapper;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RacionDtoMapper {

    private final RecetaDtoMapper recetaDtoMapper;

    public RacionDto toDto(Racion racion) {
        return RacionDto.builder()
                .id(racion.getId())
                .stockPreparado(racion.getStockPreparado())
                .stockRestante(racion.getStockRestante())
                .receta(recetaDtoMapper.toDto(racion.getReceta()))
                .fechaPreparacion(racion.getFechaPreparacion())
                .fechaVencimiento(racion.getFechaVencimiento())
                .build();
    }

}
