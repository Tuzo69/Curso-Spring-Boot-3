package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

public record DatosDetalleConsulta(
    Long id,
    Long idPaciente,
    Long idMedico,
    LocalDateTime fecha) {

    // public DatosDetalleConsulta(Consulta consulta) {
    //     this(consulta.getId(),consulta.getPaciente(),consulta.getMedico(),consulta.getData());
    // }

}
