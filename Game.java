//#bangpoundben

import java.awt.event.KeyEvent;

public class Game {

    GraphicsClass graphics = new GraphicsClass("Awesome-Title");
    //GraphicsClass bufferGraphics = new GraphicsClass("Buffer");
    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();
    WorldState world = new WorldState();

    World mainWorld = new World("TestMap");

    boolean finished = false;

    public Game() {
        graphics.addKeyListener(input);
        System.out.println(KeyEvent.VK_Q);
    }

    public void Init() {
        SM.Add("Menu", menu);
        SM.Change("Menu", new String[]{"A", "B"});

        //bufferGraphics.Init(true);
        //bufferGraphics.setWorld(mainWorld);
        graphics.Init(false);
        graphics.setWorld(mainWorld);
    }

    public Boolean Update(double elapsedTime) {
        if(input.keyStates[KeyEvent.VK_Q]) {
            finished = true;
        }

        SM.Update(0.0f);
        return finished;
    }

    public void Render() {
        SM.Render(graphics);

        //bufferGraphics.Render();
        //graphics = bufferGraphics;
        graphics.Render();
    }

    public void Quit() {
        SM.Quit();
        //bufferGraphics.Quit();
        graphics.Quit();
        System.out.printf("\nQuitso");
    }
}
