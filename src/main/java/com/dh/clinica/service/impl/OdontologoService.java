package com.dh.clinica.service.impl;

import com.dh.clinica.dto.response.OdontologoResponseDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo){
        Odontologo odontologoARetornar = odontologoRepository.save(odontologo);
        logger.info("Odontólogo guardado: " + odontologoARetornar);
        return odontologoARetornar;
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
        logger.info("Odontólogo modificado con éxito");
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            odontologoRepository.deleteById(id);
            logger.info("Odontólogo con id " +id+ " eliminado con éxito");
        } else {
            logger.info("Odontólogo no encontrado");
            throw new ResourceNotFoundException("El odontólogo " +id+ " no fue encontrado");
        }
    }

    @Override
    public List<Odontologo> buscarLikeMatricula(String nroMatricula) {
        return odontologoRepository.findByNroMatriculaLike(nroMatricula);
    }

    private OdontologoResponseDto convertirOdontologoAResponse(Odontologo odontologoDesdeDB){
        OdontologoResponseDto odontologoARetornar = new OdontologoResponseDto(
                odontologoDesdeDB.getId(), odontologoDesdeDB.getNroMatricula(),
                odontologoDesdeDB.getNombre(), odontologoDesdeDB.getApellido()
        );

        return odontologoARetornar;
    }
}
