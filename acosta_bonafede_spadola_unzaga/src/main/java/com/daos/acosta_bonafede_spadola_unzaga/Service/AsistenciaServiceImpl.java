package com.daos.acosta_bonafede_spadola_unzaga.Service;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.AsistenciaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.DAO.AsistidoDAO;
import com.daos.acosta_bonafede_spadola_unzaga.DAO.RacionRepository;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.ResourceNotFoundException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaRequestDto;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia.AsistenciaResponseDto;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistido;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Racion;
import com.daos.acosta_bonafede_spadola_unzaga.mapper.AsistenciaMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final AsistidoDAO asistidoRepository;
    private final RacionRepository racionRepository;
    private final AsistenciaMapper asistenciaMapper;

    @Override
    @Transactional
    public AsistenciaResponseDto crear(AsistenciaRequestDto dto){
        Asistido asistido = asistidoRepository.findById(dto.getIdAsistido())
                .orElseThrow(() -> new ResourceNotFoundException("Asistido no encontrado con ID: " + dto.getIdAsistido()));

        Racion racion = racionRepository.findById(dto.getIdRacion())
                .orElseThrow(() -> new ResourceNotFoundException("Ración no encontrada con ID: " + dto.getIdRacion()));
        if (dto.getFechaEntrega().isAfter(racion.getFechaVencimiento())) {
            throw new IllegalArgumentException("La fecha de entrega (" + dto.getFechaEntrega() +
                    ") no puede ser posterior al vencimiento de la ración (" + racion.getFechaVencimiento() + ").");
        }
        Asistencia asistencia = new Asistencia();
        asistencia.setAsistido(asistido);
        asistencia.setRacion(racion);
        asistencia.setFechaEntrega(dto.getFechaEntrega());

        Asistencia guardada = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDto(guardada);
    }
    @Override
    @Transactional
    public AsistenciaResponseDto actualizar(Long id, AsistenciaRequestDto dto) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada con ID: " + id));

        Asistido asistido = asistidoRepository.findById(dto.getIdAsistido())
                .orElseThrow(() -> new ResourceNotFoundException("Asistido no encontrado"));

        Racion racion = racionRepository.findById(dto.getIdRacion())
                .orElseThrow(() -> new ResourceNotFoundException("Ración no encontrada"));

        if (dto.getFechaEntrega().isAfter(racion.getFechaVencimiento())) {
            throw new IllegalArgumentException("La fecha de entrega no puede ser posterior al vencimiento de la ración.");
        }

        asistencia.setAsistido(asistido);
        asistencia.setRacion(racion);
        asistencia.setFechaEntrega(dto.getFechaEntrega());

        return asistenciaMapper.toDto(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaResponseDto obtenerPorId(Long id) {
        return asistenciaRepository.findById(id)
                .map(asistenciaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada"));
    }

    @Override
    public List<AsistenciaResponseDto> obtenerTodas() {
        return asistenciaRepository.findAll().stream()
                .map(asistenciaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asistencia no encontrada");
        }
        asistenciaRepository.deleteById(id);
    }
}
