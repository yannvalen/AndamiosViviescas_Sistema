package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/VerificarCodigoServlet")
public class VerificarCodigoServlet extends HttpServlet {
    private ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String codigoIngresado = request.getParameter("codigo");
        String nuevaClave = request.getParameter("nueva");
        
        HttpSession session = request.getSession();
        // Recuperar con los mismos nombres del servlet anterior
        String codigoReal = (String) session.getAttribute("codigoRecuperacion");
        Cliente cliente = (Cliente) session.getAttribute("clienteRecuperacion");

        if (codigoReal != null && codigoReal.equals(codigoIngresado) && cliente != null) {
            // Actualizar en base de datos
            boolean actualizado = dao.actualizarContrasena(cliente.getIdCliente(), nuevaClave);
            
            if (actualizado) {
                session.invalidate(); 
                response.sendRedirect("index.jsp?msg=success");
            } else {
                request.setAttribute("mensaje", "❌ Error al actualizar en base de datos");
                request.getRequestDispatcher("verificarCodigo.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mensaje", "❌ Código incorrecto o sesión expirada");
            request.getRequestDispatcher("verificarCodigo.jsp").forward(request, response);
        }
    }
}