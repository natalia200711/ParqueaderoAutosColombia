package Datos;

import LogicaNegocio.Celda;
import java.sql.*;

public class CeldaDAO {

    private ConexionDB conexionDB;

    public CeldaDAO() {
        this.conexionDB = new ConexionDB();
    }

    public boolean registrarCelda(Celda celda) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "INSERT INTO celda (numero, estado) VALUES (?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, celda.getNumero());
            stmt.setString(2, celda.getEstado());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }

    public void consultarCeldas() {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM celda");

            System.out.println("\n┌────────────┬──────────────┐");
            System.out.println("│ Celda      │ Estado       │");
            System.out.println("├────────────┼──────────────┤");

            boolean hayDatos = false;

            while (rs.next()) {
                hayDatos = true;

                System.out.printf("│ %-10d │ %-12s │%n",
                        rs.getInt("numero"),
                        rs.getString("estado"));
            }

            if (!hayDatos) {
                System.out.println("│ No hay celdas registradas      │");
            }

            System.out.println("└────────────┴──────────────┘");

        } catch (SQLException e) {
            System.out.println("Error al consultar celdas");
        } finally {
            conexionDB.desconectar(conexion);
        }
    }

    public boolean actualizarEstado(int numero, String estado) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "UPDATE celda SET estado=? WHERE numero=?";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, estado);
            stmt.setInt(2, numero);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }
}