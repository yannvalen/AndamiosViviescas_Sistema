package com.gestionandamios.controller;

import com.gestionandamios.dao.ProveedorDAO;
import com.gestionandamios.modelo.Proveedor;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {
    ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Carga la lista y la envía al JSP
        request.setAttribute("listaProveedores", dao.listar());
        request.getRequestDispatcher("gestiondeproveedores.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Proveedor p = new Proveedor();
        p.setNombre(request.getParameter("nombre"));
        p.setRutNit(request.getParameter("rut_nit"));
        p.setContactoPrincipal(request.getParameter("contacto_principal"));
        p.setTelefono(request.getParameter("telefono"));

        dao.insertar(p);
        
        // MUY IMPORTANTE: Redirigir al doGet para refrescar la lista
        response.sendRedirect("ProveedorServlet");
    }
}