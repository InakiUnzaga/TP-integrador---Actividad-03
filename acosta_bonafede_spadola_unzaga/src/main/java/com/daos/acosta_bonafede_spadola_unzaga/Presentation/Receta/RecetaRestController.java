package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta;

import com.daos.acosta_bonafede_spadola_unzaga.Service.RecetaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@RequestMapping("/recetas")
@Tag(name = "S04 Recetas", description = "Gestión de recetas registradas en la base de datos")
public class RecetaRestController {

    private final RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<RecetaDto>> obtenerTodasLasRecetas() {
        List<RecetaDto> recetas = recetaService.obtenerTodasLasRecetas();
        recetas.forEach(this::construirRespuesta);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDto> obtenerReceta(@PathVariable int id) {
        RecetaDto recetaDto = recetaService.obtenerReceta(id);
        return ResponseEntity.ok(construirRespuestaGet(recetaDto));
    }

    @PostMapping
    public ResponseEntity<RecetaDto> crearReceta(@Valid @RequestBody RecetaRequestDto recetaNueva) {
        RecetaDto recetaDto = recetaService.crearReceta(recetaNueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(construirRespuesta(recetaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDto> actualizarReceta(
            @Valid @RequestBody RecetaRequestDto recetaActualizar,
            @PathVariable int id) {
        RecetaDto recetaDto = recetaService.actualizarReceta(id, recetaActualizar);
        return ResponseEntity.ok(construirRespuesta(recetaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReceta(@PathVariable int id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }

    private RecetaDto construirRespuestaGet(RecetaDto dto) {
        RecetaDto respuesta = construirRespuesta(dto);
        
        // Link para consultar las preparaciones/raciones de esta receta
        respuesta.add(linkTo(RecetaRestController.class)
                .slash(dto.getId())
                .slash("raciones")
                .withRel("raciones")
                .withType("GET"));
        
        return respuesta;
    }

    private RecetaDto construirRespuesta(RecetaDto dto) {
        RecetaRequestDto recetaFalsa = new RecetaRequestDto();

        dto.add(linkTo(methodOn(RecetaRestController.class).obtenerReceta(dto.getId()))
                .withSelfRel()
                .withType("GET"));
        dto.add(linkTo(methodOn(RecetaRestController.class).crearReceta(recetaFalsa))
                .withRel("create")
                .withType("POST"));
        dto.add(linkTo(methodOn(RecetaRestController.class).actualizarReceta(recetaFalsa, dto.getId()))
                .withRel("update")
                .withType("PUT"));
        dto.add(linkTo(methodOn(RecetaRestController.class).eliminarReceta(dto.getId()))
                .withRel("delete")
                .withType("DELETE"));

        return dto;
    }

}
