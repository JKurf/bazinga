package src.Menu;

import src.Graphics.Renderer;

public class MenuText implements MenuItem {
    String contents;
    private float xPos;
    private float yPos;

    public MenuText(String cont) {
        contents = cont;
    }

    @Override
    public void update(boolean _active) {

    }

    @Override
    public void render(Renderer graphics) {
        render(graphics, false);
    }

    @Override
    public void render(Renderer graphics, boolean highlight) {
        graphics.drawText(contents, xPos, yPos, highlight);
    }

    @Override
    public String getContents() {
        return contents!= null ? contents : "null";
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
            return (contents.length() * 16.0f);
        else return 0;
    }
}
