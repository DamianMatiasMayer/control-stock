package ar.edu.davinci.controlstock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.davinci.controlstock.entity.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    List<Deposito> findAllByOrderByNombreAsc();
}