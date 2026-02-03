package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SeccionesAndamioServlet")
public class SeccionesAndamioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Map<String, Object>> lista = new ArrayList<>();
        // Acción de eliminar por GET
        String accion = request.getParameter("accion");
        if ("eliminar".equals(accion)) {
            eliminarEquipo(request);
            response.sendRedirect("SeccionesAndamioServlet");
            return;
        }

        // LISTAR: Consultamos la tabla real de maquinaria
        String sql = "SELECT * FROM secciones_andamio";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Map<String, Object> reg = new HashMap<>();
                reg.put("id", rs.getInt("id_seccion"));
                reg.put("codigo", rs.getString("codigo"));
                reg.put("tipo", rs.getString("tipo"));
                reg.put("altura", rs.getDouble("altura_metros"));
                reg.put("estado", rs.getString("estado"));
                reg.put("ubicacion", rs.getString("ubicacion"));
                reg.put("precio", rs.getDouble("precio"));
                lista.add(reg);
            }
            request.setAttribute("inventario", lista);
            request.getRequestDispatcher("reportedeinventario.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        
        try (Connection con = ConexionDB.getConexion()) {
            if ("insertar".equals(accion)) {
                String sql = "INSERT INTO secciones_andamio (codigo, tipo, altura_metros, estado, ubicacion, precio) VALUES (?,?,?,?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, request.getParameter("codigo"));
                    ps.setString(2, request.getParameter("tipo"));
                    ps.setDouble(3, Double.parseDouble(request.getParameter("altura")));
                    ps.setString(4, request.getParameter("estado"));
                    ps.setString(5, request.getParameter("ubicacion"));
                    ps.setDouble(6, Double.parseDouble(request.getParameter("precio")));
                    ps.executeUpdate();
                }
            } else if ("actualizar".equals(accion)) {
                String sql = "UPDATE secciones_andamio SET codigo=?, tipo=?, precio=?, estado=? WHERE id_seccion=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, request.getParameter("codigo"));
                    ps.setString(2, request.getParameter("tipo"));
                    ps.setDouble(3, Double.parseDouble(request.getParameter("precio")));
                    ps.setString(4, request.getParameter("estado"));
                    ps.setInt(5, Integer.parseInt(request.getParameter("id")));
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("SeccionesAndamioServlet");
    }

    private void eliminarEquipo(HttpServletRequest request) {
        try (Connection con = ConexionDB.getConexion()) {
            String sql = "DELETE FROM secciones_andamio WHERE id_seccion=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ps.executeUpdate();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}