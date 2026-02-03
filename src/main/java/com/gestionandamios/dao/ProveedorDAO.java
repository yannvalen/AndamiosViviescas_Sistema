package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Proveedor;

import java.sql.*;
import java.util.*;

public class ProveedorDAO {

    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM proveedores";

        try (Connection cn = ConexionDB.getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getInt("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setRutNit(rs.getString("rut_nit"));
                p.setContactoPrincipal(rs.getString("contacto_principal"));
                p.setTelefono(rs.getString("telefono"));
                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error al listar proveedores");
            e.printStackTrace();
        }

        return lista;
    }

    public void insertar(Proveedor p) {
        String sql = "INSERT INTO proveedores(nombre, rut_nit, contacto_principal, telefono) VALUES (?,?,?,?)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRutNit());
            ps.setString(3, p.getContactoPrincipal());
            ps.setString(4, p.getTelefono());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al insertar proveedor");
            e.printStackTrace();
        }
    }
}
