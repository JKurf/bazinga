public class Menu {
    public MenuItem[] items = new MenuItem[4];
    float x;// = GraphicsClass.WIDTH  / 2;
    float y;// = GraphicsClass.HEIGHT / 4;
    int active = 0;
    int type = 0;
    int n = 0;

    public Menu() {
        setType(0);
    }

    public void Init() {
        if(type == 0) {
            x = GraphicsClass.WIDTH  / 2;
            y = GraphicsClass.HEIGHT / 4;
        }
        if(type == 1) {
            x = GraphicsClass.WIDTH/4;
            y = GraphicsClass.HEIGHT / 8 * 3;
        }
        for(int n = 0; n < 4; n ++) {
            items[n] = null;
        }
    }

    public void setType(int T) {
        type = T;
        Init();
    }

    public void Update(double elapsedTime) {

    }

    public void Render(GraphicsClass graphics) {
        for(int n = 0; n < 4; n ++) {
            if(items[n] != null) {
                if(type == 0)
                    graphics.drawTextMenu(items[n].contents, x, y + n*8, n == active);
                if(type == 1)
                    graphics.drawTextMenu(items[n].contents, x, y + n*8, n == active);
            }
        }
    }

    public void Add(String str) {
        items[n++] = new MenuItem(str);
    }
}
