package com.daos.acosta_bonafede_spadola_unzaga.Service;


import java.util.List;
import java.util.Optional;

import com.daos.acosta_bonafede_spadola_unzaga.entity.Ciudad;

import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.Excepcion;
import com.daos.acosta_bonafede_spadola_unzaga.Presentation.ciudades.CiudadesBuscarForm;
/**
 * Clase que permite gestionar la entidad Ciudad en el sistema.
 * @author kuttel
 * @version 1.0
 */

public interface CiudadService {

	

	/**
	 * Obtiene la lista completa de ciudades
	 * @return Todas las ciudades
	 */
	List<Ciudad> getAll();
	
	/**
	 * Obtiene una ciudad determinada
	 * @param idCiudad Identificador de la ciudad buscada
	 * @return Ciudad encontrada
	 */
	Optional<Ciudad> getById(Long idCiudad) throws Excepcion;
	
	List<Ciudad> filter(CiudadesBuscarForm filter) throws Excepcion;

	void deleteByid(Long id);

	void save(Ciudad c) throws Excepcion;


	/**
	 * Elimina una ciudad del sistema
	 * @param id id de la ciudad a eliminar
	 */
	void delete(Long id);
	/**
	 * Crea una nueva ciudad
	 * @param p
	 */
	void insert(Ciudad c) throws Exception;
	/**
	 * Actualiza datos de una ciudad
	 * @param p
	 */
	void update(Ciudad c);

}
