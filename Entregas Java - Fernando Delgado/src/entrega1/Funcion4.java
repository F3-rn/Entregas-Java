package entrega1;

public class Funcion4 {

    public static long factorial(int num) {
        long resultado = 1;
        for (int i = 1; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static long combinatorio(int n, int k) {
        if (k > n) {
            return 0;
        }
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    public static double calcularS(int n, int k) {
        double sumatorio = 0;

        for (int i = 0; i < k; i++) {
            long combinatorioValor = combinatorio(k + 1, i + 1);
            double termino = Math.pow(-1, i) * combinatorioValor * Math.pow(k - i, n);
            sumatorio += termino;
        }

        return sumatorio / factorial(k);
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 3;

        double resultado = calcularS(n, k);
        System.out.println("El valor de S(" + n + ", " + k + ") es: " + resultado);
    }
}
