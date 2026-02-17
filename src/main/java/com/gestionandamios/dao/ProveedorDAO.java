package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Proveedor;
import java.sql.*;
import java.util.*;

public class ProveedorDAO {

    public void insertar(Proveedor p) {
        String sql = "INSERT INTO proveedores(nombre, ruc_nit, contacto_principal, telefono) VALUES (?,?,?,?)";
        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRutNit());
            ps.setString(3, p.getContactoPrincipal());
            ps.setString(4, p.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                p.setRutNit(rs.getString("ruc_nit"));
                p.setContactoPrincipal(rs.getString("contacto_principal"));
                p.setTelefono(rs.getString("telefono"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // --- NUEVOS MÉTODOS ---

    public void eliminar(int id) {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Proveedor buscarPorId(int id) {
        Proveedor p = new Proveedor();
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p.setIdProveedor(rs.getInt("id_proveedor"));
                    p.setNombre(rs.getString("nombre"));
                    p.setRutNit(rs.getString("ruc_nit"));
                    p.setContactoPrincipal(rs.getString("contacto_principal"));
                    p.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public void actualizar(Proveedor p) {
        String sql = "UPDATE proveedores SET nombre=?, ruc_nit=?, contacto_principal=?, telefono=? WHERE id_proveedor=?";
        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRutNit());
            ps.setString(3, p.getContactoPrincipal());
            ps.setString(4, p.getTelefono());
            ps.setInt(5, p.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}