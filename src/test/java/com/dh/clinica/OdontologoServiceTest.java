package com.dh.clinica;


import com.dh.clinica.dao.impl.OdontologoDaoH2;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceTest {

    public static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());


    @BeforeAll
    static void tablas(){
        H2Connection.crearTablas();
    }

    @Test
    @DisplayName("Testear que se guarde un odontologo en la db")
    void caso1(){
        Odontologo odontologo = new Odontologo(569, "SEBASTIAN","MENDOZA");
        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);

        assertNotNull(odontologoDesdeDB.getId());
    }

    @Test
    @DisplayName("Testear que nos muestre todos los odontologos guardados")
    void caso2(){
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoService.busarTodos();
        assertFalse(odontologos.isEmpty());
    }



}







