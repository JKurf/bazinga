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
        player.loadTexture("Data/TestImg.png", GraphicsClass.textureLoader);
        initialized = true;
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.isKeyDown(GLFW_KEY_SPACE)) {
            camera.x += 20.0f * elapsedTime;
            player.location.setX(player.location.xPos() + (float)(20.0f * elapsedTime));
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
