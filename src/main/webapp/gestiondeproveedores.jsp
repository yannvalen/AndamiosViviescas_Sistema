<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.Proveedor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Proveedores</title>
</head>

<body>

<h2>Registrar proveedor</h2>

<form action="ProveedorServlet" method="post">
Nombre: <input name="nombre"><br>
RUT/NIT: <input name="rut_nit"><br>
Contacto: <input name="contacto_principal"><br>
Teléfono: <input name="telefono"><br>
<button>Guardar</button>
</form>

<hr>

<table border="1">
<tr>
<th>ID</th>
<th>Nombre</th>
<th>RUT</th>
<th>Contacto</th>
<th>Teléfono</th>
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
<%
    }
}
%>

</table>

</body>
</html>
