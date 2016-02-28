//import "Graphics"

import java.io.IOException;

public class GameSystem {

    GraphicsClass graphics = new GraphicsClass();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();

    World mainWorld;

    public static void main(String[] args){
        GameSystem Game = new GameSystem();

        Game.Init();

        Boolean done = false;

        while (!done) {
            done = Game.Update();

            Game.Render();
        }

        Game.Quit();
    }

    public void Init() {
        SM.Add("Menu", menu);
        SM.Change("Menu", new String[]{"A", "B"});
        graphics.Init();

        mainWorld = new World("TestMap");

        graphics.setWorld(mainWorld);
    }

    public Boolean Update() {
        SM.Update(0.0f);
        return false;
    }

    public void Render() {
        SM.Render(graphics);
        graphics.Render();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
    }
}
