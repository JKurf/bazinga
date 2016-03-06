/**
 * Created by graf on 3/4/2016.
 */
public class MenuItem {
    String contents;

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
    }

    public void Update(double elapsedTime, int activeC) {
        //current = child[activeC].contents;
        current = contents;
    }
}
