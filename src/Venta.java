import java.util.List;

public class Venta {
    private double monto;
    private int codigoVehiculo;
    private String apellido;
    private String nombre;
    private String documento;

    public Venta(double monto, int codigoVehiculo, String apellido, String nombre, String documento) {
        this.monto = monto;
        this.codigoVehiculo = codigoVehiculo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.documento = documento;
    }

    // Getters
    public double getMonto() { return monto; }
    public int getCodigoVehiculo() { return codigoVehiculo; }
    public String getApellido() { return apellido; }
    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }

public static boolean registrarVenta(List<Venta> ventas, List<Vehiculos> vehiculos, Venta nuevaVenta) {
    boolean existe = vehiculos.stream().anyMatch(v -> v.getCodigo() == nuevaVenta.getCodigoVehiculo());
    boolean yaVendida = ventas.stream().anyMatch(v -> v.getCodigoVehiculo() == nuevaVenta.getCodigoVehiculo());

    if (!existe) {
        System.out.println(" Vehículo no existe.");
        return false;
    } else if (yaVendida) {
        System.out.println(" Vehículo ya fue vendido.");
        return false;
    } else {
        ventas.add(nuevaVenta);
        return true;
    }
}
}
