package ar.edu.davinci.controlstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.Existencia;

public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
}