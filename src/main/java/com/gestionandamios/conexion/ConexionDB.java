package com.gestionandamios.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
        "jdbc:mysql://localhost:3306/proyecto_formativo"
        + "?useSSL=false"
        + "&allowPublicKeyRetrieval=true"
        + "&serverTimezone=UTC";

    private static final String USUARIO = "root";
    private static final String CONTRASENA = "Yann1s$VaLent1n@";

    public static Connection getConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("✅ Conexión exitosa a MySQL");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver JDBC no encontrado");
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }
}
