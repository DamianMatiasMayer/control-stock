package ar.edu.davinci.controlstock.service;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(String deposito, int disponible, int solicitado) {
        super("Stock insuficiente en " + deposito
                + ": disponible " + disponible
                + ", solicitado " + solicitado);
    }
}