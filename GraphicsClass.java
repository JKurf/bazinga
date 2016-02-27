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

        pane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0,0, null);
            }
        };

        System.out.println("ayy");

        frame.add(pane);
    }

    public void Quit() {

    }

    public void Update() {
        frame.repaint();
    }

    public void DrawSquare() {

    }
}