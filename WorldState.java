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

        player = new Player("Dave", "0");
    }

    @Override
    public void Init() {
        if(!initialized) {
            player.loadTexture("Data/TestImg.png");

            player.location.setX(world.xStart);
            player.location.setY(world.yStart);

            //enemy.loadTexture("Data/Char01.png");
            //enemy.location.setX(5);
            //enemy.location.setY(5);

            world.Init();

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        float speed = 5.0f;

        if(InputClass.isKeyDown(GLFW_KEY_D)) {
            player.location.move((float)(speed * elapsedTime), 0, world);
        }
        if(InputClass.isKeyDown(GLFW_KEY_A)) {
            player.location.move((float)(-speed * elapsedTime), 0, world);
        }
        if(InputClass.isKeyDown(GLFW_KEY_W)) {
            player.location.move(0, (float)(speed * elapsedTime), world);
        }
        if(InputClass.isKeyDown(GLFW_KEY_S)) {
            player.location.move(0, (float)(-speed * elapsedTime), world);
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

        for(int n = 0; n < world.nMobs; n ++) {
            if (Math.sqrt(Math.pow(player.location.xPos() - world.mobs[n].location.xPos(),2) + Math.pow(player.location.yPos() - world.mobs[n].location.yPos(),2)) < 1) {
                        action = "battle:" + Integer.toString(n);
            }
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawWorld(world);
        if(drawClip) graphics.drawClip(world);
        graphics.drawEntity(player);
        for(int n = 0; n < world.nMobs; n ++) {
            graphics.drawEntity(world.mobs[n]);
        }

        graphics.drawText("POSITION!", 0, 0);
        graphics.drawText(String.format("(%.1f,%.1f)",player.location.xPos(), player.location.yPos()), 0, 8);

        String text = world.name;

        graphics.drawText(text, 0, 16);
        //graphics.highlightTile(player);

        //graphics.drawText(String.format("ABC DEF"), graphics.Font, 8, 0, 0, 8.0f, 8.0f);

        for(int n = 0; n < world.nMobs; n ++) {
            if (world.mobs[n].alive == false) {
                world.mobs[n] = null;

                //remove n from list
                for(int J = n; J < world.nMobs-1; J++) {
                    world.mobs[J] = world.mobs[J+1];
                }

                world.nMobs--;
            }
        }
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
