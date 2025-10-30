package acosta_bonafede_spadola_unzaga.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acosta_bonafede_spadola_unzaga.Entities.Provincia;

@Repository
public interface IProvinciaRepo extends JpaRepository<Provincia, Long> {

	/**
	 * busca las ciudades que coincidan con el id indicado
	 * @param nombre
	 * @return
	 */
	List<Provincia> findByNombre(String nombre);
	/**
	 * busca las provincias que coincidan con el nombre indicado y no coincidan con el id indicado
	 * @param nombre
	 * @param idDistintoDe
	 * @return
	 */
	List<Provincia> findByNombreAndIdNot(String nombre,Long idDistintoDe);
	
}
