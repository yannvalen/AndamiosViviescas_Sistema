<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.Proveedor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Proveedores</title>
    <style>
        body { font-family: Arial; margin: 20px; background-color: #f4f7f6; }
        table { border-collapse: collapse; width: 100%; background: white; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; }
        .btn-edit { color: blue; text-decoration: none; font-weight: bold; }
        .btn-delete { color: red; text-decoration: none; font-weight: bold; margin-left: 10px; }
    </style>
</head>
<body>
    <h2>Gestión de Proveedores</h2>
    <a href="registro_proveedores.jsp" style="text-decoration:none;">
        <button style="padding:10px; background:#28a745; color:white; border:none; border-radius:5px; cursor:pointer;">
            + Registrar Nuevo Proveedor
        </button>
    </a>
    <hr>
    <table>
        <tr>
            <th>ID</th><th>Nombre</th><th>RUT/NIT</th><th>Contacto</th><th>Teléfono</th><th>Acciones</th>
        </tr>
        <%
        List<Proveedor> lista = (List<Proveedor>) request.getAttribute("listaProveedores");
        if (lista != null) {
            for (Proveedor p : lista) {
        %>
        <tr>
            <td><%= p.getIdProveedor() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getRutNit() %></td>
            <td><%= p.getContactoPrincipal() %></td>
            <td><%= p.getTelefono() %></td>
            <td>
                <a class="btn-edit" href="ProveedorServlet?accion=editar&id=<%= p.getIdProveedor() %>">Editar</a>
                <a class="btn-delete" href="ProveedorServlet?accion=eliminar&id=<%= p.getIdProveedor() %>" 
                   onclick="return confirm('¿Eliminar este proveedor?')">Eliminar</a>
            </td>
        </tr>
        <% } } %>
    </table>
    <br>
    <a href="index.jsp">Volver al Panel</a>
</body>
</html>