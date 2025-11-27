package com.daos.acosta_bonafede_spadola_unzaga.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daos.acosta_bonafede_spadola_unzaga.DAO.AsistidoDAO;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;
import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistido;
import com.daos.acosta_bonafede_spadola_unzaga.ExceptionPersonal.CheckedException;

@Service
public class AsistidoServiceImp implements AsistidoService {

    @Autowired
    private AsistidoDAO repoAsistido; 
    
    @Override
    @Transactional
    public Asistido insert(Asistido asistido) throws CheckedException {
        
        // Establecer fecha de registro si es nuevo
        if (asistido.getId() == null && asistido.getFechaRegistro() == null) {
            asistido.setFechaRegistro(LocalDate.now());
        }
        

        if (asistido.getDni() != null) {
            // Solo verifica unicidad si el DNI fue proporcionado
            Asistido existingAsistido = repoAsistido.findByDni(asistido.getDni());
            if (existingAsistido != null && (asistido.getId() == null || !existingAsistido.getId().equals(asistido.getId()))) {
                throw new CheckedException("El DNI " + asistido.getDni() + " ya está registrado.", "dni");
            }
        }
        
     // 3. ESTABLECER LA RELACIÓN BIDIRECCIONAL (NUEVA LÓGICA)
        // Esto asegura que la clave foránea (asistido_id) se establezca en cada Asistencia.
        if (asistido.getAsistencias() != null && !asistido.getAsistencias().isEmpty()) {
            for (Asistencia asistencia : asistido.getAsistencias()) {
                // Utilizamos el setter directo o el método addAsistencia de la entidad Asistido,
                // si el mapeo del DTO al POJO no lo hizo automáticamente.
                // Si usamos el setter directo (setAsistido), es suficiente para la persistencia.
                asistencia.setAsistido(asistido); 
            }
        }
        
        // Guardar (insertar)
        return repoAsistido.save(asistido);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombreCompletoButNotId(String nombreCompleto, Long id) {           
        return !repoAsistido.findByNombreAndIdNot(nombreCompleto, id).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDniButNotId(Long dni, Long id) {
     
        return repoAsistido.findByDniAndIdNot(dni, id).isPresent();
    }


    @Override
	@Transactional
	public Asistido update(Asistido asistido) {
    	
    	// 3. ESTABLECER LA RELACIÓN BIDIRECCIONAL (NUEVA LÓGICA)
        // Esto asegura que la clave foránea (asistido_id) se establezca en cada Asistencia.
        if (asistido.getAsistencias() != null && !asistido.getAsistencias().isEmpty()) {
            for (Asistencia asistencia : asistido.getAsistencias()) {
                // Utilizamos el setter directo o el método addAsistencia de la entidad Asistido,
                // si el mapeo del DTO al POJO no lo hizo automáticamente.
                // Si usamos el setter directo (setAsistido), es suficiente para la persistencia.
                asistencia.setAsistido(asistido); 
            }
        }	
       
       return repoAsistido.save(asistido);
	}
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Asistido> getById(Long id) {      
        Optional<Asistido> asistidoOptional = repoAsistido.findById(id); 

        if (asistidoOptional.isPresent()) {
            Asistido asistido = asistidoOptional.get();
            
            // FORZAR LA CARGA DE LA COLECCIÓN (Inicialización Eager)
            // Esto evita el LazyInitializationException
            if (asistido.getAsistencias() != null) {
                asistido.getAsistencias().size(); 
            }
        }
        return asistidoOptional;       
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Asistido> getAll() {
    	
        return repoAsistido.findAll();
    }


    @Override
    @Transactional
    public void logicalErase(Long id) throws CheckedException {
        // Lógica de borrado lógico (inactivar)
        Asistido asistido = repoAsistido.findById(id).orElse(null); 
        
        if (asistido == null) {
            throw new CheckedException("Asistido no encontrado con ID: " + id, "id");
        }
        asistido.setEstaActiva(false);
        
        repoAsistido.save(asistido);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        // Método de borrado físico, no pedido.
        repoAsistido.deleteById(id);
    }

    // --- MÉTODOS DE VALIDACIÓN DE UNICIDAD ---
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByNombreCompleto(String nombreCompleto) {    
      
        return !repoAsistido.findByNombreContaining(nombreCompleto).isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDni(Long dni) {
      
        return repoAsistido.findByDni(dni) != null;
    }

}