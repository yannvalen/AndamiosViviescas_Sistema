<%@ page import="java.util.List" %>
<%@ page import="com.gestionandamios.modelo.SeccionAndamio" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario de Andamios</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .controls {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            flex-wrap: wrap;
            gap: 10px;
        }

        .controls-left button {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        .controls-right {
            display: flex;
            align-items: center;
            gap: 10px;
            flex-wrap: wrap;
        }

        .controls-right input,
        .controls-right select,
        .controls-right button {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .controls-right button {
            background-color: #218838;
            color: white;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th,
        table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }

        .action-buttons a {
            padding: 6px 10px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-right: 5px;
            font-size: 13px;
        }

        .action-buttons a:hover {
            background-color: #c82333;
        }
    </style>
</head>

<body>

<div class="container">
    <h1>Inventario de Secciones y Accesorios</h1>

    <div class="controls">
        <div class="controls-left">
            <button onclick="window.location.href='registrar_seccion.html'">
                Registrar Nuevo Producto
            </button>
        </div>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID Sección</th>
                <th>Código</th>
                <th>Tipo</th>
                <th>Altura (m)</th>
                <th>Estado</th>
                <th>Ubicación</th>
                <th>Precio</th>
                <th>Acciones</th>
            </tr>
        </thead>

        <tbody>
        <%
            List<SeccionAndamio> lista = (List<SeccionAndamio>) request.getAttribute("lista");

            if (lista != null && !lista.isEmpty()) {
                for (SeccionAndamio s : lista) {
        %>
            <tr>
                <td><%= s.getIdSeccion() %></td>
                <td><%= s.getCodigo() %></td>
                <td><%= s.getTipo() %></td>
                <td><%= s.getAlturaMetros() %></td>
                <td><%= s.getEstado() %></td>
                <td><%= s.getUbicacion() %></td>
                <td>$ <%= s.getPrecio() %></td>
                <td class="action-buttons">
                    <a href="EliminarSeccionServlet?id=<%= s.getIdSeccion() %>">Eliminar</a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="8" style="text-align:center;">
                    No hay registros en la base de datos
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
