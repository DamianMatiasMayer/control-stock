package ar.edu.davinci.controlstock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.davinci.controlstock.entity.AlertaStock;
import ar.edu.davinci.controlstock.entity.Categoria;
import ar.edu.davinci.controlstock.entity.Deposito;
import ar.edu.davinci.controlstock.entity.Existencia;
import ar.edu.davinci.controlstock.entity.MovimientoStock;
import ar.edu.davinci.controlstock.entity.Producto;
import ar.edu.davinci.controlstock.repository.AlertaStockRepository;
import ar.edu.davinci.controlstock.repository.CategoriaRepository;
import ar.edu.davinci.controlstock.repository.DepositoRepository;
import ar.edu.davinci.controlstock.repository.ExistenciaRepository;
import ar.edu.davinci.controlstock.repository.MovimientoStockRepository;
import ar.edu.davinci.controlstock.repository.ProductoRepository;

@SpringBootTest
@Transactional
class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private ExistenciaRepository existenciaRepository;

    @Autowired
    private MovimientoStockRepository movimientoStockRepository;

    @Autowired
    private AlertaStockRepository alertaStockRepository;

    private Producto yerba;
    private Deposito central;
    private Deposito norte;

    @BeforeEach
    void prepararDatos() {
        Categoria almacen = categoriaRepository.save(new Categoria("Almacén", "Productos de almacén"));
        yerba = productoRepository.save(new Producto("ALM-001", "Yerba 1 kg", almacen));
        central = depositoRepository.save(new Deposito("Central", "Av. Siempreviva 123"));
        norte = depositoRepository.save(new Deposito("Norte", "Calle Falsa 456"));
    }

    @Test
    void egresoMenorAlStock_descuentaYGuardaElMovimiento() {
        Existencia existencia = existenciaRepository.save(new Existencia(yerba, central, 20, 5));

        MovimientoStock movimiento = inventarioService.registrarEgreso(existencia.getId(), 8, "Venta");

        assertEquals(12, existenciaRepository.findById(existencia.getId()).get().getCantidad());
        assertEquals(1, movimientoStockRepository.count());
        assertEquals(8, movimiento.getCantidad());
        assertEquals(0, alertaStockRepository.count());
    }

    @Test
    void egresoMayorAlStock_lanzaExcepcionYNoGuardaNada() {
        Existencia existencia = existenciaRepository.save(new Existencia(yerba, central, 10, 3));

        assertThrows(StockInsuficienteException.class,
                () -> inventarioService.registrarEgreso(existencia.getId(), 15, "Venta"));

        assertEquals(10, existenciaRepository.findById(existencia.getId()).get().getCantidad());
        assertEquals(0, movimientoStockRepository.count());
    }

    @Test
    void egresoQueDejaElStockJustoEnElMinimo_generaUnaAlerta() {
        Existencia existencia = existenciaRepository.save(new Existencia(yerba, norte, 10, 3));

        MovimientoStock movimiento = inventarioService.registrarEgreso(existencia.getId(), 7, "Venta");

        assertEquals(1, alertaStockRepository.count());
        AlertaStock alerta = alertaStockRepository.findAll().get(0);
        assertEquals(3, alerta.getStockAlMomento());
        assertEquals(movimiento.getId(), alerta.getMovimiento().getId());
    }

    @Test
    void hayStockEnOtroDepositoPeroNoEnElElegido_seRechaza() {
        existenciaRepository.save(new Existencia(yerba, central, 20, 5));
        Existencia enNorte = existenciaRepository.save(new Existencia(yerba, norte, 5, 2));

        assertThrows(StockInsuficienteException.class,
                () -> inventarioService.registrarEgreso(enNorte.getId(), 10, "Venta"));

        assertEquals(5, existenciaRepository.findById(enNorte.getId()).get().getCantidad());
    }

    @Test
    void egresoConCantidadCero_seRechaza() {
        Existencia existencia = existenciaRepository.save(new Existencia(yerba, central, 10, 3));

        assertThrows(IllegalArgumentException.class,
                () -> inventarioService.registrarEgreso(existencia.getId(), 0, "Venta"));

        assertEquals(10, existenciaRepository.findById(existencia.getId()).get().getCantidad());
    }
}