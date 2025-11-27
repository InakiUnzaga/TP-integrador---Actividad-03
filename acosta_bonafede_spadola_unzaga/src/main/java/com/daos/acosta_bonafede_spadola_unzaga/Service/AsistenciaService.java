package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaRequestDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaResponseDto;

import java.util.List;

public interface AsistenciaService {

    AsistenciaResponseDto crear(AsistenciaRequestDto dto) throws Exception;
    AsistenciaResponseDto actualizar(Long id, AsistenciaRequestDto dto) throws Exception;
    AsistenciaResponseDto obtenerPorId(Long id) throws Exception;
    List<AsistenciaResponseDto> obtenerTodas();
    void eliminar(Long id)throws Exception;


}
