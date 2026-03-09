package Presentacion;

import LogicaNegocio.ControlEntradaSalida;
import LogicaNegocio.Registro;
import java.util.List;
import java.util.Scanner;

public class InterfazConsulta {
    private ControlEntradaSalida control;
    private Scanner scanner;

    public InterfazConsulta() {
        this.control = new ControlEntradaSalida();
        this.scanner = new Scanner(System.in);
    }

    public void mostrar() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   VEHÍCULOS DENTRO DEL PARQUEADERO ║");
        System.out.println("╚══════════════════════════════════╝");

        List<Registro> vehiculos = control.consultarVehiculosDentro();

        if (vehiculos.isEmpty()) {
            System.out.println("\n No hay vehículos en el parqueadero");
        } else {
            System.out.println("\n┌──────────┬───────────────┬──────────┐");
            System.out.println("│ Placa    │ Fecha Entrada │ Hora     │");
            System.out.println("├──────────┼───────────────┼──────────┤");

            for (Registro r : vehiculos) {
                System.out.printf("│ %-8s │ %-13s │ %-8s │%n",
                        r.getVehiculo().getPlaca(),
                        r.getFechaEntrada().toString(),
                        r.getHoraEntrada().toString());
            }

            System.out.println("└──────────┴───────────────┴──────────┘");
        }

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}
