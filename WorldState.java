import static org.lwjgl.glfw.GLFW.*;

import java.awt.event.KeyEvent;

public class WorldState implements IState{
    private boolean initialized = false;

    World world;
    Player player;

    int worldWidth;
    int worldHeight;

    public WorldState(String worldName) {
        world = new World(worldName);
        worldWidth = world.cols * GraphicsClass.TILE_WIDTH;
        worldHeight = world.rows * GraphicsClass.TILE_HEIGHT;

        /*
        Camera.x = worldWidth / 2;
        Camera.y = worldHeight / 2;
        */

        player = new Player("Dave", "0");
    }

    @Override
    public void Init() {
        player.loadTexture("Data/TestImg.png");
        initialized = true;
    }

    @Override
    public void Update(double elapsedTime) {
        float speed = 50.0f;

        if(InputClass.isKeyDown(GLFW_KEY_RIGHT)) {
            player.location.move((float)(speed * elapsedTime), 0);
        }
        if(InputClass.isKeyDown(GLFW_KEY_LEFT)) {
            player.location.move((float)(-speed * elapsedTime), 0);
        }
        if(InputClass.isKeyDown(GLFW_KEY_UP)) {
            player.location.move(0, (float)(speed * elapsedTime));
        }
        if(InputClass.isKeyDown(GLFW_KEY_DOWN)) {
            player.location.move(0, (float)(-speed * elapsedTime));
        }

        Camera.x = player.location.xPos();
        Camera.y = player.location.yPos();
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawWorld(world);
        //graphics.drawEntity(player);

        graphics.drawText(String.format("Position:"), graphics.Font, 16, 0, 0, 16.0f, 16.0f);
        graphics.drawText(String.format("(%.1f,%.1f)",Camera.x/16.0f, Camera.y/16.0f), graphics.Font, 16, 0, 16, 16.0f, 16.0f);

        //graphics.drawText(String.format("ABC DEF"), graphics.Font, 8, 0, 0, 8.0f, 8.0f);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void ProcessInput(KeyEvent e) {

    }
}
