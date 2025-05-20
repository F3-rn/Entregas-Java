package entrega6test;

import java.time.LocalDateTime;

import entrega6preguntas.PreguntasAeropuertos;

public class TestPreguntasAeropuertos {

    public static void main(String[] args) {
        try {
            LocalDateTime fechaInicio = LocalDateTime.of(2020, 4, 1, 0, 0);
            LocalDateTime fechaFin = LocalDateTime.of(2020, 5, 1, 0, 0);

            System.out.println("Test Imperativo");
            String ciudadImperativa = PreguntasAeropuertos.ciudadAeropuertoMayorFacturacionImperativo(fechaInicio, fechaFin);
            System.out.println("Ciudad con mayor facturación (Imperativo): " + ciudadImperativa);

            System.out.println("\nTest Funcional");
            String ciudadFuncional = PreguntasAeropuertos.ciudadAeropuertoMayorFacturacionFuncional(fechaInicio, fechaFin);
            System.out.println("Ciudad con mayor facturación (Funcional): " + ciudadFuncional);

            if (ciudadImperativa != null && ciudadImperativa.equals(ciudadFuncional)) {
                System.out.println("\nAmbos métodos devolvieron el mismo resultado.");
            } else {
                System.out.println("\nLos métodos devolvieron resultados diferentes.");
            }

        } catch (Exception e) {
            System.err.println("Error al ejecutar el test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
