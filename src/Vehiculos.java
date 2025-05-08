public class Vehiculos {

    private int codigo;
    private String marca;
    private String tipo;
    private int modelo;
    private int kilometraje;
    private double precio;

    public Vehiculos(int codigo, String marca, String tipo, int modelo, int kilometraje) {
        this.codigo = codigo;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.precio = 0;
    }

    // Getters
    public int getCodigo() { return codigo; }
    public String getMarca() { return marca; }
    public String getTipo() { return tipo; }
    public int getModelo() { return modelo; }
    public int getKilometraje() { return kilometraje; }
    public double getPrecio() { return precio; }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
