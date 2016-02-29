import java.awt.*;
import java.awt.event.KeyListener;
import java.net.URL;

public class GraphicsClass_SDL implements GraphicsClass {
    final int renderOffsetX = 8;
    final int renderOffsetY = 31;
    int xOffset = 16;
    int yOffset = 16;

    final int TILE_HEIGHT = 16;
    final int TILE_WIDTH = 16;
    final int SPRITESHEET_HEIGHT = 25;
    final int SPRITESHEET_WIDTH = 25;

    /**
     * Default Constructor
     */
    public GraphicsClass_SDL() {
    }

    public GraphicsClass_SDL(String title) {
    }

    public void addKeyListener(InputClass inp) {

    }

    @Override
    public void Init() {

    }

    @Override
    public void LoadSpriteSheet(String filename) {

    }

    public void setWorld(World world) {

    }

    @Override
    public void DrawWorld() {

    }

    @Override
    public void paint(Graphics g) {

    }

    @Override
    public void clearScreen() {

    }

    @Override
    public void updateScreen() {

    }

    @Override
    public void Quit() {

    }

    @Override
    public void Render() {

    }

    @Override
    public URL getURL(String filename) {
        return null;
    }

    @Override
    public Image loadImage(String filename) {
        return null;
    }
}