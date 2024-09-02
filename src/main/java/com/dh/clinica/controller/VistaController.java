package com.dh.clinica.controller;

import com.dh.clinica.model.Paciente;
import com.dh.clinica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VistaController {
    private PacienteService pacienteService;

    public VistaController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/index/{id}")
    public String mostrarPacientePorId(Model model, @PathVariable Integer id){
        Paciente paciente = pacienteService.buscarPorId(id);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "paciente";
    }
}
