package com.gestionandamios.controller;

import com.gestionandamios.conexion.ConexionDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/OlvidoContrasenaServlet")
public class OlvidoContrasenaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");

        String sql = "SELECT contrasena FROM clientes WHERE correo_electronico = ?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                response.sendRedirect("olvido_contrasena.jsp?ok=1");
            } else {
                response.sendRedirect("olvido_contrasena.jsp?error=1");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("olvido_contrasena.jsp?error=db");
        }
    }
}
