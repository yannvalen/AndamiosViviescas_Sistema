package com.gestionandamios.controller;

import com.gestionandamios.dao.PagoDAO;
import com.gestionandamios.modelo.Pago;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EditarPagoServlet")
public class EditarPagoServlet extends HttpServlet {
    private PagoDAO dao = new PagoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        // Necesitas un método obtenerPorId en tu PagoDAO (lo incluyo abajo)
        Pago pago = dao.obtenerPorId(id); 
        request.setAttribute("pago", pago);
        request.getRequestDispatcher("editar_pago.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Pago p = new Pago();
        p.setIdPago(Integer.parseInt(request.getParameter("id_pago")));
        p.setIdAlquiler(Integer.parseInt(request.getParameter("id_alquiler")));
        p.setMonto(Double.parseDouble(request.getParameter("monto")));
        p.setMetodoPago(request.getParameter("metodo_pago"));
        p.setFechaPago(Date.valueOf(request.getParameter("fecha_pago")));

        dao.actualizar(p);
        response.sendRedirect("PagoServlet");
    }
}