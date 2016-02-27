//import "Graphics"

public class GameSystem {

    Graphics graphics = new Graphics();
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();

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
    }

    public Boolean Update() {
        SM.Update(0.0f);
        return true;
    }

    public void Render() {
        SM.Render(graphics);
        graphics.DrawSquare();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
    }
}
