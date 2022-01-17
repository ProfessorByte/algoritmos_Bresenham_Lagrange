package estructuras;

public class Punto implements Comparable<Punto> {

    private int x;
    private int y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Punto p) {
        int ans;
        if (x < p.getX()) {
            ans = -1;
        } else if (x == p.getX()) {
            ans = 0;
        } else {
            ans = 1;
        }
        return ans;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
