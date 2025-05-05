import java.util.*;

public class Concesionario {
    public static void main(String[] args) {
        List<Vehiculos> vehiculos = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();
        Random rand = new Random();

        String[] marcasAutos = {"Toyota", "Mazda", "Renault", "Chevrolet", "Nissan", "Hyundai", "Kia", "Volkswagen", "BMW", "Ferrari"};
        String[] marcasCamionetas = {"Ford", "Chevrolet", "Toyota", "Nissan", "Hyundai", "Isuzu", "Mazda", "Volkswagen", "RAM", "Mitsubishi"};
        String[] marcasMotos = {"Honda", "Yamaha", "Suzuki", "Kawasaki", "AKT", "Bajaj", "TVS", "Hero", "Benelli", "Ducati"};

        // Autos
        for (int i = 1; i <= 10; i++) {
            vehiculos.add(new Auto(
                    i,
                    marcasAutos[i - 1],
                    rand.nextInt(8) + 2015,          // Modelo entre 2015 y 2022
                    rand.nextInt(80000) + 5000       // Kilometraje entre 5.000 y 85.000
            ));
        }

        // Camionetas
        for (int i = 11; i <= 20; i++) {
            vehiculos.add(new Camioneta(
                    i,
                    marcasCamionetas[i - 11],
                    rand.nextInt(8) + 2014,         // Modelo entre 2014 y 2021
                    rand.nextInt(100000) + 15000    // Kilometraje entre 15.000 y 115.000
            ));
        }

        // Motocicletas
        for (int i = 21; i <= 30; i++) {
            vehiculos.add(new Motocicleta(
                    i,
                    marcasMotos[i - 21],
                    rand.nextInt(5) + 2018,         // Modelo entre 2018 y 2022
                    rand.nextInt(30000) + 1000      // Kilometraje entre 1.000 y 31.000
            ));
        }

        // Registrar ventas válidas
        Venta.registrarVenta(ventas, vehiculos, new Venta(45000.99, 3, "Lopez", "Carlos", "11223344"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(75000.50, 17, "Martinez", "Ana", "55667788"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(12000.00, 25, "Diaz", "Pedro", "99887766"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(18999.99, 5, "Gomez", "Laura", "12345678"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(30000.00, 12, "Moreno", "Luis", "87654321"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(22000.00, 21, "Rojas", "Marta", "66778899"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(99000.00, 1, "Perez", "Diana", "33445566"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(49999.00, 18, "Mejia", "Andres", "12344321"));
        Venta.registrarVenta(ventas, vehiculos, new Venta(17999.99, 28, "Salazar", "Camila", "77779999"));
        // Venta inválida: vehículo ya vendido
        Venta.registrarVenta(ventas, vehiculos, new Venta(15000.00, 3, "Falsos", "Usuario", "00000000"));

        // Mostrar vehículos
        System.out.println("\n=== VEHÍCULOS DISPONIBLES ===");
        System.out.printf("%-6s | %-12s | %-15s | %-6s | %-12s%n",
                "Código", "Tipo", "Marca", "Modelo", "Kilometraje");
        for (Vehiculos v : vehiculos) {
            System.out.printf("%6d | %-12s | %-15s | %6d | %,10d km%n",
                    v.getCodigo(),
                    v.getTipo(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getKilometraje());
        }

        // Mostrar ventas
        System.out.println("\n=== VENTAS REGISTRADAS ===");
        System.out.printf("%-20s | %-15s | %-10s | %-10s | %-12s%n",
                "Cliente", "Documento", "Vehículo", "Tipo", "Monto");
        for (Venta venta : ventas) {
            Vehiculos vehiculoVendido = vehiculos.stream()
                    .filter(v -> v.getCodigo() == venta.getCodigoVehiculo())
                    .findFirst()
                    .orElse(null);

            System.out.printf("%-20s | %-15s | %10d | %-10s | $%,10.2f%n",
                    venta.getNombre() + " " + venta.getApellido(),
                    venta.getDocumento(),
                    venta.getCodigoVehiculo(),
                    (vehiculoVendido != null) ? vehiculoVendido.getTipo() : "DESCONOCIDO",
                    venta.getMonto());
        }
    }
}
