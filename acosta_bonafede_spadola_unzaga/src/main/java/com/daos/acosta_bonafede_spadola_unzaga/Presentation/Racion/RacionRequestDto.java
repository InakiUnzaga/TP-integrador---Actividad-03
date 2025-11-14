package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RacionRequestDto {
    @NotNull(message = "debe agregar un numero de stock")
    @Min(value = 1, message = "debe agregar un numero mayor a 0")
    private int stockPreparado;

    @NotNull(message = "debe agregar una receta")
    private int idReceta;
}
