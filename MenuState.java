import static org.lwjgl.glfw.GLFW.*;

public class MenuState implements IState {
    private boolean initialized = false;
    MenuItem root = new MenuItem("root");
    int activeP = 0;
    int activeC = 0;
    int MaxChildren = 4;

    String action = null;

    @Override
    public void Init() {
        if(!initialized) {
            root.Init();

            MenuItem resume = new MenuItem("resume");
            MenuItem settings = new MenuItem("settings");
            settings.Init();
            settings.addChild(new MenuItem("game"));
            settings.addChild(new MenuItem("audio"));
            settings.addChild(new MenuItem("video"));

            MenuItem memes = new MenuItem("memes");
            MenuItem quit = new MenuItem("quit");

            root.addChild(resume);
            root.addChild(settings);
            root.addChild(memes);
            root.addChild(quit);

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
            if(root.current.equals("quit")) {
                action = "quit";
            }
            else if(root.current.equals("resume")) {
                action = "resume";
            }
        }
        else
            action = null;

        if(activeC < 0) activeC = MaxChildren-1;
        if(activeC > MaxChildren-1) activeC = 0;
        if(activeP < 0) activeP = MaxChildren-1;
        if(activeP > MaxChildren-1) activeP = 0;

        root.Update(elapsedTime, activeC);
    }

    @Override
    public void Render(GraphicsClass graphics) {
        root.Render(graphics, activeC, false);
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
        return action;
    }
}
