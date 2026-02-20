package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
    private ClienteDAO dao = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null && accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("ClienteServlet");
            return; 
        } 
        
        if (accion != null && accion.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Cliente clienteExistente = null;
            for(Cliente c : dao.listar()){
                if(c.getIdCliente() == id) { 
                    clienteExistente = c; 
                    break; 
                }
            }
            request.setAttribute("cliente", clienteExistente);
            request.getRequestDispatcher("editar_cliente.jsp").forward(request, response);
            return;
        }

        request.setAttribute("listaClientes", dao.listar());
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente c = new Cliente();
        String idStr = request.getParameter("id_cliente");
        
        c.setNombre(request.getParameter("nombre"));
        c.setApellido(request.getParameter("apellido"));
        c.setCedula(request.getParameter("cedula"));
        c.setTelefono(request.getParameter("telefono"));
        c.setDireccion(request.getParameter("direccion"));
        c.setCorreoElectronico(request.getParameter("correo"));
        c.setContrasena(request.getParameter("contrasena"));
        
        // Manejo del ROL para evitar errores en MySQL
        String rolRecibido = request.getParameter("rol");
        if (rolRecibido != null && !rolRecibido.isEmpty()) {
            c.setRol(rolRecibido);
        } else {
            c.setRol("CLIENTE"); // Valor por defecto si no se envía en Postman
        }

        String fecha = request.getParameter("fechaNacimiento");
        if (fecha != null && !fecha.isEmpty()) {
            c.setFechaNacimiento(Date.valueOf(fecha));
        }

        if (idStr != null && !idStr.isEmpty()) {
            c.setIdCliente(Integer.parseInt(idStr));
            dao.actualizar(c);
        } else {
            dao.insertar(c);
        }

        response.sendRedirect("ClienteServlet");
    }
}