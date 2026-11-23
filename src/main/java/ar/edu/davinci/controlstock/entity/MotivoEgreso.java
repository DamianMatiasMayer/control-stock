package ar.edu.davinci.controlstock.entity;

public enum MotivoEgreso {
    VENTA("Venta"),
    ROTURA("Rotura"),
    VENCIMIENTO("Vencimiento"),
    USO_INTERNO("Uso interno"),
    DEVOLUCION_PROVEEDOR("Devolución al proveedor"),
    AJUSTE_INVENTARIO("Ajuste de inventario");

    private final String descripcion;

    MotivoEgreso(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}