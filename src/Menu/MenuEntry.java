package src.Menu;

import src.Graphics.Renderer;
import src.Input;
import static org.lwjgl.glfw.GLFW.*;

public class MenuEntry implements MenuItem {
    String contents = "null";
    private float xPos;
    private float yPos;

    String entry = "empty";
    String ret = "a";
    int maxLength = 16;
    boolean entering = false;

    public MenuEntry(String cont) {
        contents = cont;
    }

    @Override
    public void update(boolean _active) {
        if(_active) {
            if(entering) {
                if (Input.keyPress(GLFW_KEY_ENTER)) {
                    ret = entry;
                    entering = false;
                } else {
                    ret = null;
                }
                if (Input.keyPress(GLFW_KEY_BACKSPACE)) {
                    if(entry.length() >= 1)
                        entry = entry.substring(0, entry.length()-1);
                }

                for (int n = GLFW_KEY_A; n < GLFW_KEY_Z; n++) {
                    if (Input.keyPress(n)) {
                        entry += (char) n;
                    }
                }
            } else {
                if(Input.keyPress(GLFW_KEY_D)) {
                    entering = true;
                }
            }
        }
    }

    @Override
    public void render(Renderer graphics) {
        render(graphics, false);
    }

    @Override
    public void render(Renderer graphics, boolean highlight) {
        graphics.drawText(contents + ":" + entry, xPos, yPos, highlight);
    }

    @Override
    public String getContents() {
        return ("cvar:" + contents + "|" + ret);
        //return "entry";
    }

    @Override
    public void sendData(String _data) {
        contents = _data;
    }

    @Override
    public void sendData(String[] _data) {
        contents = _data[0];
    }

    @Override
    public void setPos(float _x, float _y) {
        xPos = _x;
        yPos = _y;
    }

    @Override
    public float getSize() {
        if(contents != null)
            return (contents.length() + maxLength)* 16.0f;
        else return 0;
    }
}