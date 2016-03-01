//#bangpoundben

import java.awt.event.KeyEvent;

public class Game {

    //GraphicsClass_Java2D graphics = new GraphicsClass_Java2D("Awesome-Title");
    GraphicsClass graphics = new GraphicsClass();

    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();
    WorldState World1 = new WorldState("TestMap");

    boolean finished = false;

    public Game() {
        //graphics.addKeyListener(input);
        System.out.println(KeyEvent.VK_Q);
    }

    public void Init() {
        SM.Add("Menu", menu);
        SM.Add("World1", World1);
        SM.Change("Menu");
        SM.Change("World1");

        //bufferGraphics.Init(true);
        //bufferGraphics.setWorld(mainWorld);
        graphics.Init();
        //graphics.setWorld(mainWorld);
    }

    public Boolean Update(double elapsedTime) {
        if(input.keyStates[KeyEvent.VK_Q]) {
            finished = true;
        }

        SM.Update(elapsedTime);
        return finished;
    }

    public void Render() {
        graphics.clearScreen();

        SM.Render(graphics);

        graphics.updateScreen();
    }

    public void Quit() {
        SM.Quit();
        //bufferGraphics.Quit();
        graphics.Quit();
        System.out.printf("\nQuitso");
    }
}