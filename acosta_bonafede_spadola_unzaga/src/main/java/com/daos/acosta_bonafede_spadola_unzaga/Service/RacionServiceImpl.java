package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.RacionRepository;
import com.daos.acosta_bonafede_spadola_unzaga.DAO.RecetaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.ResourceNotFoundException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion.RacionRequestDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Receta;
import com.daos.acosta_bonafede_spadola_unzaga.mapper.RacionDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public RacionDto crearNuevaRacion(RacionRequestDto dto) {
        Receta receta = recetaRepository.findById(dto.getIdReceta()).orElseThrow(
                () -> new ResourceNotFoundException("Receta no encontrada")
        );

        Racion racionGuardada = new Racion();
        racionGuardada.setReceta(receta);
        racionGuardada.setStockPreparado(dto.getStockPreparado());
        racionGuardada.setStockRestante(dto.getStockPreparado());
        racionGuardada.setFechaVencimiento(dto.getFechaVencimiento());

        racionRepository.save(racionGuardada);

        return racionDtoMapper.toDto(racionGuardada);
    }

    @Override
    @Transactional
    public RacionDto actualizarRacion(int id, RacionRequestDto dto) {
        Racion racionEncontrada  = racionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Racion no encontrada")
        );

        Receta recetaNueva = recetaRepository.findById(dto.getIdReceta()).orElseThrow(
                () -> new ResourceNotFoundException("Receta no encontrada")
        );

        racionEncontrada.setStockPreparado(dto.getStockPreparado());
        racionEncontrada.setStockRestante(dto.getStockPreparado());
        racionEncontrada.setReceta(recetaNueva);
        racionEncontrada.setFechaVencimiento(dto.getFechaVencimiento());

        racionRepository.save(racionEncontrada);

        return racionDtoMapper.toDto(racionEncontrada);
    }

    @Override
    public void eliminarRacion(int id) {
        Racion racionEncontrada = racionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Racion no encontrada")
        );

        racionRepository.delete(racionEncontrada);
    }

}
