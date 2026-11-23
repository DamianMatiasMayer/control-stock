package ar.edu.davinci.controlstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.davinci.controlstock.dto.MovimientoForm;
import ar.edu.davinci.controlstock.entity.MotivoEgreso;
import ar.edu.davinci.controlstock.entity.MovimientoStock;
import ar.edu.davinci.controlstock.service.InventarioService;
import ar.edu.davinci.controlstock.service.StockInsuficienteException;
import jakarta.validation.Valid;

@Controller
public class EgresoController {

    private final InventarioService inventarioService;

    public EgresoController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/egresos/nuevo")
    public String mostrarFormulario(@RequestParam Long existenciaId, Model model) {
        MovimientoForm form = new MovimientoForm();
        form.setExistenciaId(existenciaId);

        model.addAttribute("movimientoForm", form);
        prepararFormulario(existenciaId, model);
        return "egreso";
    }

    @PostMapping("/egresos")
    public String registrarEgreso(@Valid @ModelAttribute("movimientoForm") MovimientoForm form,
                                  BindingResult resultado,
                                  Model model) {
        // Errores de validación: vuelvo al formulario con los mensajes de cada campo
        if (resultado.hasErrors()) {
            prepararFormulario(form.getExistenciaId(), model);
            return "egreso";
        }

        try {
            MovimientoStock movimiento = inventarioService.registrarEgreso(
                    form.getExistenciaId(), form.getCantidad(), form.getMotivo().getDescripcion());
            return "redirect:/movimientos/" + movimiento.getId();
        } catch (StockInsuficienteException e) {
            // Error de negocio (RN1): vuelvo al formulario con el mensaje
            model.addAttribute("error", e.getMessage());
            prepararFormulario(form.getExistenciaId(), model);
            return "egreso";
        }
    }

    // Los datos que necesita la pantalla del formulario, además del formulario en sí
    private void prepararFormulario(Long existenciaId, Model model) {
        model.addAttribute("existencia", inventarioService.buscarExistencia(existenciaId));
        model.addAttribute("motivos", MotivoEgreso.values());
    }
}