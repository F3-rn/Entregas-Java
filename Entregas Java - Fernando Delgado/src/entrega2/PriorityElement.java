package entrega2;

public record PriorityElement<E, P extends Comparable<P>>(E value, P priority) {}
