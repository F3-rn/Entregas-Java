package entrega2;

import java.util.*;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
    private final Comparator<E> comparator;

    public ListaOrdenada(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
        return new ListaOrdenada<>(comparator);
    }

    private int indexOrder(E e) {
        int index = 0;
        for (; index < elementos.size(); index++) {
            if (comparator.compare(e, elementos.get(index)) < 0) {
                break;
            }
        }
        return index;
    }

    @Override
    public void add(E e) {
        int index = indexOrder(e);
        elementos.add(index, e);
    }
}
