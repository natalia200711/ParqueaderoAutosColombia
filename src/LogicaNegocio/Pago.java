package LogicaNegocio;

import java.sql.Date;
import java.sql.Time;

public class Pago {

    private int idPago;
    private Vehiculo vehiculo;
    private Usuario usuario;
    private double valor;
    private Date fechaPago;
    private Time horaPago;

    public Pago() {}

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getFechaPago() { return fechaPago; }
    public void setFechaPago(Date fechaPago) { this.fechaPago = fechaPago; }

    public Time getHoraPago() { return horaPago; }
    public void setHoraPago(Time horaPago) { this.horaPago = horaPago; }
}
