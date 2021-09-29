package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.service.impl.TurnoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    final static Logger log = Logger.getLogger(PacienteController.class);

    @Autowired
    TurnoServiceImpl turnoService;


    @PostMapping("/registrar")
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno) throws BadRequestException, ResourceNotFoundException {
        log.debug("Registrando al turno con id: " + turno.getId());
        return ResponseEntity.ok(turnoService.registrar(turno));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Integer id){
        ResponseEntity<TurnoDTO> response = null;
        log.debug("Buscando al Turno con id: " + id);
        if(turnoService.buscarPorID(id) !=null){
            response = ResponseEntity.ok(turnoService.buscarPorID(id));
            log.info("Se encontró al Turno con id: " + id);
        }else{
            response = ResponseEntity.notFound().build();
            log.info("No se encontró al Turno con id: " + id);
        }
        return response;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        log.debug("Buscando todos los Turnos");
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDTO> actualizar(@RequestBody TurnoDTO t){
        log.debug("Actualizando al Turno con id:"+ t.getId());
        ResponseEntity<TurnoDTO> response = null;
        if(turnoService.actualizar(t)!= null){
            response = ResponseEntity.ok(turnoService.actualizar(t));
            log.info("Turno con id: " + t.getId() + "actualizado.");
        }else{
            response = ResponseEntity.notFound().build();
            log.info("No se encontró al Turno con id: " + t.getId());
        }
        return response;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

        log.debug("Eliminando al Turno con id:"+ id);ResponseEntity<String> response;

        if (turnoService.buscarPorID(id) != null) {
            turnoService.eliminarPorID(id);
            response = ResponseEntity.ok("Se eliminó el Turno con id " + id);
            log.info("No se encontró al Turno con id: " + id);
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Turno con id: " + id+ " eliminado");
        }
        return response;
    }
}
