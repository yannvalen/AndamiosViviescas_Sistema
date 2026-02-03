package com.gestionandamios.controller;

import com.gestionandamios.dao.PagoDAO;
import com.gestionandamios.dao.AlquilerDAO;
import com.gestionandamios.modelo.Pago;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PagoServlet")
public class PagoServlet extends HttpServlet {
    private PagoDAO dao = new PagoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pagos", dao.listar());
        request.getRequestDispatcher("gestiondepagos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        AlquilerDAO alqDao = new AlquilerDAO();

        if ("registrar".equals(accion)) {
            int idAlquiler = Integer.parseInt(request.getParameter("id_alquiler"));
            Pago p = new Pago();
            p.setIdAlquiler(idAlquiler);
            p.setMonto(Double.parseDouble(request.getParameter("monto")));
            p.setMetodoPago(request.getParameter("metodo_pago"));
            p.setFechaPago(new Date(System.currentTimeMillis()));

            dao.insertar(p);
            // Marcar como pagado
            alqDao.actualizarEstadoPago(idAlquiler, "PAGADO");

        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            // LÓGICA DE SEGURIDAD:
            // 1. Buscamos el pago para saber de qué alquiler es
            Pago p = dao.obtenerPorId(id);
            if (p != null) {
                // 2. Eliminamos el registro de dinero
                dao.eliminar(id);
                // 3. Devolvemos el alquiler a PENDIENTE
                alqDao.actualizarEstadoPago(p.getIdAlquiler(), "PENDIENTE");
            }
        }
        response.sendRedirect("PagoServlet");
    }
}