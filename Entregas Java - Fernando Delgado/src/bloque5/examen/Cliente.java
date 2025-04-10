package bloque5.examen;

import java.util.Objects;

public final class Cliente implements Comparable<Cliente> {

    private final String nombre;
    private final int antiguedad;

    public Cliente(String nombre, int antiguedad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (antiguedad < 0) {
            throw new IllegalArgumentException("La antiguedad no puede ser negativa");
        }
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    public static Cliente of(String nombre, int antiguedad) {
        return new Cliente(nombre, antiguedad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente other = (Cliente) obj;
        return antiguedad == other.antiguedad &&
               Objects.equals(nombre, other.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, antiguedad);
    }

    @Override
    public int compareTo(Cliente other) {
        return Integer.compare(this.antiguedad, other.antiguedad);
    }

    @Override
    public String toString() {
        return String.format("Cliente[nombre=%s, antigüedad=%d]", nombre, antiguedad);
    }
}
