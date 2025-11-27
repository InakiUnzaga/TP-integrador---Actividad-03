package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecetaRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private Double peso;
    
    @Positive(message = "Las calorías por ración deben ser un valor positivo")
    private int caloriasPorRacion;
}
