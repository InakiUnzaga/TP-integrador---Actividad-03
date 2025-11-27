package com.daos.acosta_bonafede_spadola_unzaga.DAO;


import com.daos.acosta_bonafede_spadola_unzaga.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
}
