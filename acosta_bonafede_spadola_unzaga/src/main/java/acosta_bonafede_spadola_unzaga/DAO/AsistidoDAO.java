package acosta_bonafede_spadola_unzaga.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistidoDAO extends JpaRepository<Asistido, Long> {

    // --- Métodos de Lectura (Usados en Service y Controller) ---  
   Optional<Asistido> findById(Long id);
    // findByDni(Long dni) es usado para unicidad.
   Asistido findByDni(Long dni);
    // --- Métodos de Validación de Unicidad (Nombre Completo) ---
    /**
     * Busca asistidos por nombre y apellido combinados para la validación de "Nombre Completo" único.
     * La entidad Asistido hereda 'nombre' y 'apellido' de Persona.
     * @param nombre Nombre del asistido.
     * @param apellido Apellido del asistido.
     * @return Una lista de Asistidos que coinciden (debe ser 0 o 1).
     */
    List<Asistido> findByNombreAndApellido(String nombre, String apellido);
	List<Asistido> findByNombreContaining(String nombre);	
	List<Asistido> findByNombreAndApellidoAndIdNot(String nombre, String apellido, Long id);
	Optional<Asistido> findByDniAndIdNot(Long dni, Long id);
	
	
}
