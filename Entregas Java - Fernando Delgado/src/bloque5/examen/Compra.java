package bloque5.examen;

import java.util.Objects;

public final class Compra {

    private final Cliente cliente;
    private final String descripcion;
    private final double importe;

    public Compra(Cliente cliente, String descripcion, double importe) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede estar vacío");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("El importe no puede ser negativo");
        }
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public static Compra of(Cliente cliente, String descripcion, double importe) {
        return new Compra(cliente, descripcion, importe);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Compra)) return false;
        Compra other = (Compra) obj;
        return Double.compare(importe, other.importe) == 0 &&
               Objects.equals(cliente, other.cliente) &&
               Objects.equals(descripcion, other.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, descripcion, importe);
    }

    @Override
    public String toString() {
        return String.format("Compra [Nombre de cliente= %s, descripción= %s, importe= %.2f €]",
                cliente.getNombre(), descripcion, importe);
    }
}
