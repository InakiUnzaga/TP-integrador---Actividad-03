package acosta_bonafede_spadola_unzaga.Service;

import java.util.List;
import java.util.Optional;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;
import acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;

public interface AsistidoService {
    
    /**
     * Inserta un nuevo asistido.
     * @param asistido el asistido a guardar.
     * @return El asistido guardado.
     * @throws CheckedException si el DNI está duplicado (la implementación actual lo requiere).
     */
    Asistido insert(Asistido asistido) throws CheckedException; 
    
    /**
     * Actualiza un asistido existente.
     * @param asistido el asistido a actualizar.
     * @return El asistido actualizado.
     */
    Asistido update(Asistido asistido);
    
    /**
     * Busca un asistido por su ID.
     * @param id el ID del asistido.
     * @return Un Optional que contiene el asistido si existe.
     */
    Optional<Asistido> getById(Long id);      
    
    /**
     * Obtiene todos los asistidos.
     * @return Una lista de todos los asistidos.
     */
    List<Asistido> getAll();
    
    /**
     * Elimina un asistido lógicamente (inactiva).
     * @param id el ID del asistido a inactivar.
     * @throws CheckedException si el asistido no es encontrado.
     */
    void logicalErase(Long id) throws CheckedException;
    
    /**
     * Elimina un asistido físicamente (para ser consistente con CiudadRestController.java).
     * Si se usa borrado lógico, este método no sería necesario.
     * @param id el ID del asistido a borrar.
     */
    void delete(Long id); 
    
    /**
     * Verifica si existe un asistido con el nombre completo especificado.
     * Necesario para la validación de unicidad en POST/PUT.
     * @param nombreCompleto el nombre completo a verificar.
     * @return true si existe un asistido con ese nombre, false en caso contrario.
     */
    boolean existsByNombreCompleto(String nombreCompleto);

    /**
     * Verifica si existe un asistido con el DNI especificado.
     * Necesario para la validación de unicidad en POST/PUT.
     * @param dni el DNI a verificar.
     * @return true si existe un asistido con ese DNI, false en caso contrario.
     */
    boolean existsByDni(Long dni); 
        
    /**
     * Verifica si existe un asistido con el nombre completo, excluyendo el ID proporcionado.
     */
    boolean existsByNombreCompletoButNotId(String nombreCompleto, Long id);

    /**
     * Verifica si existe un asistido con el DNI, excluyendo el ID proporcionado.
     */
    boolean existsByDniButNotId(Long dni, Long id);
    
    
    
    
    
    
    
}