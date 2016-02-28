import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class GraphicsClass extends JFrame {
    private Image image;
    AffineTransform identity = new AffineTransform();

    public GraphicsClass() {
        super("Game");

        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit content = Toolkit.getDefaultToolkit();

        image = content.getImage(getURL("Data/TestImg.png"));
    }

    private URL getURL(String filename) {
        URL url = null;

        try {
            url = this.getClass().getResource(filename);
        } catch (Exception e){}

        return url;
    }

    public void paint(Graphics g) {
        AffineTransform transform = new AffineTransform();

        Random rand = new Random();

        Graphics2D spriteBatch = (Graphics2D)g;

        spriteBatch.setColor(Color.BLACK);
        spriteBatch.fillRect(0, 0, getSize().width, getSize().height);

        for(int i = 0; i < 50; i++) {
            transform.setTransform(identity);

            transform.translate(
                    rand.nextInt() % getSize().width,
                    rand.nextInt() % getSize().height);

            transform.rotate(Math.toRadians(360 * rand.nextDouble()));

            double scale = rand.nextDouble()+1;
            transform.scale(scale, scale);

            spriteBatch.drawImage(image, transform, this);
        }
    }

    public void Render() {

    }

    public void Init() {

    }

    public void Quit() {

    }
}

    /*
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
*/