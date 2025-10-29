package acosta_bonafede_spadola_unzaga.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acosta_bonafede_spadola_unzaga.Entities.Familia;

import java.util.List;
import java.util.Optional;

@Repository
public interface FamiliaDAO extends JpaRepository<Familia,Long> {
				
	List<Familia> findByEstaActivaTrue();
    Optional<Familia> findByNroFamilia(Long nroFamilia);  
    List<Familia> findByNombreContainingIgnoreCase(String nombre);
	List<Familia>findByNombre(String nombre);	
	
}
