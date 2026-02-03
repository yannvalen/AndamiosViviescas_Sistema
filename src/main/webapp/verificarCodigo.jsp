<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verificar Código</title>
    <style>
        body { background-color: #0d1a44; color: white; font-family: sans-serif; text-align: center; padding-top: 50px; }
        form { background: rgba(255,255,255,0.1); padding: 20px; display: inline-block; border-radius: 10px; }
        input { margin: 10px; padding: 8px; border-radius: 5px; border: none; }
    </style>
</head>
<body>
    <h2>Recuperación de Contraseña</h2>
    <p>Ingresa el código que llego a tu correo</p>
    
    <form action="VerificarCodigoServlet" method="post">
        Código: <input type="text" name="codigo" required><br>
        Nueva Contraseña: <input type="password" name="nueva" required><br>
        <input type="submit" value="Actualizar Contraseña">
    </form>

    <p style="color:red;">${mensaje}</p>
</body>
</html>