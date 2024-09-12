package com.dh.clinica.service.impl;

import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.OdontologoResponseDto;
import com.dh.clinica.dto.response.PacienteResponseDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    @Autowired
    private ModelMapper modelMapper;


    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoRequestDto.getOdontologoId());
        Turno turno = new Turno();
        Turno turnoDesdeDB = null;
        TurnoResponseDto turnoARetornar = null;
        if(paciente.isPresent() && odontologo.isPresent()){

            // mapear el turnoRequestDto aa turno
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));

            // persistimos el turno
            turnoDesdeDB = turnoRepository.save(turno);
            logger.info("turno guardado "+ turnoDesdeDB);

            // mapear el turnoDesdeDB a turnoResponseDto
            // turno mapeado a mano
            // turnoARetornar = convertirTurnoAResponse(turnoDesdeDB);
            // turno mapeado con modelMapper

            turnoARetornar = mapearATurnoResponse(turnoDesdeDB);


        } else {
            throw new ResourceNotFoundException("El odontólogo o el paciente no existen");
        }
        return turnoARetornar;
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turnoDesdeLaDB = turnoRepository.findById(id);
        TurnoResponseDto turnoResponseDto = null;
        if(turnoDesdeLaDB.isPresent()){
            turnoResponseDto = mapearATurnoResponse(turnoDesdeLaDB.get());
        }
        return Optional.ofNullable(turnoResponseDto);
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnoRespuesta = new ArrayList<>();
        for (Turno t: turnos){

            TurnoResponseDto turnoAuxiliar = mapearATurnoResponse(t);

            logger.info("turnos "+ t);

            turnoRespuesta.add(turnoAuxiliar);
        }
        return turnoRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModificarDto turnoModificarDto) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoModificarDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(turnoModificarDto.getOdontologo_id());
        Turno turno = null;
        if(paciente.isPresent() && odontologo.isPresent()){
            turno = new Turno(turnoModificarDto.getId(), paciente.get(), odontologo.get(),
                    LocalDate.parse(turnoModificarDto.getFecha()));
            // voy a persistir el turno
            turnoRepository.save(turno);
        }
    }

    @Override
    public void eliminarTurno(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("El turno " +id+ " no fue encontrado");
        }

    }

    public TurnoResponseDto mapearATurnoResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }

    public List<Turno> buscarTurnoPaciente(String apellidoPaciente){
        return turnoRepository.buscarTurnoPorApellidoPaciente(apellidoPaciente);
    }
}
