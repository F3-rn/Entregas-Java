package entrega2;

import java.util.*;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {

    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
        super(comparator);
    }

    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
        return new ListaOrdenadaSinRepeticion<>(comparator);
    }

    @Override
    public void add(E e) {
        if (!elements().contains(e)) {
            super.add(e);
        }
    }
}
