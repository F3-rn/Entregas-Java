package entrega6test;

import entrega6preguntas.PreguntasBiblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TestPreguntasBiblioteca {

    public static void main(String[] args) throws IOException {
        List<String> libros = Files.readAllLines(Paths.get("biblioteca/libros.txt"));

        System.out.println("Test masVecesPrestado");
        var maxPrestadoImp = PreguntasBiblioteca.masVecesPrestadoImperativo();
        var maxPrestadoFunc = PreguntasBiblioteca.masVecesPrestadoFuncional();

        System.out.println("Imperativo: Libro con más préstamos: " +
                (maxPrestadoImp != null ? maxPrestadoImp.getKey() + " con " + maxPrestadoImp.getValue() + " préstamos" : "No hay datos"));
        System.out.println("Funcional: Libro con más préstamos: " +
                (maxPrestadoFunc != null ? maxPrestadoFunc.getKey() + " con " + maxPrestadoFunc.getValue() + " préstamos" : "No hay datos"));

        System.out.println("\nTest librosPorAutor");
        List<String> autoresFiltrados = Arrays.asList("Mariano Uriarte Mínguez", "Anastasia Borrás Castillo");
        Map<String, Set<String>> filtradoImp = PreguntasBiblioteca.librosPorAutorImperativo(libros, autoresFiltrados);
        Map<String, Set<String>> filtradoFunc = PreguntasBiblioteca.librosPorAutorFuncional(libros, autoresFiltrados);

        System.out.println("Imperativo: ");
        filtradoImp.forEach((autor, titulos) -> System.out.println("  Autor: " + autor + ", títulos: " + titulos));

        System.out.println("Funcional: ");
        filtradoFunc.forEach((autor, titulos) -> System.out.println("  Autor: " + autor + ", títulos: " + titulos));
    }
}
