package ar.edu.davinci.controlstock.service;

import org.springframework.stereotype.Component;

import ar.edu.davinci.controlstock.entity.AlertaStock;
import ar.edu.davinci.controlstock.entity.Existencia;
import ar.edu.davinci.controlstock.entity.MovimientoStock;
import ar.edu.davinci.controlstock.repository.AlertaStockRepository;

@Component
public class AlertaStockBajoObserver implements ObservadorStock {

    private final AlertaStockRepository alertaStockRepository;

    public AlertaStockBajoObserver(AlertaStockRepository alertaStockRepository) {
        this.alertaStockRepository = alertaStockRepository;
    }

    @Override
    public void actualizar(MovimientoStock movimiento) {
        Existencia existencia = movimiento.getExistencia();

        // RN2: si después del movimiento la cantidad quedó en el mínimo o por debajo, se genera una alerta
        if (existencia.getCantidad() <= existencia.getStockMinimo()) {
            AlertaStock alerta = new AlertaStock(existencia, movimiento, existencia.getCantidad());
            alertaStockRepository.save(alerta);
        }
    }
}