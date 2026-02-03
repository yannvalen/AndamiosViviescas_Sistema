<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario ADMIN - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; margin: 30px; background-color: #f4f7f6; }
        .card { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th { background: #0d1a44; color: white; padding: 12px; }
        td { padding: 10px; border-bottom: 1px solid #ddd; text-align: center; }
        .btn-del { color: #dc3545; text-decoration: none; font-weight: bold; margin-left: 10px; }
        .form-box { background: #eef9ff; padding: 15px; margin-bottom: 20px; border-radius: 5px; border-left: 5px solid #00c8ff; }
        input, select { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        .btn-save { background: #28a745; color: white; border: none; padding: 6px 12px; cursor: pointer; border-radius: 4px; }
    </style>
</head>
<body>
<div class="card">
    <h1>📋 Gestión de Inventario (ADMIN)</h1>

    <div class="form-box">
        <h3>+ Registrar Nuevo Equipo</h3>
        <form action="SeccionesAndamioServlet" method="POST">
            <input type="hidden" name="accion" value="insertar">
            <input type="text" name="codigo" placeholder="Código" required style="width: 80px;">
            <input type="text" name="tipo" placeholder="Nombre de Maquinaria" required>
            <input type="number" step="0.01" name="altura" placeholder="Altura" required style="width: 70px;">
            <input type="number" step="0.01" name="precio" placeholder="Precio" required style="width: 90px;">
            <select name="estado">
                <option value="Disponible">Disponible</option>
                <option value="Mantenimiento">Mantenimiento</option>
            </select>
            <input type="text" name="ubicacion" value="BodegaCentral" style="width: 100px;">
            <button type="submit" class="btn-save" style="background:#007bff">Guardar</button>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Código</th>
                <th>Tipo</th>
                <th>Estado</th>
                <th>Precio Actual</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Map<String, Object>> lista = (List<Map<String, Object>>) request.getAttribute("inventario");
                if (lista != null) {
                    for (Map<String, Object> r : lista) {
            %>
            <tr>
                <form action="SeccionesAndamioServlet" method="POST">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="<%= r.get("id") %>">
                    <td><%= r.get("id") %></td>
                    <td><input type="text" name="codigo" value="<%= r.get("codigo") %>" style="width:70px;"></td>
                    <td><input type="text" name="tipo" value="<%= r.get("tipo") %>"></td>
                    <td>
                        <select name="estado">
                            <option value="Disponible" <%= "Disponible".equals(r.get("estado")) ? "selected" : "" %>>Disponible</option>
                            <option value="Alquilado" <%= "Alquilado".equals(r.get("estado")) ? "selected" : "" %>>Alquilado</option>
                            <option value="Mantenimiento" <%= "Mantenimiento".equals(r.get("estado")) ? "selected" : "" %>>Mantenimiento</option>
                        </select>
                    </td>
                    <td>$<input type="number" step="0.01" name="precio" value="<%= r.get("precio") %>" style="width:80px;"></td>
                    <td>
                        <button type="submit" class="btn-save">Actualizar</button>
                        <a href="SeccionesAndamioServlet?accion=eliminar&id=<%= r.get("id") %>" 
                           class="btn-del" onclick="return confirm('¿Eliminar equipo?')">🗑</a>
                    </td>
                </form>
            </tr>
            <% 
                    }
                } 
            %>
        </tbody>
    </table>
    <br>
    <a href="gestiondealquileres.jsp">⬅ Volver al Panel</a>
</div>
</body>
</html>