package bloque5.examen;

import java.util.*;
import java.util.stream.Collectors;

public class EstadisticasClientes {

    public static Map<Cliente, List<Compra>> agruparComprasPorClienteFuncional(List<Compra> compras) {
        return compras.stream()
                      .collect(Collectors.groupingBy(Compra::getCliente));
    }

    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativa(List<Compra> compras) {
        Map<Cliente, List<Compra>> comprasPorCliente = new HashMap<>();
        
        for (Compra compra : compras) {
            Cliente cliente = compra.getCliente();
            comprasPorCliente.putIfAbsent(cliente, new ArrayList<>());
            comprasPorCliente.get(cliente).add(compra);
        }
        
        return comprasPorCliente;
    }
}
