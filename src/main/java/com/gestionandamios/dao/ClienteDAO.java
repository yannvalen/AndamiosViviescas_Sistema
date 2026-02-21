package com.gestionandamios.dao;

import com.gestionandamios.conexion.ConexionDB;
import com.gestionandamios.modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, apellido, cedula, telefono, direccion, correo_electronico, fecha_nacimiento, contrasena, rol) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getCedula());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCorreoElectronico());
            ps.setDate(7, c.getFechaNacimiento());
            ps.setString(8, c.getContrasena());
            ps.setString(9, c.getRol());
            
            int filas = ps.executeUpdate();
            System.out.println("LOG DAO: Filas insertadas -> " + filas);
        } catch (SQLException e) { 
            System.out.println("ERROR SQL AL INSERTAR: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCedula(rs.getString("cedula"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                c.setCorreoElectronico(rs.getString("correo_electronico"));
                c.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                c.setContrasena(rs.getString("contrasena"));
                c.setRol(rs.getString("rol"));
                lista.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public boolean actualizar(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, apellido=?, cedula=?, telefono=?, direccion=?, correo_electronico=?, fecha_nacimiento=?, contrasena=?, rol=? WHERE id_cliente=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getCedula());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getCorreoElectronico());
            ps.setDate(7, c.getFechaNacimiento());
            ps.setString(8, c.getContrasena());
            ps.setString(9, c.getRol());
            ps.setInt(10, c.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // --- MÉTODOS NECESARIOS PARA RECUPERAR CONTRASEÑA ---
    
    public Cliente buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM clientes WHERE correo_electronico = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreoElectronico(rs.getString("correo_electronico"));
                c.setRol(rs.getString("rol"));
                return c;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean actualizarContrasena(int id, String nuevaClave) {
        String sql = "UPDATE clientes SET contrasena = ? WHERE id_cliente = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevaClave);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}