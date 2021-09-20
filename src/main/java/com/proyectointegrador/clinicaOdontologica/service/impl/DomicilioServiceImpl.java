package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.DomicilioDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IDomicilioRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DomicilioServiceImpl implements IService<DomicilioDTO> {
    @Autowired
    private IDomicilioRepository domicilioRepository;

    public DomicilioServiceImpl(){}

    @Override
    public DomicilioDTO registrar(DomicilioDTO d) {
        return new DomicilioDTO(domicilioRepository.save(d.toEntity()));
    }

    @Override
    public DomicilioDTO buscarPorID(Integer id) {
        return new DomicilioDTO(domicilioRepository.getById(id));
    }

    @Override
    public List<DomicilioDTO> buscarTodos() {
        List<DomicilioDTO> domicilios = new ArrayList<>();
        for(Domicilio d: domicilioRepository.findAll()){
            domicilios.add(new DomicilioDTO(d));
        }
        return domicilios;
    }

    @Override
    public void eliminarPorID(Integer id) {
        domicilioRepository.deleteById(id);
    }

    @Override
    public DomicilioDTO actualizar(DomicilioDTO d) {
       Domicilio actualizado = null;
        if(domicilioRepository.findById(d.getId()).isPresent()){
            actualizado = domicilioRepository.save(d.toEntity());
        }
        return new DomicilioDTO(actualizado);
    }
}
