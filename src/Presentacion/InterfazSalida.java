package Presentacion;

import LogicaNegocio.ControlEntradaSalida;
import java.util.Scanner;

public class InterfazSalida {
    private ControlEntradaSalida control;
    private Scanner scanner;

    public InterfazSalida() {
        this.control = new ControlEntradaSalida();
        this.scanner = new Scanner(System.in);
    }

    public void mostrar() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║   REGISTRAR SALIDA DE VEHÍCULO   ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("PLACA DEL VEHÍCULO: ");
        String placa = scanner.nextLine().toUpperCase();

        System.out.println("Fecha y hora de salida (Se registra automáticamente)");

        System.out.print("\n¿Registrar salida? (S/N): ");
        String confirmar = scanner.nextLine().toUpperCase();

        if (confirmar.equals("S")) {
            control.registrarSalida(placa);
        }

        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}
