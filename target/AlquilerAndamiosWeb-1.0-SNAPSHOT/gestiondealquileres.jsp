<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.gestionandamios.modelo.Alquiler" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Alquileres - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; margin: 40px; background-color: #f4f7f6; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        th { background-color: #0d1a44; color: white; padding: 15px; }
        td { padding: 12px; text-align: center; border-bottom: 1px solid #eee; }
        .btn-nuevo { background: #00c8ff; color: #0d1a44; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold; }
        .badge { padding: 4px 8px; border-radius: 4px; color: white; font-size: 11px; font-weight: bold; }
        .btn-pago { background: #2ecc71; color: white; padding: 4px 8px; border-radius: 4px; text-decoration: none; font-size: 11px; }
    </style>
</head>
<body>
    <h1>🏗️ Control de Alquileres</h1>
    
    <div style="margin-bottom: 25px; display: flex; justify-content: space-between;">
        <a href="registrar_alquiler.jsp" class="btn-nuevo">+ Nuevo Alquiler</a>
        <a href="index.jsp" style="text-decoration: none; color: #0d1a44; font-weight: bold;">← Volver al Panel</a>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Inicio</th>
                <th>Fin Estimado</th>
                <th>Estado Pago</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Alquiler> lista = (List<Alquiler>) request.getAttribute("listaAlquileres");
            if (lista != null) {
                for (Alquiler a : lista) {
                    String estado = (a.getEstadoPago() != null) ? a.getEstadoPago() : "PENDIENTE";
                    String color = "PAGADO".equals(estado) ? "#2ecc71" : "#ff4b4b";
        %>
            <tr>
                <td><%= a.getIdAlquiler() %></td>
                <td>ID: <%= a.getIdCliente() %></td>
                <td><%= a.getFechaInicio() %></td>
                <td><%= a.getFechaFinEstimada() %></td>
                <td>
                    <span class="badge" style="background: <%= color %>;">
                        <%= estado %>
                    </span>
                </td>
                <td>
                    <% if(!"PAGADO".equals(estado)) { %>
                        <a href="registrar_pago.jsp?id_alquiler=<%= a.getIdAlquiler() %>&monto=<%= a.getCostoTotal() %>" class="btn-pago">Cobrar</a>
                    <% } %>
                    | <a href="EliminarAlquilerServlet?id=<%= a.getIdAlquiler() %>" onclick="return confirm('¿Eliminar registro?')">🗑️</a>
                </td>
            </tr>
        <% } } else { %>
            <tr><td colspan="6">No hay datos para mostrar. Revisa la conexión.</td></tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>