package bresenham;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class MenuBresenham implements ActionListener {

    JFrame f = new JFrame("Bresenham");
    int xi, yi, xf, yf;
    JTextField tx, ty, txx, tyy;
    JButton x1, x3, x5, x7, cre;
    JCheckBox punteado;
    int g = 5;
    boolean seg = false;

    public MenuBresenham() {

        JPanel panel = new JPanel(null);
        JLabel label = new JLabel();
        panel.add(label);
        f.add(panel);
        label.setLocation(100, 50);
        label.setText("ALGORITMO DE BRESENHAM");
        label.setSize(label.getPreferredSize());

        JLabel coords = new JLabel("Coordenadas:");
        coords.setLocation(100, 75);
        coords.setSize(coords.getPreferredSize());
        panel.add(coords);

        tx = new JTextField();
        tx.setBounds(105, 100, 30, 20);
        panel.add(tx);

        ty = new JTextField();
        ty.setBounds(145, 100, 30, 20);
        panel.add(ty);

        txx = new JTextField();
        txx.setBounds(185, 100, 30, 20);
        panel.add(txx);

        tyy = new JTextField();
        tyy.setBounds(225, 100, 30, 20);
        panel.add(tyy);

        JLabel xy = new JLabel(" x0         y0         x1          y1");
        xy.setLocation(111, 120);
        xy.setSize(xy.getPreferredSize());
        panel.add(xy);

        JLabel gro = new JLabel("Grosor: ");
        gro.setLocation(100, 140);
        gro.setSize(gro.getPreferredSize());
        panel.add(gro);

        x1 = new JButton("●");
        x1.setFont(new Font("Arial", Font.PLAIN, 15));
        x1.setBounds(105, 165, 50, 25);
        x1.addActionListener(this);
        panel.add(x1);

        x3 = new JButton("●");
        x3.setFont(new Font("Arial", Font.PLAIN, 20));
        x3.setBounds(165, 165, 50, 25);
        x3.addActionListener(this);
        panel.add(x3);

        x5 = new JButton("●");
        x5.setFont(new Font("Arial", Font.PLAIN, 30));
        x5.setBounds(105, 195, 55, 25);
        x5.addActionListener(this);
        panel.add(x5);

        x7 = new JButton("●");
        x7.setFont(new Font("Arial", Font.PLAIN, 40));
        x7.setBounds(170, 195, 60, 25);
        x7.addActionListener(this);
        panel.add(x7);

        punteado = new JCheckBox("Punteado");
        punteado.setBounds(100, 230, 100, 40);
        panel.add(punteado);

        cre = new JButton("CREAR");
        cre.setBounds(100, 270, 190, 40);
        cre.addActionListener(this);
        panel.add(cre);

        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        punteado.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                seg = (e.getStateChange() == 1 ? true : false);
            }
        });

        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (tx.getText() == "" || ty.getText() == "" || txx.getText() == ""
                || tyy.getText() == "") {
            xi = 0;
            yi = 0;
            xf = 0;
            yf = 0;
        } else {
            xi = Integer.parseInt(tx.getText());
            yi = Integer.parseInt(ty.getText());
            xf = Integer.parseInt(txx.getText());
            yf = Integer.parseInt(tyy.getText());
        }

        if (e.getSource() == x1) {

        } else if (e.getSource() == x3) {
            g += 3;
        } else if (e.getSource() == x5) {
            g += 8;
        } else if (e.getSource() == x7) {
            g += 12;
        } else if (e.getSource() == cre) {
            Screen s = new Screen(xi, yi, xf, yf, g, seg);
            JFrame fr = new JFrame("Resultado");
            fr.setSize(1200, 1800);
            fr.setResizable(false);
            fr.setLocationRelativeTo(null);
            fr.setLayout(new GridLayout(1, 1, 0, 0));
            fr.add(s);
            fr.setVisible(true);
            g = 5;
        }
    }
}
