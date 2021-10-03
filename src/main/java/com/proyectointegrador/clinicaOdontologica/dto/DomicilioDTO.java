package com.proyectointegrador.clinicaOdontologica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioDTO implements Serializable {

    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    public DomicilioDTO() {
    }

    public DomicilioDTO(Domicilio d) {
        id = d.getId();
        calle = d.getCalle();
        numero = d.getNumero();
        localidad = d.getLocalidad();
        provincia = d.getProvincia();
    }

    public Domicilio toEntity() {
        Domicilio entity = new Domicilio();

        entity.setId(id);
        entity.setCalle(calle);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        entity.setNumero(numero);

        return entity;
    }
    public Integer getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }



}
