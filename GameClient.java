import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameClient implements Runnable {
    static double fps;
    public static void main(String[] args){
        GameClient GC = new GameClient();
        GC.run();
    }

    @Override
    public void run() {
        Game game = new Game();
        //Thread gameLoop = new Thread(this);
        game.Init();
        //Boolean done = false;

        long window = game.graphics.getWindow();

        long currentTime;
        double elapsedTime = 0.0f;

        //while (!done) {
        while (glfwWindowShouldClose(window) == GLFW_FALSE) {
            currentTime = System.nanoTime();
            game.Update(elapsedTime);
            game.Render();


            /*try {
                gameLoop.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            elapsedTime = (System.nanoTime() - currentTime) / 1000000000.0f;
            fps = 1/elapsedTime;

            //System.out.printf("deltaT:\t%f    fps: %f\n", elapsedTime, fps);
        }

        game.Quit();
    }
}
