package LogicaNegocio;

import Datos.*;

public class ControlUsuariosCeldas {

    private UsuarioDAO usuarioDAO;
    private CeldaDAO celdaDAO;
    private RegistroDAO registroDAO;

    public ControlUsuariosCeldas() {
        this.usuarioDAO = new UsuarioDAO();
        this.celdaDAO = new CeldaDAO();
        this.registroDAO = new RegistroDAO();
    }

    // ---------------- USUARIOS ----------------

    public void registrarUsuario(String nombre, String doc, String tel) {
        Usuario u = new Usuario(nombre, doc, tel);
        usuarioDAO.registrarUsuario(u);
    }

    public void consultarUsuarios() {
        usuarioDAO.consultarUsuarios();
    }

    public void actualizarUsuario(String doc, String nombre, String tel) {
        usuarioDAO.actualizarUsuario(doc, nombre, tel);
    }

    public void eliminarUsuario(String doc) {
        usuarioDAO.eliminarUsuario(doc);
    }

    // ---------------- CELDAS ----------------

    public void registrarCelda(int numero) {
        Celda c = new Celda(numero, "Disponible");
        celdaDAO.registrarCelda(c);
    }

    public void consultarCeldas() {
        celdaDAO.consultarCeldas();
    }

    public void asignarCelda(String placa, int numero) {

        Vehiculo vehiculo = VehiculoDAO.buscarPorPlaca(placa);

        if (vehiculo == null) {
            System.out.println(" Vehículo no existe");
            return;
        }

        Registro activo = registroDAO.buscarActivoPorVehiculo(vehiculo.getIdVehiculo());

        if (activo == null) {
            System.out.println(" El vehículo NO está dentro del parqueadero");
            return;
        }

        celdaDAO.actualizarEstado(numero, "Ocupada");

        System.out.println(" Celda " + numero + " asignada al vehículo con placa" + placa);
    }

    public void liberarCelda(int numero) {
        celdaDAO.actualizarEstado(numero, "Disponible");
        System.out.println(" Celda " + numero + " liberada");
    }
}