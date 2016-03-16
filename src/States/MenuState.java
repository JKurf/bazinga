package src.States;

import src.Graphics.Renderer;
import src.Input;
import src.Menu.Menu;

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
        /*for(int n = 0; n < A.length; n ++) {
            root.Add(A[n]);
        }*/
    }

    @Override
    public void Init() {
        root.active = 0;
        if(!initialized) {
            root.Init();

            //for(int n = 0; n < A.length; n ++) {
            for(String n : A) {
                root.Add(n);
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
