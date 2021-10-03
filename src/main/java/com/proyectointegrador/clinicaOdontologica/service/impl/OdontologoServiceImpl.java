package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
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
    public OdontologoDTO buscarPorID(Integer id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if(odontologo != null) {
        return new OdontologoDTO(odontologoRepository.getById(id));
        }else{
            throw new ResourceNotFoundException("Odontologo con id "+ id+ " no encontrado");
        }
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
    public void eliminarPorID(Integer id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(id).orElse(null);
        if(odontologo != null) {
            odontologoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Odontologo con id "+ id+ " no encontrado");
        }
    }

    @Override
    public OdontologoDTO actualizar(OdontologoDTO o) throws BadRequestException {
        Odontologo actualizado = null;
        if(odontologoRepository.findById(o.getId()).isPresent()){
            actualizado = odontologoRepository.save(o.toEntity());
        }else{
            throw new BadRequestException("odontologo inexistente");
        }
        return new OdontologoDTO(actualizado);
    }
}
