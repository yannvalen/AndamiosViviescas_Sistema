<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.Proveedor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Proveedores</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <h2>Registrar Nuevo Proveedor</h2>
    <form action="ProveedorServlet" method="post">
        Nombre: <input name="nombre" required><br>
        RUT/NIT: <input name="rut_nit" required><br>
        Contacto: <input name="contacto_principal"><br>
        Teléfono: <input name="telefono"><br>
        <button type="submit">Guardar</button>
    </form>
    <hr>
    <h2>Lista de Proveedores</h2>
    <table>
        <tr>
            <th>ID</th><th>Nombre</th><th>RUT</th><th>Contacto</th><th>Teléfono</th>
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
        </tr>
        <% } } %>
    </table>
</body>
</html>