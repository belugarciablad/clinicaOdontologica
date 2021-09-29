package com.proyectointegrador.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO implements Serializable {

    private Integer id;
    private LocalDateTime fechaTurno;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    public TurnoDTO() {
    }

    public TurnoDTO(Turno t){
        this.id = t.getId();
        this.fechaTurno = t.getFechaTurno();
        this.paciente = new PacienteDTO(t.getPaciente());
        this.odontologo = new OdontologoDTO(t.getOdontologo());

    }

    public Turno toEntity(){
        Turno entity = new Turno();

        entity.setId(id);
        entity.setFechaTurno(fechaTurno);
        entity.setPaciente(paciente.toEntity());
        entity.setOdontologo(odontologo.toEntity());

        return entity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDateTime fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
    }
}
