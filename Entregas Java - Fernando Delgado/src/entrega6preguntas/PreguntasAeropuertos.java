package entrega6preguntas;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class PreguntasAeropuertos {

    private static final String BASE_PATH = "aeropuertos/";

    private static List<String> leerCSV(String archivo) throws IOException {
        String basePath = System.getProperty("user.dir");
        Path path = Paths.get(basePath, "aeropuertos", archivo);
        List<String> lineas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(path.toFile()), StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea.replace("\uFEFF", "").trim());
            }
        }

        return lineas;
    }


    private static List<String> cargarVuelos() throws IOException {
        return leerCSV("vuelos.csv");
    }

    private static List<String> cargarVuelosProgramados() throws IOException {
        return leerCSV("vuelosProgramados.csv");
    }

    private static List<String> cargarAeropuertos() throws IOException {
        return leerCSV("aeropuertos.csv");
    }

    public static String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime a, LocalDateTime b) throws Exception {
        if (a == null || b == null || !a.isBefore(b) || a.plusDays(1).isAfter(b)) {
            throw new IllegalArgumentException("Las fechas deben estar en orden y separadas por más de un día.");
        }

        List<String> vuelos = cargarVuelos();
        List<String> vuelosProgramados = cargarVuelosProgramados();
        List<String> aeropuertos = cargarAeropuertos();

        Map<String, String> codigoAeropuertoACiudad = new HashMap<>();
        for (String linea : aeropuertos) {
            String[] partes = linea.split(",", -1);
            if (partes.length >= 4) {
                codigoAeropuertoACiudad.put(partes[2], partes[3]);
            }
        }

        Map<String, String> idVueloACodigoOrigen = new HashMap<>();
        for (String linea : vuelosProgramados) {
            String[] partes = linea.split(",", -1);
            if (partes.length >= 3) {
                String aerolinea = partes[0];
                String numero = partes[1];
                String origen = partes[2];
                idVueloACodigoOrigen.put(aerolinea + numero, origen);
            }
        }

        Map<String, Double> facturacionPorCiudad = new HashMap<>();
        for (String linea : vuelos) {
            String[] partes = linea.split(",", -1);
            if (partes.length >= 3) {
                String idVuelo = partes[0];
                LocalDateTime fecha = LocalDateTime.parse(partes[1].replace(" ", "T"));
                double precio = Double.parseDouble(partes[2]);

                if ((fecha.isEqual(a) || fecha.isAfter(a)) && (fecha.isEqual(b) || fecha.isBefore(b))) {
                    String origen = idVueloACodigoOrigen.getOrDefault(idVuelo, null);
                    if (origen != null) {
                        String ciudad = codigoAeropuertoACiudad.getOrDefault(origen, null);
                        if (ciudad != null) {
                            facturacionPorCiudad.merge(ciudad, precio, Double::sum);
                        }
                    }
                }
            }
        }

        return facturacionPorCiudad.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static String ciudadAeropuertoMayorFacturacionFuncional(LocalDateTime a, LocalDateTime b) throws Exception {
        if (a == null || b == null || !a.isBefore(b) || a.plusDays(1).isAfter(b)) {
            throw new IllegalArgumentException("Las fechas deben estar en orden y separadas por más de un día.");
        }

        Map<String, String> codigoAeropuertoACiudad = cargarAeropuertos().stream()
                .map(l -> l.split(",", -1))
                .filter(p -> p.length >= 4)
                .collect(Collectors.toMap(p -> p[2], p -> p[3]));

        Map<String, String> idVueloACodigoOrigen = cargarVuelosProgramados().stream()
                .map(l -> l.split(",", -1))
                .filter(p -> p.length >= 3)
                .collect(Collectors.toMap(p -> p[0] + p[1], p -> p[2]));

        return cargarVuelos().stream()
                .map(l -> l.split(",", -1))
                .filter(p -> p.length >= 3)
                .map(p -> {
                    String id = p[0];
                    LocalDateTime fecha = LocalDateTime.parse(p[1].replace(" ", "T"));
                    double precio = Double.parseDouble(p[2]);
                    return new Object[]{id, fecha, precio};
                })
                .filter(p -> {
                    LocalDateTime fecha = (LocalDateTime) p[1];
                    return (fecha.isEqual(a) || fecha.isAfter(a)) && (fecha.isEqual(b) || fecha.isBefore(b));
                })
                .map(p -> {
                    String idVuelo = (String) p[0];
                    double precio = (double) p[2];
                    String origen = idVueloACodigoOrigen.get(idVuelo);
                    String ciudad = origen != null ? codigoAeropuertoACiudad.get(origen) : null;
                    return ciudad != null ? Map.entry(ciudad, precio) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingDouble(Map.Entry::getValue)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}

