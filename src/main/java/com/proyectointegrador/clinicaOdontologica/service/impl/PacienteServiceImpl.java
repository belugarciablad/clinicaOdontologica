package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.IPacienteRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

    @Autowired
    private IPacienteRepository pacienteRepository;

    public PacienteServiceImpl() {}

    @Override
    public PacienteDTO registrar(PacienteDTO p) {
        return new PacienteDTO(pacienteRepository.save(p.toEntity()));
    }

    @Override
    public PacienteDTO buscarPorID(Integer id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if(paciente != null) {
            return new PacienteDTO(pacienteRepository.getById(id));
        }else{
            throw new ResourceNotFoundException("Paciente con id "+ id+ " no encontrado");
        }
    }

    @Override
    public List<PacienteDTO> buscarTodos() {
        List<PacienteDTO> pacientes = new ArrayList<>();
        for(Paciente p: pacienteRepository.findAll()){
            pacientes.add(new PacienteDTO(p));
        }
        return pacientes;
    }

    @Override
    public void eliminarPorID(Integer id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if(paciente != null) {
            pacienteRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Paciente con id "+ id+ " no encontrado");
        }
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO p) throws BadRequestException {
        Paciente actualizado = null;
        if(pacienteRepository.findById(p.getId()).isPresent()){
            actualizado = pacienteRepository.save(p.toEntity());
        }else{
            throw new BadRequestException("paciente inexistente");
        }
        return new PacienteDTO(actualizado);
    }
}
