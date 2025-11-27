package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.RecetaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.ResourceNotFoundException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaRequestDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Receta;
import com.daos.acosta_bonafede_spadola_unzaga.mapper.RecetaDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final RecetaDtoMapper recetaDtoMapper;

    @Override
    @Transactional(readOnly = true)
    public RecetaDto obtenerReceta(int id) {
        Receta recetaEncontrada = recetaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Receta no encontrada con id: " + id)
        );
        return recetaDtoMapper.toDto(recetaEncontrada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecetaDto> obtenerTodasLasRecetas() {
        return recetaRepository.findAll().stream()
                .map(recetaDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDto crearReceta(RecetaRequestDto recetaRequestDto) {
        Receta receta = recetaDtoMapper.toEntity(recetaRequestDto);
        receta.setId(0); // Asegurar que sea un nuevo registro
        Receta recetaGuardada = recetaRepository.save(receta);
        return recetaDtoMapper.toDto(recetaGuardada);
    }

    @Override
    public RecetaDto actualizarReceta(int id, RecetaRequestDto recetaRequestDto) {
        Receta recetaExistente = recetaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Receta no encontrada con id: " + id)
        );
        
        // Actualizar solo los campos permitidos (no el ID)
        recetaExistente.setNombre(recetaRequestDto.getNombre());
        recetaExistente.setPeso(recetaRequestDto.getPeso());
        recetaExistente.setCaloriasPorRacion(recetaRequestDto.getCaloriasPorRacion());
        
        Receta recetaActualizada = recetaRepository.save(recetaExistente);
        return recetaDtoMapper.toDto(recetaActualizada);
    }

    @Override
    public void eliminarReceta(int id) {
        if (!recetaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receta no encontrada con id: " + id);
        }
        recetaRepository.deleteById(id);
    }

}
