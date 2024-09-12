package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("El paciente se guardo");
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        logger.info("El paciente se modifico");
        pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            logger.info("El paciente se elimino");
            pacienteRepository.deleteById(id);
        } else {
            logger.info("El paciente no fue encontrado");
            throw new ResourceNotFoundException("El paciente " +id+ " no fue encontrado");
        }

    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findByApellidoAndNombre(apellido, nombre);
    }

    @Override
    public List<Paciente> buscarLikeNombre(String nombre) {
        return pacienteRepository.findByNombreLike(nombre);
    }

    public List<Paciente> buscarLikeDni(String dni){
        return pacienteRepository.findByDniLike(dni);
    }
}
