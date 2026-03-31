package LogicaNegocio;

import Datos.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class ControlPagos {

    private PagoDAO pagoDAO;
    private RegistroDAO registroDAO;

    public ControlPagos() {
        this.pagoDAO = new PagoDAO();
        this.registroDAO = new RegistroDAO();
    }


    public double calcularPago(Registro registro) {
        return 2000; // valor fijo para evitar errores
    }


    public void registrarPago(String placa, String documento) {

        Vehiculo vehiculo = VehiculoDAO.buscarPorPlaca(placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no existe");
            return;
        }

        Registro activo = registroDAO.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());

        if (activo == null) {
            System.out.println("No hay registro activo");
            return;
        }

        double valor = calcularPago(activo);

        Usuario usuario = new Usuario();
        usuario.setDocumento(documento);

        Pago pago = new Pago();
        pago.setVehiculo(vehiculo);
        pago.setUsuario(usuario);
        pago.setValor(valor);
        pago.setFechaPago(Date.valueOf(LocalDate.now()));
        pago.setHoraPago(Time.valueOf(LocalTime.now()));

        if (pagoDAO.registrarPago(pago)) {
            System.out.println("Pago registrado: $" + valor);
        } else {
            System.out.println("Error en pago");
        }
    }


    public void consultarPagos() {
        pagoDAO.consultarPagos();
    }
}