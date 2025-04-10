package entrega2;

import java.util.*;

public abstract class AgregadoLineal<E> {
    protected List<E> elementos = new ArrayList<>();

    public int size() {
        return elementos.size();
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }

    public List<E> elements() {
        return new ArrayList<>(elementos);
    }

    public abstract void add(E e);

    public void addAll(List<E> list) {
        for (E e : list) {
            add(e);
        }
    }

    public E remove() {
        if (!elementos.isEmpty()) {
            return elementos.remove(0);
        }
        return null;
    }

    public List<E> removeAll() {
        List<E> removed = new ArrayList<>(elementos);
        elementos.clear();
        return removed;
    }
}
