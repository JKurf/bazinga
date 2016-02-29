import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GraphicsClass extends JFrame{
    private Image spriteSheet;
    AffineTransform identity = new AffineTransform();
    Toolkit content;
    World currentWorld;

    BufferedImage backBuffer;

    Graphics2D g2d;

    final int renderOffsetX = 8;
    final int renderOffsetY = 31;
    int xOffset = 16;
    int yOffset = 16;

    boolean loaded = false;

    final int TILE_HEIGHT = 16;
    final int TILE_WIDTH = 16;
    final int SPRITESHEET_HEIGHT = 25;
    final int SPRITESHEET_WIDTH = 25;

    /**
     * Default Constructor
     */
    public GraphicsClass() {
        super("Game");
    }

    public GraphicsClass(String Title) {
        super(Title);
    }

    /**
     * Initialize the GraphicsClass
     */
    public void Init(boolean buffer) {
        setSize(600, 600);
        if (!buffer) {
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            setVisible(false);
        }

        content = Toolkit.getDefaultToolkit();

        backBuffer = new BufferedImage(this.getHeight(),this.getWidth(), BufferedImage.TYPE_INT_RGB);
        g2d = backBuffer.createGraphics();
    }

    /**
     * Load the SpriteSheet image
     * @param filename SpriteSheet filename
     */
    private void LoadSpriteSheet(String filename) {
        spriteSheet = this.content.getImage(getURL(filename));
    }

    /**
     * Set current world to render
     * @param world World to render
     */
    public void setWorld(World world) {
        currentWorld = world;

        LoadSpriteSheet("Data/TileMap.png");
        //LoadSpriteSheet("Data/" + world.name + "_Tilemap.png");

        loaded = true;
    }

    /**
     * Draw the current world to the back buffer
     * @param g Graphics2d Interface
     */
    public void DrawWorld(Graphics g) {
        if(true) {
            for (int j = 0; j < currentWorld.rows; j++) {
                for (int i = 0; i < currentWorld.cols; i++) {

                    int drawx = i * TILE_WIDTH + renderOffsetX + xOffset;
                    int drawy = j * TILE_HEIGHT + renderOffsetY + yOffset;
                    int ID = currentWorld.map[j][i];
                    int sheetx = ID % SPRITESHEET_WIDTH;
                    int sheety = (ID / SPRITESHEET_HEIGHT);

                    g.drawImage(spriteSheet,
                            drawx, drawy,                           //Screen Top Left corner
                            TILE_WIDTH + drawx, TILE_HEIGHT + drawy,    //Screen Bot Right corner
                            sheetx*TILE_WIDTH, sheety*TILE_HEIGHT,                                  //Tilemap Top Left corner
                            sheetx*TILE_WIDTH + TILE_WIDTH, sheety*TILE_HEIGHT + TILE_HEIGHT,       //Tilemap Bot Right corner
                            this);
                }
            }
        }
    }

    /**
     * Call Graphics2D paint method
     * @param g Graphics Interface
     */
    public void paint(Graphics g) {
        AffineTransform transform = new AffineTransform();

        Graphics2D spriteBatch = (Graphics2D)g;

        spriteBatch.setColor(Color.BLACK);
        spriteBatch.fillRect(0, 0, this.getSize().width, this.getSize().height);

        transform.setTransform(identity);
        transform.translate(8, 31); //Origin
        //spriteBatch.drawImage(image, transform, this);

        DrawWorld(backBuffer.getGraphics());
        //backBuffer.getScaledInstance(2*TILE_WIDTH, 2*TILE_HEIGHT, 0);

        backBuffer.getGraphics().drawString("Lmao", 8, 41);

        spriteBatch.drawImage(backBuffer, 0, 0, this);
        g.drawImage(backBuffer, 0, 0, this);
    }

    /**
     * Quit everything cleanly
     */
    public void Quit() {
        this.dispose();
        System.out.println("Quitting");
    }

    /**
     * Flip the buffers, Render to screen
     */
    public void Render() {
        paint(backBuffer.getGraphics());
    }

    /**
     * Get resource filepath
     * @param filename resource file
     * @return Path to file
     */
    private URL getURL(String filename) {
        URL url = null;

        try {
            url = this.getClass().getResource(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
}