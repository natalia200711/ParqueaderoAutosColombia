package Presentacion;

import LogicaNegocio.ControlPagos;
import java.util.Scanner;

public class InterfazPago {

    private ControlPagos control;
    private Scanner scanner;

    public InterfazPago() {
        control = new ControlPagos();
        scanner = new Scanner(System.in);
    }

    public void mostrar() {

        int opcion;

        do {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║       GESTIÓN PAGOS        ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Registrar Pago          ║");
            System.out.println("║ 2. Consultar Pagos         ║");
            System.out.println("║ 0. Volver                  ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    System.out.print("Placa: ");
                    String placa = scanner.nextLine().toUpperCase();

                    System.out.print("Documento usuario: ");
                    String doc = scanner.nextLine();

                    control.registrarPago(placa, doc);
                    pausa();
                    break;

                case 2:
                    control.consultarPagos();
                    pausa();
                    break;
            }

        } while (opcion != 0);
    }

    private void pausa() {
        System.out.println("\nPresione ENTER para continuar...");
        scanner.nextLine();
    }
}