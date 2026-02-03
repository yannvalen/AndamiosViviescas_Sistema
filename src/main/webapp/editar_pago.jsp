<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.gestionandamios.modelo.Pago" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Pago - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #0d1a44; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 30px; border-radius: 12px; width: 380px; box-shadow: 0 10px 25px rgba(0,0,0,0.5); }
        h2 { color: #0d1a44; text-align: center; }
        .field { margin-bottom: 15px; }
        label { display: block; font-weight: bold; color: #555; margin-bottom: 5px; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 6px; box-sizing: border-box; }
        .btn-update { width: 100%; padding: 12px; background: #00c8ff; color: #0d1a44; border: none; border-radius: 6px; font-weight: bold; cursor: pointer; }
        .cancel { display: block; text-align: center; margin-top: 15px; color: #888; text-decoration: none; }
    </style>
</head>
<body>
<% Pago p = (Pago) request.getAttribute("pago"); %>
<div class="card">
    <h2>✏️ Corregir Pago #<%= p.getIdPago() %></h2>
    <form action="EditarPagoServlet" method="POST">
        <input type="hidden" name="id_pago" value="<%= p.getIdPago() %>">
        <input type="hidden" name="id_alquiler" value="<%= p.getIdAlquiler() %>">
        
        <div class="field">
            <label>Monto ($):</label>
            <input type="number" step="0.01" name="monto" value="<%= p.getMonto() %>" required>
        </div>

        <div class="field">
            <label>Fecha de Pago:</label>
            <input type="date" name="fecha_pago" value="<%= p.getFechaPago() %>" required>
        </div>

        <div class="field">
            <label>Método:</label>
            <select name="metodo_pago">
                <option value="Efectivo" <%= p.getMetodoPago().equals("Efectivo") ? "selected" : "" %>>Efectivo</option>
                <option value="Transferencia" <%= p.getMetodoPago().equals("Transferencia") ? "selected" : "" %>>Transferencia</option>
                <option value="Nequi/Daviplata" <%= p.getMetodoPago().equals("Nequi/Daviplata") ? "selected" : "" %>>Nequi / Daviplata</option>
            </select>
        </div>

        <button type="submit" class="btn-update">Actualizar Registro</button>
    </form>
    <a href="PagoServlet" class="cancel">Cancelar</a>
</div>
</body>
</html>