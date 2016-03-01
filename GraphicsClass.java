import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by graf on 2/29/2016.
 */
public class GraphicsClass {
    /*
    private Image spriteSheet;
    AffineTransform identity = new AffineTransform();
    Toolkit content;
    World currentWorld = null;

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
    */


    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    // The window handle
    private long window;

    public long getWindow() {
        return window;
    }

    public void Init() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GLFW_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        int WIDTH = 300;
        int HEIGHT = 300;

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GLFW_TRUE); // We will detect this in our rendering loop
            }
        });

        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    /*
    public void DrawWorld() {
        if(currentWorld != null) {
            for (int j = 0; j < currentWorld.rows; j++) {
                for (int i = 0; i < currentWorld.cols; i++) {

                    int drawx = i * TILE_WIDTH + renderOffsetX + xOffset;
                    int drawy = j * TILE_HEIGHT + renderOffsetY + yOffset;
                    int ID = currentWorld.map[j][i];
                    int sheetx = ID % SPRITESHEET_WIDTH;
                    int sheety = (ID / SPRITESHEET_HEIGHT);

                    backBuffer.getGraphics().drawImage(spriteSheet,
                            drawx, drawy,                           //Screen Top Left corner
                            TILE_WIDTH + drawx, TILE_HEIGHT + drawy,    //Screen Bot Right corner
                            sheetx*TILE_WIDTH, sheety*TILE_HEIGHT,                                  //Tilemap Top Left corner
                            sheetx*TILE_WIDTH + TILE_WIDTH, sheety*TILE_HEIGHT + TILE_HEIGHT,       //Tilemap Bot Right corner
                            this);
                }
            }
        }
    }
    */

    public void Render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();

    }

    public void Quit() {
        // Destroy window and window callbacks
        glfwDestroyWindow(window);
        //keyCallback.free();
        keyCallback.release();

        glfwTerminate();
        //errorCallback.free();
        keyCallback.release();
    }
}
