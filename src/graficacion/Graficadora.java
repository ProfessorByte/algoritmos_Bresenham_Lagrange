package graficacion;

import estructuras.Punto;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graficadora {

    public static Plano plano;

    public static void startGraph(float[] polyLagrange, ArrayList<Punto> points) {

        JFrame frame = new JFrame("Graficador");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300, 300, 900, 900);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        plano = new Plano(polyLagrange, points);
        frame.add(plano, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
