package src.Menu;

import src.Graphics.Renderer;

public interface MenuItem {
    void update(boolean _active);

    void render(Renderer graphics);
    void render(Renderer graphics, boolean highlight);

    String getContents();
    void sendData(String _data);
    void sendData(String[] _data);

    void setPos(float _x, float _y);

    float getSize();
}
