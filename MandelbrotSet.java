import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotSet extends JFrame {
    private final int MAX_ITER = 5000;
    private final double ZOOM = 200;
    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    public MandelbrotSet() {
        super("Mandelbrot Set");
        setBounds(100, 100, WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                float a = (float) ((x - WIDTH / 2) / ZOOM);
                float b = (float) ((y - HEIGHT / 2) / ZOOM);

                float aa = a;
                float bb = b;

                int iter = MAX_ITER;
                while (a * a + b * b < 4 && iter > 0) {
                    float tmp = a * a - b * b + aa;
                    b = 2 * a * b + bb;
                    a = tmp;
                    iter--;
                }

                int color = iter | (iter << 8);
                image.setRGB(x, y, color);
            }
        }
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        new MandelbrotSet().setVisible(true);
    }
}