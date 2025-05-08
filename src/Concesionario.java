import java.util.*;

public class Concesionario {
    public static void main(String[] args) {
        System.out.println("\n=== BIENVENIDO A CONCESIONARIO LOS MIAU ===\n");

        List<Vehiculos> vehiculos = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();
        Set<Integer> vendidos = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        String[] marcasAutos = {"Toyota", "Mazda", "Renault", "Chevrolet", "Nissan", "Hyundai", "Kia", "Volkswagen", "BMW", "Ferrari"};
        String[] marcasCamionetas = {"Ford", "Chevrolet", "Toyota", "Nissan", "Hyundai", "Isuzu", "Mazda", "Volkswagen", "RAM", "Mitsubishi"};
        String[] marcasMotos = {"Honda", "Yamaha", "Suzuki", "Kawasaki", "AKT", "Bajaj", "TVS", "Hero", "Benelli", "Ducati"};

        // Autos
        for (int i = 1; i <= 10; i++) {
            vehiculos.add(new Auto(i, marcasAutos[i - 1], rand.nextInt(8) + 2015, rand.nextInt(80000) + 5000));
        }
        // Camionetas
        for (int i = 11; i <= 20; i++) {
            vehiculos.add(new Camioneta(i, marcasCamionetas[i - 11], rand.nextInt(8) + 2014, rand.nextInt(100000) + 15000));
        }
        // Motocicletas
        for (int i = 21; i <= 30; i++) {
            vehiculos.add(new Motocicleta(i, marcasMotos[i - 21], rand.nextInt(5) + 2018, rand.nextInt(30000) + 1000));
        }

        registrarVentasIniciales(ventas, vendidos);

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Modo Empleado (Agregar vehículos)\n2. Modo Comercial (Ver y comprar vehículos)\n3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            if (opcionPrincipal == 1) {
                System.out.print("Ingrese la clave de acceso: ");
                String clave = scanner.nextLine();
                if (clave.equals("Admin")) {
                    while (true) {
                        System.out.println("\n--- MODO EMPLEADO ---");
                        System.out.println("1. Agregar vehículo");
                        System.out.println("2. Ver vehículos vendidos");
                        System.out.println("3. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");

                        int opcionEmpleado = scanner.nextInt();
                        scanner.nextLine();

                        if (opcionEmpleado == 1) {
                            agregarVehiculo(scanner, vehiculos);
                        } else if (opcionEmpleado == 2) {
                            mostrarVehiculosVendidos(ventas, vehiculos);
                        } else if (opcionEmpleado == 3) {
                            break;
                        } else {
                            System.out.println("Opción inválida.");
                        }
                    }
                } else {
                    System.out.println("Clave incorrecta.");
                }
            } else if (opcionPrincipal == 2) {
                while (true) {
                    System.out.println("\n=== VEHÍCULOS DISPONIBLES ===");
                    mostrarVehiculosDisponibles(vehiculos, vendidos);

                    System.out.println("\n--- MENÚ COMERCIAL ---");
                    System.out.println("1. Comprar vehículo\n2. Volver al menú principal");
                    System.out.print("Seleccione una opción: ");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    if (opcion == 1) {
                        filtrarYComprar(scanner, vehiculos, ventas, vendidos);
                    } else if (opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opción inválida.");
                    }
                }
            } else if (opcionPrincipal == 3) {
                break;
            } else {
                System.out.println("Opción inválida.");
            }
        }

        mostrarVentas(ventas, vehiculos);
    }

    private static void agregarVehiculo(Scanner scanner, List<Vehiculos> vehiculos) {
        System.out.println("\n--- AGREGAR NUEVO VEHÍCULO ---");

        System.out.print("Código del vehículo: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.println("Seleccione el tipo de vehículo:");
        System.out.println("1. Auto\n2. Camioneta\n3. Motocicleta");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine();

        String tipo = switch (tipoVehiculo) {
            case 1 -> "Auto";
            case 2 -> "Camioneta";
            case 3 -> "Motocicleta";
            default -> {
                System.out.println("Tipo inválido. Vehículo no agregado.");
                yield null;
            }
        };
        if (tipo == null) return;

        System.out.print("Modelo (año): ");
        int modelo = scanner.nextInt();

        System.out.print("Kilometraje: ");
        int kilometraje = scanner.nextInt();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        // Verificar si es una marca nueva
        boolean marcaExiste = vehiculos.stream()
                .anyMatch(v -> v.getMarca().equalsIgnoreCase(marca));

        if (!marcaExiste) {
            System.out.println("✅ Marca nueva registrada: " + marca);
        }

        Vehiculos nuevo = new Vehiculos(codigo, marca, tipo, modelo, kilometraje);
        nuevo.setPrecio(precio);
        vehiculos.add(nuevo);

        System.out.println("Vehículo agregado exitosamente.");
    }

    private static void registrarVentasIniciales(List<Venta> ventas, Set<Integer> vendidos) {
        int[][] datos = {
                {45000, 3}, {75000, 17}, {12000, 25}, {18999, 5}, {30000, 12},
                {22000, 21}, {99000, 1}, {49999, 18}, {17999, 28}, {15000, 3} // duplicada
        };
        String[][] clientes = {
                {"Lopez", "Carlos", "11223344"}, {"Martinez", "Ana", "55667788"}, {"Diaz", "Pedro", "99887766"},
                {"Gomez", "Laura", "12345678"}, {"Moreno", "Luis", "87654321"}, {"Rojas", "Marta", "66778899"},
                {"Perez", "Diana", "33445566"}, {"Mejia", "Andres", "12344321"}, {"Salazar", "Camila", "77779999"},
                {"Falsos", "Usuario", "00000000"}
        };

        for (int i = 0; i < datos.length; i++) {
            int codigo = datos[i][1];
            if (!vendidos.contains(codigo)) {
                ventas.add(new Venta(datos[i][0], codigo, clientes[i][0], clientes[i][1], clientes[i][2]));
                vendidos.add(codigo);
            }
        }
    }

    private static void mostrarVehiculosDisponibles(List<Vehiculos> vehiculos, Set<Integer> vendidos) {
        System.out.printf("%-6s | %-12s | %-15s | %-6s | %-12s | %-10s%n",
                "Código", "Tipo", "Marca", "Modelo", "Kilometraje", "Precio");
        for (Vehiculos v : vehiculos) {
            if (!vendidos.contains(v.getCodigo())) {
                System.out.printf("%6d | %-12s | %-15s | %6d | %,10d km | $%,10.2f%n",
                        v.getCodigo(), v.getTipo(), v.getMarca(), v.getModelo(), v.getKilometraje(), calcularPrecio(v));
            }
        }
    }

    private static void mostrarVentas(List<Venta> ventas, List<Vehiculos> vehiculos) {
        System.out.println("\n=== VENTAS REGISTRADAS ===");
        System.out.printf("%-20s | %-15s | %-10s | %-10s | %-12s%n",
                "Cliente", "Documento", "Vehículo", "Tipo", "Monto");
        for (Venta venta : ventas) {
            Vehiculos v = vehiculos.stream().filter(x -> x.getCodigo() == venta.getCodigoVehiculo()).findFirst().orElse(null);
            System.out.printf("%-20s | %-15s | %10d | %-10s | $%,10.2f%n",
                    venta.getNombre() + " " + venta.getApellido(), venta.getDocumento(),
                    venta.getCodigoVehiculo(), (v != null ? v.getTipo() : "Desconocido"), venta.getMonto());
        }
    }

    private static double calcularPrecio(Vehiculos v) {
        return 20000 - (v.getModelo() - 2015) * 500 - v.getKilometraje() * 0.05;
    }

    private static void filtrarYComprar(Scanner scanner, List<Vehiculos> vehiculos, List<Venta> ventas, Set<Integer> vendidos) {
        System.out.println("\nSeleccione el tipo de vehículo:");
        System.out.println("1. Auto");
        System.out.println("2. Camioneta");
        System.out.println("3. Motocicleta");
        System.out.print("Ingrese una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String tipo;
        switch (opcion) {
            case 1: tipo = "Auto"; break;
            case 2: tipo = "Camioneta"; break;
            case 3: tipo = "Motocicleta"; break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        List<String> marcas = new ArrayList<>();
        for (Vehiculos v : vehiculos) {
            if (v.getTipo().equalsIgnoreCase(tipo) && !vendidos.contains(v.getCodigo())) {
                if (!marcas.contains(v.getMarca())) marcas.add(v.getMarca());
            }
        }

        if (marcas.isEmpty()) {
            System.out.println("No hay vehículos disponibles de ese tipo.");
            return;
        }

        System.out.println("\nMarcas disponibles:");
        for (int i = 0; i < marcas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, marcas.get(i));
        }

        System.out.print("Seleccione la marca: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        if (seleccion < 1 || seleccion > marcas.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        String marcaSeleccionada = marcas.get(seleccion - 1);
        List<Vehiculos> filtrados = new ArrayList<>();
        for (Vehiculos v : vehiculos) {
            if (v.getTipo().equalsIgnoreCase(tipo) &&
                    v.getMarca().equalsIgnoreCase(marcaSeleccionada) &&
                    !vendidos.contains(v.getCodigo())) {
                filtrados.add(v);
            }
        }

        if (filtrados.isEmpty()) {
            System.out.println("No hay vehículos disponibles con ese filtro.");
            return;
        }

        System.out.println("\nVehículos disponibles:");
        for (int i = 0; i < filtrados.size(); i++) {
            Vehiculos v = filtrados.get(i);
            System.out.printf("%d. %s - %s - %d - $%,.2f%n", i + 1, v.getTipo(), v.getMarca(), v.getModelo(), calcularPrecio(v));
        }

        System.out.print("Seleccione el vehículo a comprar (0 para volver): ");
        int eleccion = scanner.nextInt();
        scanner.nextLine();

        if (eleccion == 0) return;

        if (eleccion < 1 || eleccion > filtrados.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Vehiculos vehiculoSeleccionado = filtrados.get(eleccion - 1);

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido del cliente: ");
        String apellido = scanner.nextLine();

        String documento;
        while (true) {
            System.out.print("Documento (solo números): ");
            documento = scanner.nextLine();
            if (documento.matches("\\d+")) break;
            System.out.println("Documento inválido. Solo se permiten números.");
        }

        double monto = calcularPrecio(vehiculoSeleccionado);
        ventas.add(new Venta(monto, vehiculoSeleccionado.getCodigo(), apellido, nombre, documento));
        vendidos.add(vehiculoSeleccionado.getCodigo());


        System.out.println("\n===== RESUMEN DE LA VENTA =====");
        System.out.printf("Cliente: %s %s%n", nombre, apellido);
        System.out.println("Documento: " + documento);
        System.out.printf("Vehículo: %s - %s - Modelo %d%n", vehiculoSeleccionado.getTipo(), vehiculoSeleccionado.getMarca(), vehiculoSeleccionado.getModelo());
        System.out.printf("Precio: $%,.2f%n", monto);
        System.out.println("Estado: VENDIDO");
        System.out.println("================================\n");
    }
    private static void mostrarVehiculosVendidos(List<Venta> ventas, List<Vehiculos> vehiculos) {
        if (ventas.isEmpty()) {
            System.out.println("No hay vehículos vendidos aún.");
            return;
        }

        System.out.println("\n===== VEHÍCULOS VENDIDOS =====");
        for (Venta v : ventas) {
            // Buscar el vehículo por código
            Vehiculos vehiculo = null;
            for (Vehiculos vh : vehiculos) {
                if (vh.getCodigo() == v.getCodigoVehiculo()) {
                    vehiculo = vh;
                    break;
                }
            }

            if (vehiculo != null) {
                System.out.printf("Vehículo: %s - %s - Modelo %d - $%,.2f%n",
                        vehiculo.getTipo(), vehiculo.getMarca(), vehiculo.getModelo(), v.getMonto());
                System.out.printf("Cliente: %s %s - Documento: %s%n",
                        v.getNombre(), v.getApellido(), v.getDocumento());
                System.out.println("---------------------------------------");
            }
        }
    }

}
