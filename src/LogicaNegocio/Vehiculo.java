package LogicaNegocio;

public class Vehiculo {

    private int idVehiculo;
    private String placa;

    // Constructor vacío
    public Vehiculo() {}

    // Constructor con placa
    public Vehiculo(String placa) {
        this.placa = placa;
    }

    // GETTERS Y SETTERS (para acceder a los atributos)
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}



