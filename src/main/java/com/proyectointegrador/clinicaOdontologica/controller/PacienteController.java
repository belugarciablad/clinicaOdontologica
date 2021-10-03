package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.service.impl.PacienteServiceImpl;
import org.apache.log4j.Logger;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO paciente){
        log.debug("Registrando al paciente con id: " + paciente.getId());
        paciente.setFechaIngreso(LocalDate.now());
        return ResponseEntity.ok(pacienteService.registrar(paciente));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        ResponseEntity<PacienteDTO> response = null;
        log.debug("Buscando al paciente con id: " + id);
        if(pacienteService.buscarPorID(id) !=null){
            response = ResponseEntity.ok(pacienteService.buscarPorID(id));
            log.info("Se encontró al paciente con id: " + id);
        }else{
            response = ResponseEntity.notFound().build();
            log.info("No se encontró al paciente con id: " + id);
        }
        return response;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> buscarTodos() {
        log.debug("Buscando todos los pacientes");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO p) throws BadRequestException, ResourceNotFoundException {
        log.debug("Actualizando al paciente con id:"+ p.getId());
        ResponseEntity<PacienteDTO> response = null;
        if(pacienteService.actualizar(p)== null){
            response = ResponseEntity.notFound().build();
            log.info("No se encontró al paciente con id: " + p.getId());
        }else{
            p.setFechaIngreso(LocalDate.now());
            response = ResponseEntity.ok(pacienteService.buscarPorID(p.getId()));
        }
        return response;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) throws ResourceNotFoundException {

        log.debug("Eliminando al paciente con id:"+ id);ResponseEntity<String> response;

        if (pacienteService.buscarPorID(id) != null) {
            pacienteService.eliminarPorID(id);
            response = ResponseEntity.ok("Se eliminó el paciente con id " + id);
            log.info("No se encontró al paciente con id: " + id);
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Paciente con id: " + id+ " eliminado");
        }
        return response;
    }
}
