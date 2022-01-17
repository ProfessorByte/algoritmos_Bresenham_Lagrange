package lagrange;

import estructuras.Arbol;
import estructuras.Punto;
import graficacion.Graficadora;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuLagrange implements ActionListener {

    JFrame f = new JFrame("Lagrange");
    JPanel panel;
    int xi, yi;
    JTextField tx, ty;
    JButton cre, gra, eli;
    Arbol arbol;
    JList list;
    int g;
    String funcLagrange = "";
    Lagrange l;
    JLabel fx = null;
    ArrayList<Punto> aux;

    public MenuLagrange() {
        panel = new JPanel(null);
        JLabel label = new JLabel();
        panel.add(label);
        f.add(panel);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setLocation(100, 70);
        label.setText("INTERPOLACION DE LAGRANGE");
        label.setSize(label.getPreferredSize());

        JLabel coords = new JLabel("Coordenadas:");
        coords.setLocation(100, 90);
        coords.setSize(coords.getPreferredSize());
        panel.add(coords);

        tx = new JTextField();
        tx.setBounds(125, 120, 30, 20);
        panel.add(tx);

        ty = new JTextField();
        ty.setBounds(175, 120, 30, 20);
        panel.add(ty);

        JLabel xy = new JLabel("    X              Y");
        xy.setLocation(125, 140);
        xy.setSize(xy.getPreferredSize());
        panel.add(xy);

        cre = new JButton("+");
        cre.setBounds(230, 115, 45, 30);
        cre.addActionListener(this);
        panel.add(cre);

        gra = new JButton("GRAFICAR");
        gra.setBounds(100, 180, 190, 40);
        gra.addActionListener(this);
        panel.add(gra);

        eli = new JButton("X");
        eli.setBounds(500, 70, 45, 30);
        eli.addActionListener(this);
        panel.add(eli);

        List<String> ls = new ArrayList<>();
        list = new JList<>(ls.toArray(new String[ls.size()]));
        list.setBounds(375, 70, 100, 180);
        panel.add(list);

        arbol = new Arbol();

        f.setSize(720, 400);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("".equals(tx.getText()) || "".equals(ty.getText())) {
            xi = 0;
            yi = 0;
        } else {
            xi = Integer.parseInt(tx.getText());
            yi = Integer.parseInt(ty.getText());
        }
        if (e.getSource() == cre) {
            panel.remove(list);
            arbol.agregar(new Punto(xi, yi));
            aux = arbol.obtenerLista();
            ArrayList<String> conv = new ArrayList<>();
            char a = 'A';
            l = null;
            l = new Lagrange();
            funcLagrange = l.convertToPolynomial(l.getPolynomial(getAllX(aux), getAllY(aux)));
            if (fx != null) {
                fx.setText("");
            }
            fx = null;
            fx = new JLabel(funcLagrange);
            fx.setLocation(9, 333);
            fx.setSize(fx.getPreferredSize());
            panel.add(fx);
            for (int i = 0; i < aux.size(); i++) {
                conv.add(a + " " + aux.get(i).toString());
                a++;
            }
            JList lista = new JList<>(conv.toArray(new String[conv.size()]));
            lista.setBounds(375, 70, 100, 180);
            list = lista;
            panel.add(lista);
            panel.revalidate();
            panel.repaint();

        }
        if (e.getSource() == eli) {
            panel.remove(list);
            arbol = new Arbol();
            l = new Lagrange();
            aux = new ArrayList<>();
            List<String> ls = new ArrayList<>();
            list = new JList<>(ls.toArray(new String[ls.size()]));
            list.setBounds(375, 70, 100, 180);
            panel.add(list);
            panel.revalidate();
            panel.repaint();
        }
        if (e.getSource() == gra) {
            ArrayList<Punto> trampa = new ArrayList<>();
            ArrayList<Punto> listaArbol = arbol.obtenerLista();
            for (int i = 0; i < listaArbol.size(); i++) {
                trampa.add(new Punto(listaArbol.get(i).getX() * 15, listaArbol.get(i).getY() * 15));
            }
            Graficadora.startGraph(l.getPolynomial(getAllX(trampa), getAllY(trampa)), aux);
        }
    }

    private float[] getAllX(ArrayList<Punto> arr) {
        float[] res = new float[arr.size()];
        int i;
        for (i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i).getX();
        }
        return res;
    }

    private float[] getAllY(ArrayList<Punto> arr) {
        float[] res = new float[arr.size()];
        int i;
        for (i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i).getY();
        }
        return res;
    }
}
