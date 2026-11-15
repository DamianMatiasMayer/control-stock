package ar.edu.davinci.controlstock.service;

import ar.edu.davinci.controlstock.entity.MovimientoStock;

public interface ObservadorStock {

    void actualizar(MovimientoStock movimiento);
}