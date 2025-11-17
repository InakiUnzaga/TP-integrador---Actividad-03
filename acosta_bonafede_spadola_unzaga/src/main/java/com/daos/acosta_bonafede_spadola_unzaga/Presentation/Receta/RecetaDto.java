package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecetaDto {
    private int id;
    private String nombre;
    private Double peso;
    private int caloriasPorRacion;
}
