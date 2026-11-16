package ar.edu.davinci.controlstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.edu.davinci.controlstock.service.InventarioService;

@Controller
public class MovimientoController {

    private final InventarioService inventarioService;

    public MovimientoController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/movimientos/{id}")
    public String verMovimiento(@PathVariable Long id, Model model) {
        model.addAttribute("movimiento", inventarioService.buscarMovimiento(id));
        model.addAttribute("alerta", inventarioService.buscarAlertaDelMovimiento(id));
        return "movimiento";
    }
}