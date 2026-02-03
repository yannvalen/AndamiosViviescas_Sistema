<%@ page import="java.util.*, com.gestionandamios.modelo.Ubicacion" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ubicaciones - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #0d1a44; color: white; padding: 20px; }
        .container { max-width: 1100px; margin: auto; background: rgba(255,255,255,0.05); padding: 30px; border-radius: 15px; border: 1px solid #00c8ff; }
        h1 { color: #00c8ff; border-bottom: 2px solid #00c8ff; padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; background: white; color: #333; margin-top: 20px; border-radius: 10px; overflow: hidden; }
        th { background: #000D2A; color: #00c8ff; padding: 15px; text-transform: uppercase; font-size: 13px; }
        td { padding: 12px; text-align: center; border-bottom: 1px solid #eee; }
        .badge-active { background: #00ff88; color: #0d1a44; padding: 5px 10px; border-radius: 15px; font-weight: bold; font-size: 11px; }
        .badge-expired { background: #ff4b4b; color: white; padding: 5px 10px; border-radius: 15px; font-weight: bold; font-size: 11px; }
        .btn-back { color: #00c8ff; text-decoration: none; font-weight: bold; display: inline-block; margin-top: 20px; }
    </style>
</head>
<body>
<div class="container">
    <h1>📍 Ubicaciones Activas en Obra</h1>
    <p style="color: #a0aec0;">Este reporte se genera automáticamente al registrar un nuevo alquiler.</p>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Cliente / Obra</th>
                <th>Dirección Exacta</th>
                <th>Estado del Alquiler</th>
                <th>Fecha Entrega</th>
                <th>Fecha Devolución</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Ubicacion> lista = (List<Ubicacion>) request.getAttribute("lista");
                java.util.Date hoy = new java.util.Date();
                if (lista != null && !lista.isEmpty()) {
                    for (Ubicacion u : lista) {
                        boolean vencido = (u.getFechaFin() != null && u.getFechaFin().before(hoy));
            %>
            <tr>
                <td><strong>#<%= u.getIdUbicacion() %></strong></td>
                <td style="text-align: left;"><%= u.getCliente() %></td>
                <td><%= u.getDireccion() %></td>
                <td>
                    <% if(vencido) { %>
                        <span class="badge-expired">VENCIDO / POR RECOGER</span>
                    <% } else { %>
                        <span class="badge-active">EQUIPO EN CAMPO</span>
                    <% } %>
                </td>
                <td><%= u.getFechaInicio() %></td>
                <td><%= u.getFechaFin() %></td>
                <td>
                    <a href="UbicacionServlet?accion=eliminar&id=<%= u.getIdUbicacion() %>" 
                       onclick="return confirm('¿Eliminar historial de esta ubicación?')"
                       style="text-decoration: none;">🗑️</a>
                </td>
            </tr>
            <% 
                    }
                } else {
            %>
            <tr><td colspan="7" style="padding: 40px; color: #888;">No hay equipos en campo actualmente.</td></tr>
            <% } %>
        </tbody>
    </table>
    
    <a href="index.jsp" class="btn-back">← Volver al Panel de Control</a>
</div>
</body>
</html>