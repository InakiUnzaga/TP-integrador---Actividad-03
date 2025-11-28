package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionRequestDto;

import java.util.List;

public interface RacionService {
    RacionDto obtenerRacion(int id);
    List<RacionDto> obtenerRaciones();
    RacionDto crearNuevaRacion(RacionRequestDto dto);
    RacionDto actualizarRacion(int id, RacionRequestDto dto);
    void eliminarRacion(int id);
}
