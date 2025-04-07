package entrega1;

import java.io.*;
import java.util.*;

public class Lecturas {

    // FUNCIÓN 6
    public static int contarPalabra(String nombreFichero, String sep, String cad) {
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(sep);

                for (String palabra : palabras) {
                    if (palabra.equals(cad)) {
                        contador++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return contador;
    }

    // FUNCIÓN 7
    public static List<String> obtenerLineasConCadena(String nombreFichero, String cad) {
        List<String> lineasConCadena = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(cad)) {
                    lineasConCadena.add(linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return lineasConCadena;
    }

    // FUNCIÓN 8
    public static List<String> obtenerPalabrasUnicas(String nombreFichero) {
        Set<String> palabrasUnicas = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                
                for (String palabra : palabras) {
                    palabra = palabra.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚ]", "").toLowerCase();
                    if (!palabra.isEmpty()) {
                        palabrasUnicas.add(palabra);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return new ArrayList<>(palabrasUnicas);
    }

    // FUNCIÓN 9
    public static double longitudMediaLineas(String nombreFichero, String sep) {
        int totalLongitud = 0;
        int numLineas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(sep);
                totalLongitud += palabras.length;
                numLineas++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return numLineas == 0 ? 0 : (double) totalLongitud / numLineas;
    }

    public static void main(String[] args) {
        // Test 6
        String nombreArchivo1 = "resources/archivo_palabras.txt";
        String separador1 = "\\s+";
        String palabraBuscada = "proyecto";
        int ocurrencias = contarPalabra(nombreArchivo1, separador1, palabraBuscada);
        System.out.println("La palabra '" + palabraBuscada + "' aparece " + ocurrencias + " veces.");

        // Test 7
        List<String> lineasEncontradas = obtenerLineasConCadena(nombreArchivo1, palabraBuscada);
        System.out.println("Líneas que contienen la palabra '" + palabraBuscada + "':");
        for (String linea : lineasEncontradas) {
            System.out.println(linea);
        }

        // Test 8
        String nombreArchivo2 = "resources/lin_quijote.txt";
        List<String> palabrasUnicas = obtenerPalabrasUnicas(nombreArchivo2);
        System.out.println("Palabras únicas en el archivo:");
        for (String palabra : palabrasUnicas) {
            System.out.println(palabra);
        }

        // Test 9
        String separador2 = "\\s+";
        double longitudMedia = longitudMediaLineas(nombreArchivo2, separador2);
        System.out.println("La longitud media de las líneas es: " + longitudMedia);
    }
}
