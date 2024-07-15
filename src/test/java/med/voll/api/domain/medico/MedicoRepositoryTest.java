// package med.voll.api.domain.medico;

// import static org.junit.jupiter.api.Assertions.assertAll;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.mockito.ArgumentMatchers.assertArg;

// import java.time.DayOfWeek;
// import java.time.LocalDate;
// import java.time.temporal.TemporalAdjuster;
// import java.time.temporal.TemporalAdjusters;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
// import org.springframework.test.context.ActiveProfiles;

// import lombok.experimental.var;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @ActiveProfiles("test")
// public class MedicoRepositoryTest {
//     @Autowired
//     private MedicoRepository medicoRepository;

//     @Autowired
//     private TestEntityManager en;

//     @Test
//     @DisplayName("debería retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
//     void testSeleccionarMedicoConEspecialidadEnFechaEscenario1() {
//         // var proximoLunes10H = LocalDate.now()
//         //     .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
//         //     .atTime(10,0);

//         // var medico =
//         // var paciente = 
//         // registrarConsulta(medico,paciente,proximoLunes10H);

//         // var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10H);

//         // // assertThat(medicoLibre).isNull();
//         // // assertNotNull(medicoLibre);
//         // assertNull(medicoLibre);
//     }
// }
