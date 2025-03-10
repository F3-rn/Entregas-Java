package entrega1;

import java.io.*;

public class Funcion6 {

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

    public static void main(String[] args) {
        String nombreArchivo = "resources/archivo_palabras.txt";
        String separador = "\\s+";
        String palabraBuscada = "proyecto";

        int ocurrencias = contarPalabra(nombreArchivo, separador, palabraBuscada);

        System.out.println("La palabra '" + palabraBuscada + "' aparece " + ocurrencias + " veces.");
    }
}
