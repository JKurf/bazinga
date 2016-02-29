
public class GameClient implements Runnable {
    public static void main(String[] args){
        GameClient GC = new GameClient();
        GC.run();
    }

    @Override
    public void run() {
        Game game = new Game();
        Thread gameLoop = new Thread(this);
        game.Init();
        Boolean done = false;
        int frames = 0;

        double fps;
        long currentTime;
        double elapsedTime = 0.0f;

        while (!done) {
            currentTime = System.nanoTime();
            done = game.Update(elapsedTime);
            game.Render();


            try {
                gameLoop.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            elapsedTime = (System.nanoTime() - currentTime) / 1000000000.0f;
            fps = 1/elapsedTime;

            System.out.printf("\nFrame %d: deltaT:\t%f    fps: %f", frames++, elapsedTime, fps);
        }
        game.Quit();
    }
}
