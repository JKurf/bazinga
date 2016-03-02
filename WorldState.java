import static org.lwjgl.glfw.GLFW.*;

import java.awt.event.KeyEvent;

public class WorldState implements IState{
    private boolean initialized = false;

    World world;
    Camera camera;
    Player player;

    int worldWidth;
    int worldHeight;

    public WorldState(String worldName) {
        world = new World(worldName);
        worldWidth = world.cols * GraphicsClass.TILE_WIDTH;
        worldHeight = world.rows * GraphicsClass.TILE_HEIGHT;

        camera = new Camera();
        camera.x = worldWidth / 2;
        camera.y = worldHeight / 2;

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
            player.location.move(0, (float)(-speed * elapsedTime));
        }
        if(InputClass.isKeyDown(GLFW_KEY_DOWN)) {
            player.location.move(0, (float)(speed * elapsedTime));
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawWorld(world, camera);
        graphics.drawEntity(player);
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
