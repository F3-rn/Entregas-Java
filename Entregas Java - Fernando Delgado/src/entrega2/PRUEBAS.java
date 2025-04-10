package entrega2;

import java.util.*;

public class PRUEBAS {
    public static void main(String[] args) {
        System.out.println("===== PRUEBAS DE ESTRUCTURAS LINEALES =====\n");

        //----- Prueba de ListaOrdenada -----
          System.out.println("----- Prueba de ListaOrdenada -----");
          ListaOrdenada<Integer> lista = ListaOrdenada.of(Integer::compareTo);
          System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3");
          lista.add(5);
          lista.add(2);
          lista.add(8);
          lista.add(1);
          lista.add(3);
          System.out.println("Elementos en la lista: " + lista.elements());
          System.out.println("Tamaño de la lista: " + lista.size());
          System.out.println("Eliminando el primer elemento: " + lista.remove());
          System.out.println("Elementos después de eliminar: " + lista.elements());
          lista.addAll(List.of(4, 6, 7));
          System.out.println("Añadiendo elementos en lote: 4, 6, 7");
          System.out.println("Elementos después de añadir lote: " + lista.elements());
          System.out.println("Eliminando todos los elementos: " + lista.removeAll());
          System.out.println("¿Está vacía? " + lista.isEmpty());

          System.out.println("\nPrueba con strings:");
          ListaOrdenada<String> listaStrings = ListaOrdenada.of(String::compareTo);
          listaStrings.addAll(List.of("Pedro", "Juan", "Pepe", "Carlos"));
          System.out.println("Elementos ordenados: " + listaStrings.elements());

          //----- Prueba de ListaOrdenadaSinRepeticion -----
          System.out.println("\n----- Prueba de ListaOrdenadaSinRepeticion -----");
          ListaOrdenadaSinRepeticion<Integer> listaSinRep = ListaOrdenadaSinRepeticion.of(Integer::compareTo);
          System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
          listaSinRep.addAll(List.of(5, 2, 8, 1, 3, 5, 2));
          System.out.println("Elementos en la lista: " + listaSinRep.elements());
          System.out.println("Tamaño de la lista: " + listaSinRep.size());
          System.out.println("Se esperan 5 elementos únicos ordenados");
          System.out.println("Eliminando el primer elemento: " + listaSinRep.remove());
          System.out.println("Elementos después de eliminar: " + listaSinRep.elements());
          listaSinRep.addAll(List.of(4, 6, 7, 4));
          System.out.println("Añadiendo elementos en lote: 4, 6, 7, 4");
          System.out.println("Elementos después de añadir lote: " + listaSinRep.elements());
          System.out.println("Se espera que el 4 aparezca solo una vez");

          //----- Prueba de Cola (FIFO) -----
          System.out.println("\n----- Prueba de Cola (FIFO) -----");
          Cola<String> cola = Cola.of();
          System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
          cola.add("primero");
          cola.add("segundo");
          cola.add("tercero");
          System.out.println("Elementos en la cola: " + cola.elements());
          System.out.println("Tamaño de la cola: " + cola.size());
          System.out.println("Desencolando elementos:");
          try {
           System.out.println("Desencolado: " + cola.remove());
           System.out.println("Cola restante: " + cola.elements());
           System.out.println("Desencolado: " + cola.remove());
           System.out.println("Cola restante: " + cola.elements());
           System.out.println("Desencolado: " + cola.remove());
           System.out.println("Cola restante: " + cola.elements());
           System.out.println("¿Está vacía? " + cola.isEmpty());
           System.out.print("Excepción correctamente lanzada al intentar desencolar de una cola vacía: ");
           cola.remove();
          } catch (Exception e) {
           System.out.println("No se puede eliminar de un agregado vacío.");
          }

          //----- Prueba de Pila (LIFO) -----
          System.out.println("\n----- Prueba de Pila (LIFO) -----");
          Pila<Double> pila = new Pila<>();
          System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
          pila.add(1.1);
          pila.add(2.2);
          pila.add(3.3);
          System.out.println("Elementos en la pila: " + pila.elements());
          System.out.println("Tamaño de la pila: " + pila.size());
          System.out.println("Elemento en el tope: " + pila.top());
          System.out.println("Desapilando elementos:");
          try {
           System.out.println("Desapilado: " + pila.remove());
           System.out.println("Pila restante: " + pila.elements());
           System.out.println("Desapilado: " + pila.remove());
           System.out.println("Pila restante: " + pila.elements());
           System.out.println("Desapilado: " + pila.remove());
           System.out.println("Pila restante: " + pila.elements());
           System.out.println("¿Está vacía? " + pila.isEmpty());
           System.out.print("Excepción correctamente lanzada al intentar acceder al tope de una pila vacía: ");
           System.out.println(pila.top());
          } catch (Exception e) {
           System.out.println("La pila está vacía.");
          }

          //----- Prueba de ColaPrioridad -----
          System.out.println("\n----- Prueba de ColaPrioridad -----");
          ColaPrioridad<String, Integer> colaPrio = ColaPrioridad.ofPriority();
          System.out.println("Añadiendo elementos con prioridad:");
          colaPrio.add("Crítico", 1);
          colaPrio.add("Normal", 3);
          colaPrio.add("Urgente", 2);
          colaPrio.add("Bajo", 4);
          System.out.println("Elementos en la cola por prioridad: " + colaPrio.valuesAsList());
          System.out.println("Elementos con sus prioridades: " + colaPrio.elements());
          System.out.println("Tamaño de la cola: " + colaPrio.size());
          System.out.println("Cambiando la prioridad de 'Normal' de 3 a 1:");
          colaPrio.decreasePriority("Normal", 1);
          System.out.println("Elementos con prioridad actualizada: " + colaPrio.valuesAsList());
          System.out.println("Desencolando elementos por prioridad:");
          try {
           System.out.println("Desencolado: " + colaPrio.removeValue());
           System.out.println("Cola restante: " + colaPrio.valuesAsList());
           System.out.println("Desencolado: " + colaPrio.removeValue());
           System.out.println("Cola restante: " + colaPrio.valuesAsList());
           System.out.println("Desencolado: " + colaPrio.removeValue());
           System.out.println("Cola restante: " + colaPrio.valuesAsList());
           System.out.println("Desencolado: " + colaPrio.removeValue());
           System.out.println("Cola restante: " + colaPrio.valuesAsList());
           System.out.println("¿Está vacía? " + colaPrio.isEmpty());
           System.out.print("Excepción correctamente lanzada al intentar desencolar de una cola vacía: ");
           colaPrio.removeValue();
          } catch (Exception e) {
           System.out.println("No se puede eliminar de una cola vacía.");
          }

          System.out.println("\nPrueba con addAll:");
          colaPrio.addAllValues(List.of("Tarea A", "Tarea B", "Tarea C"), 2);
          colaPrio.add("Tarea Urgente", 1);
          System.out.println("Después de añadir 'Tarea Urgente' con prioridad 1: " + colaPrio.valuesAsList());

          System.out.println("\n===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }
}
