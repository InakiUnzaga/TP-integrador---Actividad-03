package com.daos.acosta_bonafede_spadola_unzaga.Presentation.Receta;

import com.daos.acosta_bonafede_spadola_unzaga.Service.RecetaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/recetas")
public class RecetaRestController {

    private RecetaService recetaService;

    @GetMapping("{id}")
    public ResponseEntity<RecetaDto> obtenerReceta(@PathVariable int id) {
        return ResponseEntity.ok(recetaService.obtenerReceta(id));
    }

}
