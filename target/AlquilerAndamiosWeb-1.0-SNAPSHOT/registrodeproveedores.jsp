<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Registro de Proveedores</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
.form-container {
    background: white;
    padding: 30px;
    border-radius: 8px;
    width: 400px;
}
label { display: block; margin-top: 10px; }
input { width: 100%; padding: 8px; margin-top: 5px; }
button { margin-top: 15px; padding: 10px; width: 100%; }
</style>

</head>
<body>

<div class="form-container">
<h2>Registro de Proveedor</h2>

<form action="ProveedorServlet" method="POST">

    <label>Nombre</label>
    <input type="text" name="nombre" required>

    <label>Teléfono</label>
    <input type="text" name="telefono">

    <label>Dirección</label>
    <input type="text" name="direccion">

    <label>Correo</label>
    <input type="email" name="correo">

    <button type="submit">Guardar Proveedor</button>

</form>

</div>

</body>
</html>
