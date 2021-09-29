package com.proyectointegrador.clinicaOdontologica.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name ="turnos")
public class Turno {

    @Id
    @SequenceGenerator(name="turno_sequence", sequenceName="turno_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turno_sequence")
    private Integer id;
    private LocalDateTime fechaTurno;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name ="paciente_id", nullable = false)
    private Paciente paciente;
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name ="odontologo_id", nullable = false)
    private Odontologo odontologo;

    public Turno() {
    }

    public Turno(Integer id, Odontologo odontologo, LocalDateTime fechaTurno, Paciente paciente) {
        this.id = id;
        this.odontologo = odontologo;
        this.fechaTurno = fechaTurno;
        this.paciente = paciente;
    }
}
