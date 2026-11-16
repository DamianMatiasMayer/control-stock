package ar.edu.davinci.controlstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.davinci.controlstock.service.InventarioService;

@Controller
public class StockController {

    private final InventarioService inventarioService;

    public StockController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/stock";
    }

    @GetMapping("/stock")
    public String verStock(Model model) {
        model.addAttribute("existencias", inventarioService.listarExistencias());
        return "stock";
    }
}