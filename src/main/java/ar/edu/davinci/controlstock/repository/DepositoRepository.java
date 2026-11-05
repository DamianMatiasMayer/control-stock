package ar.edu.davinci.controlstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {
}