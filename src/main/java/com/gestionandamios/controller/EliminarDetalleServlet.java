package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarDetalleServlet")
public class EliminarDetalleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idDetalle = request.getParameter("idDetalle");
        String idAlquiler = request.getParameter("idAlquiler");

        if (idDetalle != null && idAlquiler != null) {
            try (Connection con = ConexionDB.getConexion();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM detalle_alquiler WHERE id_detalle = ?")) {

                ps.setInt(1, Integer.parseInt(idDetalle));
                ps.executeUpdate();
                
                // Gracias al Trigger de MySQL, el costo total de la tabla alquileres ya se bajó solo
                response.sendRedirect("DetalleAlquilerServlet?id=" + idAlquiler);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("AlquilerServlet?error=delete");
            }
        }
    }
}