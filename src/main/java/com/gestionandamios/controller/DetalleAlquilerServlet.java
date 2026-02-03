package com.gestionandamios.controller;

import com.gestionandamios.dao.DetalleAlquilerDAO;
import com.gestionandamios.modelo.DetalleAlquiler;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleAlquilerServlet")
public class DetalleAlquilerServlet extends HttpServlet {

    private DetalleAlquilerDAO dao = new DetalleAlquilerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Verificar sesión
        HttpSession session = request.getSession();
        if (session.getAttribute("correo") == null) {
            response.sendRedirect("registro_cliente.jsp");
            return;
        }

        // 2. Obtener el ID del alquiler que viene de la tabla
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                List<DetalleAlquiler> lista = dao.listarPorAlquiler(id);
                
                // Pasamos la lista al JSP
                request.setAttribute("detalles", lista);
                request.setAttribute("idAlquilerActual", id); // Para saber a qué alquiler pertenecen
                
                request.getRequestDispatcher("Detalle_Alquiler.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendRedirect("AlquilerServlet");
            }
        } else {
            response.sendRedirect("AlquilerServlet");
        }
    }
}