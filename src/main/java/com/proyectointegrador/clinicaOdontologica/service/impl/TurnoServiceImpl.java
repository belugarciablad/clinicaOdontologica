package com.proyectointegrador.clinicaOdontologica.service.impl;

import com.proyectointegrador.clinicaOdontologica.dto.OdontologoDTO;
import com.proyectointegrador.clinicaOdontologica.dto.PacienteDTO;
import com.proyectointegrador.clinicaOdontologica.dto.TurnoDTO;
import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.GlobalExceptionsHandler;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import com.proyectointegrador.clinicaOdontologica.persistence.repositories.ITurnoRepository;
import com.proyectointegrador.clinicaOdontologica.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Integer paciente_id = turnoDTO.getPaciente().getId();
        Integer odontologo_id = turnoDTO.getOdontologo().getId();
        PacienteDTO paciente = pacienteService.buscarPorID(paciente_id);
        OdontologoDTO odontologo = odontologoService.buscarPorID(odontologo_id);
        TurnoDTO turno = null;

        if(paciente != null|| odontologo != null){
            if(turnoDisponible(turnoDTO)){
                turnoDTO.setOdontologo(odontologo);
                turnoDTO.setPaciente(paciente);
                turno = new TurnoDTO(turnoRepository.save(turnoDTO.toEntity()));
            }else{
                throw new BadRequestException("El turno no esta disponible");
            }
        }else{
            throw new BadRequestException("Odontologo o Paciente inexistente");
        }
        return turno;
    }

    @Override
    public TurnoDTO buscarPorID(Integer id) throws BadRequestException, ResourceNotFoundException{
        Turno turno = turnoRepository.findById(id).orElse(null);
        if(turno != null) {
            return new TurnoDTO(turnoRepository.getById(id));
        }else{
            throw new ResourceNotFoundException("Turno con id "+ id+ " no encontrado");
        }
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
    public void eliminarPorID(Integer id) throws BadRequestException, ResourceNotFoundException{
        Turno turno = turnoRepository.findById(id).orElse(null);
        if(turno != null) {
            turnoRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Turno con id "+ id + " no encontrado");
        }
    }

    @Override
    public TurnoDTO actualizar(TurnoDTO turnoDTO) throws BadRequestException, ResourceNotFoundException {
        Integer paciente_id = turnoDTO.getPaciente().getId();
        Integer odontologo_id = turnoDTO.getOdontologo().getId();
        LocalDateTime fechaturno = turnoDTO.getFechaTurno();
        PacienteDTO paciente = pacienteService.buscarPorID(paciente_id);
        OdontologoDTO odontologo = odontologoService.buscarPorID(odontologo_id);
        TurnoDTO turnoActualizado = null;
        Optional<Turno> turnoBD = turnoRepository.findById(turnoDTO.getId());
        if(turnoBD.isPresent()){
            if(turnoDisponible(turnoDTO)) {
                turnoDTO.setOdontologo(odontologo);
                turnoDTO.setPaciente(paciente);
                turnoActualizado = new TurnoDTO(turnoRepository.save(turnoDTO.toEntity()));
            }else{
                throw new BadRequestException("El turno a actualizar no esta disponible");
            }
        }else{
            throw new BadRequestException("Turno inexistente");
        }
        return turnoActualizado;
    }

    public boolean turnoDisponible(TurnoDTO turnoDTO){
        Integer pacienteId = turnoDTO.getPaciente().getId();
        Integer odontologoId = turnoDTO.getOdontologo().getId();
        LocalDateTime fechaturno = turnoDTO.getFechaTurno();
        boolean rta = true;
        for(Turno t: turnoRepository.findAll()) {
            if (t.getFechaTurno().equals(fechaturno)) {
                if (t.getPaciente().getId().equals(pacienteId) || t.getOdontologo().getId().equals(odontologoId)) {
                    rta = false;
                }
            }
        }
        return rta;
    }

    public List<Turno> turnosProxSemana() throws ResourceNotFoundException {
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime proximaSemana = hoy.plusDays(7);
        List<Turno> listaTodosTurnos = turnoRepository.findAll();
        List<Turno> turnosProximaSemana = new ArrayList<>();
        if (listaTodosTurnos.size() <= 0){
            throw new ResourceNotFoundException("No hay turnos cargados");
        }
        for (Turno turno:listaTodosTurnos){
            if ((turno.getFechaTurno().isBefore(proximaSemana) && turno.getFechaTurno().isAfter(hoy)) || turno.getFechaTurno().isEqual(hoy) || turno.getFechaTurno().isEqual(proximaSemana)){
                turnosProximaSemana.add(turno);
            }
        }
        if (turnosProximaSemana.size()<=0)
            throw new ResourceNotFoundException("No hay turnos agendados para la próxima semana");
        return turnosProximaSemana;
    }
}
