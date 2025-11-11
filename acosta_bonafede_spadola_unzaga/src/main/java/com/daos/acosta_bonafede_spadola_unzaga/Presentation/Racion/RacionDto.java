package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RacionDto {

    private int id;

    private int stockPreparado;

    private int stockRestante;

    private LocalDateTime fechaPreparacion;

    private LocalDateTime fechaVencimiento;

}
