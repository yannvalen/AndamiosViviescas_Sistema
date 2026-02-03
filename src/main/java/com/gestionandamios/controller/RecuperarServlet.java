package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;
import com.gestionandamios.util.EmailService; // Asegúrate de haber creado esta clase
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet("/RecuperarServlet")
public class RecuperarServlet extends HttpServlet {
    private ClienteDAO dao = new ClienteDAO();
    private EmailService emailService = new EmailService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        Cliente cliente = dao.buscarPorCorreo(correo);

        if (cliente == null) {
            request.setAttribute("mensaje", "❌ El correo no está registrado");
            request.getRequestDispatcher("olvido_contrasena.jsp").forward(request, response);
            return;
        }

        // 1. Generar código de 6 dígitos
        String codigo = String.valueOf(100000 + new Random().nextInt(900000));

        // 2. Guardar en sesión para validar después
        HttpSession session = request.getSession();
        session.setAttribute("codigoRecuperacion", codigo);
        session.setAttribute("clienteRecuperacion", cliente);

        // 3. ENVIAR CORREO REAL AL CLIENTE
        emailService.enviarCodigo(correo, codigo);

        // 4. Redirigir a la página de verificación
        response.sendRedirect("verificarCodigo.jsp");
    }
}