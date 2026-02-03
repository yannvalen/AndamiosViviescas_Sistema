package com.gestionandamios.modelo;

public class DetalleAlquiler {

    private int idDetalle;
    private int idAlquiler;
    private int idSeccion;
    private int cantidad;
    private double precioUnitario;

    public DetalleAlquiler() {
    }

    public DetalleAlquiler(int idAlquiler, int idSeccion, int cantidad, double precioUnitario) {
        this.idAlquiler = idAlquiler;
        this.idSeccion = idSeccion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
