package ar.edu.davinci.controlstock.service;

public class DatoNoEncontradoException extends RuntimeException {

    public DatoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}