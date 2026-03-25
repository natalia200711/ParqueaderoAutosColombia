package LogicaNegocio;

public class Celda {

    private int numero;
    private String estado;

    public Celda() {}

    public Celda(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public int getNumero() { return numero; }
    public String getEstado() { return estado; }

    public void setNumero(int numero) { this.numero = numero; }
    public void setEstado(String estado) { this.estado = estado; }
}