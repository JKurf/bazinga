package src.Graphics;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import src.*;
import src.Ent.Entity;
import src.Ent.Frame;
import src.Ent.Location;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Renderer {
    public static final int TILE_HEIGHT = 16;
    public static final int TILE_WIDTH = 16;
    public static int WIDTH = (int)(160 * 4);
    public static int HEIGHT = (int)(160 * 4);
    public static float zoom = 2.0f;

    public int TileMap_WIDTH;
    public int TileMap_HEIGHT;
    public int TileMap_NUM_TILES_X;
    public int TileMap_NUM_TILES_Y;

    private String TileMapFilename = "Data/TileMap.png";
    private String FontFilename = "Data/CustomFont.png";

    public Texture Font;
    public static Texture TileMap;
    public static TextureLoader textureLoader;

    // We need to strongly reference callback instances.
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    // The window handle
    private long window;

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
        window = glfwCreateWindow(WIDTH, HEIGHT, "Eric Game", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                Input.poll(window, key, scancode, action, mods);
            }
        });


        // Get the resolution of the primary monitor
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 4*3 + 64,
                (vidmode.height() - HEIGHT) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(130.0f/255.0f, 105.0f / 255.0f, 183.0f / 255.0f, 0.0f);

        glEnable(GL11.GL_TEXTURE_2D);

        // disable the OpenGL depth test since we're rendering 2D graphics
        glDisable(GL11.GL_DEPTH_TEST);

        glMatrixMode(GL11.GL_PROJECTION);
        glLoadIdentity();

        GL11.glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        textureLoader = new TextureLoader();

        TileMap = loadTexture(TileMapFilename);

        TileMap_WIDTH = TileMap.getImageWidth();
        TileMap_HEIGHT = TileMap.getImageHeight();
        TileMap_NUM_TILES_X = TileMap_WIDTH / TILE_WIDTH;
        TileMap_NUM_TILES_Y= TileMap_HEIGHT / TILE_HEIGHT;

        Font = loadTexture(FontFilename);
    }

    public static Texture loadTexture(String filename) {
        try {
            return textureLoader.getTexture(filename, GL_TEXTURE_2D, GL_RGBA, GL_NEAREST, GL_NEAREST);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load texture: " + filename);
        }
        return null;
    }

    public void draw(int sx1, int sy1, int sx2, int sy2,
                     float x, float y, float w, float h, Texture tex) {

        float u1 = (float)sx1 / getPow2(tex.getImageWidth());
        float v1 = (float)sy1 / getPow2(tex.getImageHeight());
        float u2 = (float)sx2 / getPow2(tex.getImageWidth());
        float v2 = (float)sy2 / getPow2(tex.getImageHeight());

        // store the current model matrix
        glPushMatrix();

        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_NOTEQUAL, 0);

        // bind to the appropriate texture for this sprite
        tex.bind();

        // translate to the right location and prepare to draw
        glTranslatef(world2ScreenX(x) - TILE_WIDTH/2*zoom, world2ScreenY(y) + TILE_HEIGHT/2*zoom, 0);
        glColor3f(1,1,1);

        // draw a quad textured to match the sprite
        glBegin(GL11.GL_QUADS);
        {
            glTexCoord2f(u1, v1);       //uv
            glVertex2f(0, 0);               //xy
            glTexCoord2f(u1, v2);       //uv
            glVertex2f(0, h*zoom);              //xy
            glTexCoord2f(u2, v2);       //uv
            glVertex2f(w*zoom, h*zoom);             //xy
            glTexCoord2f(u2, v1);       //uv
            glVertex2f(w*zoom,0);               //xy
        }
        GL11.glEnd();

        glDisable(GL_ALPHA_TEST);

        // restore the model view matrix to prevent contamination
        GL11.glPopMatrix();
    }

    public void drawWorld(World world) {
        for (int j = 0; j < world.rows; j++) {
            for (int i = 0; i < world.cols; i++) {

                int worldSizeX = world.cols;
                int worldSizeY = world.rows;

                int drawx = (i);
                int drawy = worldSizeY - (j);

                int ID = world.map[j][i];
                int sheetx = (ID % TileMap_NUM_TILES_X) * TILE_WIDTH;
                int sheety = (ID / TileMap_NUM_TILES_Y) * TILE_HEIGHT;

                draw(sheetx, sheety, sheetx + TILE_WIDTH, sheety + TILE_HEIGHT,
                        drawx, drawy, (TILE_WIDTH), (TILE_HEIGHT), TileMap);
            }
        }
    }

    public void drawClip(World world) {
        for (int j = 0; j < world.rows; j++) {
            for (int i = 0; i < world.cols; i++) {

                int worldSizeX = world.cols;
                int worldSizeY = world.rows;

                int drawx = (i);
                int drawy = worldSizeY - (j);

                //draw(sheetx, sheety, sheetx + TILE_WIDTH, sheety + TILE_HEIGHT,
                //        drawx, drawy, (int)(TILE_WIDTH), (int)(TILE_HEIGHT), TileMap);
                if(world.clip[j][i])
                    drawRect(drawx, drawy, TILE_WIDTH, TILE_HEIGHT);
            }
        }
    }

    public void drawPoints(Location[] pts) {
        drawPoints(pts, 8.0f);
    }

    public void drawPoints(Location[] pts, float size) {
        // store the current model matrix
        glPushMatrix();

        // translate to the right location and prepare to draw
        glTranslatef(0, 0, 0);
        glColor3f(1,0,0);

        glEnable(GL_ALPHA_TEST);
        glAlphaFunc(GL_NOTEQUAL, 0);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable( GL_POINT_SMOOTH );
        glPointSize( size );


        // draw a quad textured to match the sprite
        glBegin(GL11.GL_POINTS);
        {
            //for (int n = 0; n < pts.length; n ++) {
            for (Location n : pts) {
                glVertex2f(n.xPos(), n.yPos());
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

    public void drawRect(int x, int y, int w, int h) {
        // store the current model matrix
        glPushMatrix();

        // translate to the right location and prepare to draw
        //glTranslatef(world2ScreenX(x), world2ScreenX(y), 0);
        glTranslatef(world2ScreenX(x) - TILE_WIDTH/2*zoom, world2ScreenY(y) + TILE_HEIGHT/2*zoom, 0);
        glColor4f(1,0,0, 0.5f);

        glEnable(GL_BLEND);
        //glBlendFunc(GL_ONE_MINUS_DST_COLOR, GL_ONE_MINUS_SRC_ALPHA);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


        // draw a quad textured to match the sprite
        glBegin(GL11.GL_QUADS);
        {
            glVertex2f(0, 0);
            glVertex2f(0, h*zoom);
            glVertex2f(w*zoom, h*zoom);
            glVertex2f(w*zoom, 0);
        }
        GL11.glEnd();

        glBlendFunc(GL_NONE, GL_NONE);
        glDisable(GL_BLEND);

        // restore the model view matrix to prevent contamination
        GL11.glPopMatrix();
    }

    public void drawEntity(Entity ent) {
        if(ent != null) {
            //Frame f = ent.Animations[ent.currentAnimation].getCurrent();
            Frame f = ent.Animations[ent.currentAnimation].getCurrent();
            draw(f.u1, f.v1, f.u2, f.v2, ent.location.xPos(), (ent.location.yPos() + 1), TILE_WIDTH, TILE_HEIGHT, ent.tex);
        }
    }

    public void drawEntityScreen(Entity ent, float x, float y, float Z) {
        if(ent != null) {
            Frame f = ent.Animations[ent.currentAnimation].getCurrent();
            float u1 = (float) f.u1 / getPow2(ent.tex.getImageWidth());
            float v1 = (float) f.v1 / getPow2(ent.tex.getImageHeight());
            float u2 = (float) f.u2 / getPow2(ent.tex.getImageWidth());
            float v2 = (float) f.v2 / getPow2(ent.tex.getImageHeight());

            // store the current model matrix
            glPushMatrix();

            glEnable(GL_ALPHA_TEST);
            glAlphaFunc(GL_NOTEQUAL, 0);

            // bind to the appropriate texture for this sprite
            ent.tex.bind();

            // translate to the right location and prepare to draw
            glTranslatef(x, y, 0);
            glColor3f(1,1,1);

            // draw a quad textured to match the sprite
            glBegin(GL11.GL_QUADS);
            {
                glTexCoord2f(u1, v1);       //uv
                glVertex2f(0, 0);               //xy
                glTexCoord2f(u1, v2);       //uv
                glVertex2f(0, 16*zoom*Z);              //xy
                glTexCoord2f(u2, v2);       //uv
                glVertex2f(16*zoom*Z, 16*zoom*Z);             //xy
                glTexCoord2f(u2, v1);       //uv
                glVertex2f(16*zoom*Z,0);               //xy
            }
            GL11.glEnd();

            glDisable(GL_ALPHA_TEST);

            // restore the model view matrix to prevent contamination
            GL11.glPopMatrix();
        }
    }

    public void drawText(String stra, Texture tex, int gridSize, float x, float y,
                         float W, float H){
        float w = W * 2.0f;
        float h = H * 2.0f;

        String strUp = stra.toUpperCase();

        glPushMatrix();

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        tex.bind();

        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        for (int i = 0; i < strUp.length(); i++) {
            int asciiCode = (int) strUp.charAt(i);
            final float cellSize = 1.0f / gridSize;
            float cellX = ( asciiCode % gridSize) * cellSize;
            float cellY = ( asciiCode / gridSize) * cellSize;

            glTexCoord2f(cellX, cellY);
            glVertex2f(i * w, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * w + w, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * w + w, y + h);
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * w, y + h);
        }

        glEnd();

        glDisable(GL_BLEND);

        glPopMatrix();
    }

    public void drawText(String str, float x, float y){
        float w = 8.0f * 2.0f;
        float h = 8.0f * 2.0f;

        String strUp = str.toUpperCase();

        glPushMatrix();

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        Font.bind();

        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        for (int i = 0; i < strUp.length(); i++) {
            int asciiCode = (int) strUp.charAt(i);
            final float cellSize = 1.0f / 16;
            float cellX = ( asciiCode % 16) * cellSize;
            float cellY = ( asciiCode / 16) * cellSize;

            /*
            glTexCoord2f(cellX, cellY);
            glVertex2f(i * w / 3, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * w / 3 + w / 2, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * w / 3 + w / 2, y + h);
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * w / 3, y + h);
            */

            glTexCoord2f(cellX, cellY);
            glVertex2f(i * w, 0);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * w + w, 0);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * w + w, h);
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * w, h);
        }

        glEnd();

        glDisable(GL_BLEND);

        glPopMatrix();
    }

    public void drawTextMenu(String stra, float x, float y, boolean highlight){
        float W = 8.0f;
        float H = 8.0f;
        int gridSize = 16;

        float w = W * 2.0f;
        float h = H * 2.0f;

        int L = stra.length();

        String strUp = stra.toUpperCase();

        glPushMatrix();

        if(!highlight)
            glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        Font.bind();

        glTranslatef(x - L*w/2, y - h/2, 0);
        glBegin(GL_QUADS);

        for (int i = 0; i < strUp.length(); i++) {
            int asciiCode = (int) strUp.charAt(i);
            //int asciiCode = customAscii(strUp.charAt(i));
            final float cellSize = 1.0f / gridSize;
            float cellX = ( asciiCode % gridSize) * cellSize;
            float cellY = ( asciiCode / gridSize) * cellSize;

            glTexCoord2f(cellX, cellY);
            glVertex2f(i * w, y);
            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * w + w, y);
            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * w + w, y + h);
            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * w, y + h);
        }

        glEnd();

        glDisable(GL_BLEND);

        glPopMatrix();
    }

    public float world2ScreenX(float x) {
        return ((x - Camera.x)*TILE_WIDTH*zoom + WIDTH/2);
    }

    public float world2ScreenY(float y) {
        return ((-y + Camera.y)*TILE_HEIGHT*zoom + HEIGHT/2);
    }

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        glDisable(GL_BLEND);
    }

    public void updateScreen() {
        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    public void highlightTile(Entity ent) {
        // store the current model matrix
        glPushMatrix();

        int I = (int)Math.floor(ent.location.xPos() / TILE_WIDTH);
        int J = (int)Math.floor(ent.location.yPos() / TILE_HEIGHT);

        // translate to the right location and prepare to draw
        glTranslatef(world2ScreenX(I*TILE_WIDTH - TILE_WIDTH/2), world2ScreenY(J*TILE_HEIGHT + TILE_HEIGHT/2), 0);
        glColor4f(0,0,1, 0.5f);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int[] Dx = {1, 1, 0, 0};
        int[] Dy = {0, -1, -1, 0};

        // draw a quad textured to match the sprite
        glBegin(GL11.GL_QUADS);
        {
            for(int n = 0; n < 4; n ++) {
                glVertex2f(Dx[n]*TILE_WIDTH, Dy[n]*TILE_HEIGHT);                            //xy
                glVertex2f(Dx[n]*TILE_WIDTH, Dy[n]*TILE_HEIGHT + TILE_HEIGHT);              //xy
                glVertex2f(Dx[n]*TILE_WIDTH + TILE_WIDTH, Dy[n]*TILE_HEIGHT + TILE_HEIGHT); //xy
                glVertex2f(Dx[n]*TILE_WIDTH + TILE_WIDTH,Dy[n]*TILE_HEIGHT);                //xy
            }
        }
        GL11.glEnd();

        glDisable(GL_BLEND);

        // restore the model view matrix to prevent contamination
        GL11.glPopMatrix();
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

    public static int getPow2(int n) {
        int ret = 2;
        while (ret < n) {
            ret *= 2;
        }
        return ret;
    }

    public long getWindow() {
        return window;
    }
}
