package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Racion;

import com.daos.acosta_bonafede_spadola_unzaga.Service.RacionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/raciones")
public class RacionRestController {

    private final RacionService racionService;

    @GetMapping("{id}")
    public ResponseEntity<RacionDto> obtenerRacion(@PathVariable int id) {
        return ResponseEntity.ok(racionService.obtenerRacion(id));
    }

    @PostMapping
    public ResponseEntity<RacionDto> crearRacion(@Valid @RequestBody RacionRequestDto racionNueva) {
        RacionDto racion = racionService.crearNuevaRacion(racionNueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(racion);
    }

    @PutMapping("{id}")
    public ResponseEntity<RacionDto> actualizarRacion(@Valid @RequestBody RacionRequestDto racionActualizar, @PathVariable int id) {
        RacionDto racion = racionService.actualizarRacion(id, racionActualizar);
        return ResponseEntity.ok(racion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarRacion(@PathVariable int id) {
        racionService.eliminarRacion(id);
        return ResponseEntity.noContent().build();
    }

}
