package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.RecetaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.ResourceNotFoundException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Receta;
import com.daos.acosta_bonafede_spadola_unzaga.mapper.RecetaDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;

    private final RecetaDtoMapper recetaDtoMapper;

    @Override
    public RecetaDto obtenerReceta(int id) {
        Receta recetaEncontrada = recetaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("receta no encontrada")
        );

        return recetaDtoMapper.toDto(recetaEncontrada);

    }

}
