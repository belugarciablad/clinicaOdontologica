package com.proyectointegrador.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Odontologo;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDTO implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    public OdontologoDTO() {
    }

    public OdontologoDTO(Odontologo o){
        this.id = o.getId();
        this.nombre = o.getNombre();
        this.apellido = o.getApellido();
        this.matricula = o.getMatricula();
    }

    public Odontologo toEntity(){
        Odontologo entity = new Odontologo();

        entity.setId(id);
        entity.setApellido(apellido);
        entity.setNombre(nombre);
        entity.setMatricula(matricula);

        return entity;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
