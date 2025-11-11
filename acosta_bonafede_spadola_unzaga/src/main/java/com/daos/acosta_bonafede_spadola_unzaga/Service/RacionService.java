package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionNuevaDto;

public interface RacionService {
    RacionDto obtenerRacion(int id);
    RacionDto crearNuevaRacion(RacionNuevaDto racion);
    RacionDto actualizarRacion(RacionDto racion);
    void eliminarRacion(int id);
}
