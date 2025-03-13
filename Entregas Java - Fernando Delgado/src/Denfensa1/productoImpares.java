package Denfensa1;

public class productoImpares {
    
    public static long productoImpares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El número debe ser mayor que 0");
        }
        long producto = 1;
        int contador = 0;
        int numero = 1;
        
        while (contador < n) {
            producto *= numero;
            numero += 2;
            contador++;
        }
        
        return producto;
    }
//CASOS DE PRUEBA
    public static void main(String[] args) {
        int[] casosDePrueba = {4, 5};
        long[] resultadosEsperados = {15, 105};
        
        for (int i = 0; i < casosDePrueba.length; i++) {
            try {
                long resultado = productoImpares(casosDePrueba[i]);
                System.out.println("productoImpares(" + casosDePrueba[i] + ") = " + resultado);
                assert resultado == resultadosEsperados[i] : "Error en la prueba con n=" + casosDePrueba[i];
            } catch (Exception e) {
                System.out.println("Error con entrada " + casosDePrueba[i] + ": " + e.getMessage());
            }
        }
        int[] casosInvalidos = {0, -1};
        
        for (int caso : casosInvalidos) {
            try {
                productoImpares(caso);
                System.out.println("Error: No se lanzó excepción para n=" + caso);
            } catch (IllegalArgumentException e) {
                System.out.println("Correcto: Excepción capturada para n=" + caso + " -> " + e.getMessage());
            }
        }
    }
}