package com.gestionandamios.controller;

import com.gestionandamios.dao.UbicacionDAO;
import com.gestionandamios.modelo.Ubicacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UbicacionServlet")
public class UbicacionServlet extends HttpServlet {
    private UbicacionDAO dao = new UbicacionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        // Manejo de eliminación
        if ("eliminar".equals(accion)) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                dao.eliminar(id);
            }
        }
        
        // Listar siempre al final
        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("gestiondeubicaciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Como la inserción ahora es automática desde Alquileres, 
        // este método se puede dejar listo para futuras actualizaciones manuales
        // o simplemente redireccionar para evitar errores de acceso.
        response.sendRedirect("UbicacionServlet");
    }
}