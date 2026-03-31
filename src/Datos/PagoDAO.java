package Datos;

import LogicaNegocio.Pago;
import java.sql.*;

public class PagoDAO {

    private ConexionDB conexionDB;

    public PagoDAO() {
        this.conexionDB = new ConexionDB();
    }

    public boolean registrarPago(Pago pago) {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();

            String sql = "INSERT INTO pago (idVehiculo, documentoUsuario, valor, fechaPago, horaPago) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, pago.getVehiculo().getIdVehiculo());
            stmt.setString(2, pago.getUsuario().getDocumento());
            stmt.setDouble(3, pago.getValor());
            stmt.setDate(4, pago.getFechaPago());
            stmt.setTime(5, pago.getHoraPago());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar pago");
            return false;
        } finally {
            conexionDB.desconectar(conexion);
        }
    }


    public void consultarPagos() {
        Connection conexion = null;

        try {
            conexion = conexionDB.conectar();
            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM pago");

            System.out.println("\n┌────────┬────────────┬────────────┬──────────┐");
            System.out.println("│ ID     │ Vehículo   │ Usuario    │ Valor    │");
            System.out.println("├────────┼────────────┼────────────┼──────────┤");

            while (rs.next()) {
                System.out.printf("│ %-6d │ %-10d │ %-10s │ %-8.2f │%n",
                        rs.getInt("idPago"),
                        rs.getInt("idVehiculo"),
                        rs.getString("documentoUsuario"),
                        rs.getDouble("valor"));
            }

            System.out.println("└────────┴────────────┴────────────┴──────────┘");

        } catch (SQLException e) {
            System.out.println("Error al consultar pagos");
        } finally {
            conexionDB.desconectar(conexion);
        }
    }
}
