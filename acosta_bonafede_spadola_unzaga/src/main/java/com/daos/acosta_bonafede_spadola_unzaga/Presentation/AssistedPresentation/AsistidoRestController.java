package com.daos.acosta_bonafede_spadola_unzaga.Presentation.AssistedPresentation;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistido;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Ciudad;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.Excepcion;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.ciudades.CiudadRestController;
//import acosta_bonafede_spadola_unzaga.Presentation.asistencias.AsistenciaRestController;  //Asumido para link S03
import com.daos.acosta_bonafede_spadola_unzaga.Service.AsistidoService;
import com.daos.acosta_bonafede_spadola_unzaga.Service.CiudadService;
import com.daos.acosta_bonafede_spadola_unzaga.error.MensajeError;


@RestController
@RequestMapping("/asistidos")
@Tag(name = "Asistidos", description = "Gestiona personas asistidas")
public class AsistidoRestController {

	@Autowired
	private AsistidoService service;
	
	@Autowired
	private CiudadService ciudadService;
	
	//private final ObjectMapper objectMapper = new ObjectMapper(); 

	// ------------------------------------ GET ------------------------------------

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<AsistidoResponseDTO>> getAsistidos() throws Exception
	{
		List<Asistido> asistidos = service.getAll();
		List<AsistidoResponseDTO> asistidosDto = new ArrayList<>();
		
		for (Asistido asistido : asistidos) {
			asistidosDto.add(buildResponse(asistido));
		}
		
		return new ResponseEntity<>(asistidosDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AsistidoResponseDTO> getById(@PathVariable Long id) throws Exception
	{
		Optional<Asistido> rta = service.getById(id);
		if(rta.isPresent())
		{
			Asistido pojo = rta.get();
			return new ResponseEntity<>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	// ------------------------------------ POST (Crear) ------------------------------------

	@PostMapping
	public ResponseEntity<Object> insertar( @Valid @RequestBody AsistidoDTO dto, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.formatearError(result));
		}
		
		// Convertir DTO a POJO y buscar Ciudad
		Asistido a = dto.toPojo();
		Optional<Ciudad> c = ciudadService.getById(dto.getIdCiudad());
		
		if(c.isPresent())
			a.setCiudad(c.get());
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
		}
		
		// Validaciones de unicidad (Nombre Completo y DNI)
		
		if(service.existsByNombreCompleto(dto.getNombre())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("04", "Nombre Repetido", "Ya existe un asistido con ese nombre completo."));
		}
		
		// Unicidad del DNI (si se provee)
		if(a.getDni() != null && service.existsByDni(a.getDni())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("05", "DNI Repetido", "Ya existe un asistido con ese DNI."));
		}
		
		// Insertar		
		service.insert(a);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(a.getId()).toUri(); 

		return ResponseEntity.created(location).build(); // 201 Created
	}
	
	// ------------------------------------ PUT (Actualizar) ------------------------------------

	@PutMapping("/{id}")
	public ResponseEntity<Object> actualizar(@Valid @RequestBody AsistidoDTO dto, @PathVariable long id, BindingResult result) throws Exception
	{
		if(result.hasErrors())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.formatearError(result));
		}
		
		Optional<Asistido> rta = service.getById(id);
		if(!rta.isPresent())
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el asistido que desea modificar.");
			
		
		Asistido asistidoOriginal = rta.get();
		
		// Verificar ID y Mapear DTO a POJO
		if(dto.getId() != null && !dto.getId().equals(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("03", "Dato no editable", "No puede modificar el Id."));
		}
		
		Asistido asistidoModificado = dto.toPojo();
		asistidoModificado.setId(id); // Asegurar el ID
		asistidoModificado.setFechaRegistro(asistidoOriginal.getFechaRegistro()); // Preservar fecha de registro
		asistidoModificado.setEstaActiva(asistidoOriginal.isEstaActiva()); // Preservar estado
		
		//Buscar Ciudad
		Optional<Ciudad> c = ciudadService.getById(dto.getIdCiudad());
		if(c.isPresent())
			asistidoModificado.setCiudad(c.get());
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
		
		
       //Validaciones de unicidad (excluyendo el propio registro)
        
       //Nombre Completo
        String nombreOriginal = asistidoOriginal.getNombre();
        String nombreNuevo = dto.getNombre(); // Nombre completo del DTO
     
        if (!nombreOriginal.equalsIgnoreCase(nombreNuevo)) {          
            if (service.existsByNombreCompletoButNotId(nombreNuevo, id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("04", "Nombre Repetido", "Ya existe otro asistido con ese nombre completo."));
            }
        }
        
        if (asistidoModificado.getDni() != null) {
            // Comprobar si el DNI ha cambiado
            if (asistidoOriginal.getDni() == null || !asistidoOriginal.getDni().equals(asistidoModificado.getDni())) {
                // Si ha cambiado, verificar unicidad EXCLUYENDO el ID actual
                if (service.existsByDniButNotId(asistidoModificado.getDni(), id)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("05", "DNI Repetido", "Ya existe otro asistido con ese DNI."));
                }
            }
        }
		
		service.update(asistidoModificado);
		return ResponseEntity.ok(buildResponse(asistidoModificado));
	}
	
	// ------------------------------------ DELETE (Dar de Baja / Inactivar) ------------------------------------

	@DeleteMapping("/{id}")	
	public ResponseEntity<String> eliminar(@PathVariable Long id) throws Excepcion, CheckedException 
	{	   
	   if(!service.getById(id).isPresent()) 
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un asistido con ese id");
	   
	    service.logicalErase(id);     
	    
	    return ResponseEntity.ok().build(); // 200 OK
	}
	
	// ------------------------------------ MÉTODOS AUXILIARES ------------------------------------

	/**
	 * Construye el objeto AsistidoResponseDTO incluyendo los links HATEOAS requeridos.
	 */
	private AsistidoResponseDTO buildResponse(Asistido pojo) throws Excepcion {
		try {
			AsistidoResponseDTO dto = new AsistidoResponseDTO(pojo);
			
			//Self link (GET /asistidos/{id})
			Link selfLink = WebMvcLinkBuilder.linkTo(AsistidoRestController.class)
										.slash(pojo.getId())                
										.withSelfRel();
										
			//Link a la Ciudad (GET /ciudades/{idCiudad}) (servicio implementado en clases)
			Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
			       													.getById(pojo.getCiudad().getId()))
			       													.withRel("ciudadDondeVive");
			       													
			// Link a las Asistencias recibidas (Servicio S03)
			// Se asume la existencia de AsistenciaRestController con el método getAsistenciasByAsistido(id)
			//Link asistenciasLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AsistenciaRestController.class)
			//       													.getAsistenciasByAsistido(pojo.getId())) 
			//       													.withRel("asistenciasRecibidas"); 
			       													
			dto.add(selfLink);
			dto.add(ciudadLink);
			//dto.add(asistenciasLink); 
			
			return dto;
		} catch (Exception e) {
			throw new Excepcion("Error al construir la respuesta HATEOAS: " + e.getMessage(), 500);
		}
	}
	
	/**
	 * Formatea los errores de validación (JSR-303) a JSON (Copiado de CiudadRestController.java)
	 */
	private String formatearError(BindingResult result) throws JsonProcessingException
	{
		List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
															Map<String, String> error= new HashMap<>();
															error.put(err.getField(),err.getDefaultMessage() );
															return error;
														}).collect(Collectors.toList());
		MensajeError e1=new MensajeError();
		e1.setCodigo("01");
		e1.setMensajes(errores);
		
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
	}

	/**
	 * Crea un JSON de error con código y descripción específicos (Copiado de CiudadRestController.java)
	 */
	private String getError(String code, String err, String descr) throws JsonProcessingException
	{
		MensajeError e1=new MensajeError();
		e1.setCodigo(code);
		ArrayList<Map<String,String>> errores=new ArrayList<>();
		Map<String, String> error=new HashMap<String, String>();
		error.put(err, descr);
		errores.add(error);
		e1.setMensajes(errores);
		
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
	}
}