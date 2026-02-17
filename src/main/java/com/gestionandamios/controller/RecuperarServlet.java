package com.gestionandamios.controller;

import com.gestionandamios.dao.ClienteDAO;
import com.gestionandamios.modelo.Cliente;
import com.gestionandamios.util.EmailService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

        // Generar código de 6 dígitos
        String codigo = String.valueOf(100000 + new Random().nextInt(900000));

        // GUARDAR EN SESIÓN (Fíjate bien en estos nombres)
        HttpSession session = request.getSession();
        session.setAttribute("codigoRecuperacion", codigo);
        session.setAttribute("clienteRecuperacion", cliente); // Usamos 'clienteRecuperacion'

        // Enviar correo
        emailService.enviarCodigo(correo, codigo);

        response.sendRedirect("verificarCodigo.jsp");
    }
}