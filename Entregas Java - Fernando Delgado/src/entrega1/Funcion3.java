package entrega1;

public class Funcion3 {
    
    public static long factorial(int num) {
        long resultado = 1;
        for (int i = 1; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static long calcularCombinatorio(int n, int k) {
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual que k");
        }
        
        long factorialN = factorial(n);
        long factorialK = factorial(k);
        long factorialNK = factorial(n - k);
        
        return factorialN / (factorialK * factorialNK);
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 2;
        
        long resultado = calcularCombinatorio(n, k);
        System.out.println("El número combinatorio (" + n + " " + k + ") es: " + resultado);
    }
}
