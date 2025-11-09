package com.daos.acosta_bonafede_spadola_unzaga.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.AsistidoDAO;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistido;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;

@Service
public class AsistidoServiceImp implements AsistidoService {

    @Autowired
    private AsistidoDAO repoAsistido; 
    
    // --- MÉTODOS DE LA INTERFAZ ---

    /**
     * Renombrado de 'save' a 'insert' para claridad en el contexto REST,
     * manteniendo la lógica de validación de DNI de tu código original.
     */
    @Override
    @Transactional
    public Asistido insert(Asistido asistido) throws CheckedException {
        
        // Establecer fecha de registro si es nuevo
        if (asistido.getId() == null && asistido.getFechaRegistro() == null) {
            asistido.setFechaRegistro(LocalDate.now());
        }
        
     // CÓDIGO CORREGIDO PARA PERMITIR DNI NULO:
        if (asistido.getDni() != null) { // <--- Ya no hay 'else' que lance la excepción
            // Solo verifica unicidad si el DNI fue proporcionado
            Asistido existingAsistido = repoAsistido.findByDni(asistido.getDni());
            if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
                throw new CheckedException("El DNI " + asistido.getDni() + " ya está registrado.", "dni");
            }
        }
        
        // Guardar (insertar)
        return repoAsistido.save(asistido);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombreCompletoButNotId(String nombreCompleto, Long id) {           
        return !repoAsistido.findByNombreAndIdNot(nombreCompleto, id).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDniButNotId(Long dni, Long id) {
        // Se asume la existencia de findByDniAndIdNot en el DAO.
        return repoAsistido.findByDniAndIdNot(dni, id).isPresent();
    }


    @Override
	@Transactional
	public Asistido update(Asistido asistido) {
        // Asumo que las validaciones de unicidad de DNI y Nombre Completo
        // se manejan en el RestController antes de llamar a update.
        // Si no, la lógica de validación de DNI de 'insert' debe estar aquí también.
        
        // 1. Guardar (actualizar)
       return repoAsistido.save(asistido);
	}
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Asistido> getById(Long id) {
        // En tu implementación original usabas findById(id) que devolvía Asistido.
        // Se adapta a devolver Optional<Asistido> para ser consistente con el patrón de CiudadRestController.java.
        // Asumo que AsistidoDAO extiende JpaRepository, que tiene findById que devuelve Optional.
        return repoAsistido.findById(id); 
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Asistido> getAll() {
        // Retorna todos los asistidos, asumiendo findAll() en el DAO.
        return repoAsistido.findAll();
    }


    @Override
    @Transactional
    public void logicalErase(Long id) throws CheckedException {
        // Lógica de borrado lógico (inactivar) de tu código original
        Asistido asistido = repoAsistido.findById(id).orElse(null); // Uso de Optional para ser más robusto.
        
        if (asistido == null) {
            throw new CheckedException("Asistido no encontrado con ID: " + id, "id");
        }
        asistido.setEstaActiva(false);
        
        repoAsistido.save(asistido);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        // Método de borrado físico, para ser consistente con CiudadRestController.java.
        repoAsistido.deleteById(id);
    }


    // --- MÉTODOS DE VALIDACIÓN DE UNICIDAD ---
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombreCompleto(String nombreCompleto) {    
        // CORRECCIÓN: Usamos isEmpty() en la lista y negamos el resultado.
        return !repoAsistido.findByNombreContaining(nombreCompleto).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDni(Long dni) {
        // Verifica si el DNI existe (uso de tu método findByDni y conversión a booleano).
        return repoAsistido.findByDni(dni) != null;
    }

}