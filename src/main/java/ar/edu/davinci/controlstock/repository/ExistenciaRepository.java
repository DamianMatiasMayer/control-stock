package ar.edu.davinci.controlstock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ar.edu.davinci.controlstock.entity.Existencia;

public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {

    @Query("SELECT e FROM Existencia e"
            + " WHERE (:depositoId IS NULL OR e.deposito.id = :depositoId)"
            + " AND (:texto IS NULL"
            + "      OR LOWER(e.producto.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))"
            + "      OR LOWER(e.producto.codigo) LIKE LOWER(CONCAT('%', :texto, '%')))"
            + " AND (:soloStockBajo = false OR e.cantidad <= e.stockMinimo)"
            + " ORDER BY CASE WHEN e.cantidad <= e.stockMinimo THEN 0 ELSE 1 END,"
            + "          e.deposito.nombre, e.producto.nombre")
    Page<Existencia> buscar(@Param("depositoId") Long depositoId,
                            @Param("texto") String texto,
                            @Param("soloStockBajo") boolean soloStockBajo,
                            Pageable pageable);
}