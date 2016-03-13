package src.States;

import src.Graphics.Camera;
import src.Ent.Animation;
import src.Ent.Player;
import src.Graphics.Renderer;
import src.Input;

import static org.lwjgl.glfw.GLFW.*;

public class WorldState implements IState{
    private boolean initialized = false;

    public World world;
    public Player player;

    public int worldWidth;
    public int worldHeight;

    public boolean track = true;
    public boolean drawClip = false;

    public String action = null;

    public WorldState(String worldName) {
        world = new World(worldName);
        worldWidth = world.cols * Renderer.TILE_WIDTH;
        worldHeight = world.rows * Renderer.TILE_HEIGHT;

        player = new Player("Dave", "0001");
    }

    @Override
    public void Init() {
        if(!initialized) {
            player.loadAnimationData("Data/player.json");

            player.location.setX(world.xStart);
            player.location.setY(world.yStart);

            player.currentAnimation = 4;

            world.Init();

            for(int i = 0; i < 4; i ++) {
                world.mobs[i].loadAnimationData("Data/" + world.mobs[i].name + ".json");
                world.mobs[i].currentAnimation = Animation.IDLE;
            }

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        action = null;

        for(int n = 0; n < world.nMobs; n ++) {
            if (!world.mobs[n].alive) {
                world.mobs[n] = null;

                //remove n from list
                for(int J = n; J < world.nMobs-1; J++) {
                    world.mobs[J] = world.mobs[J+1];
                }

                world.nMobs--;
            }
        }

        float speed = 5.0f;

        player.currentAnimation = Animation.IDLE;
        if(Input.isKeyDown(GLFW_KEY_D)) {
            player.location.move((float)(speed * elapsedTime), 0, world);
            player.currentAnimation = Animation.RIGHT;
        }
        if(Input.isKeyDown(GLFW_KEY_A)) {
            player.location.move((float)(-speed * elapsedTime), 0, world);
            player.currentAnimation = Animation.LEFT;
        }
        if(Input.isKeyDown(GLFW_KEY_W)) {
            player.location.move(0, (float)(speed * elapsedTime), world);
            player.currentAnimation = Animation.UP;
        }
        if(Input.isKeyDown(GLFW_KEY_S)) {
            player.location.move(0, (float)(-speed * elapsedTime), world);
            player.currentAnimation = Animation.DOWN;
        }

        if(Input.keyPress(GLFW_KEY_SPACE)) {
            player.levelUp();
        }
        if(Input.keyPress(GLFW_KEY_R))
            drawClip = !drawClip;

        if(Input.keyPress(GLFW_KEY_P))
            action = "pause";

        if(track) {
            Camera.x = player.location.xPos();
            Camera.y = player.location.yPos();
        }

        player.Animations[player.currentAnimation].Update(elapsedTime);

        for(int n = 0; n < world.nMobs; n ++) {
            world.mobs[n].Animations[world.mobs[n].currentAnimation].Update(elapsedTime);
            if (Math.sqrt(Math.pow(player.location.xPos() - world.mobs[n].location.xPos(),2) + Math.pow(player.location.yPos() - world.mobs[n].location.yPos(),2)) < 0.75f) {
                        action = "battle:" + Integer.toString(n);
            }
        }

        if(!player.alive) {
            action = "dead";
        }
    }

    @Override
    public void Render(Renderer graphics) {
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
