package ar.edu.davinci.controlstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}