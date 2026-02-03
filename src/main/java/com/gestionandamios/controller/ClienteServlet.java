package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("listaClientes", dao.listar());
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente c = new Cliente();
        c.setNombre(request.getParameter("nombre"));
        c.setApellido(request.getParameter("apellido"));
        c.setCedula(request.getParameter("cedula"));
        c.setTelefono(request.getParameter("telefono"));
        c.setCorreoElectronico(request.getParameter("correo"));
        c.setContrasena(request.getParameter("contrasena"));

        String fecha = request.getParameter("fechaNacimiento");
        if (fecha != null && !fecha.isEmpty()) {
            c.setFechaNacimiento(Date.valueOf(fecha));
        }

        dao.insertar(c);

        HttpSession session = request.getSession();
        session.setAttribute("usuario", c.getNombre());

        response.sendRedirect("index.jsp");
    }
}
