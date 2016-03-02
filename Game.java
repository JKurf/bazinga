//#bangpoundben

import java.awt.event.KeyEvent;
import static org.lwjgl.glfw.GLFW.*;

public class Game {

    //GraphicsClass_Java2D graphics = new GraphicsClass_Java2D("Awesome-Title");
    GraphicsClass graphics = new GraphicsClass();

    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();
    WorldState World1 = new WorldState("Map2");

    boolean finished = false;

    public Game() {
        //graphics.addKeyListener(input);
        //System.out.println(KeyEvent.VK_Q);
    }

    public void Init() {
        graphics.Init();

        SM.Add("Menu", menu);
        SM.Add("World1", World1);
        SM.Change("Menu");
        SM.Change("World1");

        //bufferGraphics.Init(true);
        //bufferGraphics.setWorld(mainWorld);
        //graphics.setWorld(mainWorld);
    }

    public void Update(double elapsedTime) {
        if(input.isKeyDown(GLFW_KEY_Q) || input.isKeyDown(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(graphics.getWindow(), GLFW_TRUE);
        }

        SM.Update(elapsedTime);
    }

    public void Render() {
        graphics.clearScreen();

        SM.Render(graphics);
        graphics.drawPoints(new Location[] {new Location(GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/2)});

        graphics.updateScreen();
    }

    public void Quit() {
        SM.Quit();
        //bufferGraphics.Quit();
        graphics.Quit();
        System.out.printf("\nQuitso");
    }
}