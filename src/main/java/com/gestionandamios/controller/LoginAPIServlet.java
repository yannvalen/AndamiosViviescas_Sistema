package com.gestionandamios.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginAPIServlet", urlPatterns = {"/api/login"})
public class LoginAPIServlet extends HttpServlet {

    // Este es el que ya tienes (PARA PRODUCCIÓN)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    // AGREGAMOS ESTE (PARA PROBAR EN EL NAVEGADOR)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesarSolicitud(request, response);
    }

    private void procesarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String usuario = request.getParameter("user");
        String clave = request.getParameter("password");

        try {
            if ("admin".equals(usuario) && "12345".equals(clave)) {
                out.print("{\"status\": \"success\", \"message\": \"Autenticación satisfactoria\"}");
            } else {
                // Si entras sin parámetros, te dará este error (es normal)
                out.print("{\"status\": \"error\", \"message\": \"Credenciales invalidas o ausentes\"}");
            }
        } finally {
            out.close();
        }
    }
}