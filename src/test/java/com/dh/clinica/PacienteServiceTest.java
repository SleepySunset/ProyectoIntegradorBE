package com.dh.clinica;

import com.dh.clinica.entity.Domicilio;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional

class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDB;

    @BeforeEach
    void crearPaciente(){
        Domicilio domicilio = new Domicilio(null, "Falsa", 456, "Cipolleti", "Rio Negro");
        paciente = new Paciente();
        paciente.setApellido("Romero");
        paciente.setNombre("Luciana");
        paciente.setDni("56655");
        paciente.setFechaIngreso(LocalDate.of(2024, 07, 16));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDB = pacienteService.guardarPaciente(paciente);
    }

    @Test
    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
    void caso1(){
        assertNotNull((pacienteDesdeDB.getId()));
    }

    @Test
    @DisplayName("Testear que un paciente pueda ser obtenido cuando se envia el id")
    void caso2(){
        //Dado
        Integer id = pacienteDesdeDB.getId();
        //Cuando
        Paciente pacienteEncontrado = pacienteService.buscarPorId(id).get();
        //Entonces
        assertEquals(id, pacienteEncontrado.getId());
    }
}
