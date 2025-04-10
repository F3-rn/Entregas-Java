package bloque5.examen;

import entrega2.ListaOrdenada;
import java.util.List;
import java.util.stream.Collectors;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

    public ClientesPorAntiguedad() {
        super((c1, c2) -> Integer.compare(c2.getAntiguedad(), c1.getAntiguedad()));
    }

    public List<Cliente> topClientes(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El valor de n debe ser mayor que 0");
        }
        
        int limit = Math.min(n, size());

        return elementos.stream()
                        .limit(limit)
                        .collect(Collectors.toList());
    }
}
