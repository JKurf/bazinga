import static org.lwjgl.glfw.GLFW.*;

public class WorldState implements IState{
    private boolean initialized = false;

    World world;
    Player player;

    int worldWidth;
    int worldHeight;

    boolean track = true;
    boolean drawClip = false;

    String action = null;

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
        if(!initialized) {
            player.loadTexture("Data/TestImg.png");

            player.location.setX(0.0f);
            player.location.setY(0.0f);

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        float speed = 5.0f;

        if(InputClass.isKeyDown(GLFW_KEY_D)) {
            player.location.move((float)(speed * elapsedTime * GraphicsClass.TILE_WIDTH), 0);
        }
        if(InputClass.isKeyDown(GLFW_KEY_A)) {
            player.location.move((float)(-speed * elapsedTime * GraphicsClass.TILE_WIDTH), 0);
        }
        if(InputClass.isKeyDown(GLFW_KEY_W)) {
            player.location.move(0, (float)(speed * elapsedTime * GraphicsClass.TILE_HEIGHT));
        }
        if(InputClass.isKeyDown(GLFW_KEY_S)) {
            player.location.move(0, (float)(-speed * elapsedTime * GraphicsClass.TILE_HEIGHT));
        }

        if(InputClass.keyPress(GLFW_KEY_SPACE)) {
            track = !track;
        }
        if(InputClass.keyPress(GLFW_KEY_R))
            drawClip = !drawClip;

        if(InputClass.keyPress(GLFW_KEY_P))
            action = "pause";
        else
            action = null;

        if(track) {
            Camera.x = player.location.xPos();
            Camera.y = player.location.yPos();
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawWorld(world);
        if(drawClip) graphics.drawClip(world);
        graphics.drawEntity(player);

        graphics.drawText(String.format("POSITION:"), graphics.Font, 16, 0, 0, 8.0f, 8.0f);
        graphics.drawText(String.format("(%.1f,%.1f)",player.location.xPos()/16.0f, player.location.yPos()/16.0f), graphics.Font, 16, 0, 8, 8.0f, 8.0f);

        String text = "dank";
        if(track) {text = "Track";}
        else {text = "Not Track";}

        graphics.drawText(String.format("%d", InputClass.cooldown[GLFW_KEY_R]), graphics.Font, 16, 0, 16, 8, 8);

        //graphics.drawText(String.format("ABC DEF"), graphics.Font, 8, 0, 0, 8.0f, 8.0f);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        return action;
    }
}
