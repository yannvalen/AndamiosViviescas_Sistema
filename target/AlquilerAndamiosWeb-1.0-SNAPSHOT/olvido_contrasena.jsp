<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Recuperar contraseña</title>
</head>
<body>

<h2>Recuperar contraseña</h2>

<form method="post" action="<%= request.getContextPath() %>/RecuperarServlet">
    Correo:
    <input type="email" name="correo" required>
    <br><br>
    <input type="submit" value="Enviar">
</form>

<p style="color:green;">
    ${mensaje}
</p>

<a href="index.jsp">Volver</a>

</body>
</html>
