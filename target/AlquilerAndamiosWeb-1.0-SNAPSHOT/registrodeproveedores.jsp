<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Proveedores</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; display: flex; justify-content: center; align-items: center; height: 100vh; }
        .form-container { background: white; padding: 30px; border-radius: 8px; width: 400px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
        label { display: block; margin-top: 10px; font-weight: bold; }
        input { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box; }
        button { margin-top: 15px; padding: 10px; width: 100%; background-color: #007bff; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Registro de Proveedor</h2>
        <form action="ProveedorServlet" method="POST">
            <label>Nombre / Razón Social</label>
            <input type="text" name="nombre" required>

            <label>RUT / NIT</label>
            <input type="text" name="rut_nit" required>

            <label>Contacto Principal (Nombre)</label>
            <input type="text" name="contacto_principal">

            <label>Teléfono</label>
            <input type="text" name="telefono">

            <button type="submit">Guardar Proveedor</button>
        </form>
    </div>
</body>
</html>