package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Alquiler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDAO {

    // 1. CREAR: Registrar nuevo alquiler (Estado PENDIENTE automático)
    public void insertar(Alquiler a) {
        String sql = "INSERT INTO alquileres (id_cliente, fecha_inicio, fecha_fin_estimada, costo_total) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdCliente());
            ps.setDate(2, a.getFechaInicio());
            ps.setDate(3, a.getFechaFinEstimada());
            ps.setDouble(4, a.getCostoTotal());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 2. LEER: Listar todos los alquileres
    public List<Alquiler> listar() {
        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM alquileres ORDER BY id_alquiler DESC";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearAlquiler(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // 3. ACTUALIZAR: Cambiar estado de pago y otros datos
    public void actualizarEstadoPago(int idAlquiler, String nuevoEstado) {
        String sql = "UPDATE alquileres SET estado_pago = ? WHERE id_alquiler = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idAlquiler);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 4. ELIMINAR: Borrar registro de alquiler
    public void eliminar(int id) {
        String sql = "DELETE FROM alquileres WHERE id_alquiler = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Método auxiliar para mapear los datos de la DB al objeto Java
    private Alquiler mapearAlquiler(ResultSet rs) throws SQLException {
        Alquiler a = new Alquiler();
        a.setIdAlquiler(rs.getInt("id_alquiler"));
        a.setIdCliente(rs.getInt("id_cliente"));
        a.setFechaInicio(rs.getDate("fecha_inicio"));
        a.setFechaFinEstimada(rs.getDate("fecha_fin_estimada"));
        a.setFechaFinReal(rs.getDate("fecha_fin_real"));
        a.setCostoTotal(rs.getDouble("costo_total"));
        a.setEstadoPago(rs.getString("estado_pago")); 
        return a;
    }
}