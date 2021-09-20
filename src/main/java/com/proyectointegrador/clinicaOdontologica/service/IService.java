package com.proyectointegrador.clinicaOdontologica.service;

import java.util.List;

public interface IService<T> {
    T registrar(T t);

    T buscarPorID(Integer id);

    List<T> buscarTodos();

    void eliminarPorID(Integer id);

    T actualizar(T t);

}
