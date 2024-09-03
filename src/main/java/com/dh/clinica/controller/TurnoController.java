package com.dh.clinica.controller;

import com.dh.clinica.entity.Turno;
import com.dh.clinica.service.impl.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService){
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTurno(@RequestBody Turno turno){
        Turno turnoAGuardar = turnoService.guardarTurno(turno);
        if (turnoAGuardar != null){
            return ResponseEntity.ok(turnoAGuardar);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if(turno.isPresent()){
            return ResponseEntity.ok(turno.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el turno buscado");
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Turno>> buscartodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody Turno turno){
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(turno.getId());
        if(turnoEncontrado.isPresent()){
            turnoService.modificarTurno(turno);
            return ResponseEntity.ok("El turno fue modificado");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if(turno.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("El paciente fue eliminado");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
