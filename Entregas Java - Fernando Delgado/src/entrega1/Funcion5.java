package entrega1;

public class Funcion5 {

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
        float valorInicial = 1.0f;
        float epsilon = 0.0001f;

        float raiz = metodoDeNewton(valorInicial, epsilon);

        System.out.println("La raíz aproximada es: " + raiz);
    }
}
