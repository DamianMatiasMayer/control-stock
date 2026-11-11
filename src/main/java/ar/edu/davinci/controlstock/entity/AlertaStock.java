package ar.edu.davinci.controlstock.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;

@Entity
public class AlertaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "existencia_id", nullable = false)
    private Existencia existencia;

    @OneToOne
    @JoinColumn(name = "movimiento_id", nullable = false, unique = true)
    private MovimientoStock movimiento;

    @Min(0)
    @Column(nullable = false)
    private int stockAlMomento;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private boolean atendida = false;

    protected AlertaStock() {
        // Constructor vacío requerido por JPA
    }

    public AlertaStock(Existencia existencia, MovimientoStock movimiento, int stockAlMomento) {
        this.existencia = existencia;
        this.movimiento = movimiento;
        this.stockAlMomento = stockAlMomento;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Existencia getExistencia() {
        return existencia;
    }

    public MovimientoStock getMovimiento() {
        return movimiento;
    }

    public int getStockAlMomento() {
        return stockAlMomento;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void marcarComoAtendida() {
        this.atendida = true;
    }
}