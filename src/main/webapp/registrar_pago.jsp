<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Pago - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #0d1a44; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 30px; border-radius: 12px; width: 350px; box-shadow: 0 10px 25px rgba(0,0,0,0.5); }
        h2 { color: #0d1a44; text-align: center; margin-bottom: 20px; }
        .field { margin-bottom: 15px; }
        label { display: block; font-weight: bold; color: #555; margin-bottom: 5px; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; box-sizing: border-box; }
        .btn-confirm { width: 100%; padding: 12px; background: #2ecc71; color: white; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; font-size: 16px; transition: 0.3s; }
        .btn-confirm:hover { background: #27ae60; }
        .cancel { display: block; text-align: center; margin-top: 15px; color: #888; text-decoration: none; }
    </style>
</head>
<body>

<div class="card">
    <h2>Registrar Pago</h2>
    <form action="PagoServlet" method="POST">
        <input type="hidden" name="accion" value="registrar">
        
        <div class="field">
            <label>ID Alquiler:</label>
            <input type="text" name="id_alquiler" value="<%= request.getParameter("id_alquiler") %>" readonly style="background: #f0f0f0;">
        </div>

        <div class="field">
            <label>Monto a Cobrar ($):</label>
            <input type="number" step="0.01" name="monto" value="<%= request.getParameter("monto") %>" required>
        </div>

        <div class="field">
            <label>Método de Pago:</label>
            <select name="metodo_pago">
                <option value="Efectivo">Efectivo</option>
                <option value="Transferencia">Transferencia</option>
                <option value="Nequi/Daviplata">Nequi / Daviplata</option>
                <option value="Tarjeta">Tarjeta de Crédito</option>
            </select>
        </div>

        <button type="submit" class="btn-confirm">Confirmar y Guardar Pago</button>
    </form>
    <a href="AlquilerServlet" class="cancel">Volver sin guardar</a>
</div>

</body>
</html>