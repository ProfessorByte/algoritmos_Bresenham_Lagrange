package bresenham;

import javax.swing.JPanel;
import java.awt.Graphics;
import static java.lang.Math.abs;

public class Screen extends JPanel {

    int xx;
    int yy;
    int xxx;
    int yyy;
    int i;

    int grosor = 10;
    boolean segmentada = false;

    public Screen(int x1, int y1, int x2, int y2, int g, boolean seg) {
        xx = x1;
        yy = y1;
        xxx = x2 * grosor + grosor;
        yyy = y2 * grosor + grosor;
        grosor = g;
        segmentada = seg;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void paint(Graphics g) {
        double n, dx, dy, t;
        int sx, sy, cambio, i, x, y;

        int x1 = xx;
        int y1 = yy;

        int x2 = xxx;
        int y2 = yyy;

        int contador = 0;

        x = x1;
        y = y1;
        dx = abs((x2 - x1));
        dy = abs((y2 - y1));

        sx = signo(x2 - x1);
        sy = signo(y2 - y1);

        if (dy > dx) {
            t = dx;
            dx = dy;
            dy = t;
            cambio = 1;
        } else {
            cambio = 0;
        }

        n = 2 * dy - dx;
        for (i = 1; i <= dx; i++) {
            if (contador < 3 * (grosor / 5) && segmentada) {
                g.fillRect(x, y, grosor, grosor);
                contador++;
            } else if (contador < grosor && segmentada) {
                contador++;
            } else if (segmentada) {
                contador = 0;
            } else {
                g.fillRect(x, y, grosor, grosor);
            }

            if (n >= 0) {
                if (cambio == 1) {
                    x += sx;
                } else {
                    y += sy;
                }
                n -= 2 * dx;
            }
            if (cambio == 1) {
                y += sy;
            } else {
                x += sx;
            }
            n += 2 * dy;
        }
    }

    public int signo(int numero) {
        int res;
        if (numero < 0) {
            res = -3;
        } else if (numero > 0) {
            res = 3;
        } else {
            res = 0;
        }
        return res;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public int getGrosor() {
        return grosor;
    }

    public void setSeg(boolean seg) {
        segmentada = seg;
    }
}
