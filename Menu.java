public class Menu {
    public MenuItem[] items = new MenuItem[4];
    float x = GraphicsClass.WIDTH  / 2;
    float y = GraphicsClass.HEIGHT / 4;
    int active = 0;
    int n = 0;

    public void Init() {
        for(int n = 0; n < 4; n ++) {
            items[n] = null;
        }
    }

    public void Update(double elapsedTime) {
    }

    public void Render(GraphicsClass graphics) {
        for(int n = 0; n < 4; n ++) {
            if(items[n] != null) {
                graphics.drawTextMenu(items[n].contents, x, y + n*8, n == active);
            }
        }
    }

    public void Add(String str) {
        items[n++] = new MenuItem(str);
    }
}
