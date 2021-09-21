package com.proyectointegrador.clinicaOdontologica.controller;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.service.impl.OdontologoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    OdontologoServiceImpl odontologoService;

    @PostMapping("/registrar")
    public ResponseEntity<OdontologoDTO> registrarOdontologo(@RequestBody OdontologoDTO o){
        log.debug("Registrando al odontólogo con id: " + o.getId());
        return ResponseEntity.ok(odontologoService.registrar(o));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable Integer id){
        log.debug("Buscando al odontólogo con id: " + id);
        ResponseEntity<OdontologoDTO> response = null;

        if(odontologoService.buscarPorID(id) !=null){
            response = ResponseEntity.ok(odontologoService.buscarPorID(id));
            log.info("Se encontró al odontólogo con id: " + id);
        }else{
             ResponseEntity.notFound().build();
            log.info("No se encontró al odontólogo con id: " + id);
        }
        return response;
    }
    @GetMapping("/todos")
    public ResponseEntity<List<OdontologoDTO>> buscarTodos(){
        log.debug("Buscando todos los odontólogos");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDTO> actualizar(@RequestBody OdontologoDTO o){
        log.debug("Actualizando al odontólogo con id:"+ o.getId());
        ResponseEntity<OdontologoDTO> response = null;
        if(odontologoService.actualizar(o)!= null){
            response = ResponseEntity.ok(odontologoService.actualizar(o));
            log.info("Odontologo con id: " + o.getId() + "actualizado.");
        }else{
            response = ResponseEntity.notFound().build();
            log.info("No se encontró al odontólogo con id: " + o.getId());
        }
        return response;
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.debug("Eliminando al odontólogo con id:"+ id);
        ResponseEntity<String> response;

        if (odontologoService.buscarPorID(id) != null) {
            odontologoService.eliminarPorID(id);
            response = ResponseEntity.ok("Se eliminó el odontólogo con id " + id);
            log.info("No se encontró al odontólogo con id: " + id);
        } else {
            response = ResponseEntity.notFound().build();
            log.info("Odontólogo con id: " + id+ " eliminado");
        }
        return response;
    }
}
