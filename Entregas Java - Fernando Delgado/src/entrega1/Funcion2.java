package entrega1;

public class Funcion2 {
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

    public static void main(String[] args) {
        double a1 = 2;
        double r = 3;
        int k = 4;

        double resultado = calcularProductoGeometrico(a1, r, k);
        System.out.println("El producto de los primeros " + k + " términos es: " + resultado);
    }
}
