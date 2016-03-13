package src;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    public static boolean[] keys = new boolean[1024];
    public static int[] cooldown = new int[1024];
    public static int cooldownMax = 15;

    public static void poll(long window, int key, int scanconde, int action, int mods) {
        //Input.keysOld[key] = Input.keys[key];

        Input.keys[key] = (action != GLFW_RELEASE);
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }

    public static boolean keyPress(int keycode) {
        if((cooldown[keycode] == 0) && (keys[keycode])) {
            Input.cooldown[keycode] = Input.cooldownMax;
            return true;
        } else {
            return false;
        }
    }

    public static void Update(double elapsedTime) {
        for (int n = 0; n < 1024; n ++) {
            if(cooldown[n] <=0) cooldown[n] = 0;
            else {
                cooldown[n] = cooldown[n] - (int)(100*elapsedTime);
            }
        }
    }
}