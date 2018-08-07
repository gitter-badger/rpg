package de.computercamp.rpg;

public class Vector2D {

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x = 0;
    public int y = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (x != vector2D.x) return false;
        return y == vector2D.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
