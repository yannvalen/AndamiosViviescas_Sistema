<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.DetalleAlquiler" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalles del Alquiler - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; margin: 40px; }
        .container { max-width: 950px; margin: auto; background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        h2, h3 { color: #0d1a44; border-bottom: 2px solid #00c8ff; padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; margin-bottom: 10px; }
        th { background-color: #0d1a44; color: white; padding: 12px; text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #eee; }
        tr:hover { background-color: #f9f9f9; }
        
        .total-row { background-color: #0d1a44 !important; color: white; font-weight: bold; font-size: 1.1em; }
        .btn-volver { display: inline-block; text-decoration: none; color: #0d1a44; font-weight: bold; border: 1px solid #0d1a44; padding: 8px 15px; border-radius: 5px; transition: 0.3s; }
        .btn-volver:hover { background: #0d1a44; color: white; }
        
        .admin-form { background: #eef9ff; padding: 20px; border-radius: 8px; border: 1px solid #bde4ff; margin-top: 30px; }
        .btn-guardar { background: #00c8ff; color: #0d1a44; border: none; padding: 10px 20px; border-radius: 5px; font-weight: bold; cursor: pointer; }
        .btn-update { background: #28a745; color: white; border: none; padding: 5px 8px; border-radius: 3px; cursor: pointer; font-size: 0.8em; }
        .btn-eliminar { color: #ff4b4b; text-decoration: none; font-weight: bold; font-size: 0.9em; margin-left: 10px; }
        
        .input-mini { width: 60px; padding: 5px; border: 1px solid #ccc; border-radius: 4px; }
        .nota-negocio { font-size: 0.85em; color: #555; font-style: italic; margin-top: 5px; }
    </style>
</head>
<body>
<div class="container">
    <h2>📋 Detalle del Alquiler #<%= request.getAttribute("idAlquilerActual") %></h2>

    <table>
        <thead>
            <tr>
                <th>ID Detalle</th>
                <th>Sección (ID)</th>
                <th>Cantidad (Sets)</th>
                <th>Precio Unitario</th>
                <th>Subtotal</th>
                <% if ("ADMIN".equals(session.getAttribute("rol"))) { %>
                    <th>Acciones</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
        <%
            List<DetalleAlquiler> lista = (List<DetalleAlquiler>) request.getAttribute("detalles");
            boolean isAdmin = "ADMIN".equals(session.getAttribute("rol"));
            Object idActual = request.getAttribute("idAlquilerActual");
            double granTotal = 0;

            if (lista != null && !lista.isEmpty()) {
                for (DetalleAlquiler d : lista) {
                    double subtotal = d.getCantidad() * d.getPrecioUnitario();
                    granTotal += subtotal;
        %>
            <tr>
                <td><%= d.getIdDetalle() %></td>
                <td><strong>#<%= d.getIdSeccion() %></strong></td>
                <td>
                    <% if (isAdmin) { %>
                        <form action="GuardarDetalleServlet" method="post" style="display: flex; gap: 5px; align-items: center;">
                            <input type="hidden" name="accion" value="actualizarCantidad">
                            <input type="hidden" name="id_detalle" value="<%= d.getIdDetalle() %>">
                            <input type="hidden" name="idAlquiler" value="<%= idActual %>">
                            <input type="number" name="cantidad" value="<%= d.getCantidad() %>" class="input-mini">
                            <button type="submit" class="btn-update" title="Actualizar Cantidad">💾</button>
                        </form>
                    <% } else { %>
                        <%= d.getCantidad() %>
                    <% } %>
                </td>
                <td>$<%= d.getPrecioUnitario() %></td>
                <td>$<%= subtotal %></td>
                <% if (isAdmin) { %>
                    <td>
                        <a href="EliminarDetalleServlet?idDetalle=<%= d.getIdDetalle() %>&idAlquiler=<%= d.getIdAlquiler() %>" 
                           class="btn-eliminar" 
                           onclick="return confirm('¿Remover esta pieza del alquiler?')">Eliminar</a>
                    </td>
                <% } %>
            </tr>
        <%
                }
        %>
            <tr class="total-row">
                <td colspan="4" style="text-align: right;">TOTAL A PAGAR:</td>
                <td>$<%= granTotal %></td>
                <% if (isAdmin) { %> <td></td> <% } %>
            </tr>
        <%
            } else {
        %>
            <tr><td colspan="<%= isAdmin ? 6 : 5 %>" style="text-align:center; padding:30px; color: #888;">No hay equipos registrados en este contrato.</td></tr>
        <% } %>
        </tbody>
    </table>

    <% if (isAdmin) { %>
    <div class="admin-form">
        <h3>+ Agregar Pieza al Alquiler</h3>
        <p class="nota-negocio">* Recuerda: 1 Sección equivale a 2 cuerpos de andamio con sus crucetas.</p>
        <form action="GuardarDetalleServlet" method="post" style="display: flex; gap: 15px; align-items: flex-end; flex-wrap: wrap;">
            <input type="hidden" name="idAlquiler" value="<%= idActual %>">
            
            <div style="display: flex; flex-direction: column;">
                <label>ID Sección:</label>
                <input type="number" name="idSeccion" required style="width: 80px; padding: 8px;">
            </div>
            <div style="display: flex; flex-direction: column;">
                <label>Cantidad:</label>
                <input type="number" name="cantidad" required style="width: 80px; padding: 8px;">
            </div>
            <div style="display: flex; flex-direction: column;">
                <label>Precio Unitario:</label>
                <input type="number" step="0.01" name="precioUnitario" required style="width: 120px; padding: 8px;">
            </div>
            
            <button type="submit" class="btn-guardar">Registrar Equipo</button>
        </form>
    </div>
    <% } %>

    <br>
    <a href="AlquilerServlet" class="btn-volver">⬅ Volver a Gestión de Alquileres</a>
</div>
</body>
</html>