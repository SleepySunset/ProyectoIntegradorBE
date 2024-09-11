package com.dh.clinica;


import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional

class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologoDesdeDB;

    @BeforeEach
    void crearOdontologo(){
        odontologo = new Odontologo();
        odontologo.setNroMatricula("123");
        odontologo.setApellido("Gonzalez");
        odontologo.setNombre("Tomas");
        odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
    }

    @Test
    @DisplayName("Testear que se guarde un odontologo en la db")
    void caso1(){
        assertNotNull(odontologoDesdeDB.getId());
    }

    @Test
    @DisplayName("Testear que nos muestre todos los odontologos guardados")
    void caso2(){
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = odontologoService.buscarTodos();
        assertFalse(odontologos.isEmpty());
    }

    @Test
    @DisplayName("Testear que nos busque un odontologo por id")
    void caso3(){
        //dado
        Integer id = odontologoDesdeDB.getId();
        //cuando
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id).get();
        //entonces
        assertEquals(id, odontologoEncontrado.getId());
    }

}







