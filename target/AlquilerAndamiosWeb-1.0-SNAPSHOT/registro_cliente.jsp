<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Alquiler de Andamios - Login & Registro</title>
    <style>
        body { margin: 0; padding: 0; background-color: #0d1a44; font-family: Arial, sans-serif; color: #ffffff; display: flex; flex-direction: column; align-items: center; min-height: 100vh; padding-top: 50px; }
        section { width: 100%; max-width: 400px; display: none; }
        section.active { display: block; }
        h1 { font-size: 32px; margin-bottom: 40px; text-align: center; }
        label { display: block; margin-bottom: 8px; font-size: 12px; }
        input { width: 100%; padding: 12px; border: 2px solid white; border-radius: 20px; margin-bottom: 20px; background-color: transparent; color: white; box-sizing: border-box; }
        button { width: 100%; border: none; border-radius: 20px; padding: 15px; font-weight: bold; cursor: pointer; background-color: #00c8ff; color: white; margin-bottom: 10px; }
        .btn-outline { background: transparent; border: 2px solid white; }
        .msg-ok { color: #00ff88; text-align: center; margin-bottom: 20px; }
    </style>
</head>
<body>

    <!-- SECCIÓN DE LOGIN -->
    <section id="login-section" class="active">
        <h1>Ingresar</h1>
        <% if ("ok".equals(request.getParameter("recuperar"))) { %>
            <p class="msg-ok">Si el correo existe, se han enviado las instrucciones.</p>
        <% } %>
        <form action="LoginServlet" method="POST">
            <label>CORREO ELECTRÓNICO</label>
            <input type="email" name="contacto" required>

            <label>CONTRASEÑA</label>
            <input type="password" name="contrasena" required>

            <button type="submit">Iniciar Sesión</button>
            <button type="button" class="btn-outline" onclick="mostrarRegistro()">Crear una cuenta</button>
            <br><br>
            <a href="olvido_contrasena.jsp" style="color:white; display:block; text-align:center;">¿Olvidó su contraseña?</a>
        </form>
    </section>

    <!-- SECCIÓN DE REGISTRO -->
    <section id="registro-section">
        <h1>Registro</h1>
        <form action="ClienteServlet" method="POST">
            <label>NOMBRE</label>
            <input type="text" name="nombre" required>

            <label>APELLIDOS</label>
            <input type="text" name="apellido" required>

            <label>CÉDULA</label>
            <input type="text" name="cedula" required>

            <label>DIRECCIÓN</label>
            <input type="text" name="direccion" required placeholder="Calle, Ciudad...">

            <label>FECHA DE NACIMIENTO</label>
            <input type="date" name="fechaNacimiento" required>

            <label>CORREO ELECTRÓNICO</label>
            <input type="email" name="correo" required>

            <label>TELÉFONO</label>
            <input type="tel" name="telefono" required>

            <label>CONTRASEÑA</label>
            <input type="password" name="contrasena" required>

            <button type="submit">Guardar Registro</button>
            <button type="button" class="btn-outline" onclick="mostrarLogin()">Regresar al Login</button>
        </form>
    </section>

    <script>
        function mostrarRegistro() {
            document.getElementById('login-section').style.display = 'none';
            document.getElementById('registro-section').style.display = 'block';
        }
        function mostrarLogin() {
            document.getElementById('registro-section').style.display = 'none';
            document.getElementById('login-section').style.display = 'block';
        }
    </script>
</body>
</html>