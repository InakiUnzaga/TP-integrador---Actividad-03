package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion;

import com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta.RecetaRestController;
import com.daos.acosta_bonafede_spadola_unzaga.Service.RacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@RequestMapping("/raciones")
public class RacionRestController {

    private final RacionService racionService;

    @GetMapping("{id}")
    public ResponseEntity<RacionDto> obtenerRacion(@PathVariable int id) {
        RacionDto racionDto = racionService.obtenerRacion(id);
        return ResponseEntity.ok(construirRespuestaGet(racionDto));
    }

    @PostMapping
    public ResponseEntity<RacionDto> crearRacion(@Valid @RequestBody RacionRequestDto racionNueva) {
        RacionDto racionDto = racionService.crearNuevaRacion(racionNueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(construirRespuesta(racionDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<RacionDto> actualizarRacion(@Valid @RequestBody RacionRequestDto racionActualizar, @PathVariable int id) {
        RacionDto racionDto = racionService.actualizarRacion(id, racionActualizar);
        return ResponseEntity.ok(construirRespuesta(racionDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarRacion(@PathVariable int id) {
        racionService.eliminarRacion(id);
        return ResponseEntity.noContent().build();
    }

    private RacionDto construirRespuestaGet(RacionDto dto) {
        RacionDto respuesta = construirRespuesta(dto);

        respuesta.add(linkTo(methodOn(RecetaRestController.class).obtenerReceta(dto.getReceta().getId())).withRel("receta").withType("GET"));

        return respuesta;
    }

    private RacionDto construirRespuesta(RacionDto dto) {
        RacionRequestDto racionFalsa = new RacionRequestDto();

        dto.add(linkTo(methodOn(RacionRestController.class).obtenerRacion(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(RacionRestController.class).crearRacion(racionFalsa)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(RacionRestController.class).actualizarRacion(racionFalsa, dto.getId())).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(RacionRestController.class).eliminarRacion(dto.getId())).withRel("delete").withType("DELETE"));

        return dto;
    }

}
