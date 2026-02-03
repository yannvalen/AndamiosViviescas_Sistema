package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegistrarAlquilerServlet")
public class RegistrarAlquilerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // 1. Recuperar datos (Asegúrate que los nombres coincidan con el name="" de tu JSP)
            int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
            String fechaInicio = request.getParameter("fecha_inicio");
            String fechaFin = request.getParameter("fecha_fin_estimado");
            
            // El costo total inicial siempre debe ser 0 para que los Triggers sumen el resto
            double costoTotal = 0.0;

            // 2. Insertar en la base de datos
            String sql = "INSERT INTO alquileres (id_cliente, fecha_inicio, fecha_fin_estimado, costo_total) VALUES (?, ?, ?, ?)";

            try (Connection con = ConexionDB.getConexion();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idCliente);
                ps.setString(2, fechaInicio);
                ps.setString(3, fechaFin);
                ps.setDouble(4, costoTotal);

                int filasAfectadas = ps.executeUpdate();
                
                if(filasAfectadas > 0) {
                    // Alquiler creado, vamos a la lista para que el usuario entre a "Ver Detalle"
                    response.sendRedirect("AlquilerServlet?mensaje=exito");
                } else {
                    response.sendRedirect("registrar_alquiler.jsp?error=no_inserto");
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registrar_alquiler.jsp?error=datos_invalidos");
        }
    }
}