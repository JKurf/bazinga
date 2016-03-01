import java.awt.*;
import java.awt.event.KeyListener;
import java.net.URL;

public interface GraphicsClass_dep {
    /**
     * Initialize the GraphicsClass_Java2D
     */
    void Init();

    /**
     * Load the SpriteSheet image
     * @param filename SpriteSheet filename
     */
    void LoadSpriteSheet(String filename);

    /**
     * Set current world to render
     * @param world World to render
     */
    void setWorld(World world) ;

    /**
     * Draw the current world to the back buffer
     */
    void DrawWorld();

    /**
     * Call Graphics2D paint method
     * @param g Graphics Interface
     */
    void paint(Graphics g);

    void clearScreen();
    void updateScreen();

    /**
     * Quit everything cleanly
     */
    void Quit();

    /**
     * Flip the buffers, Render to screen
     */
    void Render();

    /**
     * Get resource filepath
     * @param filename resource file
     * @return Path to file
     */
    URL getURL(String filename);

    Image loadImage(String filename);

    void addKeyListener(InputClass inp);
}