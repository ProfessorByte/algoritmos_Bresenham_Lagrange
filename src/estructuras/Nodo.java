package estructuras;

public class Nodo {

    Punto punto;
    Nodo izq;
    Nodo der;

    Nodo(Punto punto) {
        this.punto = punto;
        izq = null;
        der = null;
    }

    public Punto getPunto() {
        return punto;
    }
}
