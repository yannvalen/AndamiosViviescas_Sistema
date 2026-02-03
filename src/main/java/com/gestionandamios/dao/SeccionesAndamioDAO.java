package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.SeccionAndamio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de las operaciones CRUD de la tabla secciones_andamio
 */
public class SeccionesAndamioDAO {

    // =========================
    // INSERTAR SECCIÓN
    // =========================
    public boolean insertar(SeccionAndamio s) {
        String sql = "INSERT INTO secciones_andamio (codigo, tipo, altura_metros, estado, ubicacion, precio) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getCodigo());
            ps.setString(2, s.getTipo());
            ps.setDouble(3, s.getAlturaMetros());
            ps.setString(4, s.getEstado());
            ps.setString(5, s.getUbicacion());
            ps.setDouble(6, s.getPrecio());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("✔ Sección insertada correctamente");
                return true;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error real de MYSQL");
        }
        return false;
    }

    // =========================
    // LISTAR TODAS LAS SECCIONES
    // =========================
    public List<SeccionAndamio> listar() {

        List<SeccionAndamio> lista = new ArrayList<>();
        String sql = "SELECT * FROM secciones_andamio";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                SeccionAndamio s = new SeccionAndamio();
                s.setIdSeccion(rs.getInt("id_seccion"));
                s.setCodigo(rs.getString("codigo"));
                s.setTipo(rs.getString("tipo"));
                s.setAlturaMetros(rs.getDouble("altura_metros"));
                s.setEstado(rs.getString("estado"));
                s.setUbicacion(rs.getString("ubicacion"));
                s.setPrecio(rs.getDouble("precio"));

                lista.add(s);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar secciones de andamio");
        }

        return lista;
    }

    // =========================
    // ACTUALIZAR SECCIÓN
    // =========================
    public boolean actualizar(SeccionAndamio s) {

        String sql = "UPDATE secciones_andamio "
                   + "SET codigo = ?, tipo = ?, altura_metros = ?, estado = ?, ubicacion = ?, precio = ? "
                   + "WHERE id_seccion = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getCodigo());
            ps.setString(2, s.getTipo());
            ps.setDouble(3, s.getAlturaMetros());
            ps.setString(4, s.getEstado());
            ps.setString(5, s.getUbicacion());
            ps.setDouble(6, s.getPrecio());
            ps.setInt(7, s.getIdSeccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar sección de andamio");
            return false;
        }
    }

    // =========================
    // ELIMINAR SECCIÓN
    // =========================
    public boolean eliminar(int idSeccion) {

        String sql = "DELETE FROM secciones_andamio WHERE id_seccion = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSeccion);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar sección de andamio");
            return false;
        }
    }
}
