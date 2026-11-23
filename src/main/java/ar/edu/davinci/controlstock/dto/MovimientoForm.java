package ar.edu.davinci.controlstock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import ar.edu.davinci.controlstock.entity.MotivoEgreso;

public class MovimientoForm {

    @NotNull
    private Long existenciaId;

    @NotNull(message = "Ingresá la cantidad")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "Elegí el motivo")
    private MotivoEgreso motivo;

    public Long getExistenciaId() {
        return existenciaId;
    }

    public void setExistenciaId(Long existenciaId) {
        this.existenciaId = existenciaId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public MotivoEgreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEgreso motivo) {
        this.motivo = motivo;
    }
}