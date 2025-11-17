package com.daos.acosta_bonafede_spadola_unzaga.mapper;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Receta;
import org.springframework.stereotype.Component;

@Component
public class RecetaDtoMapper {

    public RecetaDto toDto(Receta receta) {
        return RecetaDto.builder()
                .id(receta.getId())
                .nombre(receta.getNombre())
                .peso(receta.getPeso())
                .caloriasPorRacion(receta.getCaloriasPorRacion())
                .build();
    }

}
