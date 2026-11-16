package ar.edu.davinci.controlstock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.davinci.controlstock.entity.Existencia;
import ar.edu.davinci.controlstock.entity.MovimientoStock;
import ar.edu.davinci.controlstock.entity.TipoMovimiento;
import ar.edu.davinci.controlstock.repository.ExistenciaRepository;
import ar.edu.davinci.controlstock.repository.MovimientoStockRepository;

@Service
public class InventarioService {

    private final ExistenciaRepository existenciaRepository;
    private final MovimientoStockRepository movimientoStockRepository;
    private final List<ObservadorStock> observadores;

    public InventarioService(ExistenciaRepository existenciaRepository,
                             MovimientoStockRepository movimientoStockRepository,
                             List<ObservadorStock> observadores) {
        this.existenciaRepository = existenciaRepository;
        this.movimientoStockRepository = movimientoStockRepository;
        this.observadores = observadores;
    }

        public List<Existencia> listarExistencias() {
        return existenciaRepository.findAllByOrderByDepositoNombreAscProductoNombreAsc();
    }

        public Existencia buscarExistencia(Long existenciaId) {
        Existencia existencia = existenciaRepository.findById(existenciaId).orElse(null);
        if (existencia == null) {
            throw new IllegalArgumentException("El stock seleccionado no existe");
        }
        return existencia;
    }

    @Transactional
    public MovimientoStock registrarEgreso(Long existenciaId, int cantidad, String motivo) {
        // RN3: la cantidad debe ser mayor a 0
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Existencia existencia = buscarExistencia(existenciaId);

        // RN1: no se puede sacar más de lo que hay en el depósito
        if (cantidad > existencia.getCantidad()) {
            throw new StockInsuficienteException(
                    existencia.getDeposito().getNombre(),
                    existencia.getCantidad(),
                    cantidad);
        }

        existencia.setCantidad(existencia.getCantidad() - cantidad);
        existenciaRepository.save(existencia);

        MovimientoStock movimiento = new MovimientoStock(existencia, TipoMovimiento.EGRESO, cantidad, motivo);
        movimientoStockRepository.save(movimiento);

        notificarObservadores(movimiento);

        return movimiento;
    }

    private void notificarObservadores(MovimientoStock movimiento) {
        for (ObservadorStock observador : observadores) {
            observador.actualizar(movimiento);
        }
    }
}