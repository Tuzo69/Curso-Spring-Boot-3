package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndDataBetween(@NotNull Long idPaciente, LocalDateTime primerHorario,
            LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndData(Long idMedico, @NotNull @Future LocalDateTime fecha);

}
