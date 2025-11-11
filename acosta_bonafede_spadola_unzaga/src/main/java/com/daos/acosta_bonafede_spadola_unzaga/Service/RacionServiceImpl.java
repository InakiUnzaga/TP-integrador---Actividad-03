package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.RacionRepository;
import com.daos.acosta_bonafede_spadola_unzaga.DAO.RecetaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.ResourceNotFoundException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionNuevaDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import com.daos.acosta_bonafede_spadola_unzaga.mapper.Racion.RacionDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RacionServiceImpl implements RacionService {

    private final RacionRepository racionRepository;

    private final RecetaRepository recetaRepository;

    private final RacionDtoMapper racionDtoMapper;

    @Override
    public RacionDto obtenerRacion(int id) {
        Racion racionEncontrada = racionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Racion no encontrada")
        );

        return racionDtoMapper.toDto(racionEncontrada);
    }

    @Override
    public RacionDto crearNuevaRacion(RacionNuevaDto racion) {
        return null;
    }

    @Override
    public RacionDto actualizarRacion(RacionDto racion) {
        return null;
    }

    @Override
    public void eliminarRacion(int id) {

    }

}
