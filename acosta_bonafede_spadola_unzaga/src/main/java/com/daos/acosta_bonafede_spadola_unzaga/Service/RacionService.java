package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionRequestDto;

public interface RacionService {
    RacionDto obtenerRacion(int id);
    RacionDto crearNuevaRacion(RacionRequestDto dto);
    RacionDto actualizarRacion(int id, RacionRequestDto dto);
    void eliminarRacion(int id);
}
