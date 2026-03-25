package Presentacion;

import LogicaNegocio.ControlUsuariosCeldas;
import java.util.Scanner;

public class InterfazUsuario {

    private ControlUsuariosCeldas control;
    private Scanner scanner;

    public InterfazUsuario() {
        control = new ControlUsuariosCeldas();
        scanner = new Scanner(System.in);
    }

    public void mostrar() {
        int opcion;

        do {
            System.out.println("\n╔════════════════════════════╗");
            System.out.println("║     GESTIÓN DE USUARIOS    ║");
            System.out.println("╠════════════════════════════╣");
            System.out.println("║ 1. Registrar Usuario       ║");
            System.out.println("║ 2. Consultar Usuarios      ║");
            System.out.println("║ 3. Actualizar Usuario      ║");
            System.out.println("║ 4. Eliminar Usuario        ║");
            System.out.println("║ 0. Volver                  ║");
            System.out.println("╚════════════════════════════╝");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║      REGISTRAR USUARIO         ║");
                    System.out.println("╚════════════════════════════════╝");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Documento: ");
                    String doc = scanner.nextLine();

                    System.out.print("Teléfono: ");
                    String tel = scanner.nextLine();

                    control.registrarUsuario(nombre, doc, tel);

                    System.out.println("\n Usuario registrado correctamente");
                    pausa();
                    break;

                case 2:
                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║      LISTA DE USUARIOS         ║");
                    System.out.println("╚════════════════════════════════╝");

                    control.consultarUsuarios();
                    pausa();
                    break;

                case 3:
                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║     ACTUALIZAR USUARIO         ║");
                    System.out.println("╚════════════════════════════════╝");

                    System.out.print("Documento: ");
                    doc = scanner.nextLine();

                    System.out.print("Nuevo nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Nuevo teléfono: ");
                    tel = scanner.nextLine();

                    control.actualizarUsuario(doc, nombre, tel);

                    System.out.println("\n Usuario actualizado correctamente");
                    pausa();
                    break;

                case 4:
                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║      ELIMINAR USUARIO          ║");
                    System.out.println("╚════════════════════════════════╝");

                    System.out.print("Documento: ");
                    doc = scanner.nextLine();

                    control.eliminarUsuario(doc);

                    System.out.println("\n Usuario eliminado correctamente");
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