package com.gestionandamios.controller;

import com.gestionandamios.dao.ProveedorDAO;
import com.gestionandamios.modelo.Proveedor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {

    ProveedorDAO dao = new ProveedorDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Proveedor> lista = dao.listar();
        request.setAttribute("listaProveedores", lista);
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

        response.sendRedirect("ProveedorServlet");
    }
}
