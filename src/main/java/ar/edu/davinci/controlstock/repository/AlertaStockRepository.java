package ar.edu.davinci.controlstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.AlertaStock;

public interface AlertaStockRepository extends JpaRepository<AlertaStock, Long> {
}