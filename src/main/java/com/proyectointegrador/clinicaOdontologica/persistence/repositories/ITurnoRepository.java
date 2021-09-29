package com.proyectointegrador.clinicaOdontologica.persistence.repositories;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
}
