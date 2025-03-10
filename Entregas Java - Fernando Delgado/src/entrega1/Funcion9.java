package entrega1;

import java.io.*;

public class Funcion9 {

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
        String nombreArchivo = "resources/lin_quijote.txt";
        String separador = "\\s+";
        double longitudMedia = longitudMediaLineas(nombreArchivo, separador);

        System.out.println("La longitud media de las líneas es: " + longitudMedia);
    }
}
