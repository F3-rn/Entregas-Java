package Denfensa1;

import java.io.*;
import java.util.*;

public class filtrarLineasConsecutivas {

    public static List<String> filtrarLineasConsecutivas(String nombreArchivo, List<String> palabrasClave) {
        List<String> lineasFiltradas = new ArrayList<>();
        
        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío.");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (contienePalabrasConsecutivas(linea, palabrasClave)) {
                    lineasFiltradas.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        return lineasFiltradas;
    }
    private static boolean contienePalabrasConsecutivas(String linea, List<String> palabrasClave) {
        String[] palabras = linea.split("\\s+");
        
        for (int i = 0; i < palabras.length - 1; i++) {
            if (palabrasClave.contains(palabras[i]) && palabrasClave.contains(palabras[i + 1])) {
                return true;
            }
        }
        return false;
    }
//PRUEBA USANDDO 'archivo_palabras.txt'
    public static void main(String[] args) {
        List<String> palabrasClave = Arrays.asList("futuro", "datos", "software");

        String rutaArchivo = "resources/archivo_palabras.txt";
        
        try {
            List<String> lineas = filtrarLineasConsecutivas(rutaArchivo, palabrasClave);
            System.out.println("Líneas filtradas:");
            for (String linea : lineas) {
                System.out.println(linea);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}

