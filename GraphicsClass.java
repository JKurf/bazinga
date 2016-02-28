import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.Random;

public class GraphicsClass extends JFrame {
    private Image image;
    private Image spriteSheet;
    AffineTransform identity = new AffineTransform();
    Toolkit content;
    World currentWorld;

    Random rand;

    boolean loaded = false;

    final int SPRITE_HEIGHT = 16;
    final int SPRITE_WIDTH = 16;
    final int SPRITESHEET_HEIGHT = 25;
    final int SPRITESHEET_WIDTH = 25;

    public GraphicsClass() {
        super("Game");

        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = Toolkit.getDefaultToolkit();

        image = content.getImage(getURL("Data/TestImg.png"));

        rand = new Random();
    }

    private URL getURL(String filename) {
        URL url = null;

        try {
            url = this.getClass().getResource(filename);
        } catch (Exception e) {}

        return url;
    }
    public void LoadSpriteSheet(String filename) {
        spriteSheet = this.content.getImage(getURL(filename));
    }
    public void setWorld(World world) {
        currentWorld = world;
        System.out.println("Loaded!");
        System.out.println(world.cols + "  " + world.rows);
        loaded = true;
    }

    /**
     *
     * @param g
     */
    public void DrawWorld(Graphics2D g) {
        //world.loadSpriteSheet();
        if(loaded) {
            for (int j = 0; j < currentWorld.rows; j++) {
                for (int i = 0; i < currentWorld.cols; i++) {
                    //drawImage(tile[x][y]);
                    int drawx = j * SPRITE_HEIGHT;
                    int drawy = i * SPRITE_WIDTH;
                    int ID = currentWorld.map[j][i];
                    int sheetx = ID % SPRITESHEET_WIDTH;
                    int sheety = (int) (ID / SPRITESHEET_WIDTH);

                    g.drawImage(image, 0, 0, 16, 16, 16, 0, 32, 16, null);

                    System.out.printf("(%d,%d)\t", sheety, sheety);
                }
                System.out.println();
            }
        }
    }
    public void paint(Graphics g) {
        AffineTransform transform = new AffineTransform();

        Graphics2D spriteBatch = (Graphics2D)g;

        spriteBatch.setColor(Color.BLACK);
        spriteBatch.fillRect(0, 0, getSize().width, getSize().height);

        /*for(int i = 0; i < 50; i++) {
            transform.setTransform(identity);

            transform.translate(
                    rand.nextInt() % getSize().width,
                    rand.nextInt() % getSize().height);

            transform.rotate(Math.toRadians(360 * rand.nextDouble()));

            spriteBatch.drawImage(image, transform, this);
        }*/

        transform.setTransform(identity);
        transform.translate(1, 31);

        //transform.rotate(Math.toRadians(360 * rand.nextDouble()));

        //spriteBatch.drawImage(image, 0, 0, 16, 16, 0, 0, 16, 16, this);
        //spriteBatch.drawImage(image, transform, this);
        //transform.translate(0,16);
        //spriteBatch.drawImage(image, transform, this);

        spriteBatch.drawImage(image, 0, 31, 31, 63, 0, 0, 16, 16, this);

        //DrawWorld(spriteBatch);
    }

    public void Render() {
        //repaint();
        //validate();
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