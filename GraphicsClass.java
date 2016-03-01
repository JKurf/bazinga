import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GraphicsClass {
    /*
    final int renderOffsetX = 8;
    final int renderOffsetY = 31;
    int xOffset = 16;
    int yOffset = 16;
    */

    Camera camera = new Camera();

    World currentWorld = new World("TestMap");

    static final int TILE_HEIGHT = 16;
    static final int TILE_WIDTH = 16;
    static final int WIDTH = 320*2;
    static final int HEIGHT = 320*2;

    int TileMap_WIDTH;
    int TileMap_HEIGHT;
    int TileMap_NUM_TILES_X;
    int TileMap_NUM_TILES_Y;

    Texture texture;
    Texture TileMap;
    TextureLoader textureLoader;

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

        glEnable(GL11.GL_TEXTURE_2D);

        // disable the OpenGL depth test since we're rendering 2D graphics
        glDisable(GL11.GL_DEPTH_TEST);

        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();

        GL11.glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);

        textureLoader = new TextureLoader();

        try {
            texture = textureLoader.getTexture("Data/TestImg.png");
            TileMap = textureLoader.getTexture("Data/TileMap.png");

            TileMap_WIDTH = TileMap.getImageWidth();
            TileMap_HEIGHT = TileMap.getImageHeight();
            TileMap_NUM_TILES_X = TileMap_WIDTH / TILE_WIDTH;
            TileMap_NUM_TILES_Y= TileMap_HEIGHT / TILE_HEIGHT;
        } catch (IOException e) {
            e.printStackTrace();
        }

        camera.x = 50;
        camera.y = 50;
    }

    public void draw(int sx1, int sy1, int sx2, int sy2,
                     int x, int y, int w, int h, Texture tex) {

        float u1 = (float)sx1 / getPow2(tex.getImageWidth());
        float v1 = (float)sy1 / getPow2(tex.getImageHeight());
        float u2 = (float)sx2 / getPow2(tex.getImageWidth());
        float v2 = (float)sy2 / getPow2(tex.getImageHeight());

        // store the current model matrix
        glPushMatrix();

        // bind to the appropriate texture for this sprite
        tex.bind();

        // translate to the right location and prepare to draw
        glTranslatef(x, y, 0);
        glColor3f(1,1,1);

        // draw a quad textured to match the sprite
        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(u1, v1);       //uv
            glVertex2f(0, 0);               //xy
            glTexCoord2f(u1, v2);       //uv
            glVertex2f(0, h);              //xy
            glTexCoord2f(u2, v2);       //uv
            glVertex2f(w, h);             //xy
            glTexCoord2f(u2, v1);       //uv
            glVertex2f(w,0);               //xy
        }
        GL11.glEnd();

        // restore the model view matrix to prevent contamination
        GL11.glPopMatrix();
    }

    private int getPow2(int n) {
        int ret = 2;
        while (ret < n) {
            ret *= 2;
        }
        return ret;
    }


    public void drawWorld(World world) {
        for (int j = 0; j < world.rows; j++) {
            for (int i = 0; i < world.cols; i++) {

                int drawx = (i * TILE_WIDTH) + WIDTH/2 - camera.x;//  + (-camera.trueX) + camera.w/2;
                int drawy = (j * TILE_HEIGHT) + HEIGHT/2 - camera.y;// + (-camera.trueY) + camera.h/2;

                int ID = world.map[j][i];
                int sheetx = (ID % TileMap_NUM_TILES_X) * TILE_WIDTH;
                int sheety = (ID / TileMap_NUM_TILES_Y) * TILE_HEIGHT;

                draw(sheetx, sheety, sheetx + TILE_WIDTH, sheety + TILE_HEIGHT,
                        drawx, drawy, TILE_WIDTH, TILE_HEIGHT, TileMap);
            }
        }
    }

    public void drawPoints(Location[] pts) {
        // store the current model matrix
        glPushMatrix();

        // translate to the right location and prepare to draw
        glTranslatef(0, 0, 0);
        glColor3f(1,1,1);

        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_NOTEQUAL, 0);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable( GL_POINT_SMOOTH );
        glPointSize( 8.0f );

        // draw a quad textured to match the sprite
        glBegin(GL11.GL_POINTS);
        {
            for (int n = 0; n < pts.length; n ++) {
                glVertex2f(pts[n].xPos(), pts[n].yPos());
            }
        }
        GL11.glEnd();

        glDisable(GL_POINT_SMOOTH);
        glBlendFunc(GL_NONE, GL_NONE);
        glDisable(GL_BLEND);
        glDisable(GL_ALPHA_TEST);

        // restore the model view matrix to prevent contamination
        GL11.glPopMatrix();

    }

    public void drawEntity(Entity ent) {
        draw(0, 0, 16, 16, ent.location.xPos(), ent.location.yPos(), 16, 16, ent.tex);
    }


    public void Render() {
        //clearScreen();

        drawWorld(currentWorld);

        //updateScreen();
    }

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }

    public void updateScreen() {
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