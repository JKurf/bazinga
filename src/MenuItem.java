package src;

import src.Graphics.Renderer;

public class MenuItem {
    public String contents;

    public int x;
    public int y;
    public int n = 0;
    public String current;

    public MenuItem() {
        this("Empty MenuItem");
    }

    public MenuItem(String cont) {
        contents = cont;
    }

    public void Init() {
        x = Renderer.WIDTH / 2;
        y = Renderer.HEIGHT / 4;
    }

    public void Update(double elapsedTime, int activeC) {
        //current = child[activeC].contents;
        current = contents;
    }
}
