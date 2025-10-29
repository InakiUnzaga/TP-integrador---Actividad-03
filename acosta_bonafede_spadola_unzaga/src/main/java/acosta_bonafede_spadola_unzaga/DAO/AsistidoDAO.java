package acosta_bonafede_spadola_unzaga.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;

import java.util.List;


@Repository
public interface AsistidoDAO extends JpaRepository<Asistido,Integer> {
	List<Asistido>findByNombreContaining(String nombre);	
	boolean existsByNombre(String nombre);
    Asistido findByDni(Long dni);
    Asistido findById(Long id);	
}
