package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO dao = new ClienteDAO();

    // ==============================
    // REGISTRAR CLIENTE (POST)
    // ==============================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        Cliente cliente = new Cliente();
        String contentType = request.getContentType();

        try {

            // ===== SI VIENE DE POSTMAN (JSON) =====
            if (contentType != null && contentType.contains("application/json")) {

                StringBuilder sb = new StringBuilder();
                String line;

                try (BufferedReader reader = request.getReader()) {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                }

                String json = sb.toString();

                if (json == null || json.isEmpty()) {
                    response.getWriter().write("{\"error\":\"No llegó JSON\"}");
                    return;
                }

                json = json.replace("{", "")
                           .replace("}", "")
                           .replace("\"", "");

                String[] campos = json.split(",");

                for (String campo : campos) {

                    String[] partes = campo.split(":");
                    if (partes.length < 2) continue;

                    String key = partes[0].trim();
                    String value = partes[1].trim();

                    switch (key) {
                        case "nombre": cliente.setNombre(value); break;
                        case "apellido": cliente.setApellido(value); break;
                        case "cedula": cliente.setCedula(value); break;
                        case "correo": cliente.setCorreoElectronico(value); break;
                        case "telefono": cliente.setTelefono(value); break;
                        case "direccion": cliente.setDireccion(value); break;
                        case "contrasena": cliente.setContrasena(value); break;
                        case "fechaNacimiento": cliente.setFechaNacimiento(Date.valueOf(value)); break;
                        case "rol": cliente.setRol(value); break;
                    }
                }

            } else {
                // ===== VIENE DEL JSP (FORMULARIO) =====
                cliente.setNombre(request.getParameter("nombre"));
                cliente.setApellido(request.getParameter("apellido"));
                cliente.setCedula(request.getParameter("cedula"));
                cliente.setCorreoElectronico(request.getParameter("correo"));
                cliente.setTelefono(request.getParameter("telefono"));
                cliente.setDireccion(request.getParameter("direccion"));
                cliente.setContrasena(request.getParameter("contrasena"));

                String fecha = request.getParameter("fechaNacimiento");
                if (fecha != null && !fecha.isEmpty()) {
                    cliente.setFechaNacimiento(Date.valueOf(fecha));
                }

                cliente.setRol("CLIENTE");
            }

            dao.insertar(cliente);

            response.getWriter().write("{\"mensaje\":\"Cliente registrado correctamente\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\":\"Error procesando solicitud\"}");
        }
    }

    // ==============================
    // LISTAR CLIENTES (GET)
    // ==============================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        List<Cliente> lista = dao.listar();
        PrintWriter out = response.getWriter();

        out.print("[");

        for (int i = 0; i < lista.size(); i++) {
            Cliente c = lista.get(i);

            out.print("{");
            out.print("\"id\":" + c.getIdCliente() + ",");
            out.print("\"nombre\":\"" + c.getNombre() + "\",");
            out.print("\"correo\":\"" + c.getCorreoElectronico() + "\"");
            out.print("}");

            if (i < lista.size() - 1) {
                out.print(",");
            }
        }

        out.print("]");
    }

    // ==============================
    // ELIMINAR CLIENTE (DELETE)
    // ==============================
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        String id = request.getParameter("id");

        if (id == null) {
            response.getWriter().write("{\"error\":\"Debe enviar id\"}");
            return;
        }

        boolean eliminado = dao.eliminar(Integer.parseInt(id));

        if (eliminado) {
            response.getWriter().write("{\"mensaje\":\"Cliente eliminado correctamente\"}");
        } else {
            response.getWriter().write("{\"error\":\"No se pudo eliminar\"}");
        }
    }
}