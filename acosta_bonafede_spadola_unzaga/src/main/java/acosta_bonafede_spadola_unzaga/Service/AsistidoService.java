package acosta_bonafede_spadola_unzaga.Service;

import acosta_bonafede_spadola_unzaga.Entities.Asistido;
import acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;

public interface AsistidoService {
    Asistido save(Asistido asistido) throws CheckedException; 
    Asistido update(Asistido asistido);
    Asistido findById(Long id);      
    void logicalErase(Long nroFamilia) throws CheckedException;
}