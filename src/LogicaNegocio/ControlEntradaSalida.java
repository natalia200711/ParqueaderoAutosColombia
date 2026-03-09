package LogicaNegocio;

import Datos.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ControlEntradaSalida {
    private VehiculoDAO vehiculoDAO;
    private RegistroDAO registroDAO;

    public ControlEntradaSalida() {
        this.vehiculoDAO = new VehiculoDAO();
        this.registroDAO = new RegistroDAO();
    }

    // RF01 y RF02: Registrar entrada
    public boolean registrarEntrada(String placa) {
        System.out.println("\n--- REGISTRANDO ENTRADA: " + placa + " ---");

        // Buscar si el vehículo existe
        Vehiculo vehiculo = VehiculoDAO.buscarPorPlaca(placa);

        // Si no existe, lo creamos
        if (vehiculo == null) {
            vehiculo = new Vehiculo(placa);
            if (!VehiculoDAO.guardar(vehiculo)) {
                System.out.println(" No se pudo crear el vehículo");
                return false;
            }
            System.out.println(" Vehículo nuevo registrado");
        }

        // Verificar que no esté dentro
        Registro activo = registroDAO.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());
        if (activo != null) {
            System.out.println(" El vehículo YA ESTÁ DENTRO");
            return false;
        }

        // Crear registro con fecha y hora actual
        Registro nuevoRegistro = new Registro();
        nuevoRegistro.setVehiculo(vehiculo);
        nuevoRegistro.setFechaEntrada(Date.valueOf(LocalDate.now()));
        nuevoRegistro.setHoraEntrada(Time.valueOf(LocalTime.now()));

        // Guardar
        boolean exito = registroDAO.registrarEntrada(nuevoRegistro);

        if (exito) {
            System.out.println(" ENTRADA REGISTRADA: " + placa);
        } else {
            System.out.println(" Error al registrar");
        }

        return exito;
    }

    // RF03 y RF04: Registrar salida
    public boolean registrarSalida(String placa) {
        System.out.println("\n--- REGISTRANDO SALIDA: " + placa + " ---");

        // Buscar vehículo
        Vehiculo vehiculo = VehiculoDAO.buscarPorPlaca(placa);

        if (vehiculo == null) {
            System.out.println(" Vehículo no encontrado");
            return false;
        }

        // Buscar registro activo
        Registro activo = registroDAO.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());

        if (activo == null) {
            System.out.println(" El vehículo NO ESTÁ DENTRO");
            return false;
        }

        // Registrar salida con fecha/hora actual
        boolean exito = registroDAO.registrarSalida(
                activo.getIdRegistro(),
                Date.valueOf(LocalDate.now()),
                Time.valueOf(LocalTime.now())
        );

        if (exito) {
            System.out.println(" SALIDA REGISTRADA: " + placa);
        } else {
            System.out.println(" Error al registrar");
        }

        return exito;
    }

    // RF05: Consultar vehículos dentro
    public List<Registro> consultarVehiculosDentro() {
        System.out.println("\n--- CONSULTANDO VEHÍCULOS DENTRO ---");
        List<Registro> registros = registroDAO.consultarVehiculosDentro();
        System.out.println("Total: " + registros.size() + " vehículos");
        return registros;
    }
}