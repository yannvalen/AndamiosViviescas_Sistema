package com.gestionandamios.modelo;

import java.sql.Date;

public class Alquiler {
    private int idAlquiler;
    private int idCliente;
    private Date fechaInicio;
    private Date fechaFinEstimada;
    private Date fechaFinReal;
    private double costoTotal;
    private String estadoPago; // Nuevo campo

    // Getters y Setters
    public int getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(int idAlquiler) { this.idAlquiler = idAlquiler; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFinEstimada() { return fechaFinEstimada; }
    public void setFechaFinEstimada(Date fechaFinEstimada) { this.fechaFinEstimada = fechaFinEstimada; }

    public Date getFechaFinReal() { return fechaFinReal; }
    public void setFechaFinReal(Date fechaFinReal) { this.fechaFinReal = fechaFinReal; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }
}