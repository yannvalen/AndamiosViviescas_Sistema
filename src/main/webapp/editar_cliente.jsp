<%@ page import="com.gestionandamios.modelo.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Cliente c = (Cliente) request.getAttribute("cliente"); %>
<!DOCTYPE html>
<html>
<head><title>Editar Cliente</title></head>
<body>
    <h2>Editar Datos del Cliente</h2>
    <form action="ClienteServlet" method="post">
        <input type="hidden" name="id_cliente" value="<%= c.getIdCliente() %>">
        
        Nombre: <input type="text" name="nombre" value="<%= c.getNombre() %>"><br>
        Apellido: <input type="text" name="apellido" value="<%= c.getApellido() %>"><br>
        Cedula: <input type="text" name="cedula" value="<%= c.getCedula() %>"><br>
        Telefono: <input type="text" name="telefono" value="<%= c.getTelefono() %>"><br>
        Direccion: <input type="text" name="direccion" value="<%= c.getDireccion() %>"><br>
        Correo: <input type="email" name="correo" value="<%= c.getCorreoElectronico() %>"><br>
        Fecha nacimiento: <input type="date" name="fechaNacimiento" value="<%= c.getFechaNacimiento() %>"><br>
        Contraseña: <input type="password" name="contrasena" value="<%= c.getContrasena() %>"><br>
        
        <button type="submit">Actualizar Datos</button>
        <a href="ClienteServlet">Cancelar</a>
    </form>
</body>
</html>