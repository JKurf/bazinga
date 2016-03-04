/**
 * Created by graf on 3/4/2016.
 */
public class MenuItem {
    String contents;
    MenuItem[] child = null;
    int x;
    int y;
    int n = 0;
    String current;


    public MenuItem() {
        this("Empty MenuItem");
    }

    public MenuItem(String cont) {
        contents = cont;
    }

    public void Init() {
        x = GraphicsClass.WIDTH / 2;
        y = GraphicsClass.HEIGHT / 4;
        child = new MenuItem[4];
    }

    public void Update(double elapsedTime, int activeC) {
        current = child[activeC].contents;
    }

    public void Render(GraphicsClass graphics, int activeC) {
        for (int i = 0; i < 4; i ++) {
            if(child[i] == null) {
                graphics.drawTextMid(contents, x, y);
            } else {
                if(i != activeC)
                    child[i].Render(graphics, activeC);
            }
        }
    }

    public void addChild(MenuItem ch) {
        child[n] = ch;
        child[n].Init();
        child[n].y = y + (n+1) * 8;
        n++;
    }

    public void Quit() {

    }
}
