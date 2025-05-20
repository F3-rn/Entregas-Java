package entrega6preguntas;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PreguntasBancos {

    private static class Persona {
        private final String dni;
        private final LocalDate fechaNacimiento;

        public Persona(String dni, LocalDate fechaNacimiento) {
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
        }

        public int calcularEdad() {
            return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
        }

        public String getDni() {
            return dni;
        }
    }

//IMPERATIVO
    public static Map<String, Double> valorTotalPrestamosImperativo(int e, double a, double b, LocalDate f) throws IOException {
        if (e <= 18 || a <= 0 || b <= 0 || a >= b) {
            throw new IllegalArgumentException("Parámetros inválidos.");
        }

        Map<String, Persona> personas = new HashMap<>();
        BufferedReader brPersonas = new BufferedReader(new FileReader("bancos/Personas.txt"));
        String linea;
        while ((linea = brPersonas.readLine()) != null) {
            String[] partes = linea.split(",");
            String dni = partes[2];
            LocalDate fechaNacimiento = LocalDate.parse(partes[3].split(" ")[0]);
            personas.put(dni, new Persona(dni, fechaNacimiento));
        }
        brPersonas.close();

        Map<String, Double> totalPrestamosPorCliente = new HashMap<>();
        BufferedReader brPrestamos = new BufferedReader(new FileReader("bancos/Prestamos.txt"));
        while ((linea = brPrestamos.readLine()) != null) {
            String[] partes = linea.split(",");
            String dniCliente = partes[1];
            LocalDate fechaPrestamo = LocalDate.parse(partes[3].split(" ")[0]);
            double valor = Double.parseDouble(partes[5]);

            Persona persona = personas.get(dniCliente);
            if (persona != null && persona.calcularEdad() < e && valor > a && valor < b && fechaPrestamo.isAfter(f)) {
                totalPrestamosPorCliente.put(
                    dniCliente,
                    totalPrestamosPorCliente.getOrDefault(dniCliente, 0.0) + valor
                );
            }
        }
        brPrestamos.close();

        return totalPrestamosPorCliente;
    }

//FUNCIONAL
    public static Map<String, Double> valorTotalPrestamosFuncional(int e, double a, double b, LocalDate f) throws IOException {
        if (e <= 18 || a <= 0 || b <= 0 || a >= b) {
            throw new IllegalArgumentException("Parámetros inválidos.");
        }

        Map<String, Persona> personas = Files.lines(Paths.get("bancos/Personas.txt"))
            .map(line -> line.split(","))
            .collect(Collectors.toMap(
                p -> p[2],
                p -> new Persona(p[2], LocalDate.parse(p[3].split(" ")[0]))
            ));

        return Files.lines(Paths.get("bancos/Prestamos.txt"))
            .map(line -> line.split(","))
            .filter(p -> {
                String dni = p[1];
                Persona persona = personas.get(dni);
                if (persona == null) return false;

                int edad = persona.calcularEdad();
                LocalDate fecha = LocalDate.parse(p[3].split(" ")[0]);
                double valor = Double.parseDouble(p[5]);

                return edad < e && valor > a && valor < b && fecha.isAfter(f);
            })
            .collect(Collectors.groupingBy(
                p -> p[1],
                Collectors.summingDouble(p -> Double.parseDouble(p[5]))
            ));
    }
}
