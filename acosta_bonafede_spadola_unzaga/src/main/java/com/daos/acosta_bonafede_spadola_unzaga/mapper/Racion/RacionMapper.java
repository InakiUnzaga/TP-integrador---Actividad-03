package com.daos.acosta_bonafede_spadola_unzaga.mapper.Racion;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionRequestDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RacionMapper {

    public Racion toEntity(RacionRequestDto dto) {
        return Racion.builder()
                .stockPreparado(dto.getStockPreparado())
                .build();
    }

}
