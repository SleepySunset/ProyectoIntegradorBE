package com.dh.clinica.controller;


import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService){
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/buscar/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable Integer id){
        return  odontologoService.buscarPorId(id);
    }

    @GetMapping("/buscartodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.busarTodos();
    }

    @PutMapping("/modificarodontologo")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarOdontologo(odontologo);
        return "el odontologo fue modificado";
    }

    @DeleteMapping("/eliminarodontologo")
    public String eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return "se elimino el odontologo";
    }



}
