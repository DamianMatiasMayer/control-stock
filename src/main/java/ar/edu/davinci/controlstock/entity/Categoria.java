package ar.edu.davinci.controlstock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //esta clase es una tabla 
public class Categoria {

    @Id //(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //la base genera el numero sola al insertar
    private Long id;

    @Column(nullable = false, unique = true, length = 50) //restricciones del modelo de la columna nombre
    private String nombre;

    @Column(length = 200)// restricciones del modelo de la columna descripcion
    private String descripcion;

    protected Categoria() {
        // Constructor vacío requerido por JPA
    }

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}