package estructuras;

import java.util.ArrayList;

public class Arbol {

    Nodo raiz;
    ArrayList<Punto> lista;

    public Arbol() {
        raiz = null;
        lista = new ArrayList<>();

    }

    public void agregar(Punto punto) {
        raiz = agregarR(raiz, punto);
    }

    private Nodo agregarR(Nodo actual, Punto punto) {
        if (actual == null) {
            return new Nodo(punto);
        }
        int aux = actual.getPunto().compareTo(punto);
        if (aux == 1) {
            actual.izq = agregarR(actual.izq, punto);
        } else if (aux == -1) {
            actual.der = agregarR(actual.der, punto);
        }
        return actual;
    }

    public void dfs(Nodo nodo) {
        if (nodo != null) {
            dfs(nodo.izq);
            lista.add(nodo.getPunto());
            dfs(nodo.der);
        }
    }

    public ArrayList<Punto> obtenerLista() {
        lista = new ArrayList<>();
        dfs(raiz);
        return lista;
    }

    public void reiniciar() {
        raiz = null;
        lista = new ArrayList<>();
    }
}
