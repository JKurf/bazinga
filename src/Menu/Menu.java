package src.Menu;

import src.Graphics.*;
import src.Input;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Menu {
    public  MenuItem[] items = new MenuItem[4];
    public float x = 0;
    public float y = 0;
    public int active = 0;
    public int size = 0;
    boolean inFocus;
    boolean current = true;
    MenuType type = MenuType.verticalScroll;

    public void Init() {
        for(int n = 0; n < 4; n ++) {
            items[n] = null;
        }
        setFocus(true);
    }

    public String Update(double elapsedTime) {
        if(inFocus) {
            if (current) {
                for (int n = 0; n < size; n++) {
                    items[n].update(active == n);
                }

                if (Input.keyPress(GLFW_KEY_W)) {
                    active--;
                }
                if (Input.keyPress(GLFW_KEY_S)) {
                    active++;
                }
                if (Input.keyPress(GLFW_KEY_D) || Input.keyPress(GLFW_KEY_ENTER)) {
                    String action = items[active].getContents().replace("<", "").replace(">", "");

                    if (action.startsWith("enter")) {
                        System.out.println("Entering submenu: " + action.substring(6));

                        items[active].sendData("true");
                        current = false;
                    }

                    if (action.startsWith("cvar")) {
                        String var = action.substring(5, action.indexOf('|'));
                        String value = action.substring(action.indexOf('|') + 1);

                        System.out.println("cvar: [" + var + "] changed to: {" + value + "}");
                    }

                    return action;
                }
            } else {
                items[active].update(false);
                String action = items[active].getContents();

                if (action.equals("leave")) {
                    System.out.println("Leaving Submenu");
                    current = true;
                }
            }
        }

        if (active > size - 1) active = 0;
        if (active < 0) active = size - 1;

        return "null";
    }

    public void Render(Renderer graphics) {
        if (current) {
            for (int n = 0; n < size; n++) {
                items[n].render(graphics, active == n && inFocus);
            }

            if(inFocus) {
                graphics.drawRectOutline(x, y, getMaxLength(), size * 16.0f);
            }
        } else {
            items[active].render(graphics);
        }
    }

    public void Add(String str, MenuItemType _type) {
        if(_type == MenuItemType.text) {
            items[size] = new MenuText(str);
        }
        else if(_type == MenuItemType.option) {
            items[size] = new MenuOption(str);
        }
        else if (_type == MenuItemType.subMenu) {
            items[size] = new SubMenu(str.substring(0, str.indexOf(':')));

            String rest = str.substring(str.indexOf(':')+1);
            List<String> subs = new ArrayList<>();

            while(rest.contains(",")) {
                subs.add(rest.substring(0, rest.indexOf(',')));

                rest = rest.substring(rest.indexOf(',')+1);
            }

            subs.add(rest);

            items[size].sendData(
                    subs.toArray(new String[subs.size()])
            );
        }
        else if (_type == MenuItemType.entry) {
            items[size] = new MenuEntry(str);
        }
        else {
            System.out.println("Wrong MenuItemType");
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
