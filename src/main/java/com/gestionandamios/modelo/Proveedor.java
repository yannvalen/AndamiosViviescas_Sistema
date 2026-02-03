package com.gestionandamios.modelo;

public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String rutNit;
    private String contactoPrincipal;
    private String telefono;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutNit() {
        return rutNit;
    }

    public void setRutNit(String rutNit) {
        this.rutNit = rutNit;
    }

    public String getContactoPrincipal() {
        return contactoPrincipal;
    }

    public void setContactoPrincipal(String contactoPrincipal) {
        this.contactoPrincipal = contactoPrincipal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
