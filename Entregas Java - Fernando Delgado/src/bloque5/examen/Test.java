package bloque5.examen;
import java.util.List;
import java.util.Map;

public class Test {

       public static void main(String[] args) {
        // CLIENTES
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        // COMPRAS
        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
        Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

        // TEST CLIENTE
        System.out.println("=== Test Cliente ===");
        System.out.println("Nombre Cliente: " + ana.getNombre());
        System.out.println("Antigüedad Cliente: " + ana.getAntiguedad());
        System.out.println("Repr. Cliente: " + ana);

        // TEST COMPRA
        System.out.println("\n=== Test Compra ===");
        System.out.println("Descripción Compra 1: " + c1.getDescripcion());
        System.out.println("Importe Compra 1: " + c1.getImporte());
        System.out.println("Cliente Compra 1: " + c1.getCliente().getNombre());
        System.out.println("Repr. Compra 1: " + c1);

        // TEST CLIENTESPORANTIGUEDAD
        System.out.println("\n=== Test ClientesPorAntiguedad ===");
        ClientesPorAntiguedad clientesPorAntiguedad = new ClientesPorAntiguedad();
        clientesPorAntiguedad.add(ana);
        clientesPorAntiguedad.add(juan);
        clientesPorAntiguedad.add(luis);
        
        System.out.println("Top 2 clientes por antigüedad:");
        List<Cliente> topClientes = clientesPorAntiguedad.topClientes(2);
        topClientes.forEach(cliente -> System.out.println(cliente));

        // TEST HISTORIALCOMPRAS
        System.out.println("\n=== Test HistorialCompras ===");
        HistorialCompras historial = new HistorialCompras();
        historial.add(c1);
        historial.add(c2);
        historial.add(c3);
        historial.add(c4);

        // TEST: Total gastado por Ana
        double totalGastadoPorAna = historial.totalGastadoPor(ana);
        System.out.println("Total gastado por Ana: " + totalGastadoPorAna);

        // TEST: Filtrar compras > 20
        List<Compra> comprasMayoresA20 = historial.comprasMayoresA(20);
        System.out.println("Compras mayores a 20:");
        comprasMayoresA20.forEach(compra -> System.out.println(compra));

        // TEST COLACOMPRASPENDIENTES
        System.out.println("\n=== Test ColaComprasPendientes ===");
        ColaComprasPendientes cola = new ColaComprasPendientes();
        cola.add(c1);
        cola.add(c2);
        cola.add(c3);
        cola.add(c4);

        // TEST: Buscar compra por descripción "Taza"
        Compra compraEncontrada = cola.buscarCompraPorDescripcion("Taza");
        System.out.println("Compra encontrada con descripción 'Taza': " + compraEncontrada);

        // TEST: Filtrar compras de Ana que contienen "Agenda"
        List<Compra> comprasFiltradas = cola.filtrarPorClienteYProducto(ana, "Agenda");
        System.out.println("Compras de Ana que contienen 'Agenda':");
        comprasFiltradas.forEach(compra -> System.out.println(compra));
        
        // TEST ESTADISTICASCLIENTE
        System.out.println("\n=== Test EstadisticasClientes ===");
        List<Compra> compras = List.of(c1, c2, c3, c4);
        
        // FUNCIONAL
        Map<Cliente, List<Compra>> comprasPorClienteFuncional = EstadisticasClientes.agruparComprasPorClienteFuncional(compras);
        System.out.println("Agrupamiento funcional:");
        comprasPorClienteFuncional.forEach((cliente, listaCompras) -> {
            System.out.println(cliente.getNombre() + ": " + listaCompras.size() + " compras");
        });

        // IMPERATIVA
        Map<Cliente, List<Compra>> comprasPorClienteImperativa = EstadisticasClientes.agruparComprasPorClienteImperativa(compras);
        System.out.println("\nAgrupamiento imperativo:");
        comprasPorClienteImperativa.forEach((cliente, listaCompras) -> {
            System.out.println(cliente.getNombre() + ": " + listaCompras.size() + " compras");
        });
    }
}
