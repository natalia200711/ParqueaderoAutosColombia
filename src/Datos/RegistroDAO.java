package Datos;

import LogicaNegocio.Registro;
import LogicaNegocio.Vehiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {
    private ConexionDB conexionDB;

    public RegistroDAO() {
        this.conexionDB = new ConexionDB();
    }

    // Registrar entrada
    public boolean registrarEntrada(Registro registro) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "INSERT INTO Registro (fechaEntrada, horaEntrada, idVehiculo) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, registro.getFechaEntrada());
            stmt.setTime(2, registro.getHoraEntrada());
            stmt.setInt(3, registro.getVehiculo().getIdVehiculo());

            int resultado = stmt.executeUpdate();

            if (resultado > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    registro.setIdRegistro(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar entrada: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return false;
    }

    // Registrar salida
    public boolean registrarSalida(int idRegistro, Date fechaSalida, Time horaSalida) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "UPDATE Registro SET fechaSalida = ?, horaSalida = ? WHERE idRegistro = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setDate(1, fechaSalida);
            stmt.setTime(2, horaSalida);
            stmt.setInt(3, idRegistro);

            int resultado = stmt.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar salida: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return false;
    }

    // Buscar registro activo por vehículo
    public Registro buscarActivoPorVehiculo(int idVehiculo) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "SELECT * FROM Registro WHERE idVehiculo = ? AND fechaSalida IS NULL";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, idVehiculo);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt("idRegistro"));
                registro.setFechaEntrada(rs.getDate("fechaEntrada"));
                registro.setHoraEntrada(rs.getTime("horaEntrada"));
                registro.setFechaSalida(rs.getDate("fechaSalida"));
                registro.setHoraSalida(rs.getTime("horaSalida"));

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));

                registro.setVehiculo(vehiculo);

                return registro;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar activo: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return null;
    }

    // Consultar vehículos dentro (RF05)
    public List<Registro> consultarVehiculosDentro() {
        List<Registro> registros = new ArrayList<>();
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            String sql = "SELECT r.*, v.placa FROM Registro r " +
                    "JOIN Vehiculo v ON r.idVehiculo = v.idVehiculo " +
                    "WHERE r.fechaSalida IS NULL";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Registro registro = new Registro();
                registro.setIdRegistro(rs.getInt("idRegistro"));
                registro.setFechaEntrada(rs.getDate("fechaEntrada"));
                registro.setHoraEntrada(rs.getTime("horaEntrada"));

                Vehiculo vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(rs.getInt("idVehiculo"));
                vehiculo.setPlaca(rs.getString("placa"));

                registro.setVehiculo(vehiculo);
                registros.add(registro);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        } finally {
            conexionDB.desconectar(conexion);
        }

        return registros;
    }
}
