package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.DetalleAlquiler;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DetalleAlquilerDAO {

    public List<DetalleAlquiler> listarPorAlquiler(int idAlquiler) {

        List<DetalleAlquiler> lista = new ArrayList<>();

        String sql = "SELECT * FROM detalle_alquiler WHERE id_alquiler = ?";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idAlquiler);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleAlquiler d = new DetalleAlquiler();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdAlquiler(rs.getInt("id_alquiler"));
                d.setIdSeccion(rs.getInt("id_seccion"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                lista.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
