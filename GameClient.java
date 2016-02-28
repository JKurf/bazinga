public class GameClient {
    public static void main(String[] args){
        Game Game = new Game();
        Game.Init();
        Boolean done = false;
        while (!done) {
            done = Game.Update();
            Game.Render();
        }
        Game.Quit();
    }
}
