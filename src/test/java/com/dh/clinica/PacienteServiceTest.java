package com.dh.clinica;

import com.dh.clinica.dao.impl.PacienteDaoH2;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Domicilio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {
    static Logger logger = LoggerFactory.getLogger(PacienteServiceTest.class);
    PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @BeforeAll
    static void tablas(){
        H2Connection.crearTablas();
    }

    @Test
    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
    void caso1(){
        //Dado
        Paciente paciente = new Paciente("Romero", "Luciana", "443343", LocalDate.of(2024, 05, 03),
                new Domicilio("Falsa", 456, "Cipolleti", "Rio Negro"));

        //Cuando
        Paciente pacienteDesdeDB = pacienteService.guardarPaciente(paciente);

        //Entonces
        assertNotNull((pacienteDesdeDB.getId()));
    }
    @Test
    @DisplayName("")
    void caso2(){
        //Dado
        Integer id = 1;
        //Cuando
        Paciente paciente = pacienteService.buscarPorId(id);
        //Entonces
        assertEquals(id, paciente.getId());
    }


}