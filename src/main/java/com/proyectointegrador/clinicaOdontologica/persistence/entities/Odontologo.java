package com.proyectointegrador.clinicaOdontologica.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="odontologos")
public class Odontologo {

    @Id
    @SequenceGenerator(name="odontologo_sequence", sequenceName="odontologo_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologo_sequence")

    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo() {
    }

    public Odontologo(Integer id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }
}
