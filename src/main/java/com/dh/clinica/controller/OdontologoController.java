package com.dh.clinica.controller;


import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarOdontologoPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el odontologo buscado");
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontólogo no fue encontrado y no se modificó");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontólogo fue eliminado\"}");

    }


    @GetMapping("/buscarOdontologoPorMatricula/{nroMatricula}")
    public ResponseEntity<List<Odontologo>> buscarMatriculaLike(@PathVariable String nroMatricula){
        return ResponseEntity.ok(odontologoService.buscarLikeMatricula(nroMatricula));
    }


}
