<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario de Equipos - Andamios Viviescas</title>
    <style>
        body { font-family: sans-serif; background-color: #0d1a44; color: white; padding: 20px; }
        .container { background: rgba(255,255,255,0.1); padding: 20px; border-radius: 10px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; color: black; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background: #00c8ff; color: white; }
        .form-add { margin-bottom: 30px; border-bottom: 1px solid #00c8ff; padding-bottom: 20px; }
        input { padding: 8px; border-radius: 4px; border: 1px solid #ccc; margin: 5px; }
        .btn { padding: 10px 15px; background: #00c8ff; border: none; color: white; cursor: pointer; border-radius: 5px; }
        .btn-update { background: #28a745; font-size: 0.8em; }
    </style>
</head>
<body>
<div class="container">
    <h2>🛠️ Gestión de Inventario y Equipos</h2>

    <div class="form-add">
        <h3>+ Agregar Nuevo Equipo</h3>
        <form action="SeccionesAndamioServlet" method="POST">
            <input type="hidden" name="accion" value="guardar">
            <input type="text" name="codigo" placeholder="Código (Ej: ESC-01)" required>
            <input type="text" name="tipo" placeholder="Tipo (Ej: Escalera)" required>
            <input type="number" step="0.01" name="altura" placeholder="Altura (mts)" required>
            <input type="number" step="0.01" name="precio" placeholder="Precio Alquiler" required>
            <button type="submit" class="btn">Registrar en Inventario</button>
        </form>
    </div>

    <h3>Lista de Equipos y Precios</h3>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Código</th>
                <th>Descripción</th>
                <th>Altura</th>
                <th>Estado</th>
                <th>Precio Actual</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Map<String, Object>> lista = (List<Map<String, Object>>) request.getAttribute("secciones");
                if (lista != null) {
                    for (Map<String, Object> s : lista) {
            %>
            <tr>
                <td><%= s.get("id_seccion") %></td>
                <td><%= s.get("codigo") %></td>
                <td><%= s.get("tipo") %></td>
                <td><%= s.get("altura") %>m</td>
                <td><strong><%= s.get("estado") %></strong></td>
                <td>$<%= s.get("precio") %></td>
                <td>
                    <form action="SeccionesAndamioServlet" method="POST" style="display:inline;">
                        <input type="hidden" name="accion" value="actualizarPrecio">
                        <input type="hidden" name="id_seccion" value="<%= s.get("id_seccion") %>">
                        <input type="number" name="nuevoPrecio" placeholder="Nuevo $" style="width:80px;" required>
                        <button type="submit" class="btn btn-update">Actualizar</button>
                    </form>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
    <br>
    <a href="AlquilerServlet" style="color: #00c8ff;">← Volver a Alquileres</a>
</div>
</body>
</html>