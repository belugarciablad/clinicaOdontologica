package com.proyectointegrador.clinicaOdontologica.persistence.repositories;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDomicilioRepository extends JpaRepository<Domicilio, Integer> {
}
