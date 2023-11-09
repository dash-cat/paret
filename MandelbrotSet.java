import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MandelbrotSet extends JFrame {
    private final int MAX_ITER = 5000;
    private final double ZOOM = 200;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private double zoom = 200;
    private boolean color = false;
    private BufferedImage image;

    public MandelbrotSet() {
        super("Mandelbrot Set");
        setBounds(100, 100, WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton button = new JButton("Change Color");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = !color;
                repaint();
            }
        });
        add(button, BorderLayout.SOUTH);

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoom -= 2; // уменьшаем zoom на 2 вместо 1
                repaint();
            }
        });
        timer.start();

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void paint(Graphics g) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                float a = (float) ((x - WIDTH / 2) / zoom);
                float b = (float) ((y - HEIGHT / 2) / zoom);

                float aa = a;
                float bb = b;

                int iter = MAX_ITER;
                while (a * a + b * b < 4 && iter > 0) {
                    float tmp = a * a - b * b + aa;
                    b = 2 * a * b + bb;
                    a = tmp;
                    iter--;
                }

                int colorValue = color ? iter | (iter << 8) : iter | (iter << 16);
                image.setRGB(x, y, colorValue);
            }
        }

        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        new MandelbrotSet().setVisible(true);
    }
}