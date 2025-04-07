package entrega1;
public class Funciones {

    // FUNCIÓN 1
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

    // FUNCIÓN 2
    public static double calcularProductoGeometrico(double a1, double r, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("El número de términos k debe ser mayor que 0");
        }

        double producto = 1;
        for (int i = 0; i < k; i++) {
            producto *= a1 * Math.pow(r, i);
        }

        return producto;
    }

    // FUNCIÓN 3
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

    // FUNCIÓN 4
    public static double calcularS(int n, int k) {
        double sumatorio = 0;

        for (int i = 0; i < k; i++) {
            long combinatorioValor = calcularCombinatorio(k + 1, i + 1);
            double termino = Math.pow(-1, i) * combinatorioValor * Math.pow(k - i, n);
            sumatorio += termino;
        }

        return sumatorio / factorial(k);
    }

    // FUNCIÓN 5
    public static float funcion(float x) {
        return (x * x - 2);
    }

    public static float derivadaFuncion(float x) {
        return 2 * x;
    }

    public static float metodoDeNewton(float a, float epsilon) {
        float x_n = a;
        float x_n_plus_1;
        float error;

        do {
            x_n_plus_1 = x_n - (funcion(x_n) / derivadaFuncion(x_n));
            error = Math.abs(funcion(x_n_plus_1));
            x_n = x_n_plus_1;
        } while (error > epsilon);

        return x_n;
    }

    public static void main(String[] args) {
        // Test 1
        int n1 = 6;
        int k1 = 3;
        long resultadoProductorio = calcularProductorio(n1, k1);
        System.out.println("El resultado del productorio es: " + resultadoProductorio);

        // Test 2
        double a1 = 2;
        double r = 3;
        int k2 = 4;
        double resultadoProductoGeometrico = calcularProductoGeometrico(a1, r, k2);
        System.out.println("El producto geométrico es: " + resultadoProductoGeometrico);

        // Test 3
        int n2 = 5;
        int k3 = 2;
        long resultadoCombinatorio = calcularCombinatorio(n2, k3);
        System.out.println("El número combinatorio (" + n2 + " " + k3 + ") es: " + resultadoCombinatorio);

        // Test 4
        int n3 = 5;
        int k4 = 3;
        double resultadoS = calcularS(n3, k4);
        System.out.println("El valor de S(" + n3 + ", " + k4 + ") es: " + resultadoS);

        // Test 5
        float valorInicial = 1.0f;
        float epsilon = 0.0001f;
        float raiz = metodoDeNewton(valorInicial, epsilon);
        System.out.println("La raíz aproximada es: " + raiz);
    }
}
