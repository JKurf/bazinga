import static org.lwjgl.glfw.GLFW.*;

public class MenuState implements IState {
    private boolean initialized = false;
    String[] A;
    Menu root = new Menu();
    int MaxChildren = 4;

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

            /*
            MenuItem resume = new MenuItem("resume");
            MenuItem settings = new MenuItem("settings");
            MenuItem memes = new MenuItem("change world");
            MenuItem quit = new MenuItem("quit");

            root.Add("resume");
            root.Add("settings");
            root.Add("change world");
            root.Add("quit");
            */

            for(int n = 0; n < A.length; n ++) {
                root.Add(A[n]);
            }

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.keyPress(GLFW_KEY_W)) {
            root.active--;
        }
        if(InputClass.keyPress(GLFW_KEY_S)) {
            root.active++;
        }

        if(InputClass.keyPress(GLFW_KEY_D)) {
            action = root.items[root.active].contents;
        }
        else
            action = null;


        if(root.active < 0) root.active = MaxChildren-1;
        if(root.active > MaxChildren-1) root.active = 0;
        //if(activeP < 0) activeP = MaxChildren-1;
        //if(activeP > MaxChildren-1) activeP = 0;

        //root.Update(elapsedTime, activeC);
        root.Update(elapsedTime);
    }

    @Override
    public void Render(GraphicsClass graphics) {
        //root.Render(graphics, activeC, false);
        root.Render(graphics);
        graphics.drawText(String.format("%d", root.active), graphics.Font, 16, 0, 0, 8.0f, 8.0f);
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
