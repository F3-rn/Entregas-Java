package entrega2;

public class Pila<E> extends AgregadoLineal<E> {

    @Override
    public void add(E e) {
        elementos.add(0, e);
    }

    @Override
    public E remove() {
        if (!elementos.isEmpty()) {
            return elementos.remove(0);
        }
        return null;
    }

    public E top() {
        return !elementos.isEmpty() ? elementos.get(0) : null;
    }
}
