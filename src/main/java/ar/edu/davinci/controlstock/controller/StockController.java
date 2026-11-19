package ar.edu.davinci.controlstock.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String verStock(@RequestParam(required = false) Long depositoId,
                           @RequestParam(required = false) String texto,
                           @RequestParam(defaultValue = "false") boolean soloStockBajo,
                           @PageableDefault(size = 10) Pageable pageable,
                           Model model) {
        model.addAttribute("pagina", inventarioService.listarExistencias(depositoId, texto, soloStockBajo, pageable));
        model.addAttribute("depositos", inventarioService.listarDepositos());

        // Los filtros elegidos, para mostrarlos en el formulario y mantenerlos al cambiar de página
        model.addAttribute("depositoId", depositoId);
        model.addAttribute("texto", texto);
        model.addAttribute("soloStockBajo", soloStockBajo);
        return "stock";
    }
}