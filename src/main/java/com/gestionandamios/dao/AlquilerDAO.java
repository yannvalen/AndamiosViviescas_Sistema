package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Alquiler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para la entidad Alquiler.
 * Esta clase centraliza todas las operaciones de persistencia en la tabla 'alquileres'.
 * Aplica estándares de seguridad mediante PreparedStatement.
 * * @author Yann Valen
 * @version 1.2
 */
public class AlquilerDAO {

    /**
     * Registra un nuevo contrato de alquiler en la base de datos.
     * @param a Objeto Alquiler con los datos cargados desde el formulario.
     */
    public void insertar(Alquiler a) {
        String sql = "INSERT INTO alquileres (id_cliente, fecha_inicio, fecha_fin_estimada, costo_total) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getIdCliente());
            ps.setDate(2, a.getFechaInicio());
            ps.setDate(3, a.getFechaFinEstimada());
            ps.setDouble(4, a.getCostoTotal());
            ps.executeUpdate();
        } catch (SQLException e) { 
            System.err.println("Error al insertar alquiler: " + e.getMessage()); 
        }
    }

    /**
     * Recupera la lista completa de alquileres registrados.
     * @return List de objetos Alquiler ordenados por ID descendente.
     */
    public List<Alquiler> listar() {
        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM alquileres ORDER BY id_alquiler DESC";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapearAlquiler(rs));
            }
        } catch (SQLException e) { 
            System.err.println("Error al listar alquileres: " + e.getMessage()); 
        }
        return lista;
    }

    /**
     * Actualiza el estado de pago de un alquiler específico.
     * @param idAlquiler Identificador único del registro.
     * @param nuevoEstado Texto (PAGADO/PENDIENTE) a actualizar.
     */
    public void actualizarEstadoPago(int idAlquiler, String nuevoEstado) {
        String sql = "UPDATE alquileres SET estado_pago = ? WHERE id_alquiler = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idAlquiler);
            ps.executeUpdate();
        } catch (SQLException e) { 
            System.err.println("Error al actualizar estado: " + e.getMessage()); 
        }
    }

    /**
     * Elimina físicamente un registro de alquiler.
     * @param id Identificador del alquiler a borrar.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM alquileres WHERE id_alquiler = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { 
            System.err.println("Error al eliminar alquiler: " + e.getMessage()); 
        }
    }

    /**
     * Método privado de utilidad para transformar un ResultSet en Objeto Java.
     */
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