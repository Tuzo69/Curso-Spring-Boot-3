package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);


    @Query(value = """
            SELECT * FROM Medico m
            WHERE m.activo = 1 
            AND m.especialidad = :especialidad 
            AND m.id NOT IN (
                SELECT c.medico_id FROM Consulta c
                WHERE c.data = :fecha
            )
            ORDER BY RAND()
            LIMIT 1
            """, nativeQuery = true)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);


    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(Long idMedico);
}
