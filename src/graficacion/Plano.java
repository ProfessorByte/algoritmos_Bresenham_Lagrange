package graficacion;

import estructuras.Punto;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import lagrange.Lagrange;

public class Plano extends JPanel {

    Lagrange l;
    float[] polyLagrange;
    ArrayList<Punto> points;

    public Plano(float[] polyLagrange, ArrayList<Punto> points) {
        init();
        l = new Lagrange();
        this.polyLagrange = polyLagrange;
        this.points = points;
    }

    public void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(24, 165, 0));

        g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());

        paintLagrangeFunc(g, polyLagrange, -300, 300);
        paintPoints(g, points);
    }

    public void paintLagrangeFunc(Graphics g, float[] polyLagrange, double x1, double x2) {
        g.setColor(Color.black);
        for (double i = x1; i < x2; i++) {
            double y = l.calculate(polyLagrange, (float) i);
            double yp = l.calculate(polyLagrange, (float) i + 1);
            g.drawLine((int) coord_x(i), (int) coord_y(y), (int) coord_x(i + 1), (int) coord_y(yp));
        }
    }

    public void paintPoints(Graphics g, ArrayList<Punto> points) {
        g.setColor(Color.red);
        char charA = 'A';
        int x, y;
        for (int i = 0; i < points.size(); i++) {
            x = (int) coord_x(points.get(i).getX() * 15) - 3;
            y = (int) coord_y(points.get(i).getY() * 15) - 3;
            g.fillRect(x, y, 6, 6);
            g.drawString(charA + "", x, y - 5);
            charA++;
        }
    }

    private double coord_x(double x) {
        double real_x = x + this.getWidth() / 2;
        return real_x;
    }

    private double coord_y(double y) {
        double real_y = -y + this.getHeight() / 2;

        return (real_y);
    }
}
