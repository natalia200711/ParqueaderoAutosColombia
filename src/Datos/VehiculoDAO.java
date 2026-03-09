package Datos;

import LogicaNegocio.Vehiculo;
import java.sql.*;

public class VehiculoDAO {
    private static ConexionDB conexionDB;

    public VehiculoDAO() {
        this.conexionDB = new ConexionDB();
    }

    // Buscar vehículo por placa
    public static Vehiculo buscarPorPlaca(String placa) {
        Vehiculo vehiculo = null;
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "SELECT * FROM Vehiculo WHERE placa = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, placa);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
                vehiculo.setPlaca(rs.getString("placa"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return vehiculo;
    }

    // Guardar nuevo vehículo
    public static boolean guardar(Vehiculo vehiculo) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "INSERT INTO Vehiculo (placa) VALUES (?)";
            PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vehiculo.getPlaca());

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    vehiculo.setIdVehiculo(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return false;
    }
}
