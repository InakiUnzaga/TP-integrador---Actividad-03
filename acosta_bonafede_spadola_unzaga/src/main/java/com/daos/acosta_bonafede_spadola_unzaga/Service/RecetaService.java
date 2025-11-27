package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaRequestDto;

import java.util.List;

public interface RecetaService {
    RecetaDto obtenerReceta(int id);
    List<RecetaDto> obtenerTodasLasRecetas();
    RecetaDto crearReceta(RecetaRequestDto recetaRequestDto);
    RecetaDto actualizarReceta(int id, RecetaRequestDto recetaRequestDto);
    void eliminarReceta(int id);
}
