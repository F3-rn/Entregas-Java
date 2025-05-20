package entrega6test;

import entrega6preguntas.PreguntasBancos;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class TestPreguntasBancos {

    public static void main(String[] args) throws IOException {
        int edadLimite = 70;
        double a = 10000.0;
        double b = 80000.0;
        LocalDate fechaLimite = LocalDate.of(2015, 1, 1);

        System.out.println("Test valorTotalPrestamos");

        Map<String, Double> resultadoImp = PreguntasBancos.valorTotalPrestamosImperativo(edadLimite, a, b, fechaLimite);
        Map<String, Double> resultadoFunc = PreguntasBancos.valorTotalPrestamosFuncional(edadLimite, a, b, fechaLimite);

        System.out.println("Imperativo: Valor total de préstamos por cliente:");
        resultadoImp.forEach((cliente, total) -> System.out.println("Cliente: " + cliente + ", total: " + total));

        System.out.println("\nFuncional: Valor total de préstamos por cliente:");
        resultadoFunc.forEach((cliente, total) -> System.out.println("Cliente: " + cliente + ", total: " + total));

        try {
            System.out.println("\nProbando con parámetros inválidos (edadLimite=15):");
            PreguntasBancos.valorTotalPrestamosImperativo(15, a, b, fechaLimite);
        } catch (IllegalArgumentException e) {
            System.out.println("Imperativo capturó excepción: " + e.getMessage());
        }

        try {
            System.out.println("\nProbando con parámetros inválidos (a=-5.0):");
            PreguntasBancos.valorTotalPrestamosFuncional(edadLimite, -5.0, b, fechaLimite);
        } catch (IllegalArgumentException e) {
            System.out.println("Funcional capturó excepción: " + e.getMessage());
        }
    }
}
