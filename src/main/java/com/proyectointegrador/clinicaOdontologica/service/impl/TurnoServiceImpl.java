package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.dto.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.GlobalExceptionsHandler;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.ITurnoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoServiceImpl implements IService<TurnoDTO> {

    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired OdontologoServiceImpl odontologoService;

    @Autowired
    public TurnoServiceImpl(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
    public TurnoServiceImpl(){ }


    @Override
    public TurnoDTO registrar(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        Integer pacienteId = turnoDTO.getPaciente().getId();
        Integer odontologoId = turnoDTO.getOdontologo().getId();
        TurnoDTO turno = null;

        if(pacienteService.buscarPorID(pacienteId) != null|| odontologoService.buscarPorID(odontologoId) != null){
            if(turnoDisponible(turnoDTO)){
                turno = new TurnoDTO(turnoRepository.save(turnoDTO.toEntity()));
            }else{
                throw new BadRequestException("El turno no esta disponible");
            }
        }
        return turno;
    }

    @Override
    public TurnoDTO buscarPorID(Integer id) {
        return new TurnoDTO(turnoRepository.getById(id));
    }

    @Override
    public List<TurnoDTO> buscarTodos() {
        List<TurnoDTO> turnos = new ArrayList<>();
        for(Turno t: turnoRepository.findAll()){
            turnos.add(new TurnoDTO(t));
        }
        return turnos;
    }

    @Override
    public void eliminarPorID(Integer id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) {
        Turno actualizado = null;
        if(turnoRepository.findById(turnoDTO.getId()).isPresent()){
            actualizado = turnoRepository.save(turnoDTO.toEntity());
        }
        return new TurnoDTO(actualizado);
    }

    public boolean turnoDisponible(TurnoDTO turnoDTO){
        Integer pacienteId = turnoDTO.getPaciente().getId();
        Integer odontologoId = turnoDTO.getOdontologo().getId();
        LocalDateTime fechaturno = turnoDTO.getFechaTurno();
//        List<TurnoDTO> turnoDTOs = new ArrayList<>();
        boolean rta = true;
        for(Turno t: turnoRepository.findAll()) {
            if (t.getFechaTurno().equals(fechaturno)) {
                if (t.getPaciente().getId().equals(pacienteId) || t.getOdontologo().getId().equals(odontologoId)) {
//                    TurnoDTO turnoDTO2 = new TurnoDTO(t);
//                    turnoDTOs.add(turnoDTO2);
                    rta = false;
                }
            }

        }
        return rta;
    }
}
