package src.Menu;

import src.Graphics.*;
import src.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Menu {
    public  MenuItem[] items = new MenuItem[4];
    public float x = 0;
    public float y = 0;
    public int active = 0;
    public int size = 0;
    boolean inFocus;
    MenuType type = MenuType.verticalScroll;

    public void Init() {
        for(int n = 0; n < 4; n ++) {
            items[n] = null;
        }
        setFocus(true);
    }

    public String Update(double elapsedTime) {
        if(inFocus) {
            for (int n = 0; n < size; n++) {
                items[n].update(active == n);
            }

            if (Input.keyPress(GLFW_KEY_W)) {
                active--;
            }
            if (Input.keyPress(GLFW_KEY_S)) {
                active++;
            }

            if (active > size - 1) active = 0;
            if (active < 0) active = size - 1;

            return Input.keyPress(GLFW_KEY_D) ? items[active].getContents() : "null";
        } else {
            return "null";
        }
    }

    public void Render(Renderer graphics) {
        for(int n = 0; n < 4; n ++) {
            if(items[n] != null) {
                items[n].render(graphics, active == n && inFocus);
            }
        }

        if(inFocus) {
            graphics.drawRectOutline(x, y, getMaxLength(), size * 16.0f);
        }
    }

    public void Add(String str, MenuItemType _type) {
        if(_type == MenuItemType.text) {
            items[size] = new MenuText(str);
        }
        if(_type == MenuItemType.option) {
            items[size] = new MenuOption(str);
        }

        items[size].setPos(x, y + 16 * size);
        size++;
    }

    public void Add(String str) {
        Add(str, MenuItemType.text);
    }

    public void setType (MenuType _type) {
        type = _type;
        updatePositions();
    }

    public void setPos(float _x, float _y, MenuAlign pos) {
        if(pos == MenuAlign.center) {
            x = _x;
            y = _y;

            x -= getMaxLength()/2;

            updatePositions();
        }

        if(pos == MenuAlign.topLeft) {
            x = _x;
            y = _y;

            updatePositions();
        }
    }

    public void setPos(MenuAlign pos) {
        setPos(Renderer.WIDTH/2, Renderer.HEIGHT/2, pos);

        updatePositions();
    }

    public void setFocus(boolean _focus) {
        inFocus = _focus;
    }

    private float getMaxLength() {
        float maxSize = 0;
        float curSize = 0;

        for(int n = 0; n < size; n ++) {
            curSize = items[n].getSize();

            if(curSize > maxSize) maxSize = curSize;
        }

        return maxSize;
    }
    private void updatePositions() {
        float dx = 0;
        float dy = 0;

        if(type == MenuType.verticalScroll) {
            dx = 0;
            dy = 16.0f;
        }
        if(type == MenuType.horizontalScroll) {
            dx = getMaxLength() + 8.0f;
            dy = 0.0f;
        }

        for(int n = 0; n < size; n ++) {
            items[n].setPos(x + n*dx, y + n*dy);
        }
    }
}
