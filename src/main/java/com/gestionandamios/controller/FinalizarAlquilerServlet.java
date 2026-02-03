package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.dao.UbicacionDAO;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FinalizarAlquilerServlet")
public class FinalizarAlquilerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idAlquiler = request.getParameter("id");
        String fechaHoy = LocalDate.now().toString(); 

        try (Connection con = ConexionDB.getConexion()) {
            // 1. Obtener el nombre del cliente antes de cerrar el alquiler
            String sqlNombre = "SELECT c.nombre FROM alquileres a JOIN clientes c ON a.id_cliente = c.id_cliente WHERE a.id_alquiler = ?";
            String nombreCliente = "";
            try (PreparedStatement psNombre = con.prepareStatement(sqlNombre)) {
                psNombre.setInt(1, Integer.parseInt(idAlquiler));
                ResultSet rs = psNombre.executeQuery();
                if (rs.next()) {
                    nombreCliente = rs.getString("nombre");
                }
            }

            // 2. Actualizar la fecha_fin_real en ALQUILERES
            String sqlAlquiler = "UPDATE alquileres SET fecha_fin_real = ? WHERE id_alquiler = ?";
            try (PreparedStatement psAlq = con.prepareStatement(sqlAlquiler)) {
                psAlq.setString(1, fechaHoy);
                psAlq.setInt(2, Integer.parseInt(idAlquiler));
                psAlq.executeUpdate();
            }

            // 3. ¡LA MAGIA! Actualizar el estado en UBICACIONES usando el DAO
            if (!nombreCliente.isEmpty()) {
                UbicacionDAO uDao = new UbicacionDAO();
                uDao.finalizarUbicacionPorCliente(nombreCliente);
            }
            
            response.sendRedirect("AlquilerServlet");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AlquilerServlet?error=devolucion");
        }
    }
}