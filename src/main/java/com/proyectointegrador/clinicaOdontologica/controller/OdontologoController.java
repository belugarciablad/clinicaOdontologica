package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.service.impl.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO o){
        return ResponseEntity.ok(odontologoService.registrar(o));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id){
        ResponseEntity<OdontologoDTO> response = null;

        if(odontologoService.buscarPorID(id) !=null){
            response = ResponseEntity.ok(odontologoService.buscarPorID(id));
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO o){
        ResponseEntity<OdontologoDTO> response = null;
        if(odontologoService.actualizar(o)!= null){
            response = ResponseEntity.ok(odontologoService.actualizar(o));
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response;

        if (odontologoService.buscarPorID(id) != null) {
            odontologoService.eliminarPorID(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con id " + id);
        } else {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}
