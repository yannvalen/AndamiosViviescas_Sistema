<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Obtenemos el correo de la sesión para validar quién entró
    String correo = (String) session.getAttribute("correo");
    
    // Definimos quién es el administrador
    boolean isAdmin = "valenr.v22@gmail.com".equals(correo);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Andamios Viviescas - Panel de Control</title>
    <style>
        body { margin: 0; padding: 0; background-color: #0d1a44; font-family: 'Segoe UI', sans-serif; color: #ffffff; }
        nav { background-color: #000D2A; padding: 15px 50px; display: flex; justify-content: space-between; align-items: center; border-bottom: 2px solid #00c8ff; }
        .logo { font-size: 22px; font-weight: bold; color: #00c8ff; }
        .user-tag { background: #0a2558; padding: 5px 15px; border-radius: 20px; font-size: 13px; border: 1px solid #00c8ff; }
        main { max-width: 1100px; margin: 40px auto; padding: 20px; text-align: center; }
        .grid-container { display: grid; grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); gap: 25px; }
        .module-card { background: rgba(255, 255, 255, 0.03); border: 1px solid rgba(255, 255, 255, 0.1); border-radius: 20px; padding: 40px 20px; text-decoration: none; color: white; transition: 0.3s; display: flex; flex-direction: column; align-items: center; }
        .module-card:hover { transform: translateY(-8px); border-color: #00c8ff; background: rgba(0, 200, 255, 0.08); }
        .module-title { font-size: 22px; font-weight: bold; color: #00c8ff; margin: 10px 0; }
        .btn-exit { background: #ff4b4b; color: white; padding: 12px 40px; text-decoration: none; border-radius: 50px; font-weight: bold; display: inline-block; margin-top: 40px; }
        .role-badge { font-size: 10px; background: #00c8ff; color: #0d1a44; padding: 2px 8px; border-radius: 10px; vertical-align: middle; margin-left: 5px; }
    </style>
</head>
<body>

    <nav>
        <div class="logo">ANDAMIOS VIVIESCAS <span style="font-weight: 200; font-size: 14px; color: white;">v2.0</span></div>
        <div class="user-tag">
            👤 <%= (correo != null) ? correo : "Invitado" %>
            <% if(isAdmin) { %><span class="role-badge">ADMIN</span><% } %>
        </div>
    </nav>

    <main>
        <h1>Panel de Control</h1>
        <p style="color: #8e9dbd; margin-bottom: 40px;">
            <%= isAdmin ? "Consola de Administración Total" : "Bienvenido a tu portal de cliente" %>
        </p>

        <div class="grid-container">
            
            <%-- MÓDULO 1: ALQUILERES (Lo ven todos) --%>
            <a href="AlquilerServlet" class="module-card">
                <div style="font-size: 50px;">🏗️</div>
                <div class="module-title">Alquileres</div>
                <div style="font-size: 14px; color: #a0aec0;">
                    <%= isAdmin ? "Gestionar contratos de clientes." : "Mis contratos y estado de equipos." %>
                </div>
            </a>

            <%-- MÓDULOS EXCLUSIVOS DEL ADMIN --%>
            <% if (isAdmin) { %>
                
                <%-- MÓDULO 2: INVENTARIO (Solo Admin) --%>
                <a href="SeccionesAndamioServlet" class="module-card">
                    <div style="font-size: 50px;">📋</div>
                    <div class="module-title">Inventario</div>
                    <div style="font-size: 14px; color: #a0aec0;">Control de stock real y precios.</div>
                </a>

                <%-- MÓDULO 3: PROVEEDORES (Solo Admin) --%>
                <a href="ProveedorServlet" class="module-card">
                    <div style="font-size: 50px;">🚚</div>
                    <div class="module-title">Proveedores</div>
                    <div style="font-size: 14px; color: #a0aec0;">Gestión de compras y suministros.</div>
                </a>

                <%-- MÓDULO 4: UBICACIONES (Solo Admin) --%>
                <a href="UbicacionServlet" class="module-card">
                    <div style="font-size: 50px;">📍</div>
                    <div class="module-title">Ubicaciones</div>
                    <div style="font-size: 14px; color: #a0aec0;">Rastreo de activos en campo.</div>
                </a>

            <% } %>

            <%-- MÓDULO 5: PAGOS (Lo ven todos, el cliente ve sus pagos, el admin ve ingresos) --%>
            <a href="PagoServlet" class="module-card">
                <div style="font-size: 50px;">💰</div>
                <div class="module-title">Pagos</div>
                <div style="font-size: 14px; color: #a0aec0;">Control financiero y facturación.</div>
            </a>
            
        </div>

        <a href="registro_cliente.jsp" class="btn-exit">Cerrar Sesión</a>
    </main>
</body>
</html>