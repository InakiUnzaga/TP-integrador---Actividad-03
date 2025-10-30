package acosta_bonafede_spadola_unzaga.Service;

import java.util.List;
import java.util.Optional;

import acosta_bonafede_spadola_unzaga.Entities.Provincia;
import acosta_bonafede_spadola_unzaga.ExceptionPersonal.Excepcion;
import acosta_bonafede_spadola_unzaga.Presentation.provincias.ProvinciasBuscarForm;
/**
 * Clase que permite gestionar la entidad Provincia en el sistema.
 * @author kuttel
 * @version 1.0
 */

public interface ProvinciaService {

	

	/**
	 * Obtiene la lista completa de Provincias
	 * @return Todas las Provincias
	 */
	List<Provincia> getAll();
	
	/**
	 * Obtiene una Provincia determinada
	 * @param idCiudad Identificador de la Provincia buscada
	 * @return Provincia encontrada
	 */
	Optional<Provincia> getById(Long idProv) ;

	List<Provincia> filter(ProvinciasBuscarForm filter) throws Excepcion;
	
	void deleteByid(Long id);

	void save(Provincia c) throws Excepcion;
}
