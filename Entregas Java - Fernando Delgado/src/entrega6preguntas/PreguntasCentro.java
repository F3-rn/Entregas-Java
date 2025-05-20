package entrega6preguntas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PreguntasCentro {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static class Alumno {
        String nombre, apellidos, dni;
        LocalDate fechaNacimiento;
        double nota;
        Alumno(String nombre, String apellidos, String dni, LocalDate fechaNacimiento, double nota) {
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
            this.nota = nota;
        }
    }

    private static class Profesor {
        String nombre, apellidos, dni;
        LocalDate fechaNacimiento;
        Profesor(String nombre, String apellidos, String dni, LocalDate fechaNacimiento) {
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.dni = dni;
            this.fechaNacimiento = fechaNacimiento;
        }
    }

    private static class Matricula {
        String dniAlumno;
        int idAsignatura;
        int grupo;
        Matricula(String dniAlumno, int idAsignatura, int grupo) {
            this.dniAlumno = dniAlumno;
            this.idAsignatura = idAsignatura;
            this.grupo = grupo;
        }
    }


    public static double promedioEdadProfesoresImperativo(String dniAlumno) throws IOException {
        if (dniAlumno == null || dniAlumno.isEmpty()) throw new IllegalArgumentException("DNI alumno inválido");

        Map<String, Profesor> profesores = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/profesores.txt"))) {
            String[] campos = linea.split(",");
            String apellidos = campos[0].trim();
            String nombre = campos[1].trim();
            String dni = campos[2].trim();
            LocalDate fechaNac = LocalDate.parse(campos[3].trim(), dtf);
            profesores.put(dni, new Profesor(nombre, apellidos, dni, fechaNac));
        }

        Map<Integer, List<String>> gruposProfesores = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/asignaciones.txt"))) {
            String[] campos = linea.split(",");
            String dniProf = campos[0].trim();
            int idAsignatura = Integer.parseInt(campos[1].trim());
            int grupo = Integer.parseInt(campos[2].trim());
            gruposProfesores.computeIfAbsent(grupo, k -> new ArrayList<>()).add(dniProf);
        }

        Set<Integer> gruposAlumno = new HashSet<>();
        for (String linea : Files.readAllLines(Paths.get("centro/matriculas.txt"))) {
            String[] campos = linea.split(",");
            String dniAl = campos[0].trim();
            int idAsig = Integer.parseInt(campos[1].trim());
            int grupo = Integer.parseInt(campos[2].trim());
            if (dniAl.equals(dniAlumno)) {
                gruposAlumno.add(grupo);
            }
        }

        Set<String> profesoresAlumno = new HashSet<>();
        for (int grupo : gruposAlumno) {
            List<String> listaProfs = gruposProfesores.get(grupo);
            if (listaProfs != null) {
                profesoresAlumno.addAll(listaProfs);
            }
        }

        int sumaEdades = 0;
        int count = 0;
        LocalDate hoy = LocalDate.now();
        for (String dniProf : profesoresAlumno) {
            Profesor p = profesores.get(dniProf);
            if (p != null) {
                int edad = Period.between(p.fechaNacimiento, hoy).getYears();
                sumaEdades += edad;
                count++;
            }
        }

        if (count == 0) return 0;
        return sumaEdades / (double) count;
    }

    public static double promedioEdadProfesoresFuncional(String dniAlumno) throws IOException {
        if (dniAlumno == null || dniAlumno.isEmpty()) throw new IllegalArgumentException("DNI alumno inválido");

        LocalDate hoy = LocalDate.now();

        Map<String, Profesor> profesores = Files.lines(Paths.get("centro/profesores.txt"))
            .map(linea -> linea.split(","))
            .collect(Collectors.toMap(
                campos -> campos[2].trim(),
                campos -> new Profesor(campos[1].trim(), campos[0].trim(), campos[2].trim(),
                        LocalDate.parse(campos[3].trim(), dtf))));

        Map<Integer, List<String>> gruposProfesores = Files.lines(Paths.get("centro/asignaciones.txt"))
            .map(linea -> linea.split(","))
            .collect(Collectors.groupingBy(
                campos -> Integer.parseInt(campos[2].trim()),
                Collectors.mapping(campos -> campos[0].trim(), Collectors.toList())
            ));

        Set<Integer> gruposAlumno = Files.lines(Paths.get("centro/matriculas.txt"))
            .map(linea -> linea.split(","))
            .filter(campos -> campos[0].trim().equals(dniAlumno))
            .map(campos -> Integer.parseInt(campos[2].trim()))
            .collect(Collectors.toSet());

        Set<String> profesoresAlumno = gruposAlumno.stream()
            .flatMap(grupo -> gruposProfesores.getOrDefault(grupo, List.of()).stream())
            .collect(Collectors.toSet());

        OptionalDouble promedio = profesoresAlumno.stream()
            .map(profesores::get)
            .filter(Objects::nonNull)
            .mapToInt(p -> Period.between(p.fechaNacimiento, hoy).getYears())
            .average();

        return promedio.orElse(0);
    }


    public static int grupoMayorDiversidadEdadImperativo() throws IOException {
        Map<String, LocalDate> alumnosFecha = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/alumnos.txt"))) {
            String[] campos = linea.split(",");
            String dni = campos[2].trim();
            LocalDate fechaNac = LocalDate.parse(campos[3].trim(), dtf);
            alumnosFecha.put(dni, fechaNac);
        }

        Map<Integer, List<String>> grupoAlumnos = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/matriculas.txt"))) {
            String[] campos = linea.split(",");
            String dni = campos[0].trim();
            int grupo = Integer.parseInt(campos[2].trim());
            grupoAlumnos.computeIfAbsent(grupo, k -> new ArrayList<>()).add(dni);
        }

        LocalDate hoy = LocalDate.now();

        int grupoMax = -1;
        int maxDiferencia = -1;

        for (var entry : grupoAlumnos.entrySet()) {
            int grupo = entry.getKey();
            List<String> dnis = entry.getValue();
            if (dnis.size() < 2) continue;

            int minEdad = Integer.MAX_VALUE;
            int maxEdad = Integer.MIN_VALUE;

            for (String dni : dnis) {
                LocalDate fn = alumnosFecha.get(dni);
                if (fn == null) continue;
                int edad = Period.between(fn, hoy).getYears();
                if (edad < minEdad) minEdad = edad;
                if (edad > maxEdad) maxEdad = edad;
            }

            int dif = maxEdad - minEdad;
            if (dif > maxDiferencia) {
                maxDiferencia = dif;
                grupoMax = grupo;
            }
        }

        return grupoMax;
    }

    public static int grupoMayorDiversidadEdadFuncional() throws IOException {
        Map<String, LocalDate> alumnosFecha = Files.lines(Paths.get("centro/alumnos.txt"))
            .map(l -> l.split(","))
            .collect(Collectors.toMap(
                campos -> campos[2].trim(),
                campos -> LocalDate.parse(campos[3].trim(), dtf)
            ));

        Map<Integer, List<String>> grupoAlumnos = Files.lines(Paths.get("centro/matriculas.txt"))
            .map(l -> l.split(","))
            .collect(Collectors.groupingBy(
                campos -> Integer.parseInt(campos[2].trim()),
                Collectors.mapping(campos -> campos[0].trim(), Collectors.toList())
            ));

        LocalDate hoy = LocalDate.now();

        Optional<Integer> grupoMax = grupoAlumnos.entrySet().stream()
        	    .filter(e -> e.getValue().size() > 1)
        	    .max(Comparator.comparingInt(e -> {
        	        IntSummaryStatistics stats = e.getValue().stream()
        	            .map(alumnosFecha::get)
        	            .filter(Objects::nonNull)
        	            .mapToInt(fn -> Period.between(fn, hoy).getYears())
        	            .summaryStatistics();
        	        return stats.getMax() - stats.getMin();
        	    }))
        	    .map(Map.Entry::getKey);

        return grupoMax.orElse(-1);
    }


    public static String alumnoMasMatriculasImperativo() throws IOException {
        Map<String, Integer> conteoMatriculas = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/matriculas.txt"))) {
            String dni = linea.split(",")[0].trim();
            conteoMatriculas.put(dni, conteoMatriculas.getOrDefault(dni, 0) + 1);
        }
        String alumnoMax = null;
        int max = -1;
        for (Map.Entry<String, Integer> e : conteoMatriculas.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                alumnoMax = e.getKey();
            }
        }
        return alumnoMax;
    }

    public static String alumnoMasMatriculasFuncional() throws IOException {
        return Files.lines(Paths.get("centro/matriculas.txt"))
            .map(line -> line.split(",")[0].trim())
            .collect(Collectors.groupingBy(dni -> dni, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }



    public static Map<String, String> rangosEdadPorAlumnoImperativo(String input) throws IOException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Cadena de rangos vacía");
        }

        String[] rangos = input.split(",");
        List<int[]> rangosEdad = new ArrayList<>();

        for (String rango : rangos) {
            String[] partes = rango.trim().split("-");
            if (partes.length != 2) {
                throw new IllegalArgumentException("Formato inválido de rango: " + rango);
            }

            try {
                int desde = Integer.parseInt(partes[0].trim());
                int hasta = Integer.parseInt(partes[1].trim());
                if (desde >= hasta) {
                    throw new IllegalArgumentException("Rango inválido (desde >= hasta): " + rango);
                }
                rangosEdad.add(new int[]{desde, hasta});
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Rango contiene valores no numéricos: " + rango);
            }
        }

        rangosEdad.sort(Comparator.comparingInt(r -> r[0]));
        for (int i = 0; i < rangosEdad.size() - 1; i++) {
            if (rangosEdad.get(i)[1] >= rangosEdad.get(i + 1)[0]) {
                throw new IllegalArgumentException("Rangos solapados: " + input);
            }
        }

        Map<String, String> resultado = new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate hoy = LocalDate.now();

        try (BufferedReader br = new BufferedReader(new FileReader("centro/alumnos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 7);
                if (partes.length < 7) continue;

                String nombreCompleto = partes[0] + " " + partes[1];
                LocalDate nacimiento = LocalDateTime.parse(partes[3], formatter).toLocalDate();
                int edad = Period.between(nacimiento, hoy).getYears();

                for (int[] rango : rangosEdad) {
                    if (edad >= rango[0] && edad <= rango[1]) {
                        String clave = rango[0] + " - " + rango[1];
                        resultado.put(nombreCompleto, clave);
                        break;
                    }
                }
            }
        }

        return resultado;
    }

 
    public static Map<String, String> rangosEdadPorAlumnoFuncional(String input) throws IOException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Cadena de rangos vacía");
        }

        List<int[]> rangosEdad = Arrays.stream(input.split(","))
            .map(String::trim)
            .map(rango -> {
                String[] partes = rango.split("-");
                if (partes.length != 2) {
                    throw new IllegalArgumentException("Formato inválido de rango: " + rango);
                }
                try {
                    int desde = Integer.parseInt(partes[0].trim());
                    int hasta = Integer.parseInt(partes[1].trim());
                    if (desde >= hasta) {
                        throw new IllegalArgumentException("Rango inválido (desde >= hasta): " + rango);
                    }
                    return new int[]{desde, hasta};
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Rango contiene valores no numéricos: " + rango);
                }
            })
            .sorted(Comparator.comparingInt(r -> r[0]))
            .collect(Collectors.toList());

        for (int i = 0; i < rangosEdad.size() - 1; i++) {
            if (rangosEdad.get(i)[1] >= rangosEdad.get(i + 1)[0]) {
                throw new IllegalArgumentException("Rangos solapados: " + input);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate hoy = LocalDate.now();

        return Files.lines(Paths.get("centro/alumnos.txt"))
            .map(linea -> linea.split(",", 7))
            .filter(partes -> partes.length >= 7)
            .map(partes -> {
                String nombre = partes[0] + " " + partes[1];
                LocalDate nacimiento = LocalDateTime.parse(partes[3], formatter).toLocalDate();
                int edad = Period.between(nacimiento, hoy).getYears();

                Optional<String> rangoEncontrado = rangosEdad.stream()
                    .filter(r -> edad >= r[0] && edad <= r[1])
                    .map(r -> r[0] + " - " + r[1])
                    .findFirst();

                return rangoEncontrado.map(r -> Map.entry(nombre, r)).orElse(null);
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }


    public static String nombreProfesorMasGruposImperativo(int edadMin, int edadMax) throws IOException {
        if (edadMin > edadMax) throw new IllegalArgumentException("Edad mínima no puede ser mayor que máxima");

        Map<String, Profesor> profesores = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/profesores.txt"))) {
            String[] campos = linea.split(",");
            String dni = campos[2].trim();
            Profesor p = new Profesor(campos[1].trim(), campos[0].trim(), dni,
                    LocalDate.parse(campos[3].trim(), dtf));
            profesores.put(dni, p);
        }

        LocalDate hoy = LocalDate.now();

        Map<String, Profesor> profsFiltrados = new HashMap<>();
        for (var e : profesores.entrySet()) {
            int edad = Period.between(e.getValue().fechaNacimiento, hoy).getYears();
            if (edad >= edadMin && edad <= edadMax) {
                profsFiltrados.put(e.getKey(), e.getValue());
            }
        }

        Map<String, Set<Integer>> profGrupos = new HashMap<>();
        for (String linea : Files.readAllLines(Paths.get("centro/asignaciones.txt"))) {
            String[] campos = linea.split(",");
            String dniProf = campos[0].trim();
            int grupo = Integer.parseInt(campos[2].trim());
            if (profsFiltrados.containsKey(dniProf)) {
                profGrupos.computeIfAbsent(dniProf, k -> new HashSet<>()).add(grupo);
            }
        }

        String mejorProfesor = null;
        int maxGrupos = -1;
        for (var e : profGrupos.entrySet()) {
            int tam = e.getValue().size();
            if (tam > maxGrupos) {
                maxGrupos = tam;
                mejorProfesor = e.getKey();
            }
        }

        if (mejorProfesor == null) return null;

        Profesor p = profesores.get(mejorProfesor);
        return p.nombre + " " + p.apellidos;
    }

    public static String nombreProfesorMasGruposFuncional(int edadMin, int edadMax) throws IOException {
        if (edadMin > edadMax) throw new IllegalArgumentException("Edad mínima no puede ser mayor que máxima");

        LocalDate hoy = LocalDate.now();

        Map<String, Profesor> profesores = Files.lines(Paths.get("centro/profesores.txt"))
            .map(l -> l.split(","))
            .collect(Collectors.toMap(
                campos -> campos[2].trim(),
                campos -> new Profesor(campos[1].trim(), campos[0].trim(), campos[2].trim(),
                        LocalDate.parse(campos[3].trim(), dtf))));

        Map<String, Profesor> profsFiltrados = profesores.entrySet().stream()
            .filter(e -> {
                int edad = Period.between(e.getValue().fechaNacimiento, hoy).getYears();
                return edad >= edadMin && edad <= edadMax;
            })
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Long> profGruposCount = Files.lines(Paths.get("centro/asignaciones.txt"))
            .map(l -> l.split(","))
            .filter(campos -> profsFiltrados.containsKey(campos[0].trim()))
            .collect(Collectors.groupingBy(campos -> campos[0].trim(), Collectors.mapping(campos -> campos[2], Collectors.toSet())))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));

        return profGruposCount.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(e -> {
                Profesor p = profesores.get(e.getKey());
                return p.nombre + " " + p.apellidos;
            }).orElse(null);
    }


    public static List<String> nombresAlumnosMayorNotaImperativo(Integer n, Integer a) throws IOException {
        if (n == null || a == null) {
            throw new IllegalArgumentException("Los parámetros n y a no pueden ser null");
        }
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("El parámetro n debe estar entre 1 y 10");
        }

        DateTimeFormatter formatter = dtf;
        LocalDate fechaFiltro = LocalDate.of(a, 12, 31);

        List<Alumno> alumnos = new ArrayList<>();

        List<String> lineas = Files.readAllLines(Paths.get("centro/alumnos.txt"));
        for (String linea : lineas) {
            String[] campos = linea.split(",");
            if (campos.length < 5) continue;

            String nombre = campos[1].trim();
            String apellidos = campos[0].trim();
            String dni = campos[2].trim();
            LocalDate fechaNacimiento = LocalDateTime.parse(campos[3].trim(), formatter).toLocalDate();
            double nota = Double.parseDouble(campos[4].trim());

            if (fechaNacimiento.isAfter(fechaFiltro)) {
                alumnos.add(new Alumno(nombre, apellidos, dni, fechaNacimiento, nota));
            }
        }

        alumnos.sort(new Comparator<Alumno>() {
            @Override
            public int compare(Alumno o1, Alumno o2) {
                return Double.compare(o2.nota, o1.nota);
            }
        });

        List<String> resultado = new ArrayList<>();
        int limite = Math.min(n, alumnos.size());
        for (int i = 0; i < limite; i++) {
            Alumno alumno = alumnos.get(i);
            resultado.add(alumno.nombre + " " + alumno.apellidos);
        }

        return resultado;
    }


    public static List<String> nombresAlumnosMayorNotaFuncional(Integer n, Integer a) throws IOException {
        if (n == null || a == null) {
            throw new IllegalArgumentException("Los parámetros n y a no pueden ser null");
        }
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("El parámetro n debe estar entre 1 y 10");
        }

        DateTimeFormatter formatter = dtf;
        LocalDate fechaFiltro = LocalDate.of(a, 12, 31);

        List<Alumno> alumnos = Files.lines(Paths.get("centro/alumnos.txt"))
            .map(linea -> linea.split(","))
            .filter(campos -> campos.length >= 5)
            .map(campos -> new Alumno(
                campos[1].trim(),
                campos[0].trim(),
                campos[2].trim(),
                LocalDateTime.parse(campos[3].trim(), formatter).toLocalDate(),
                Double.parseDouble(campos[4].trim())
            ))
            .filter(alumno -> alumno.fechaNacimiento.isAfter(fechaFiltro))
            .collect(Collectors.toList());

        return alumnos.stream()
            .sorted(Comparator.comparingDouble((Alumno a1) -> a1.nota).reversed())
            .limit(n)
            .map(alumno -> alumno.nombre + " " + alumno.apellidos)
            .collect(Collectors.toList());
    }

}
