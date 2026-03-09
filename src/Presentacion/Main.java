package Presentacion;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        InterfazEntrada interfazEntrada = new InterfazEntrada();
        InterfazSalida interfazSalida = new InterfazSalida();
        InterfazConsulta interfazConsulta = new InterfazConsulta();

        System.out.println("\n SISTEMA DE PARQUEADERO ");
        System.out.println("=========================");

        int opcion;

        do {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║           MENÚ             ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Registrar ENTRADA       ║");
            System.out.println("║ 2. Registrar SALIDA        ║");
            System.out.println("║ 3. Consultar DENTRO        ║");
            System.out.println("║ 0. Salir                   ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Opción: ");

            try {

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {

                    case 1:
                        interfazEntrada.mostrar();
                        break;

                    case 2:
                        interfazSalida.mostrar();
                        break;

                    case 3:
                        interfazConsulta.mostrar();
                        break;

                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }

            } catch (NumberFormatException e) {

                System.out.println("Ingrese un número válido");
                opcion = -1;

            }

        } while (opcion != 0);

        scanner.close();
    }
}