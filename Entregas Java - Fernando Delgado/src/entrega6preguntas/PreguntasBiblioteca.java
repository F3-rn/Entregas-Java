package entrega6preguntas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PreguntasBiblioteca {

    public static Map.Entry<String, Long> masVecesPrestadoImperativo() throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get("biblioteca/prestamos.txt"));
        Map<String, Long> contadorPrestamos = new HashMap<>();

        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length > 0) {
                String isbn = partes[0];
                contadorPrestamos.put(isbn, contadorPrestamos.getOrDefault(isbn, 0L) + 1);
            }
        }

        Map.Entry<String, Long> maxEntry = null;
        for (Map.Entry<String, Long> entry : contadorPrestamos.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    public static Map.Entry<String, Long> masVecesPrestadoFuncional() throws IOException {
        return Files.lines(Paths.get("biblioteca/prestamos.txt"))
                .map(linea -> linea.split(",")[0])
                .collect(Collectors.groupingBy(isbn -> isbn, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }


    public static Map<String, Set<String>> librosPorAutorImperativo(List<String> libros, List<String> nombres) {
        Map<String, Set<String>> resultado = new HashMap<>();

        for (String libro : libros) {
            String[] partes = libro.split(",");
            if (partes.length >= 3) {
                String titulo = partes[1];
                String autor = partes[2];

                if (nombres == null || nombres.contains(autor)) {
                    resultado.putIfAbsent(autor, new HashSet<>());
                    resultado.get(autor).add(titulo);
                }
            }
        }

        return resultado;
    }

    public static Map<String, Set<String>> librosPorAutorFuncional(List<String> libros, List<String> nombres) {
        return libros.stream()
                .map(linea -> linea.split(","))
                .filter(partes -> partes.length >= 3)
                .filter(partes -> nombres == null || nombres.contains(partes[2]))
                .collect(Collectors.groupingBy(
                        partes -> partes[2],
                        Collectors.mapping(
                                partes -> partes[1],
                                Collectors.toSet()
                        )
                ));
    }
}
