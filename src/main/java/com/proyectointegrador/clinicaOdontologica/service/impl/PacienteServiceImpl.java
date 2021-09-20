package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
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
    public PacienteDTO buscarPorID(Integer id) {
        return new PacienteDTO(pacienteRepository.getById(id));
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
    public void eliminarPorID(Integer id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteDTO actualizar(PacienteDTO p) {
        Paciente actualizado = null;
        if(pacienteRepository.findById(p.getId()).isPresent()){
            actualizado = pacienteRepository.save(p.toEntity());
        }
        return new PacienteDTO(actualizado);
    }
}
