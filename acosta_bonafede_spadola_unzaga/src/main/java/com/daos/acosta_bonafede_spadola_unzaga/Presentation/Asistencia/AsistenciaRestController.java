package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Asistencia;


import com.daos.acosta_bonafede_spadola_unzaga.DAO.AsistenciaRepository;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.AssistedPresentation.AsistidoRestController;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaRestController;
import com.daos.acosta_bonafede_spadola_unzaga.Service.AsistenciaService;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asistencias")
@AllArgsConstructor
@Tag(name = "S03 Asistencias", description = "Gestión de entregas de raciones a asistidos")
public class AsistenciaRestController {
    private final AsistenciaService asistenciaService;
    private final AsistenciaRepository asistenciaRepository;

    @PostMapping
    public ResponseEntity<AsistenciaResponseDto> crear(@Valid @RequestBody AsistenciaRequestDto dto) throws Exception {
        AsistenciaResponseDto response = asistenciaService.crear(dto);
        agregarLinks(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody AsistenciaRequestDto dto) throws Exception {
        AsistenciaResponseDto response = asistenciaService.actualizar(id, dto);
        agregarLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaResponseDto> obtenerPorId(@PathVariable Long id) throws Exception {
        AsistenciaResponseDto response = asistenciaService.obtenerPorId(id);
        agregarLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaResponseDto>> obtenerTodas() {
        List<AsistenciaResponseDto> lista = asistenciaService.obtenerTodas();
        lista.forEach(this::agregarLinks);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) throws Exception {
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private void agregarLinks(AsistenciaResponseDto dto) {
        try {
            // 1. Self Link
            Link selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(AsistenciaRestController.class).obtenerPorId(dto.getId()))
                    .withSelfRel();
            dto.add(selfLink);

            // 2. Links a Entidades Relacionadas (S01 y S04)
            // Todo esto debe estar DENTRO del bloque try
            Asistencia entity = asistenciaRepository.findById(dto.getId()).orElse(null);

            if (entity != null) {
                Link asistidoLink = WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(AsistidoRestController.class)
                                        .getById(entity.getAsistido().getId()))
                        .withRel("consultar-asistido");
                dto.add(asistidoLink);

                Link recetaLink = WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(RecetaRestController.class)
                                        .obtenerReceta(entity.getRacion().getReceta().getId()))
                        .withRel("consultar-receta");
                dto.add(recetaLink);
            }

        } catch (Exception e) {
            // El catch atrapa cualquier error ocurrido dentro del bloque try
            System.err.println("Error creando links HATEOAS: " + e.getMessage());
        }
    }
}
