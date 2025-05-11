public class Vehiculos {

    private int codigo;
    private String marca;
    private String tipo;
    private int modelo;
    private int kilometraje;
    private double precio;

    public Vehiculos(int codigo, String marca, String tipo, int modelo, int kilometraje, double precio) {
        this.codigo = codigo;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.precio = precio;
    }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getModelo() { return modelo; }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() { return kilometraje; }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}

