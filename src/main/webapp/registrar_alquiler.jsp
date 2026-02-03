<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Nuevo Alquiler - Andamios Viviescas</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #0d1a44; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
        .form-container { background-color: rgba(255, 255, 255, 0.05); padding: 30px; border-radius: 15px; box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3); border: 1px solid rgba(0, 200, 255, 0.3); width: 450px; text-align: center; }
        h2 { color: #fff; margin-bottom: 20px; }
        .form-group { text-align: left; margin-bottom: 12px; }
        label { color: #00c8ff; font-weight: bold; display: block; margin-bottom: 5px; font-size: 0.9em; }
        input { width: 100%; padding: 10px; border-radius: 5px; border: none; background: #fff; box-sizing: border-box; }
        .btn-guardar { width: 100%; padding: 12px; background-color: #00c8ff; color: #0d1a44; border: none; border-radius: 5px; font-weight: bold; cursor: pointer; margin-top: 15px; transition: 0.3s; }
        .btn-guardar:hover { background-color: #0099cc; transform: scale(1.02); }
        .cancelar { display: block; margin-top: 15px; color: #aaa; text-decoration: none; font-size: 0.9em; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>🏗️ Nuevo Alquiler</h2>
    
    <form action="AlquilerServlet" method="POST">
        <input type="hidden" name="accion" value="registrar">
        
        <div class="form-group">
            <label>ID del Cliente (Número):</label>
            <input type="number" name="id_cliente" placeholder="Ej: 2" required>
        </div>

        <div class="form-group">
            <label>Nombre del Cliente (Para reporte):</label>
            <input type="text" name="nombre_cliente" placeholder="Nombre completo" required>
        </div>

        <div class="form-group">
            <label>Dirección de la Obra:</label>
            <input type="text" name="direccion_obra" placeholder="Calle, Carrera, Ciudad" required>
        </div>

        <div class="form-group">
            <label>Fecha de Inicio:</label>
            <input type="date" name="fecha_inicio" required>
        </div>

        <div class="form-group">
            <label>Fin Estimado:</label>
            <input type="date" name="fecha_fin_estimada" required>
        </div>

        <div class="form-group">
            <label>Costo Total del Alquiler ($):</label>
            <input type="number" step="0.01" name="costo_total" value="0.00" required>
        </div>

        <button type="submit" class="btn-guardar">Crear Contrato y Ubicación</button>
    </form>

    <a href="AlquilerServlet" class="cancelar">Cancelar y Volver</a>
</div>

</body>
</html>