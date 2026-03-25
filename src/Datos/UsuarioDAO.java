package Datos;

import LogicaNegocio.Usuario;
import java.sql.*;

public class UsuarioDAO {

    private ConexionDB conexionDB;

    public UsuarioDAO() {
        this.conexionDB = new ConexionDB();
    }

    public boolean registrarUsuario(Usuario usuario) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "INSERT INTO usuario (documento, nombre, telefono) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, usuario.getDocumento());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getTelefono());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }

    public void consultarUsuarios() {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

            System.out.println("\n┌────────────┬───────────────┬──────────────┐");
            System.out.println("│ Documento  │ Nombre        │ Teléfono     │");
            System.out.println("├────────────┼───────────────┼──────────────┤");

            boolean hayDatos = false;

            while (rs.next()) {
                hayDatos = true;

                System.out.printf("│ %-10s │ %-13s │ %-12s │%n",
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("telefono"));
            }

            if (!hayDatos) {
                System.out.println("│ No hay usuarios registrados                     │");
            }

            System.out.println("└────────────┴───────────────┴──────────────┘");

        } catch (SQLException e) {
            System.out.println("Error al consultar usuarios");
        } finally {
            conexionDB.desconectar(conexion);
        }
    }

    public boolean actualizarUsuario(String doc, String nombre, String telefono) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "UPDATE usuario SET nombre=?, telefono=? WHERE documento=?";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, doc);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }

    public boolean eliminarUsuario(String doc) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "DELETE FROM usuario WHERE documento=?";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, doc);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }
}