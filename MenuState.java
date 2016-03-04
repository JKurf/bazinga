import static org.lwjgl.glfw.GLFW.*;

public class MenuState implements IState {
    private boolean initialized = false;
    MenuItem root = new MenuItem("root");
    int activeP = 0;
    int activeC = 0;
    int MaxChildren = 4;

    int action = 0;

    @Override
    public void Init() {
        if(!initialized) {
            root.Init();

            root.addChild(new MenuItem("Resume"));
            root.addChild(new MenuItem("Settings"));
            root.addChild(new MenuItem("Quit"));
            root.addChild(new MenuItem("Memes"));

            initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.keyPress(GLFW_KEY_W)) {
            activeC--;
        }
        if(InputClass.keyPress(GLFW_KEY_S)) {
            activeC++;
        }

        if(InputClass.keyPress(GLFW_KEY_D)) {
            if(root.current.equals("Quit")) {
                System.out.println("Quit!!!");
                action = 1;
            }
        }

        if(activeC < 0) activeC = MaxChildren-1;
        if(activeC > MaxChildren-1) activeC = 0;
        if(activeP < 0) activeP = MaxChildren-1;
        if(activeP > MaxChildren-1) activeP = 0;

        root.Update(elapsedTime, activeC);
    }

    @Override
    public void Render(GraphicsClass graphics) {
        root.Render(graphics, activeC);
        graphics.drawText(String.format("%d:%d", activeP, activeC), graphics.Font, 16, 0, 0, 8.0f, 8.0f);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        if (action == 1) return "quit";

        return null;
    }
}
