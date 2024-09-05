package com.dh.clinica.service;

import com.dh.clinica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    public Odontologo guardarOdontologo(Odontologo odontologo);
    public Optional<Odontologo> buscarPorId(Integer id);
    public List<Odontologo> buscarTodos();
    public void modificarOdontologo(Odontologo odontologo);
    public void eliminarOdontologo(Integer id);
}
