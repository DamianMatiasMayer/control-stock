package ar.edu.davinci.controlstock.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovimientoForm {

    @NotNull
    private Long existenciaId;

    @NotNull(message = "Ingresá la cantidad")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotBlank(message = "Ingresá el motivo")
    @Size(max = 200, message = "El motivo puede tener hasta 200 caracteres")
    private String motivo;

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}