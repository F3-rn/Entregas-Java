package Denfensa1;

public class combinatorioSinMultiplosDeTres {

    public static long combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k");
        }
        if (n <= 0 || k <= 0) {
            throw new IllegalArgumentException("n y k deben ser positivos");
        }

        long numerador = 1;
        long denominador = 1;
        for (int i = n; i > n - k; i--) {
            if (i % 3 != 0) {
                numerador *= i;
            }
        }
        for (int i = 1; i <= k; i++) {
            if (i % 3 != 0) {
                denominador *= i;
            }
        }

        return numerador / denominador;
    }
//CASOS DE PRUEBA
    public static void main(String[] args) {
        try {
            System.out.println("Prueba 1 (n = 5, k = 2): " + combinatorioSinMultiplosDeTres(5, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        try {
            System.out.println("Prueba 6 (n < k): " + combinatorioSinMultiplosDeTres(3, 4));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }

        try {
            System.out.println("Prueba 8 (k <= 0): " + combinatorioSinMultiplosDeTres(5, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}
