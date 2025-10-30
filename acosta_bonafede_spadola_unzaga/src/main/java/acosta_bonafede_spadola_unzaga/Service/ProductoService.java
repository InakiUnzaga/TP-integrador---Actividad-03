package acosta_bonafede_spadola_unzaga.Service;

import java.util.List;

import acosta_bonafede_spadola_unzaga.Entities.Producto;
import acosta_bonafede_spadola_unzaga.ExceptionPersonal.Excepcion;
import acosta_bonafede_spadola_unzaga.Presentation.productos.ProductosBuscarForm;

public interface ProductoService {



	List<Producto> getAll();

	List<Producto> filter(ProductosBuscarForm filter) throws Excepcion;

	/**
	 * Si la persona existe la actualizará, sino la creará en BD
	 * @param persona
	 * @throws Exception 
	 */
	void save(Producto entity) throws Excepcion;

	/**
	 * permite obtener una persona determinada 
	 * @param idPersona identificador de la persona buscada
	 * @return persona encontrada o null si no encontr{o la persona
	 * @throws Exception ante un error
	 */
	Producto getProductoById(Long id) throws Exception;

	void deleteProductoByid(Long id);

	List<Producto> getProductosByNombre(String nombre);

	
}
