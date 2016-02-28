import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class GraphicsClass {
    private Image spriteSheet;
    AffineTransform identity = new AffineTransform();
    Toolkit content;
    World currentWorld;
    JFrame frame;

    boolean loaded = false;

    final int TILE_HEIGHT = 16;
    final int TILE_WIDTH = 16;
    final int SPRITESHEET_HEIGHT = 25;
    final int SPRITESHEET_WIDTH = 25;

    /**
     * Default Constructor
     */
    public GraphicsClass(JFrame Frame) {
        //super("Game");
        frame = Frame;
    }

    /**
     * Initialize the GraphicsClass
     */
    public void Init() {
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = Toolkit.getDefaultToolkit();
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
        //System.out.println("Loaded!");
        //System.out.println(world.cols + "  " + world.rows);

        LoadSpriteSheet("Data/TileMap.png");
        //LoadSpriteSheet("Data/" + world.name + "_Tilemap.png");

        loaded = true;
    }

    /**
     * Draw the current world to the back buffer
     * @param g Graphics2d Interface
     */
    public void DrawWorld(Graphics2D g) {
        if(loaded) {
            for (int j = 0; j < currentWorld.rows; j++) {
                for (int i = 0; i < currentWorld.cols; i++) {
                    int drawx = i * TILE_WIDTH;
                    int drawy = j * TILE_HEIGHT;
                    int ID = currentWorld.map[j][i];
                    int sheetx = ID % SPRITESHEET_WIDTH;
                    int sheety = (ID / SPRITESHEET_HEIGHT);

                    final int renderOffsetX = 8;
                    final int renderOffsetY = 31;
                    int xOffset = 0;
                    int yOffset = 0;
                    g.drawImage(spriteSheet,
                            renderOffsetX + drawx, renderOffsetY + drawy,                           //Screen Top Left corner
                            renderOffsetX+TILE_WIDTH + drawx, renderOffsetY+TILE_HEIGHT + drawy,    //Screen Bot Right corner
                            sheetx*TILE_WIDTH, sheety*TILE_HEIGHT,                                  //Tilemap Top Left corner
                            sheetx*TILE_WIDTH + TILE_WIDTH, sheety*TILE_HEIGHT + TILE_HEIGHT,       //Tilemap Bot Right corner
                            frame);
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

        spriteBatch.setColor(Color.BLUE);
        spriteBatch.fillRect(0, 0, frame.getSize().width, frame.getSize().height);

        transform.setTransform(identity);
        transform.translate(8, 31); //Origin
        //spriteBatch.drawImage(image, transform, this);

        DrawWorld(spriteBatch);
    }

    /**
     * Quit everything cleanly
     */
    public void Quit() {
        frame.dispose();
    }

    /**
     * Flip the buffers, Render to screen
     */
    public void Render() {
        paint(this.frame.getGraphics());
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