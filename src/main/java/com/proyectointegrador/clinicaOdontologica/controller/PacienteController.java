package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImpl pacienteService;

    @PostMapping("/registrar")
    public ResponseEntity<PacienteDTO> registrarPaciente(@RequestBody PacienteDTO paciente){
//        log.debug("Registrando el paciente con id: " + paciente.getId());
        return ResponseEntity.ok(pacienteService.registrar(paciente));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PacienteDTO>> buscarTodos() {

        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id){
        ResponseEntity<PacienteDTO> response = null;

        if(pacienteService.buscarPorID(id) !=null){
            response = ResponseEntity.ok(pacienteService.buscarPorID(id));
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> actualizar(@RequestBody PacienteDTO p){
        ResponseEntity<PacienteDTO> response = null;
        if(pacienteService.actualizar(p)!= null){
            response = ResponseEntity.ok(pacienteService.actualizar(p));
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response;

        if (pacienteService.buscarPorID(id) != null) {
            pacienteService.eliminarPorID(id);
            response = ResponseEntity.ok("Se eliminó el paciente con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}
