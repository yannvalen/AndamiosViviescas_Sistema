package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/GuardarDetalleServlet")
public class GuardarDetalleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        // Capturamos el ID del alquiler para poder regresar a la misma página
        String idAlqStr = request.getParameter("idAlquiler");
        if (idAlqStr == null) {
            response.sendRedirect("AlquilerServlet");
            return;
        }
        int idAlquiler = Integer.parseInt(idAlqStr);

        try (Connection con = ConexionDB.getConexion()) {
            
            if ("actualizarCantidad".equals(accion)) {
                // --- 1. FUNCIONALIDAD: ACTUALIZAR ---
                int idDetalle = Integer.parseInt(request.getParameter("id_detalle"));
                int nuevaCant = Integer.parseInt(request.getParameter("cantidad"));

                String sql = "UPDATE detalle_alquiler SET cantidad = ? WHERE id_detalle = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, nuevaCant);
                    ps.setInt(2, idDetalle);
                    ps.executeUpdate();
                }
            } else {
                // --- 2. FUNCIONALIDAD: INSERTAR ---
                int idSeccion = Integer.parseInt(request.getParameter("idSeccion"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                double precio = Double.parseDouble(request.getParameter("precioUnitario"));

                String sql = "INSERT INTO detalle_alquiler (id_alquiler, id_seccion, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idAlquiler);
                    ps.setInt(2, idSeccion);
                    ps.setInt(3, cantidad);
                    ps.setDouble(4, precio);
                    ps.executeUpdate();
                }
            }
            
            // Al terminar, el Trigger de la DB ya actualizó el costo_total automáticamente.
            response.sendRedirect("DetalleAlquilerServlet?id=" + idAlquiler);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AlquilerServlet?error=detalle_db");
        }
    }
}