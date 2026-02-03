<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.gestionandamios.modelo.Pago" %>
<!DOCTYPE html>
<html>
<head>
    <title>Control de Pagos - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #0d1a44; color: white; padding: 30px; }
        .container { max-width: 1000px; margin: auto; background: rgba(255,255,255,0.05); padding: 25px; border-radius: 15px; border: 1px solid #00c8ff; }
        h2 { color: #00c8ff; border-bottom: 2px solid #00c8ff; padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; background: white; color: #333; border-radius: 10px; overflow: hidden; }
        th { background: #000D2A; color: #00c8ff; padding: 15px; }
        td { padding: 12px; text-align: center; border-bottom: 1px solid #eee; }
        .monto { font-weight: bold; color: #2ecc71; }
        .btn-eliminar { color: #ff4b4b; text-decoration: none; font-weight: bold; }
        .btn-editar { color: #00c8ff; text-decoration: none; font-weight: bold; margin-right: 10px; }
        .back-link { display: inline-block; margin-top: 20px; color: #00c8ff; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <h2>💰 Historial de Pagos Recibidos</h2>
    <table>
        <thead>
            <tr>
                <th>ID Pago</th>
                <th>Contrato</th>
                <th>Monto</th>
                <th>Método</th>
                <th>Fecha</th>
                <% if("valenr.v22@gmail.com".equals(session.getAttribute("correo"))){ %>
                <th>Acciones</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <%
                List<Pago> lista = (List<Pago>) request.getAttribute("pagos");
                if(lista != null && !lista.isEmpty()){
                    for(Pago p : lista){
            %>
            <tr>
                <td>#<%= p.getIdPago() %></td>
                <td>Contrato <%= p.getIdAlquiler() %></td>
                <td class="monto">$<%= p.getMonto() %></td>
                <td><strong><%= p.getMetodoPago() %></strong></td>
                <td><%= p.getFechaPago() %></td>
                <% if("valenr.v22@gmail.com".equals(session.getAttribute("correo"))){ %>
                <td>
                    <a href="EditarPagoServlet?id=<%= p.getIdPago() %>" class="btn-editar">✏️</a>
                    <form action="PagoServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id" value="<%= p.getIdPago() %>">
                        <button type="submit" class="btn-eliminar" style="background:none; border:none; cursor:pointer;" onclick="return confirm('¿Eliminar recibo?')">🗑️</button>
                    </form>
                </td>
                <% } %>
            </tr>
            <% } } else { %>
            <tr><td colspan="6" style="padding: 30px; color: #888;">No hay pagos registrados aún.</td></tr>
            <% } %>
        </tbody>
    </table>
    <a href="AlquilerServlet" class="back-link">← Volver a Alquileres</a>
</div>
</body>
</html>