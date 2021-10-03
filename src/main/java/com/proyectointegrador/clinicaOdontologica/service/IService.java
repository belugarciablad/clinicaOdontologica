package com.proyectointegrador.clinicaOdontologica.service;

import com.proyectointegrador.clinicaOdontologica.exceptions.BadRequestException;
import com.proyectointegrador.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IService<T> {
    T registrar(T t) throws BadRequestException, ResourceNotFoundException;

    T buscarPorID(Integer id) throws BadRequestException, ResourceNotFoundException;

    List<T> buscarTodos() throws BadRequestException, ResourceNotFoundException;

    void eliminarPorID(Integer id) throws BadRequestException, ResourceNotFoundException;

    T actualizar(T t)throws BadRequestException, ResourceNotFoundException;

}
