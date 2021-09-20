package com.proyectointegrador.clinicaOdontologica.persistence.repositories;

import com.proyectointegrador.clinicaOdontologica.persistence.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {
}
