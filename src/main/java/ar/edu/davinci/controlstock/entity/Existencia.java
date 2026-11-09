package ar.edu.davinci.controlstock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"producto_id", "deposito_id"}))
public class Existencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "deposito_id", nullable = false)
    private Deposito deposito;

    @Min(0)
    @Column(nullable = false)
    private int cantidad;

    @Min(0)
    @Column(nullable = false)
    private int stockMinimo;

    protected Existencia() {
        // Constructor vacío requerido por JPA
    }

    public Existencia(Producto producto, Deposito deposito, int cantidad, int stockMinimo) {
        this.producto = producto;
        this.deposito = deposito;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
}