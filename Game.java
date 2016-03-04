//#bangpoundben

import static org.lwjgl.glfw.GLFW.*;

public class Game {
    GraphicsClass graphics = new GraphicsClass();

    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();
    WorldState World1 = new WorldState("Test");

    public Game() {
    }

    public void Init() {
        graphics.Init();

        SM.Add("Menu", menu);
        SM.Add("World1", World1);
        SM.Change("Menu");
        SM.Change("World1");
    }

    public void Update(double elapsedTime) {
        InputClass.Update(elapsedTime);
        if(input.isKeyDown(GLFW_KEY_Q) || input.isKeyDown(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
        }

        SM.Update(elapsedTime);
    }

    public void Render() {
        graphics.clearScreen();

        SM.Render(graphics);
        graphics.drawPoints(new Location[] {new Location(GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/2)});

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