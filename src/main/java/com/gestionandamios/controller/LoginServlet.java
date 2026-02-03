package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("contacto"); 
        String clave = request.getParameter("contrasena");

        String sql = "SELECT id_cliente, nombre, rol, correo_electronico FROM clientes WHERE correo_electronico = ? AND contrasena = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                // Guardamos los datos con nombres claros para los demás Servlets
                session.setAttribute("id_usuario", rs.getInt("id_cliente")); 
                session.setAttribute("usuario", rs.getString("nombre"));
                session.setAttribute("rol", rs.getString("rol"));
                session.setAttribute("correo", rs.getString("correo_electronico"));

                response.sendRedirect("index.jsp"); 
            } else {
                response.sendRedirect("registro_cliente.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("registro_cliente.jsp?error=db");
        }
    }
} 