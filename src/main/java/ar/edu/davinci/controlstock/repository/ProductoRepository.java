package ar.edu.davinci.controlstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}