package med.voll.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.DatosActualizarMedico;
import med.voll.api.domain.medico.DatosListadoMedico;
import med.voll.api.domain.medico.DatosRegistroMedico;
import med.voll.api.domain.medico.DatosRespuestaMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/medicos")
public class MedicoController {
    
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                        UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = 
            new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad(),
            new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 2, sort = "nombre") Pageable paginacion){
        // return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(
            new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad(),
            new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),
                medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()))
            ); 
    }


    // Delete lógico
    @DeleteMapping("/{id}")
    @Transactional // transactional para guardar cambios a nivel de BBDD
    public ResponseEntity eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
    // Eliminar registro de la base de datos
    // public void eliminarMedico(@PathVariable Long id){
    //     Medico medico = medicoRepository.getReferenceById(id);
    //     medicoRepository.delete(medico);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornarDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        var datosMedicos = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getEspecialidad(),
        new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),medico.getDireccion().getCiudad(),
            medico.getDireccion().getNumero(),medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedicos);
    }
}
