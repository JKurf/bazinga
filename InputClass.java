import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class InputClass extends GLFWKeyCallback {
    public static boolean[] keys = new boolean[65536];

    @Override
    public void invoke(long wondow, int key, int scanconde, int action, int mods) {
        keys[key] = action != GLFW_RELEASE;
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}