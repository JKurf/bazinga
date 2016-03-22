package src.Menu;

import src.Graphics.Renderer;
import src.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

public class SubMenu implements MenuItem {
    String contents;
    private float xPos = 0;
    private float yPos = 320;

    MenuItem[] items = new MenuItem[4];
    public int active = 0;
    public int size = 0;
    MenuType type = MenuType.verticalScroll;
    boolean current = false;
    String _action = null;

    public SubMenu(String cont) {
        contents = cont;

        for(int n = 0; n < 4; n ++) {
            items[n] = null;
        }
    }

    @Override
    public void update(boolean _activeWhy) {
        _action = null;

        if(current) {
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

            if (Input.keyPress(GLFW_KEY_D)) {
                String action = items[active].getContents();

                if (action.startsWith("enter")) {
                    System.out.println("Entering submenu: " + action.substring(6));

                    items[active].sendData("true");
                }

                if (action.equals("back")) {
                    System.out.println("Leaving Submenu");
                    current = false;
                    _action = "leave";
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
        if(current) {
            for(int n = 0; n < size; n ++) {
                items[n].render(graphics, active == n);
            }
        }
        if(!current) {
            graphics.drawText(contents + "...", xPos, yPos, highlight);
        }
    }

    @Override
    public String getContents() {
        if(_action != null) {
            return _action;
        }

        return contents!= null ?
                ("enter:" + contents)
                :
                "null";
    }

    @Override
    public void sendData(String _data) {
        current = Boolean.parseBoolean(_data);      //set current equal to true or not
    }

    @Override
    public void sendData(String[] _data) {          //populate menu items
        //contents = _data[0];
        //current = Boolean.parseBoolean(_data[1]);
        for(int n = 0; n < _data.length; n ++) {
            Add(_data[n]);
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
            return (contents.length() + 3) * 16.0f;
        else return 0;
    }

    public void Add(String str, MenuItemType _type) {
        if(_type == MenuItemType.text) {
            items[size] = new MenuText(str);
        }
        else if(_type == MenuItemType.option) {
            items[size] = new MenuOption(str);
        }
        else if (_type == MenuItemType.subMenu) {
            items[size] = new SubMenu(str);
        }
        else {
            System.out.println("Wrong MenuItemType");
        }

        items[size].setPos(xPos, yPos + 16 * size);
        size++;
    }

    public void Add(String str) {
        Add(str, MenuItemType.text);
    }
}
