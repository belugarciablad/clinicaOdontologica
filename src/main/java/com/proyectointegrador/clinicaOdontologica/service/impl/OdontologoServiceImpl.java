package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IOdontologoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OdontologoServiceImpl implements IService<OdontologoDTO> {

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Override
    public OdontologoDTO registrar(OdontologoDTO o) {
        return new OdontologoDTO(odontologoRepository.save(o.toEntity()));
    }

    @Override
    public OdontologoDTO buscarPorID(Integer id) {
         return new OdontologoDTO(odontologoRepository.getById(id));
    }

    @Override
    public List<OdontologoDTO> buscarTodos() {
        List<OdontologoDTO> odontologos = new ArrayList<>();
        for(Odontologo o: odontologoRepository.findAll()){
            odontologos.add(new OdontologoDTO(o));
        }
        return odontologos;
    }

    @Override
    public void eliminarPorID(Integer id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO o) {
        Odontologo actualizado = null;
        if(odontologoRepository.findById(o.getId()).isPresent()){
            actualizado = odontologoRepository.save(o.toEntity());
        }
        return new OdontologoDTO(actualizado);
    }
}
