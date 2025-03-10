package entrega1;

import java.io.*;
import java.util.*;

public class Funcion8 {

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

    public static void main(String[] args) {
        String nombreArchivo = "resources/lin_quijote.txt";

        List<String> palabrasUnicas = obtenerPalabrasUnicas(nombreArchivo);

        System.out.println("Palabras únicas en el archivo:");
        for (String palabra : palabrasUnicas) {
            System.out.println(palabra);
        }
    }
}
