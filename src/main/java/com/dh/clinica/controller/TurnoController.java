package com.dh.clinica.controller;

import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
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
    public ResponseEntity<?> guardarTurno(@RequestBody TurnoRequestDto turnoRequestDto){
        TurnoResponseDto turnoAGuardar = turnoService.guardarTurno(turnoRequestDto);
        return ResponseEntity.ok(turnoAGuardar);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<TurnoResponseDto> turno = turnoService.buscarPorId(id);
        if(turno.isPresent()){
            return ResponseEntity.ok(turno.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno no encontrado");
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<TurnoResponseDto>> buscartodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarTurno(@RequestBody TurnoModificarDto turnoModificarDto){
       turnoService.modificarTurno(turnoModificarDto);
       return ResponseEntity.ok("{\"mensaje\": \"El turno fue modificado\"}");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"mensaje\": \"El turno fue eliminado\"}");
    }

    @GetMapping("/buscarTurnoPorApellido/{apellido}")
    public ResponseEntity<List<Turno>> buscarTurnoApellidoPorPaciente(@PathVariable String apellido){
        return ResponseEntity.ok(turnoService.buscarTurnoPaciente(apellido));
    }

}
