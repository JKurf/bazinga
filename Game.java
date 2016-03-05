//#bangpoundben

import static org.lwjgl.glfw.GLFW.*;

public class Game {
    GraphicsClass graphics = new GraphicsClass();

    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();
    WorldState World1 = new WorldState("The Black Lagoon");

    String currentWorld = "World1";

    public Game() {
    }

    public void Init() {
        graphics.Init();

        SM.Add("Menu", menu);
        SM.Add("World1", World1);

        SM.Change("World1");
    }

    public void Update(double elapsedTime) {
        InputClass.Update(elapsedTime);
        if(input.isKeyDown(GLFW_KEY_Q) || input.isKeyDown(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
        }

        String act = SM.Update(elapsedTime);
        if(act != null) {
            if (act.equals("quit")) {
                glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
            }
            if (act.equals("pause")) {
                SM.Change("Menu");
            }
            if(act.equals("resume")) {
                SM.Change(currentWorld);
            }
            if(act.equals("change")) {
                if(currentWorld == "World1") {
                    SM.Change("World2");
                    currentWorld = "World2";
                } else {
                    SM.Change("World1");
                    currentWorld = "World1";
                }
            }
            if(act.startsWith("battle:")) {
                String ent = act.substring(7);
                System.out.println("Battle Started with Entity " + ent);

                BattleState battle = new BattleState(World1.player,World1.world.mobs[Integer.valueOf(ent)]);

                SM.Add("battle", battle);
                SM.Change("battle");
            }
        }
    }

    public void Render() {
        graphics.clearScreen();

        SM.Render(graphics);
        //graphics.drawPoints(new Location[] {new Location(GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/2)});

        //graphics.drawText("Lmao This is some awesome Text :^)", graphics.Font, 16, 0, 0, 16.0f, 16.0f);

        String FPS = String.format("fps:%.1f", GameClient.fps);
        graphics.drawText(FPS, graphics.Font, 16, GraphicsClass.WIDTH/2, 0, 8.0f, 8.0f);

        graphics.updateScreen();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
        System.out.printf("\nQuitso");
    }
}
