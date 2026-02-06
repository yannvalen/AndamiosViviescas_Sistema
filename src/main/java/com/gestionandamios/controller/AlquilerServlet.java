package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.dao.UbicacionDAO;
import com.gestionandamios.modelo.Alquiler;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Controlador principal para el módulo de Alquileres.
 * Administra el flujo de peticiones GET para visualización y POST para persistencia.
 * Implementa el patrón MVC utilizando Servlets de Java EE.
 * * @author Yann Valen
 */
@WebServlet("/AlquilerServlet")
public class AlquilerServlet extends HttpServlet {

    /**
     * Maneja la obtención de datos para mostrar en la interfaz de gestión.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM alquileres ORDER BY id_alquiler DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Alquiler a = new Alquiler();
                a.setIdAlquiler(rs.getInt("id_alquiler"));
                a.setIdCliente(rs.getInt("id_cliente"));
                a.setFechaInicio(rs.getDate("fecha_inicio"));
                a.setFechaFinEstimada(rs.getDate("fecha_fin_estimada")); 
                a.setFechaFinReal(rs.getDate("fecha_fin_real"));
                a.setCostoTotal(rs.getDouble("costo_total"));
                a.setEstadoPago(rs.getString("estado_pago")); 
                lista.add(a);
            }
            
            request.setAttribute("listaAlquileres", lista);
            request.getRequestDispatcher("gestiondealquileres.jsp").forward(request, response);
            
        } catch (SQLException e) { 
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Gestiona el registro de nuevos contratos procesando datos de formulario.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try (Connection con = ConexionDB.getConexion()) {
            if ("registrar".equals(accion)) {
                // Captura de parámetros con estándar de validación básico
                int idCliente = Integer.parseInt(request.getParameter("id_cliente"));
                String fInicio = request.getParameter("fecha_inicio");
                String fFinEst = request.getParameter("fecha_fin_estimada");
                double costo = Double.parseDouble(request.getParameter("costo_total"));
                
                String nombreCliente = request.getParameter("nombre_cliente"); 
                String direccionObra = request.getParameter("direccion_obra");

                String sqlInsert = "INSERT INTO alquileres (id_cliente, fecha_inicio, fecha_fin_estimada, costo_total) VALUES (?, ?, ?, ?)";
                
                try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                    ps.setInt(1, idCliente);
                    ps.setDate(2, Date.valueOf(fInicio));
                    ps.setDate(3, Date.valueOf(fFinEst));
                    ps.setDouble(4, costo);
                    
                    if (ps.executeUpdate() > 0) {
                        // Invocación lógica a DAO secundario para ubicación automática
                        UbicacionDAO uDao = new UbicacionDAO();
                        uDao.insertarAutomatico(nombreCliente, direccionObra, Date.valueOf(fInicio), Date.valueOf(fFinEst));
                    }
                }
            }
            response.sendRedirect("AlquilerServlet");
        } catch (Exception e) {
            response.sendRedirect("AlquilerServlet?status=error");
        }
    }
}