package ar.edu.davinci.controlstock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.edu.davinci.controlstock.service.DatoNoEncontradoException;

@ControllerAdvice
public class ManejadorDeErrores {

    // Se pidió un dato que no existe (por ejemplo, /movimientos/999)
    @ExceptionHandler(DatoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String datoNoEncontrado(DatoNoEncontradoException e, Model model) {
        model.addAttribute("mensaje", e.getMessage());
        return "error/4xx";
    }
}