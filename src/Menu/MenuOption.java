package src.Menu;

import src.Graphics.Renderer;
import src.Input;
import static org.lwjgl.glfw.GLFW.*;

public class MenuOption implements MenuItem {
    String contents = "null";
    private float xPos;
    private float yPos;

    String[] options = new String[] {"off", "on"};;
    int option;

    public MenuOption(String cont) {
        contents = cont;
    }

    @Override
    public void update(boolean _active) {
        /*if(_active) {
            if(Input.keyPress(GLFW_KEY_D)) {
                option++;
            }
        }

        if(option > options.length-1) option = 0;
        if(option < 0) option = options.length-1;*/

    }

    @Override
    public void render(Renderer graphics) {
        render(graphics, false);
    }

    @Override
    public void render(Renderer graphics, boolean highlight) {
        graphics.drawText(String.format("%s: %s", contents, options[option]), xPos, yPos, highlight);
    }

    @Override
    public String getContents() {
        option++;
        if(option > options.length-1) option = 0;
        if(option < 0) option = options.length-1;

        if(contents != null) {
            return "cvar:" + contents + "|" + options[option];
        }

        return "null";
    }

    @Override
    public void sendData(String _data) {
        options[0] = _data;
    }

    @Override
    public void sendData(String[] _data) {
        int num = _data.length - 1;

        contents = _data[0];

        options = new String[num];

        for(int n = 0; n < _data.length-1; n ++) {
            options[n] = _data[n+1];
        }
    }

    @Override
    public void setPos(float _x, float _y) {
        xPos = _x;
        yPos = _y;
    }

    @Override
    public float getSize() {
        if(contents != null)
            return (contents.length() + 2 + options[option].length()) * 16.0f;
        else return 0;
    }
}
