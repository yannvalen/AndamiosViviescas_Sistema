<%@ page import="com.gestionandamios.modelo.Proveedor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Proveedor</title>
    <style>
        body { font-family: Arial; display: flex; justify-content: center; padding-top: 50px; background: #f0f0f0; }
        .form-container { background: white; padding: 30px; border-radius: 8px; width: 400px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        input { width: 100%; padding: 8px; margin: 10px 0; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <% Proveedor p = (Proveedor) request.getAttribute("prov"); %>
    <div class="form-container">
        <h2>Modificar Proveedor</h2>
        <form action="ProveedorServlet" method="POST">
            <input type="hidden" name="id_proveedor" value="<%= p.getIdProveedor() %>">
            
            <label>Nombre</label>
            <input type="text" name="nombre" value="<%= p.getNombre() %>" required>

            <label>RUT / NIT</label>
            <input type="text" name="rut_nit" value="<%= p.getRutNit() %>" required>

            <label>Contacto Principal</label>
            <input type="text" name="contacto_principal" value="<%= p.getContactoPrincipal() %>">

            <label>Teléfono</label>
            <input type="text" name="telefono" value="<%= p.getTelefono() %>">

            <button type="submit">Actualizar Datos</button>
            <a href="ProveedorServlet" style="display:block; text-align:center; margin-top:10px;">Cancelar</a>
        </form>
    </div>
</body>
</html>