package com.gestionandamios.modelo;

import java.sql.Date;

public class Pago {

    private int idPago;
    private int idAlquiler;
    private double monto;
    private Date fechaPago;
    private String metodoPago;

    public Pago() {
    }

    public Pago(int idAlquiler, double monto, Date fechaPago, String metodoPago) {
        this.idAlquiler = idAlquiler;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
