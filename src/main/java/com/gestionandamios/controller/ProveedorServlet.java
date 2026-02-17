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
        
        String accion = request.getParameter("accion");
        
        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("ProveedorServlet");
            return;
        } else if ("editar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Proveedor p = dao.buscarPorId(id);
            request.setAttribute("prov", p);
            request.getRequestDispatcher("editar_proveedor.jsp").forward(request, response);
            return;
        }

        request.setAttribute("listaProveedores", dao.listar());
        request.getRequestDispatcher("gestiondeproveedores.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id_proveedor");
        
        Proveedor p = new Proveedor();
        p.setNombre(request.getParameter("nombre"));
        p.setRutNit(request.getParameter("rut_nit"));
        p.setContactoPrincipal(request.getParameter("contacto_principal"));
        p.setTelefono(request.getParameter("telefono"));

        if (idStr == null || idStr.isEmpty()) {
            dao.insertar(p); // Nuevo registro
        } else {
            p.setIdProveedor(Integer.parseInt(idStr));
            dao.actualizar(p); // Actualización
        }
        
        response.sendRedirect("ProveedorServlet");
    }
}