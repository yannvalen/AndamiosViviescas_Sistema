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
        
        // --- ACCIÓN ELIMINAR ---
        if (accion != null && accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("ClienteServlet");
            return; 
        } 
        
        // --- ACCIÓN EDITAR (Cargar datos en el formulario) ---
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

        // --- ACCIÓN POR DEFECTO: LISTAR ---
        request.setAttribute("listaClientes", dao.listar());
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente c = new Cliente();
        
        // 1. Captura de parámetros desde el formulario o Postman
        c.setNombre(request.getParameter("nombre"));
        c.setApellido(request.getParameter("apellido"));
        c.setCedula(request.getParameter("cedula"));
        c.setTelefono(request.getParameter("telefono"));
        c.setDireccion(request.getParameter("direccion"));
        c.setCorreoElectronico(request.getParameter("correo"));
        c.setContrasena(request.getParameter("contrasena"));

        // 2. Manejo de la Fecha de Nacimiento (Evita errores si llega vacío)
        String fecha = request.getParameter("fechaNacimiento");
        if (fecha != null && !fecha.isEmpty()) {
            try {
                c.setFechaNacimiento(Date.valueOf(fecha));
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: Formato de fecha inválido: " + fecha);
            }
        }

        // 3. Manejo del ROL (Garantiza que no sea NULL para MySQL)
        String rolRecibido = request.getParameter("rol");
        if (rolRecibido != null && !rolRecibido.isEmpty()) {
            c.setRol(rolRecibido);
        } else {
            c.setRol("CLIENTE"); // Valor por defecto obligatorio
        }

        // 4. Lógica de Decisión: ¿Es un nuevo registro o una actualización?
        String idStr = request.getParameter("id_cliente");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            // Si el ID no existe o está vacío, es un INSERT
            System.out.println("LOG SERVLET: Iniciando INSERTAR para " + c.getNombre());
            dao.insertar(c);
        } else {
            // Si el ID tiene valor, es un UPDATE
            try {
                int id = Integer.parseInt(idStr);
                c.setIdCliente(id);
                System.out.println("LOG SERVLET: Iniciando ACTUALIZAR para ID: " + id);
                dao.actualizar(c);
            } catch (NumberFormatException e) {
                System.out.println("LOG SERVLET: ID inválido, intentando insertar como nuevo.");
                dao.insertar(c);
            }
        }

        // 5. Redirección al listado
        response.sendRedirect("ClienteServlet");
    }
}