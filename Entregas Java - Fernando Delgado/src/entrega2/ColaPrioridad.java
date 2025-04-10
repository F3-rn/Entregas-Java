package entrega2;

import java.util.*;

public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {

    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }

    @Override
    public void add(PriorityElement<E, P> element) {
        elementos.add(element);
        elementos.sort(Comparator.comparing(PriorityElement::priority));
    }

    public void add(E value, P priority) {
        add(new PriorityElement<>(value, priority));
    }

    public List<E> valuesAsList() {
        List<E> values = new ArrayList<>();
        for (PriorityElement<E, P> element : elementos) {
            values.add(element.value());
        }
        return values;
    }

    public void decreasePriority(E value, P newPriority) {
        for (int i = 0; i < elementos.size(); i++) {
            PriorityElement<E, P> current = elementos.get(i);
            if (current.value().equals(value) && current.priority().compareTo(newPriority) > 0) {
                elementos.set(i, new PriorityElement<>(value, newPriority));
                elementos.sort(Comparator.comparing(PriorityElement::priority));
                return;
            }
        }
    }

    public E removeValue() {
        PriorityElement<E, P> removed = remove();
        return (removed != null) ? removed.value() : null;
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(value, priority);
        }
    }
}
