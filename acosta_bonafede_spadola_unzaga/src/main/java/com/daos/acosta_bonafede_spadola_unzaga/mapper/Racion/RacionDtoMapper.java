package com.daos.acosta_bonafede_spadola_unzaga.mapper.Racion;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RacionDtoMapper {

    public RacionDto toDto(Racion racion) {
        if (racion == null) {
            return null;
        }

        return RacionDto.builder()
                .id(racion.getId())
                .stockPreparado(racion.getStockPreparado())
                .stockRestante(racion.getStockRestante())
                .fechaPreparacion(racion.getFechaPreparacion())
                .fechaVencimiento(racion.getFechaVencimiento())
                .build();
    }

}
