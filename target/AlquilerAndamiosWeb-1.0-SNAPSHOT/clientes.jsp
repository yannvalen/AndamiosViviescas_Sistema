<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Clientes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn-delete { color: red; font-weight: bold; text-decoration: none; }
        .btn-edit { color: blue; font-weight: bold; text-decoration: none; }
    </style>
</head>
<body>
    <h2>Registrar Nuevo Cliente</h2>
    <form action="ClienteServlet" method="post">
        Nombre: <input type="text" name="nombre" required><br>
        Apellido: <input type="text" name="apellido" required><br>
        Cedula: <input type="text" name="cedula" required><br>
        Telefono: <input type="text" name="telefono"><br>
        Direccion: <input type="text" name="direccion"><br>
        Correo: <input type="email" name="correo" required><br>
        Fecha nacimiento: <input type="date" name="fechaNacimiento"><br>
        Contraseña: <input type="password" name="contrasena" required><br>
        <button type="submit">Guardar</button>
    </form>

    <hr>
    <h2>Lista de Clientes</h2>
    <table>
        <tr>
            <th>ID</th><th>Nombre</th><th>Cedula</th><th>Correo</th><th>Acciones</th>
        </tr>
        <%
            List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
            if (lista != null) {
                for (Cliente c : lista) {
        %>
        <tr>
            <td><%= c.getIdCliente() %></td>
            <td><%= c.getNombre() %> <%= c.getApellido() %></td>
            <td><%= c.getCedula() %></td>
            <td><%= c.getCorreoElectronico() %></td>
            <td>
                <a class="btn-edit" href="ClienteServlet?accion=editar&id=<%= c.getIdCliente() %>">Editar</a> | 
                <a class="btn-delete" href="ClienteServlet?accion=eliminar&id=<%= c.getIdCliente() %>" 
                   onclick="return confirm('¿Está seguro de eliminar este cliente?')">Eliminar</a>
            </td>
        </tr>
        <% } } else { %>
            <tr><td colspan="5">No hay clientes registrados o la lista no cargó.</td></tr>
        <% } %>
    </table>
    <br>
    <a href="index.jsp">Volver al Panel</a>
</body>
</html>