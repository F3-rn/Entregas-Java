package entrega1;
public class Funcion1 {
    public static long calcularProductorio(int n, int k) {
        if (n <= k) {
            throw new IllegalArgumentException("n debe ser mayor que k");
        }
        
        long producto = 1;
        for (int i = 0; i <= k; i++) {
            producto *= (n - i + 1);
        }
        
        return producto;
    }

    public static void main(String[] args) {
        int n = 6;
        int k = 3;
        long resultado = calcularProductorio(n, k);
        System.out.println("El resultado es: " + resultado);
    }
}