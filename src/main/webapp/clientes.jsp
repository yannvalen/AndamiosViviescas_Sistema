<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.Cliente" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Clientes</title>
</head>
<body>

<h2>Registrar Cliente</h2>

<form action="ClienteServlet" method="post">
    Nombre: <input type="text" name="nombre"><br>
    Apellido: <input type="text" name="apellido"><br>
    Cedula: <input type="text" name="cedula"><br>
    Telefono: <input type="text" name="telefono"><br>
    Direccion: <input type="text" name="direccion"><br>
    Correo: <input type="email" name="correo"><br>
    Fecha nacimiento: <input type="date" name="fechaNacimiento"><br>
    Contraseña: <input type="password" name="contrasena"><br>

    <button type="submit">Guardar</button>
</form>

<hr>

<h2>Lista de Clientes</h2>

<table border="1">
<tr>
    <th>Nombre</th>
    <th>Cedula</th>
    <th>Correo</th>
</tr>

<%
    List<Cliente> lista = (List<Cliente>) request.getAttribute("listaClientes");
    if (lista != null) {
        for (Cliente c : lista) {
%>
<tr>
    <td><%= c.getNombre() %></td>
    <td><%= c.getCedula() %></td>
    <td><%= c.getCorreoElectronico() %></td>
</tr>
<%
        }
    }
%>

</table>

</body>
</html>
