package Denfensa1;

public class SecuenciaGeometrica {
    public static double sumaGeometricaAlternada(Double a1, Double r, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }
        if (a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos");
        }

        double suma = 0.0;
        for (int n = 1; n <= k; n++) {
            double termino = Math.pow(-1, n) * a1 * Math.pow(r, n - 1);
            suma += termino;
        }
        
        return suma;
    }
//CASOS DE PRUEBA
    public static void main(String[] args) {
        try {
            System.out.println("Prueba 1 (a1 = 3, r = 2, k = 5): " + sumaGeometricaAlternada(3.0, 2.0, 5));
            System.out.println("Prueba 2 (a1 = 5, r = 1, k = 4): " + sumaGeometricaAlternada(5.0, 1.0, 4));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        try {
            System.out.println("Prueba 5 (k <= 0): " + sumaGeometricaAlternada(3.0, 2.0, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        try {
            System.out.println("Prueba 6 (a1 <= 0): " + sumaGeometricaAlternada(-3.0, 2.0, 5));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}

