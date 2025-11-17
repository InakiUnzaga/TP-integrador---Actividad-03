package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaDto;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RacionDto extends RepresentationModel<RacionDto> {
    private int id;
    private int stockPreparado;
    private int stockRestante;
    private RecetaDto receta;
    private LocalDateTime fechaPreparacion;
    private LocalDateTime fechaVencimiento;
}
