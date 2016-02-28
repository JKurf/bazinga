import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsClass {
    JFrame frame;
    JPanel pane;
    BufferedImage image;
    Graphics g;

    GraphicsClass() {
        //this.setSize(800, 600);
        //this.setVisible(true);
    }

    private static JFrame buildFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
        return frame;
    }

    public void Init() throws IOException {
        frame = buildFrame();

        image = ImageIO.read(new File("Data/TestImg.png"));

        /*pane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };*/

        pane = drawImg(image, 0, 0);
        frame.add(pane);
    }

    public JPanel drawImg(BufferedImage img, int x ,int y) {
        JPanel scrn = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, x, y, null);
            }
        };

        return scrn;
    }

    public void Quit() {

    }

    public void Update() {
        frame.validate();
        frame.repaint();
    }

    public void DrawSquare() {

    }
}