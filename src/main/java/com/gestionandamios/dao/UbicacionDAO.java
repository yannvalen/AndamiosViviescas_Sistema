package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Ubicacion;
import java.sql.*;
import java.util.*;

public class UbicacionDAO {

    // 1. LISTAR
    public List<Ubicacion> listar() {
        List<Ubicacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM ubicaciones ORDER BY id_ubicacion DESC";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ubicacion u = new Ubicacion();
                u.setIdUbicacion(rs.getInt("id_ubicacion"));
                u.setCliente(rs.getString("cliente"));
                u.setDireccion(rs.getString("direccion"));
                u.setEstado(rs.getString("estado"));
                u.setFechaInicio(rs.getDate("fecha_inicio"));
                u.setFechaFin(rs.getDate("fecha_fin"));
                lista.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // 2. INSERTAR (Automático desde Alquileres)
    public void insertarAutomatico(String cliente, String direccion, java.sql.Date inicio, java.sql.Date fin) {
        String sql = "INSERT INTO ubicaciones (cliente, direccion, estado, fecha_inicio, fecha_fin) VALUES (?, ?, 'En Obra', ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente);
            ps.setString(2, direccion);
            ps.setDate(3, inicio);
            ps.setDate(4, fin);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 3. ACTUALIZAR (Manual para correcciones)
    public void actualizar(Ubicacion u) {
        String sql = "UPDATE ubicaciones SET cliente=?, direccion=?, estado=?, fecha_inicio=?, fecha_fin=? WHERE id_ubicacion=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getCliente());
            ps.setString(2, u.getDireccion());
            ps.setString(3, u.getEstado());
            ps.setDate(4, u.getFechaInicio());
            ps.setDate(5, u.getFechaFin());
            ps.setInt(6, u.getIdUbicacion());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 4. ELIMINAR
    public void eliminar(int id) {
        String sql = "DELETE FROM ubicaciones WHERE id_ubicacion=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // MÉTODO EXTRA: Obtener por ID (Para el formulario de editar)
    public Ubicacion obtenerPorId(int id) {
        String sql = "SELECT * FROM ubicaciones WHERE id_ubicacion=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ubicacion u = new Ubicacion();
                    u.setIdUbicacion(rs.getInt("id_ubicacion"));
                    u.setCliente(rs.getString("cliente"));
                    u.setDireccion(rs.getString("direccion"));
                    u.setEstado(rs.getString("estado"));
                    u.setFechaInicio(rs.getDate("fecha_inicio"));
                    u.setFechaFin(rs.getDate("fecha_fin"));
                    return u;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // MÉTODO DE FINALIZACIÓN AUTOMÁTICA (Llamado desde FinalizarAlquiler)
    public void finalizarUbicacionPorCliente(String nombreCliente) {
        String sql = "UPDATE ubicaciones SET estado = 'Finalizado / En Bodega' WHERE cliente = ? AND estado = 'En Obra'";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombreCliente);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}