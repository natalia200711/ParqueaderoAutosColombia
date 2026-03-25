package Presentacion;

import LogicaNegocio.ControlUsuariosCeldas;
import java.util.Scanner;

public class InterfazCelda {

    private ControlUsuariosCeldas control;
    private Scanner scanner;

    public InterfazCelda() {
        control = new ControlUsuariosCeldas();
        scanner = new Scanner(System.in);
    }

    public void mostrar() {
        int opcion;

        do {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║      GESTIÓN DE CELDAS     ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Registrar Celda         ║");
            System.out.println("║ 2. Consultar Celdas        ║");
            System.out.println("║ 3. Asignar Celda           ║");
            System.out.println("║ 4. Liberar Celda           ║");
            System.out.println("║ 0. Volver                  ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("\n╔════════════════════════════╗");
                    System.out.println("║      REGISTRAR CELDA       ║");
                    System.out.println("╚════════════════════════════╝");

                    System.out.print("Número de celda: ");
                    int num = Integer.parseInt(scanner.nextLine());

                    control.registrarCelda(num);

                    System.out.println("\nCelda registrada correctamente");
                    pausa();
                    break;

                case 2:
                    System.out.println("\n╔════════════════════════════╗");
                    System.out.println("║      LISTA DE CELDAS       ║");
                    System.out.println("╚════════════════════════════╝");

                    control.consultarCeldas();
                    pausa();
                    break;

                case 3:
                    System.out.println("\n╔════════════════════════════╗");
                    System.out.println("║       ASIGNAR CELDA        ║");
                    System.out.println("╚════════════════════════════╝");

                    System.out.print("Placa del vehículo: ");
                    String placa = scanner.nextLine().toUpperCase();

                    System.out.print("Número de celda: ");
                    num = Integer.parseInt(scanner.nextLine());

                    control.asignarCelda(placa, num);

                    pausa();
                    break;

                case 4:
                    System.out.println("\n╔════════════════════════════╗");
                    System.out.println("║       LIBERAR CELDA        ║");
                    System.out.println("╚════════════════════════════╝");

                    System.out.print("Número de celda: ");
                    num = Integer.parseInt(scanner.nextLine());

                    control.liberarCelda(num);

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