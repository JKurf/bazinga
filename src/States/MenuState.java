package src.States;

import src.Graphics.Renderer;
import src.Input;
import src.Menu.Menu;
import src.Menu.MenuItemType;

import static org.lwjgl.glfw.GLFW.*;

public class MenuState implements IState {
    public int x = 0;
    public int y = 0;

    private boolean initialized = false;
    public String[] A;
    public Menu root = new Menu();

    String action = null;

    public MenuState(String[] a) {
        A = a;
    }

    @Override
    public void Init() {
        root.active = 0;
        if(!initialized) {
            root.Init();

            for(String n : A) {
                if (n.startsWith("opt")) {
                    root.Add(n.substring(4), MenuItemType.option);
                } else {
                    root.Add(n, MenuItemType.text);
                }
            }

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        action = root.Update(elapsedTime);
    }

    @Override
    public void Render(Renderer graphics) {
        root.Render(graphics);
        graphics.drawText(String.format("%d", root.active), x, y);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        return action;
    }
}
