package entrega1;

import java.io.*;
import java.util.*;

public class Funcion7 {
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

    public static void main(String[] args) {
        String nombreArchivo = "resources/archivo_palabras.txt";
        String palabraBuscada = "proyecto";

        List<String> lineasEncontradas = obtenerLineasConCadena(nombreArchivo, palabraBuscada);

        System.out.println("Líneas que contienen la palabra '" + palabraBuscada + "':");
        for (String linea : lineasEncontradas) {
            System.out.println(linea);
        }
    }
}
