package acosta_bonafede_spadola_unzaga.Service;

import java.util.List;

import acosta_bonafede_spadola_unzaga.Entities.Familia;
import acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;

public interface FamiliaService {
	
    Familia getBynroFamilia(Long nroFamilia) throws CheckedException;	
	
	List<Familia> buscarFamilias(Long nroFamilia, String nombre);
	List<Familia> findAllActivas();		
	
	void logicalErase(Long nroFamilia) throws CheckedException;	
	void save(Familia f) throws CheckedException;	
	void update(Familia f) throws CheckedException; 	
	void deleteFamilia(Long nroFamilia); 	
    void activarFamilia(Long nroFamilia) throws CheckedException;
      
}
