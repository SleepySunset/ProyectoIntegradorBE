package com.dh.clinica;

import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.ITurnoRepository;
import com.dh.clinica.service.impl.OdontologoService;
import com.dh.clinica.service.impl.PacienteService;
import com.dh.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional

public class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;

    @Autowired
    ITurnoRepository turnoRepository;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    PacienteService pacienteService;


    Turno turno;
    Turno turnoDesdeDB;
    Paciente pacienteDesdeDB;
    Odontologo odontologoDesdeDB;


    @BeforeEach
    void crearTurno(){

        // creamos paciente
        Domicilio domicilio = new Domicilio(null, "Falsa", 456, "Cipolleti", "Rio Negro");
        Paciente paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655");
        paciente.setFechaIngreso(LocalDate.of(2024, 7, 16));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDB = pacienteService.guardarPaciente(paciente);

        // creamos odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setNroMatricula("123");
        odontologo.setApellido("Gonzalez");
        odontologo.setNombre("Tomas");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);

        //creamos el turno y lo guardamos
        turno = new Turno();
        turno.setPaciente(pacienteDesdeDB);
        turno.setOdontologo(odontologoDesdeDB);
        turno.setFecha(LocalDate.of(2024, 7, 16));

        turnoDesdeDB = turnoRepository.save(turno);
    }

    @Test
    @DisplayName("Testear que se guarde un turno en la db")
    void caso1(){
        assertNotNull(turnoDesdeDB.getId());
    }

    @Test
    @DisplayName("Testear que nos muestre todos los turnos guardados")
    void caso2(){
        List<TurnoResponseDto> turnos = new ArrayList<>();
        turnos = turnoService.buscarTodos();
        assertFalse(turnos.isEmpty());
    }

    @Test
    @DisplayName("Testear que nos busque un turno por id")
    void caso3(){
        // dado
        Integer id = turnoDesdeDB.getId();
        // cuando
        TurnoResponseDto turnoEncontrado = turnoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, turnoEncontrado.getId());
    }
}
