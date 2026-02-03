package com.gestionandamios.controller;

import com.gestionandamios.dao.AlquilerDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarAlquilerServlet") // ¡Corregido para coincidir con el JSP!
public class EliminarAlquilerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idAlquiler = request.getParameter("id");

        if (idAlquiler != null && !idAlquiler.isEmpty()) {
            try {
                AlquilerDAO dao = new AlquilerDAO();
                dao.eliminar(Integer.parseInt(idAlquiler));
                System.out.println("DEBUG: Alquiler " + idAlquiler + " eliminado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("AlquilerServlet");
    }
}