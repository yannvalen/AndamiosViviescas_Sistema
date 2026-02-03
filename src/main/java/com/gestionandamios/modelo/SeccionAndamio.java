package com.gestionandamios.modelo;

public class SeccionAndamio {

    private int idSeccion;
    private String codigo;          
    private String tipo;
    private double alturaMetros;
    private String estado;
    private String ubicacion;
    private double precio;

    public SeccionAndamio() {}

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAlturaMetros() {
        return alturaMetros;
    }

    public void setAlturaMetros(double alturaMetros) {
        this.alturaMetros = alturaMetros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
