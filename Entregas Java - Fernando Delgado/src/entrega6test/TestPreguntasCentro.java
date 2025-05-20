package entrega6test;

import entrega6preguntas.PreguntasCentro;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestPreguntasCentro {

    public static void main(String[] args) {
        testPromedioEdadProfesores();
        testGrupoMayorDiversidadEdad();
        testAlumnoMasMatriculas();
        testRangosEdadPorAlumno();
        testNombreProfesorMasGrupos();
        testNombresAlumnosMayorNotaImperativo();
        testNombresAlumnosMayorNotaFuncional();
    }

    static void testPromedioEdadProfesores() {
        try {
            String dniAlumno = "72842943B";
            double promedioImp = PreguntasCentro.promedioEdadProfesoresImperativo(dniAlumno);
            double promedioFunc = PreguntasCentro.promedioEdadProfesoresFuncional(dniAlumno);
            System.out.println("Promedio Edad Profesores (Imperativo): " + promedioImp);
            System.out.println("Promedio Edad Profesores (Funcional): " + promedioFunc);
        } catch (IOException e) {
            System.err.println("Error en testPromedioEdadProfesores: " + e.getMessage());
        }
    }

    static void testGrupoMayorDiversidadEdad() {
        try {
            int grupoImp = PreguntasCentro.grupoMayorDiversidadEdadImperativo();
            int grupoFunc = PreguntasCentro.grupoMayorDiversidadEdadFuncional();
            System.out.println("Grupo Mayor Diversidad Edad (Imperativo): " + grupoImp);
            System.out.println("Grupo Mayor Diversidad Edad (Funcional): " + grupoFunc);
        } catch (IOException e) {
            System.err.println("Error en testGrupoMayorDiversidadEdad: " + e.getMessage());
        }
    }

    static void testAlumnoMasMatriculas() {
        try {
            String alumnoImp = PreguntasCentro.alumnoMasMatriculasImperativo();
            String alumnoFunc = PreguntasCentro.alumnoMasMatriculasFuncional();
            System.out.println("Alumno con más matrículas (Imperativo): " + alumnoImp);
            System.out.println("Alumno con más matrículas (Funcional): " + alumnoFunc);
        } catch (IOException e) {
            System.err.println("Error en testAlumnoMasMatriculas: " + e.getMessage());
        }
    }

    static void testRangosEdadPorAlumno() {
        try {
            String rangos = "20 - 23, 24 - 26";

            Map<String, String> resultadoImp = PreguntasCentro.rangosEdadPorAlumnoImperativo(rangos);
            Map<String, String> resultadoFunc = PreguntasCentro.rangosEdadPorAlumnoFuncional(rangos);

            System.out.println("Rangos Edad por Alumno (Imperativo):");
            resultadoImp.forEach((nombre, rango) ->
                System.out.println("Alumno: " + nombre + " -> Rango: " + rango)
            );

            System.out.println("Rangos Edad por Alumno (Funcional):");
            resultadoFunc.forEach((nombre, rango) ->
                System.out.println("Alumno: " + nombre + " -> Rango: " + rango)
            );

        } catch (IOException e) {
            System.err.println("IOException en testRangosEdadPorAlumno: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Formato de rangos incorrecto: " + e.getMessage());
        }
    }

    static void testNombreProfesorMasGrupos() {
        try {
            int edadMin = 25;
            int edadMax = 40;
            String profesorImp = PreguntasCentro.nombreProfesorMasGruposImperativo(edadMin, edadMax);
            String profesorFunc = PreguntasCentro.nombreProfesorMasGruposFuncional(edadMin, edadMax);
            System.out.println("Profesor con más grupos (Imperativo): " + profesorImp);
            System.out.println("Profesor con más grupos (Funcional): " + profesorFunc);
        } catch (IOException e) {
            System.err.println("Error en testNombreProfesorMasGrupos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error en validación de edades en nombreProfesorMasGrupos: " + e.getMessage());
        }
    }

    static void testNombresAlumnosMayorNotaImperativo() {
        try {
            List<String> alumnosImp = PreguntasCentro.nombresAlumnosMayorNotaImperativo(3, 1997);
            System.out.println("Alumnos con mayor nota (Imperativo):");
            alumnosImp.forEach(nombre -> System.out.println(" - " + nombre));
        } catch (IOException e) {
            System.err.println("Error en testNombresAlumnosMayorNotaImperativo: " + e.getMessage());
        }
    }

    static void testNombresAlumnosMayorNotaFuncional() {
        try {
            List<String> alumnosFunc = PreguntasCentro.nombresAlumnosMayorNotaFuncional(3, 1997);
            System.out.println("Alumnos con mayor nota (Funcional):");
            alumnosFunc.forEach(nombre -> System.out.println(" - " + nombre));
        } catch (IOException e) {
            System.err.println("Error en testNombresAlumnosMayorNotaFuncional: " + e.getMessage());
        }
    }

}

