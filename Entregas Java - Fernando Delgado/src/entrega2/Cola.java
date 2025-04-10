package entrega2;

public class Cola<E> extends AgregadoLineal<E> {

    public static <E> Cola<E> of() {
        return new Cola<>();
    }

    @Override
    public void add(E e) {
        elementos.add(e);
    }

    @Override
    public E remove() {
        if (!elementos.isEmpty()) {
            return elementos.remove(0);
        }
        return null;
    }
}
