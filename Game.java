//#bangpoundben

import java.awt.event.KeyEvent;

public class Game {

    GraphicsClass graphics = new GraphicsClass("Awesome-Title");
    InputClass input = new InputClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();

    World mainWorld = new World("TestMap");

    boolean finished = false;

    public Game() {
        graphics.addKeyListener(input);
        System.out.println(KeyEvent.VK_Q);
    }

    public void Init() {
        SM.Add("Menu", menu);
        SM.Change("Menu", new String[]{"A", "B"});
        graphics.Init();
        graphics.setWorld(mainWorld);
    }

    public Boolean Update() {
        if(input.keyStates[KeyEvent.VK_Q]) {
            finished = true;
        }

        SM.Update(0.0f);
        return finished;
    }

    public void Render() {
        SM.Render(graphics);
        graphics.Render();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
        System.out.println("Quitso");
    }
}
